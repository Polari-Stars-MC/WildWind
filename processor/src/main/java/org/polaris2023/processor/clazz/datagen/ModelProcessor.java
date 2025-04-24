package org.polaris2023.processor.clazz.datagen;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.KV;
import org.polaris2023.annotation.modelgen.block.*;
import org.polaris2023.annotation.modelgen.item.*;
import org.polaris2023.annotation.modelgen.other.*;
import org.polaris2023.annotation.register.ResourceLocation;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.Override;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ModelProcessor extends ClassProcessor {

    public static final StringBuilder MODEL = new StringBuilder();

    public ModelProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    public static void check() {
        if (MODEL.isEmpty()) {
            MODEL.append("this");
        }
    }

    private final List<Annotation> annotations = new ArrayList<>();

    private <T extends Annotation> T register(T t) {
        annotations.add(t);
        return t;
    }

    public void checkAppend(TypeElement typeElement, VariableElement variableElement, String methodName,Object... appends) {
        check();
        MODEL.append("\n\t\t.")
                .append(methodName)
                .append("(")
                .append(typeElement.getQualifiedName())
                .append(".")
                .append(variableElement.getSimpleName());
        for (Object append : appends) {
            if (append instanceof String) {
                MODEL
                        .append(",")
                        .append("\"")
                        .append(append)
                        .append("\"");
            } else {
                MODEL
                        .append(",")
                        .append(append);
            }
        }
        MODEL.append(")");
    }

    public static String merge(String code, TypeElement typeElement, VariableElement element) {
        return code + "(" + typeElement.getQualifiedName() + "." + element.getSimpleName() + ".get());";
    }
    public static String mergeNext(String code, TypeElement typeElement, VariableElement element) {
        return code + "(" + typeElement.getQualifiedName() + "." + element.getSimpleName() + ".get())";
    }

    public void addCodeSpawnEggItem(TypeElement typeElement, VariableElement variableElement) {
        SpawnEggItem spawnEggItem = variableElement.getAnnotation(SpawnEggItem.class);
        if (spawnEggItem == null) return;
        InitProcessor.modelGen(context, merge("itemModelProvider.spawnEggItem", typeElement, variableElement));
    }

    public final List<BiConsumer<TypeElement, VariableElement>> codeAdderList = List.of(
            this::addCodeSpawnEggItem,
            this::addCodeBasicBlock,
            this::addCodeCubeBottomTop,
            this::addCodeParentItem,
            this::addCodeCubeAllFor,
            this::addCodeCross,
            this::addCodeCarpet,
            this::addCodeBasicItem,
            this::addCodeCubeColumn
    );

    private void addCodeBasicItem(TypeElement typeElement, VariableElement variableElement) {
        BasicItem basicItem = variableElement.getAnnotation(BasicItem.class);
        if (basicItem != null && basicItem.used()) {
            StringBuilder sb =
                    new StringBuilder(mergeNext("basicItem", typeElement, variableElement));
            Display[] display = basicItem.value().display();
            if (display.length > 0) {
                sb.append(".transforms()");
                for (Display d : display) {
                    sb.append(".transform(net.minecraft.client.renderer.block.model.ItemTransform.")
                            .append(d.name().name()).append(")");
                    Vec3f rotation = d.rotation();
                    if (rotation.x() == 0.0F && rotation.y() == 0.0F && rotation.z() == 0.0F) {
                        sb.append(".rotation(")
                                .append(rotation.x())
                                .append(",")
                                .append(rotation.y())
                                .append(",")
                                .append(rotation.z())
                                .append(")");
                    }
                    Vec3f translation = d.translation();
                    if (translation.x() == 0.0F && translation.y() == 0.0F && translation.z() == 0.0F) {
                        sb.append(".translation(")
                                .append(translation.x())
                                .append(",")
                                .append(translation.y())
                                .append(",")
                                .append(translation.z())
                                .append(")");
                    }
                    Vec3f rightRotation = d.rightRotation();
                    if (rightRotation.x() == 0.0F && rightRotation.y() == 0.0F && rightRotation.z() == 0.0F) {
                        sb.append(".rightRotation(")
                                .append(rightRotation.x())
                                .append(",")
                                .append(rightRotation.y())
                                .append(",")
                                .append(rightRotation.z())
                                .append(")");
                    }
                    Vec3f scale = d.scale();
                    if (scale.x() == 0.0F && scale.y() == 0.0F && scale.z() == 0.0F) {
                        sb.append(".scale(")
                                .append(scale.x())
                                .append(",")
                                .append(scale.y())
                                .append(",")
                                .append(scale.z())
                                .append(")");
                    }
                }
                sb.append(".end()");
            }
            var overrides = basicItem.value().overrides();
            for (var override : overrides) {
                sb.append(".override()");
                for (Predicate predicate : override.predicate()) {
                    sb.append(".predicate(ResourceLocation.parse(\"")
                            .append(predicate.name())
                            .append("\"), ")
                            .append(predicate.value())
                            .append(")");
                }
                sb.append(".model(itemModelProvider.getExistingFile(ResourceLocation.parse(")
                        .append(override.model())
                        .append("\")))");
                sb.append(".end()");
            }
            sb.append(";");
            InitProcessor.modelGen(context, sb.toString());
        }
    }


    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        BasicBlockItem basicBlockItem = register(variableElement.getAnnotation(BasicBlockItem.class));
        BasicBlockLocatedItem basicBlockLocatedItem = register(variableElement.getAnnotation(BasicBlockLocatedItem.class));
        CubeColumn cubeColumn = register(variableElement.getAnnotation(CubeColumn.class));
        Stairs stairs = register(variableElement.getAnnotation(Stairs.class));
        Slab slab = register(variableElement.getAnnotation(Slab.class));
        Wall wall = register(variableElement.getAnnotation(Wall.class));
        Log log = register(variableElement.getAnnotation(Log.class));
        Wood wood = register(variableElement.getAnnotation(Wood.class));
        Button button = register(variableElement.getAnnotation(Button.class));
        Fence fence = register(variableElement.getAnnotation(Fence.class));
        FenceGate fenceGate = register(variableElement.getAnnotation(FenceGate.class));
        PressurePlate pressurePlate = register(variableElement.getAnnotation(PressurePlate.class));
        AllWood allWood = register(variableElement.getAnnotation(AllWood.class));
        AllSign allSign = register(variableElement.getAnnotation(AllSign.class));
        AllDoor allDoor = register(variableElement.getAnnotation(AllDoor.class));
        AllBrick allBrick = register(variableElement.getAnnotation(AllBrick.class));
        //item model gen
        codeAdderList.forEach(run -> run.accept(typeElement, variableElement));

        if (basicBlockItem != null) {
            blockItem(variableElement, typeElement, basicBlockItem);
        }
        else if (basicBlockLocatedItem != null) {
            checkAppend(typeElement, variableElement,"basicBlockLocatedItem");
        }

//        if (basicItem != null && basicItem.used()) {
//            basicSet(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), basicItem, basicItem.value(), true, "");
//        } else if (typeBasicItem != null && typeBasicItem.used() && variableElement.getModifiers().contains(Modifier.STATIC)) {
//            basicSet(typeElement.getQualifiedName() + "." + variableElement.getSimpleName(), typeBasicItem, typeBasicItem.value(), true, "");
//        }
        //block model gen

        else if (cubeColumn != null) {
            checkAppend(typeElement, variableElement, "cubeColumn", cubeColumn.end(), cubeColumn.side(), cubeColumn.item(), cubeColumn.horizontal(), cubeColumn.suffix());
        }
        else if (log != null) {
            checkAppend(typeElement, variableElement, "logBlock", log.item());
        }
        else if (wood != null) {
            checkAppend(typeElement, variableElement, "woodBlock", wood.item());
        }
        else if (button != null) {
            checkAppend(typeElement, variableElement, "buttonBlock", button.texture());
        }
        else if(fence != null) {
            checkAppend(typeElement, variableElement, "fenceBlock", fence.texture(), fence.item());
        }
        else if (fenceGate != null) {
            checkAppend(typeElement, variableElement, "fenceGateBlock", fenceGate.texture(), fenceGate.item());
        }
        else if (stairs != null) {
            String all = stairs.all();
            checkAppend(typeElement, variableElement, "stairsBlock",
                    all.isEmpty() ? stairs.bottom() : all,
                    all.isEmpty() ? stairs.side() : all,
                    all.isEmpty() ? stairs.top() : all,
                    stairs.item(),
                    stairs.type()
            );
        }
        else if (slab != null) {
            String all = slab.all();
            checkAppend(typeElement, variableElement, "slabBlock",
                    all.isEmpty() ? slab.bottom() : all,
                    all.isEmpty() ? slab.side() : all,
                    all.isEmpty() ? slab.top() : all,
                    slab.item(),
                    slab.type()
            );
        }
        else if(wall != null) {
            checkAppend(typeElement, variableElement, "wallBlock", wall.wall(), wall.item(), wall.bricks());
        }
        else if (pressurePlate != null) {
            checkAppend(typeElement, variableElement, "pressurePlateBlock", pressurePlate.texture(), pressurePlate.item());
        }
        else if (allSign != null) {
            checkAppend(typeElement, variableElement, "allSignBlock");
        }
        else if (allDoor != null) {
            checkAppend(typeElement, variableElement, "allDoorBlock");
        }
        else if (allWood != null) {
            checkAppend(typeElement, variableElement, "allWoodBlock");
        }
        else if(allBrick != null) {
            checkAppend(typeElement, variableElement, "allBrickBlock");
        }
    }

    private String location(ResourceLocation location) {
        return "ResourceLocation.fromNamespaceAndPath(\"" + location.namespace() + "\", \"" + location.path() + "\")";
    }

    private void addCodeCubeBottomTop(TypeElement typeElement, VariableElement variableElement) {
        CubeBottomTop cubeBottomTop = variableElement.getAnnotation(CubeBottomTop.class);
        if (cubeBottomTop != null) {
//            StringBuilder sb = new StringBuilder();
//            sb
//                    .append("cubeBottomTop(")
//                    .append(typeElement.getQualifiedName())
//                    .append(".")
//                    .append(variableElement.getSimpleName())
//                    .append(")");
        }
    }


    private void addCodeCubeAllFor(TypeElement typeElement, VariableElement variableElement) {
        CubeAllFor cubeAllFor = variableElement.getAnnotation(CubeAllFor.class);
        if (cubeAllFor != null) {
            CubeAll cube = cubeAllFor.cube();
            InitProcessor.modelGen(context, cubeAllModel(typeElement, variableElement, cube));
            String def = cubeAllFor.def();
            if (def.isEmpty()) def = cubeAllFor.cube().all();
            for (int i = cubeAllFor.min(); i <= cubeAllFor.max(); i+=cubeAllFor.step()) {
                InitProcessor.modelGen(context, "cubeAllModel(%s.%s, \"%s\", \"%s\", %d);".formatted(
                        typeElement.getQualifiedName(),
                        variableElement.getSimpleName(),
                        cube.render_type(),
                        def,
                        i
                ));
            }
        }
    }

    private static String cubeAllModel(TypeElement typeElement, VariableElement variableElement, CubeAll cube) {
        return "cubeAllModel(%s.%s, \"%s\", \"%s\");"
                .formatted(
                        typeElement.getQualifiedName(),
                        variableElement.getSimpleName(),
                        cube != null ? cube.render_type() : "",
                        cube != null ? cube.all() : ""
                );
    }

    private static String cubeAllModel(String path, CubeAll cube) {
        return "cubeAllModel(\"%s\", \"%s\", \"%s\");"
                .formatted(
                        path,
                        cube != null ? cube.render_type() : "",
                        cube != null ? cube.all() : ""
                );
    }

    private void wall(TypeElement typeElement, VariableElement variableElement) {
        Wall annotation = variableElement.getAnnotation(Wall.class);
    }

    private void addCodeCross(TypeElement typeElement, VariableElement variableElement) {
        Cross cross = variableElement.getAnnotation(Cross.class);
        if (cross != null) {
            String sb = "cross(%s.%s, %s, \"%s\", \"%s\");".formatted(
                    typeElement.getQualifiedName(),
                    variableElement.getSimpleName(),
                    cross.item(),
                    cross.render_type(),
                    cross.cross()
            );
            InitProcessor.modelGen(context, sb);
        }
    }

    private void addCodeCarpet(TypeElement typeElement, VariableElement variableElement) {
        Carpet carpet = variableElement.getAnnotation(Carpet.class);
        if (carpet != null) {
            String sb = "carpet(%s.%s, %s, \"%s\", \"%s\");".formatted(
                    typeElement.getQualifiedName(),
                    variableElement.getSimpleName(),
                    carpet.item(),
                    carpet.render_type(),
                    carpet.carpet()
            );
            InitProcessor.modelGen(context, sb);
        }
    }

    private void addCodeBasicBlock(TypeElement typeElement, VariableElement variableElement) {
        BasicBlock basicBlock = variableElement.getAnnotation(BasicBlock.class);
        CubeAll cubeAll = variableElement.getAnnotation(CubeAll.class);

        if ( cubeAll != null || basicBlock != null) {
            String sb = "cubeAll(%s.%s, %s, \"%s\", \"%s\");"
                    .formatted(
                            typeElement.getQualifiedName(),
                            variableElement.getSimpleName(),
                            cubeAll != null ? cubeAll.item() : basicBlock.item(),
                            cubeAll != null ? cubeAll.render_type() : basicBlock.render_type(),
                            cubeAll != null ? cubeAll.all() : ""
                    );
            InitProcessor.modelGen(context, sb);
        }
    }

    private void addCodeParentItem(TypeElement typeElement, VariableElement variableElement) {
        ParentItem parentItem = variableElement.getAnnotation(ParentItem.class);
        if (parentItem != null) {
            StringBuilder sb = new StringBuilder();
            sb
                    .append("itemModelProvider.withExistingParent(net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(")
                    .append(typeElement.getQualifiedName())
                    .append(".")
                    .append(variableElement.getSimpleName())
                    .append(".get()).toString()")
                    .append(", \"")
                    .append(parentItem.parent())
                    .append("\")");
            for (KV texture : parentItem.textures()) {
                sb.append(".texture(\"").append(texture.key()).append("\", \"").append(texture.value()).append("\")");
            }
            sb.append(";");
            InitProcessor.modelGen(context, sb.toString());

        }
    }

    private void addCodeCubeColumn(TypeElement typeElement, VariableElement variableElement) {
        CubeColumn cubeColumn = variableElement.getAnnotation(CubeColumn.class);
        if (cubeColumn != null) {
            String template = "cubeColumn(%s.%s, \"%s\", \"%s\");";
            String code = template.formatted(
                    typeElement.getQualifiedName(),
                    variableElement.getSimpleName(),
                    cubeColumn.side(),
                    cubeColumn.end()
            );
            InitProcessor.modelGen(context, code);
        }
    }

    private void blockItem(VariableElement variableElement, TypeElement typeElement, BasicBlockItem basicBlockItem) {
        StringBuilder sb = new StringBuilder();

        sb
                .append("itemModelProvider.simpleBlockItem(net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(")
                .append(typeElement.getQualifiedName())
                .append(".")
                .append(variableElement.getSimpleName())
                .append(".get())");

        if (!basicBlockItem.suffix().isEmpty()) {
            sb
                    .append(".withSuffix(\"")
                    .append(basicBlockItem.suffix())
                    .append("\")");
        }
        if (!basicBlockItem.prefix().isEmpty()) {
            sb
                    .append(".withPrefix(\"")
                    .append(basicBlockItem.prefix())
                    .append("\")");
        }
        sb.append(");");
        InitProcessor.modelGen(context, sb.toString());
    }

    private static void basicSet(String name, BasicItem basicItem, Addition addition, boolean first, String prefix) {
        check();
        MODEL.append("\n\t\t")
                .append(".basicItem(")
                .append(name);
        if (addition.display().length > 0) {
            MODEL.append(", Map.of(");
            // Map.of (name, Map.of("scale", List.of(scale.x, scale.y, scale.z)))
            for (int i = 0; i < addition.display().length; i++) {
                if (i != 0) {
                    MODEL.append(", ");
                }
                Display display = addition.display()[i];
                Vec3f scale = display.scale();

                Vec3f rotation = display.rotation();
                Vec3f translation = display.translation();
                MODEL
                        .append("\"")
                        .append(display.name())
                        .append("\", Map.of(\"scale\", List.of(")
                        .append(scale.x()).append(",")
                        .append(scale.y()).append(",")
                        .append(scale.z()).append(")")
                        .append(", \"rotation\", List.of(")
                        .append(rotation.x()).append(",")
                        .append(rotation.y()).append(",")
                        .append(rotation.z()).append(")")
                        .append(", \"translation\", List.of(")
                        .append(translation.x()).append(",")
                        .append(translation.y()).append(",")
                        .append(translation.z()).append("))")
                ;
            }

            MODEL.append(")");
        }
        if (addition.overrides().length > 0) {
            MODEL.append(", List.of(");
            for (int i = 0; i < addition.overrides().length; i++) {
                var override = addition.overrides()[i];
                Predicate[] predicates = override.predicate();
                String model = override.model();
                if(i != 0) {
                    MODEL.append(", ");
                }
                MODEL.append("Map.of(");
                if (predicates.length > 0) {
                    MODEL.append("\"predicate\", Map.of(");
                    for (int i1 = 0; i1 < predicates.length; i1++) {
                        if(i1 != 0) {
                            MODEL.append(", ");
                        }
                        Predicate predicate = predicates[i1];
                        MODEL.append("\"")
                                .append(predicate.name())
                                .append("\",")
                                .append(predicate.value())
                        ;
                    }
                    MODEL
                            .append(")")
                            .append(", \"model\", ")
                            .append("\"")
                            .append(model)
                            .append("\"");
                }
                MODEL.append(")");

            }
            MODEL.append(")");

        }
        if (!prefix.isEmpty()) {
            MODEL
                    .append(", \"")
                    .append(prefix)
                    .append("\"");
        }
        MODEL.append(")");

        if (first) {
            for (KeyAddition keyAddition : basicItem.more()) {
                basicSet(name, basicItem, keyAddition.value(), false, keyAddition.key());
            }
        }
    }
}

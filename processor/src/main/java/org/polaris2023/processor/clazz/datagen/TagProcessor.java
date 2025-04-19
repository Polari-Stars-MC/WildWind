package org.polaris2023.processor.clazz.datagen;

import com.sun.source.tree.MethodTree;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.util.Context;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.MixinDefine;
import org.polaris2023.annotation.tag.CTag;
import org.polaris2023.annotation.tag.Tag;
import org.polaris2023.annotation.tag.VanillaTag;
import org.polaris2023.annotation.tag.WildWindTag;
import org.polaris2023.processor.InitProcessor;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.*;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/14 18:05:00}
 */
@SuppressWarnings("SwitchStatementWithTooFewBranches")
public class TagProcessor extends ClassProcessor {
    public TagProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    @Override
    public void fieldDef(VariableElement variableElement, TypeElement typeElement) {
        CTag cTag = variableElement.getAnnotation(CTag.class);
        VanillaTag vanillaTag = variableElement.getAnnotation(VanillaTag.class);
        Tag tag = variableElement.getAnnotation(Tag.class);
        WildWindTag wildWindTag = variableElement.getAnnotation(WildWindTag.class);
        if (cTag != null && InitProcessor.TAG_MAP.containsKey(cTag.type())) {
            MethodTree tree = InitProcessor.TAG_MAP.get(cTag.type());

            switch (cTag.type()) {
                default -> {
                    tagGen("c", cTag.names(), typeElement, variableElement, tree, context, cTag.mixin(), cTag.tag(), cTag.type());
                }
            }
        }
        if (vanillaTag != null && InitProcessor.TAG_MAP.containsKey(vanillaTag.type())) {
            MethodTree tree = InitProcessor.TAG_MAP.get(vanillaTag.type());
            switch (vanillaTag.type()) {
                default -> {
                    tagGen("minecraft", vanillaTag.names(), typeElement, variableElement, tree, context, vanillaTag.mixin(), vanillaTag.tag(), vanillaTag.type());
                }
            }
        }
        if (wildWindTag != null && InitProcessor.TAG_MAP.containsKey(wildWindTag.type())) {
            MethodTree tree = InitProcessor.TAG_MAP.get(wildWindTag.type());
            switch (wildWindTag.type()) {
                default -> {
                    tagGen("wild_wind", wildWindTag.names(), typeElement, variableElement, tree, context, wildWindTag.mixin(), wildWindTag.tag(), wildWindTag.type());
                }
            }
        }


        if (tag != null && InitProcessor.TAG_MAP.containsKey(tag.type())) {
            MethodTree tree = InitProcessor.TAG_MAP.get(tag.type());
            switch (tag.type()) {
                default -> {
                    Map<String, List<String>> names = new HashMap<>();
                    names.put("minecraft", new ArrayList<>());
                    names.put("c", new ArrayList<>());
                    names.put("wild_wind", new ArrayList<>());

                    for (var np : tag.names()) {
                        if (!np.contains(":")) {
                            names.get("minecraft").add(np);
                        } else {
                            String[] split = np.split(":");
                            if (split[0].equals("c")) {
                                names.get("c").add(split[1]);
                            } else {
                                if (!names.containsKey(split[0])) {
                                    names.put(split[0], new ArrayList<>());
                                }
                                names.get(split[0]).add(split[1]);
                            }
                        }
                    }
                    for (Map.Entry<String, List<String>> entry : names.entrySet()) {
                        tagGen(entry.getKey(), entry.getValue().toArray(new String[0]), typeElement, variableElement, tree, context, tag.mixin(), tag.tag(), tag.type());
                    }
                }
            }
        }
    }

    public static void tagGen(String modid,
                              String[] tagNames,
                              TypeElement typeElement,
                              VariableElement variableElement,
                              MethodTree tree,
                              Context context,
                              boolean mixin, boolean tag, TagType type) {
        MixinDefine mixinDefine = typeElement.getAnnotation(MixinDefine.class);
        for (String name : tagNames) {
            StringBuilder sb = new StringBuilder();
            sb
                    .append("tag(net.minecraft.tags.%sTags.create(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(\"".formatted(type.name()))
                    .append(modid)
                    .append("\",\"")
                    .append(name)
                    .append("\")))");

            if (tag) {
                sb.append(".addTag(");
            } else {
                sb.append(".add(");
            }
            if(!mixin) {
                sb
                        .append(typeElement.getQualifiedName())
                        .append(".")
                        .append(variableElement.getSimpleName())
                        .append(".get()");
            } else {
                sb
                        .append(mixinDefine.value())
                        .append(".")
                        .append(variableElement.getSimpleName());
            }
            sb.append(");");
            InitProcessor.gen(tree, context, sb.toString());
        }

    }
}

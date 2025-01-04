<script language="JavaScript">
function UrlSearch() {
    let name,value;
    let str=location.href;
    let num = str.indexOf("?");
    str=str.substring(num+1);
    const arr = str.split("&");
    for(let i=0;i < arr.length;i++) {
        num= arr[i].indexOf("=");
        if (num>0) {
            name=arr[i].substring(0, num);
            value=arr[i].substring(num+1);
            this[name]=value;
        }
    }
    let Request = new UrlSearch();
    alert(Request.id)

}
</script>

# WildWind
A Minecraft Mod for Vanilla Expandability

## Processor tutorial


```java
@AutoConfig(modid = WildWindMod.MOD_ID)
@Push("wild-wind-common")
@Note({
        "Wild wind common ->",
        "\tfirefly age?"
})
public class TutorialConfig {
    @org.polaris2023.annotation.config.Note("firefly age")
    @DefineIntRange(defaultValue = 24000, min = 2000, max = 24000)
    public static int FIREFLY_AGE;
    
    @org.polaris2023.annotation.config.SubConfig
    public static class SubConfig {
        @DefineIntRange(defaultValue = 80, min = 0, max = 65536)
        public static int OVERWORLD_BIOMES_WEIGHT;
        @DefineIntRange(defaultValue = 80, min = 0, max = 65536)
        public static int NETHER_BIOMES_WEIGHT;
    }
}
```

### Currently only applies to
    int
    enum
    string
    float
    double
    short
    boolean
    long

```java
@org.polaris2023.annotation.modelgen.item.BasicItem
private static final Object o;
@org.polaris2023.annotation.modelgen.item.SpawnEggItem
private static final Object o2;
```

### Currently only applies to
    DeferredHolder<Item, T>
    Supplier<T>

    you can add to Codes to add some method
    and goto ModelProcessor to add annotations

    you can see BasicItem SpawnEggItem

```java
@org.polaris2023.annotation.language.I18n(
        en_us = "english translate",
        zh_cn = "简中翻译",
        zh_tw = "台中翻譯",
        other = @org.polaris2023.annotation.language.I18n.Other(
                value = "key",
                translate = "translate"
        )
)
public static final Object test;
```
#### Currently only applies to:
    String
    SoundEvent
    DeferredHolder
    Supplier
    Item
    Block
    EntityType
    TranslatableContents
    MobEffect
    CreativeModeTab
    ItemStack
    
    you can add to Codes method add

# Developer-related
At the beginning of the project:

-> generate package-info by build the project

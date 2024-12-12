# WildWind
A Minecraft Mod for Vanilla Expandability


## Processor tutorial

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
    STRING
    DeferredHolder
    Supplier
    Item
    Block
    EntityType
    TranslatableContents
    MobEffect
    CreativeModeTab
    ItemStack
    
    you can add to MethodTypes LANGUAGE_ADD CODES

# Developer-related
At the beginning of the project:

-> generate package-info by build the project

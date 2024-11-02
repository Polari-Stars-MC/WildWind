module wild.wind.annotation.processor {
    requires jdk.compiler;
    requires jdk.unsupported;
    requires com.google.auto.service;
    requires com.google.common;
    requires com.squareup.javapoet;
    requires wild.wind.annotation.main;
    requires static lombok;
    exports org.polaris2023.processor;
    exports org.polaris2023.plugin;
    exports org.polaris2023.ext;
    exports org.polaris2023;
}
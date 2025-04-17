package org.polaris2023.processor.clazz.other;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.polaris2023.annotation.other.Removed;
import org.polaris2023.processor.clazz.ClassProcessor;

import javax.lang.model.element.TypeElement;
import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/16 22:14:18}
 */
public class RemovedProcessor extends ClassProcessor {
    public RemovedProcessor(JavacProcessingEnvironment environment) {
        super(environment);
    }

    public static final List<String> REMOVED = new ArrayList<>();

    @Override
    public void classDef(TypeElement typeElement) {
        Removed removed = typeElement.getAnnotation(Removed.class);
        if (removed != null) {
            REMOVED.add(typeElement.getQualifiedName().toString());
        }
    }

    public static void deleteClassFiles(JavacProcessingEnvironment environment) {
        for (String name : REMOVED) {
            try {
                var filer = environment.getFiler();
                FileObject file = filer.getResource(
                        StandardLocation.CLASS_OUTPUT,
                        "",
                        name.replace('.', '/').trim() + ".class"
                );
                if (file != null) {
                    Path path = Paths.get(file.toUri());
                    forceDelete(path, environment);
                }
            } catch (IOException e) {
                environment.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Failed to remove classes: " + e.getMessage());
            }
        }
    }

    private static void forceDelete(Path path, JavacProcessingEnvironment environment) throws IOException {
        int count = 0;
        IOException exception = null;
        while (count < 10) {
            try {
                Files.deleteIfExists(path);
                environment.getMessager().printMessage(
                        Diagnostic.Kind.NOTE,
                        "Successfully deleted " + path
                );
                return;
            } catch (AccessDeniedException e) {
                exception = e;
                count++;
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new IOException("interrupted while waiting to deletion", ex);
                }
            } catch (IOException e) {
                exception = e;
                break;
            }
        }
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                deleteWindowsFileForcefully(path);
            } else {
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            throw new IOException("Failed to delete file after 10 attempts. Last error: " + exception.getMessage(), e);
        }
    }

    private static void deleteWindowsFileForcefully(Path path) throws IOException {
        try {
            Class<?> windowsFileUtils = Class.forName("sun.nio.fs.WindowsFileSystemProvider");
            Object provider = windowsFileUtils.getDeclaredConstructor().newInstance();
            Method deleteMethod = windowsFileUtils.getDeclaredMethod("delete", Path.class, boolean.class);
            deleteMethod.invoke(provider, path, true);
        } catch (Exception e) {
            throw new IOException("Windows force delete failed",e);
        }
    }
}

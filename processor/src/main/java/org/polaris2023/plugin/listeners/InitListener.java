package org.polaris2023.plugin.listeners;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.util.Context;
import org.polaris2023.plugin.visitor.AutoConfigScanner;


public record InitListener(Context context) implements TaskListener {
    @Override
    public void started(TaskEvent e) {

    }

    @Override
    public void finished(TaskEvent e) {
        if (e.getKind() == TaskEvent.Kind.ENTER) {
            e.getCompilationUnit().accept(new AutoConfigScanner(), context);
        }
    }

//    public static boolean checkAnnotation() {
//
//    }

}

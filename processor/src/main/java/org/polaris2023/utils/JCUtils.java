package org.polaris2023.utils;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class JCUtils {

    public enum Ident {
        ModConfigSpec("net.neoforged.neoforge.common.ModConfigSpec"),
            ModConfigSpecBuilder(ModConfigSpec, "Builder"),
        ;

        private final Ident ident;
        private final List<String> names = new ArrayList<>();

        Ident(String names) {
            ident = null;
            Collections.addAll(this.names, names.split("\\."));

        }

        Ident(Ident ident,String names) {
            this.ident = ident;
            Collections.addAll(this.names, names.split("\\."));

        }

        public JCTree.JCExpression get(TreeMaker maker, Names names) {
            if (ident == null) {
                return makeIdent(maker, names, this.names.toArray(new String[0]));
            } else {
                return get(maker, names, this.names ,ident);
            }
        }

        private JCTree.JCExpression get(TreeMaker maker, Names names,List<String> names_, Ident ident) {
            if (ident == null) {
                return makeIdent(maker, names, this.names.toArray(new String[0]));
            }
            List<String> names__ = new ArrayList<>(ident.names);
            names__.addAll(this.names);
            if (ident.ident == null) {

                return makeIdent(maker, names, names__.toArray(new String[0]));
            }
            return get(maker, names, names__, ident.ident);
        }
    }
    public static JCTree.JCExpression makeIdent(TreeMaker maker, Names names, String name) {
        return makeIdent(maker, names, name.split("\\."));
    }

    public static JCTree.JCExpression makeIdent(TreeMaker maker, Names names, String[] ele) {
        JCTree.JCExpression e = maker.Ident(names.fromString(ele[0]));
        for (int i = 1; i < ele.length; i++) {
            e = maker.Select(e, names.fromString(ele[i]));
        }
        return e;
    }
}

package parser.ast;

import visitor.Visitor;

public interface AST {
    static String toString(AST thisExp, AST left, AST right) {
        return thisExp.getClass().getSimpleName() + "(" + left.toString() + "," + right.toString() + ")";
    }

    static String toString(AST thisExp, Object subcomp) {
        return thisExp.getClass().getSimpleName() + "(" + subcomp.toString() + ")";
    }

    <T> T accept(Visitor<T> v);
}

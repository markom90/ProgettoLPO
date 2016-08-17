package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 04/08/16.
 */
public interface AST {
    public static String toString(AST thisExp, AST left, AST right) {
        return thisExp.getClass().getSimpleName() + "(" + left.toString() + "," + right.toString() + ")";
    }

    public static String toString(AST thisExp, Object subcomp) {
        return thisExp.getClass().getSimpleName() + "(" + subcomp.toString() + ")";
    }

    <T> T accept(Visitor<T> v);
}

package parser.ast;

/**
 * Created by marco on 10/03/16.
 */
public interface Ident extends Exp {
    String getName();
    @Override
    String toString();

}

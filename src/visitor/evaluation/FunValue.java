package visitor.evaluation;

import parser.ast.Ident;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marco on 16/08/16.
 */
public class FunValue {
    private FunClos funClos;
    private Map<Ident, FunValue> env;

    public  FunValue(FunClos funClos){this.funClos = funClos;}

    public FunClos getFunClos() {
        return funClos;
    }

    public void setEnv(Map<Ident, FunValue> env) {
        this.env = env;
    }

    public Map<Ident, FunValue> getEnv() {
        return env;
    }
}

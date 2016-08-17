package visitor.evaluation;

import parser.ast.Exp;
import parser.ast.Ident;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 16/08/16.
 */
public class FunClos {
    private List<Ident> params;
    private Exp body;
    public FunClos(){params = new ArrayList<>();}


    public List<Ident> getParams() {
        return params;
    }

    public void addParam(Ident param){
        params.add(param);
    }

    public Exp getBody() {
        return body;
    }

    public void setBody(Exp body) {
        this.body = body;
    }
}

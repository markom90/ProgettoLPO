package parser.ast;

import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 11/03/16.
 */
public class ParamsList implements Params {

    private List<Param> params;

    public ParamsList(){ params = new ArrayList<Param>();}

    public void add(Param param) {
        params.add(param);
    }

    @Override
    public List<Param> getParams() {
        return params;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitParamList(params);
    }

}

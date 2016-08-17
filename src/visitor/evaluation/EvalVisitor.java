package visitor.evaluation;

import environments.Environment;
import environments.EnvironmentClass;
import parser.ast.*;
import visitor.Visitor;

import java.util.*;

public class EvalVisitor implements Visitor<Value> {
	private final Environment<Value> DVEnv = new EnvironmentClass<>();
    private Map<Ident, FunValue> DFEnv = new HashMap<>();

	@Override
	public Value visitAdd(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() + right.accept(this).asInt());
	}

	@Override
	public Value visitConcat(Exp left, Exp right) {
		return new StringValue(left.accept(this).asString() + right.accept(this).asString());
	}

	@Override
	public Value visitDecList(List<Dec> decs) {
		for (Dec dec : decs)
			dec.accept(this);
		return null;
	}

	@Override
	public Value visitDiv(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() / right.accept(this).asInt());
	}

	@Override
	public Value visitEq(Exp left, Exp right) {
		return new BoolValue(left.accept(this).equals(right.accept(this)));
	}

	@Override
	public Value visitGeq(Exp left, Exp right) {
		return new BoolValue(left.accept(this).compareTo(right.accept(this)) >= 0);
	}

	@Override
	public Value visitGth(Exp left, Exp right) {
		return new BoolValue(left.accept(this).compareTo(right.accept(this)) > 0);
	}

	@Override
	public Value visitSimpleIdent(Ident id) {
		return DVEnv.lookup(id);
	}

	@Override
	public Value visitIntLiteral(int value) {
		return new IntValue(value);
	}

	@Override
	public Value visitLeq(Exp left, Exp right) {
		return new BoolValue(left.accept(this).compareTo(right.accept(this)) <= 0);
	}

	@Override
	public Value visitLetIn(Decs decs, Exp exp) {
		DVEnv.enterScope();
		decs.accept(this);
		Value val = exp.accept(this);
		DVEnv.exitScope();
		return val;
	}

	@Override
	public Value visitLth(Exp left, Exp right) {
		return new BoolValue(left.accept(this).compareTo(right.accept(this)) < 0);
	}

	@Override
	public Value visitMul(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() * right.accept(this).asInt());
	}

	@Override
	public Value visitSign(Exp exp) {
		return new IntValue(-exp.accept(this).asInt());
	}

	@Override
	public Value visitSimpleDec(Ident id, Exp exp) {
		DVEnv.update(id, exp.accept(this));
		return null;
	}

	@Override
	public Value visitStringLiteral(String value) {
		return new StringValue(value);
	}

	@Override
	public Value visitSub(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() - right.accept(this).asInt());
	}

	@Override
	public Value visitType(PrimType primType) {
		return null;
	}

	@Override
	public Value visitAnd(Exp left, Exp right) {
        boolean leftValue = left.accept(this).asBool();
        if(!leftValue) return new BoolValue(leftValue);
        else return new BoolValue(leftValue && right.accept(this).asBool());	}

	@Override
	public Value visitBoolLit(Boolean value) {
		return new BoolValue(value);
	}

	@Override
	public Value visitEmptyList(Type type) {
		return new ListValue();
	}

	@Override
	public Value visitIfThenElse(Exp ifExp, Exp thenExp, Exp elseExp) {
		if(ifExp.accept(this).asBool()) return thenExp.accept(this);
		else return elseExp.accept(this);
	}

	@Override
	public Value visitListConstructor(Exp left, Exp right) {
        List<Value> list = new LinkedList<>(right.accept(this).asList());
        list.add(0,left.accept(this));
        return new ListValue(list);
	}

	@Override
	public Value visitListLength(Exp exp) {
        return new IntValue(exp.accept(this).asList().size());
    }
//TODO

	@Override
	public Value visitListType(Type type) {
		return null;
	}

	@Override
	public Value visitMod(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() % right.accept(this).asInt());
	}

	@Override
	public Value visitNeq(Exp left, Exp right) {
		return new BoolValue(!(left.accept(this).equals(right.accept(this))));	}

	@Override
	public Value visitNot(Exp exp) {
		return new BoolValue(!exp.accept(this).asBool());
	}

	@Override
	public Value visitOr(Exp left, Exp right) {
        boolean leftValue = left.accept(this).asBool();
        if(leftValue) return new BoolValue(leftValue);
        else return new BoolValue(leftValue || right.accept(this).asBool());
	}
//TODO

	@Override
	public Value visitParamList(List<Param> params) {
		return null;
	}

	@Override
	public Value visitProg(FunDecs fundecs, Exp exp) {
		fundecs.accept(this);
        return exp.accept(this);
	}

	@Override
	public Value visitSimpleFunDec(Type type, Ident ident, List<Param> params, Exp exp) {
        FunClos funclos = new FunClos();
        funclos.setBody(exp);
        for(Param param : params)
            funclos.addParam(param.getId());
        FunValue funValue = new FunValue(funclos);
        DFEnv.put(ident,funValue);
        funValue.setEnv(new HashMap<>(DFEnv));
        return null;
    }


	@Override
	public Value visitListConcat(Exp left, Exp right) {
		List<Value> list = left.accept(this).asList();
        Iterator<Value> iterator = right.accept(this).asList().iterator();
        while(iterator.hasNext()) list.add(iterator.next());
        return new ListValue(list);
    }


	@Override
	public Value visitFunDecList(List<FunDec> funDecs) {
		for(FunDec funDec : funDecs)
            funDec.accept(this);
        return null;
    }


	@Override
	public Value visitExtractHead(Exp exp) {
		List<Value> list = new LinkedList<>(exp.accept(this).asList());
        if(!list.isEmpty()) {
			return list.get(0);
		}
        throw new EvalException("Undefined operation on empty list: ExtractHead");
	}


	@Override
	public Value visitExtractTail(Exp exp) {
        List<Value> list = new LinkedList<>(exp.accept(this).asList());
        if(!list.isEmpty()) {
            list.remove(0);
            return new ListValue(list);
        }
        throw new EvalException("Undefined operation on empty list: ExtractTail");
	}

	@Override
	public Value visitFunctionCall(SimpleIdent name, List<Exp> args) {
        FunValue funValue = DFEnv.get(name);
        List<Value> values = new LinkedList<>();
        for (Exp exp : args)
            values.add(exp.accept(this));
        DVEnv.enterScope();
        for (Ident arg : funValue.getFunClos().getParams())
        DVEnv.update(arg,values.remove(0));

        Map<Ident, FunValue> aux = DFEnv;
        DFEnv = funValue.getEnv();
        Value result = funValue.getFunClos().getBody().accept(this);
        DVEnv.exitScope();
        DFEnv = aux;
        return result;
    }
//TODO

	@Override
	public Value visitArgList(List<Exp> args) {
		return null;
	}
//TODO

	@Override
	public Value visitParam(Type type, Ident ident) {
		return null;
	}

}

package visitor.typechecking;


import environments.Environment;
import environments.EnvironmentClass;
import parser.ast.*;
import parser.ast.PrimType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import visitor.Visitor;

import java.util.Iterator;
import java.util.List;

import static parser.ast.PrimType.BOOL;
import static parser.ast.PrimType.INT;
import static parser.ast.PrimType.STRING;

public class TypecheckVisitor implements Visitor<Type> {

    private final Environment<Type> VEnv = new EnvironmentClass<>();

    private final Environment<FunType> FEnv = new EnvironmentClass<>();

	private void check(Type found, Type expected) {
		if (!found.equals(expected))
			throw new TypecheckerException("Found type " + found + ", expected " + expected);
	}

	private void checkListType(Type type) {
		if (!(type instanceof ListType))
			throw new TypecheckerException("Found type " + type + ", expected ListType");
	}

    private Type visitRelOp(Exp left, Exp right) {
        check(left.accept(this),INT);
        check(right.accept(this),INT);
        return BOOL;
    }

    private Type visitMathOp(Exp left, Exp right) {
        check(left.accept(this),INT);
        check(right.accept(this),INT);
        return INT;
    }

    private Type visitBoolOp(Exp left, Exp right) {
        check(left.accept(this),BOOL);
        check(right.accept(this),BOOL);
        return BOOL;
    }

    private Type visitEqOp(Exp left, Exp right) {
        check(right.accept(this),left.accept(this));
        return BOOL;
    }

    @Override
	public Type visitAdd(Exp left, Exp right) {
		return visitMathOp(left, right);
	}

    @Override
    public Type visitSub(Exp left, Exp right) {
        return visitMathOp(left, right);
    }

    @Override
    public Type visitMul(Exp left, Exp right) {
        return visitMathOp(left, right);
    }

    @Override
    public Type visitSign(Exp exp) {
        check(exp.accept(this),INT);
        return INT;
    }

    @Override
    public Type visitDiv(Exp left, Exp right) {
        return visitMathOp(left, right);
    }

    @Override
    public Type visitMod(Exp left, Exp right) {
        return visitMathOp(left,right);
    }

    @Override
    public Type visitEq(Exp left, Exp right) {
        return visitEqOp(left,right);
    }

    @Override
    public Type visitNeq(Exp left, Exp right) {
        return visitEqOp(left,right);
    }

    @Override
    public Type visitGeq(Exp left, Exp right) {
        return visitRelOp(left,right);
    }

    @Override
    public Type visitGth(Exp left, Exp right) {
        return visitRelOp(left,right);
    }

    @Override
    public Type visitLth(Exp left, Exp right) {
        return visitRelOp(left,right);
    }

    @Override
    public Type visitLeq(Exp left, Exp right) {
        return visitRelOp(left,right);
    }

    @Override
    public Type visitAnd(Exp left, Exp right) {
        return visitBoolOp(left,right);
    }

    @Override
    public Type visitOr(Exp left, Exp right) {
        return visitBoolOp(left,right);
    }

    @Override
    public Type visitNot(Exp exp) {
        check(exp.accept(this),BOOL);
        return BOOL;
    }

    @Override
	public Type visitConcat(Exp left, Exp right) {
		check(left.accept(this),STRING);
        check(right.accept(this),STRING);
        return STRING;
    }

	@Override
	public Type visitDecList(List<Dec> decs) {
        for (Dec dec : decs)
            dec.accept(this);
        return null;
	}

	@Override
	public Type visitSimpleIdent(Ident id) {
		return VEnv.lookup(id);
	}

	@Override
	public Type visitIntLiteral(int value) {
		return INT;
	}

    @Override
	public Type visitLetIn(Decs decs, Exp exp) {
        VEnv.enterScope();
        decs.accept(this);
        Type type = exp.accept(this);
        VEnv.exitScope();
        return type;
	}

    @Override
	public Type visitSimpleDec(Ident id, Exp exp) {
        VEnv.update(id, exp.accept(this));
        return null;	}

	@Override
	public Type visitStringLiteral(String value) {
		return STRING;
	}

    @Override
	public Type visitType(PrimType primType) {
		throw new NotImplementedException();
	}

    @Override
	public Type visitBoolLit(Boolean value) {
		return BOOL;
	}

    @Override
	public Type visitEmptyList(Type type) {
		return new ListType(type);

	}

	@Override
	public Type visitIfThenElse(Exp ifExp, Exp thenExp, Exp elseExp) {
		check(ifExp.accept(this),BOOL);
        Type type = thenExp.accept(this);
        check(elseExp.accept(this),type);
        return type;
    }

	@Override
	public Type visitListConstructor(Exp left, Exp right) {
        Type listType = right.accept(this);
        checkListType(listType);
        check(left.accept(this),((ListType)listType).getType());
        return listType;

	}

	@Override
	public Type visitListLength(Exp exp) {
        checkListType(exp.accept(this));
        return INT;
    }

	@Override
	public Type visitListType(Type type) {
        throw new NotImplementedException();

    }


    @Override
	public Type visitParamList(List<Param> params) {
        throw new NotImplementedException();

    }

	@Override
	public Type visitProg(FunDecs fundecs, Exp exp) {
		FEnv.enterScope();
        fundecs.accept(this);
        Type t = exp.accept(this);
        FEnv.exitScope();
        return t;
	}

    @Override
	public Type visitSimpleFunDec(Type type, Ident ident, List<Param> params, Exp exp) {
        FunType ft = new FunTypeImpl(type);
        FEnv.update(ident,ft);
        VEnv.enterScope();
        for (Param p : params) {
            ft.addType(p.getType());
            VEnv.update(p.getId(), p.getType());
        }
        check(exp.accept(this),type);
        VEnv.exitScope();
        return null;
    }

    @Override
    public Type visitListConcat(Exp left, Exp right) {
        Type listType = left.accept(this);
        checkListType(listType);
        check(right.accept(this),listType);
        return listType;
    }

    @Override
    public Type visitFunDecList(List<FunDec> funDecs) {
        for(FunDec funDec : funDecs) funDec.accept(this);
        return null;
    }

    @Override
    public Type visitExtractHead(Exp exp) {
        Type type = exp.accept(this);
        checkListType(type);
        return ((ListType)type).getType();
    }

    @Override
    public Type visitExtractTail(Exp exp) {
        Type type = exp.accept(this);
        checkListType(type);
        return type;
    }

    @Override
    public Type visitFunctionCall(SimpleIdent name, List<Exp> args) {
        FunType funType = FEnv.lookup(name);
        Iterator<Exp> iterator = args.iterator();

        for(Type type : funType.getArgsType()){
            if(iterator.hasNext())
                check(iterator.next().accept(this),type);
            else
                throw new TypecheckerException("Number of arguments does not match");
        }
        if(iterator.hasNext())
            throw new TypecheckerException("Number of arguments does not match");
        return funType.getRetType();
    }

    @Override
    public Type visitArgList(List<Exp> args) {
        throw new NotImplementedException();

    }

    @Override
    public Type visitParam(Type type, Ident ident) {
        throw new NotImplementedException();
    }


}

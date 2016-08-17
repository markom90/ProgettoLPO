package visitor;

import parser.ast.*;

import java.util.List;

/**
 * Created by marco on 04/08/16.
 */
public interface Visitor<T> {
    T visitAdd(Exp left, Exp right);

    T visitConcat(Exp left, Exp right);

    T visitDecList(List<Dec> decs);

    T visitDiv(Exp left, Exp right);

    T visitEq(Exp left, Exp right);

    T visitGeq(Exp left, Exp right);

    T visitGth(Exp left, Exp right);

    T visitSimpleIdent(Ident id);

    T visitIntLiteral(int value);

    T visitLeq(Exp left, Exp right);

    T visitLetIn(Decs decs, Exp exp);

    T visitLth(Exp left, Exp right);

    T visitMul(Exp left, Exp right);

    T visitSign(Exp exp);

    T visitSimpleDec(Ident id, Exp exp);

    T visitStringLiteral(String value);

    T visitSub(Exp left, Exp right);

    T visitType(PrimType primType);

    T visitAnd(Exp left, Exp right);

    T visitBoolLit(Boolean value);

    T visitEmptyList(Type type);

    T visitIfThenElse(Exp ifExp, Exp thenExp, Exp elseExp);

    T visitListConstructor(Exp left, Exp right);

    T visitListLength(Exp exp);

    T visitListType(Type type);

    T visitMod(Exp left, Exp right);

    T visitNeq(Exp left, Exp right);

    T visitNot(Exp exp);

    T visitOr(Exp left, Exp right);

    T visitParamList(List<Param> params);

    T visitProg(FunDecs fundecs, Exp exp);

    T visitSimpleFunDec(Type type, Ident ident, List<Param> params, Exp exp);

    T visitListConcat(Exp left, Exp right);

    T visitFunDecList(List<FunDec> funDecs);

    T visitExtractHead(Exp exp);

    T visitExtractTail(Exp exp);

    T visitFunctionCall(SimpleIdent name, List<Exp> args);

    T visitArgList(List<Exp> args);

    T visitParam(Type type, Ident ident);
}

package environments;


import parser.ast.Ident;

public interface Environment<T> {

	void enterScope();

	void exitScope();

	T lookup(Ident id);

	void update(Ident id, T info);

}

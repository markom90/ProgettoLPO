package environments;

import parser.ast.Ident;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EnvironmentClass<T> implements Environment<T> {

	private List<Map<Ident, T>> localEnvs = new LinkedList<>();

	@Override
	public void enterScope() {
		localEnvs.add(0, new HashMap<>());
	}

	@Override
	public void exitScope() {
		localEnvs.remove(0);
	}

	@Override
	public void update(Ident id, T info) {
		localEnvs.get(0).put(id, info);
	}

	@Override
	public T lookup(Ident id) {
		for (Map<Ident, T> locEnv : localEnvs)
			if (locEnv.containsKey(id))
				return locEnv.get(id);
		throw new EnvironmentException("Undeclared identifier " + id.getName()/* + " " + getCallerClass().getName()*/);
	}
}

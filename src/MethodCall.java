import java.util.Iterator;

/**
 * Represents a MethodCall expression
 * 
 * @param objectName The name of the method
 * @param method     The function call of the method
 */
public class MethodCall extends Expression {
	Attribute object;
	FunctionCall method;

	public MethodCall(Attribute objectName, FunctionCall method) {
		this.object = objectName;
		this.method = method;
	}
	
	@Override
	public Object eval(Environment env) {
		MyObject o = (MyObject) object.eval(env);
		MyFunction f = (MyFunction) (o.env.getValue(method.funcName));

		Iterator argsExprIt = method.argExprs.iterator();
		Iterator argNamesIt = f.args.iterator();

		Environment newEnv = o.env.copyEnv();

		while (argsExprIt.hasNext() && argNamesIt.hasNext()) {
			Variable v = (Variable) argNamesIt.next();
			Expression e = (Expression) (argsExprIt.next());
			newEnv.addVariable(v.name, e.eval(env));
		}

		return method.eval(newEnv);
	}
	
	public String toString() {
		return object.toString() + "." + method.funcName + "(" + method.argExprs + ")";
	}
}

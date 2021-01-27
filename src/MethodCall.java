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
		Environment newEnv = env.copyEnv();
		newEnv.combineEnv(o.env);

		return method.eval(newEnv);
	}
	
	public String toString() {
		return object.toString() + "." + method.funcName + "(" + method.argExprs + ")";
	}
}

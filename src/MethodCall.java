/**
 * Represents a MethodCall expression
 * @param objectName The name of the method
 * @param method The function call of the method
 */
public class MethodCall extends Expression {
	Attribute object;
	FunctionCall method;

	public MethodCall(FunctionCall method) {
		this.method = method;
	}
	public MethodCall(Attribute objectName, FunctionCall method) {
		this.object = objectName;
		this.method = method;
	}
	
	@Override
	public Object eval(Environment env) {
		if (object == null) {
			return method.evalMethod(env);
		}
		MyObject o = (MyObject) object.eval(env);
//		MyObject o = (MyObject) env.getValue(object);
		return method.evalMethod(o.env);
	}
	
	public String toString() {
		return object.toString() + "." + method.funcName + "(" + method.argExprs + ")";
	}
}

/**
 * Represents a MethodCall expression
 * @param objectName The name of the method
 * @param method The function call of the method
 */
public class MethodCall extends Expression {
	String objectName;
	FunctionCall method;
	
	public MethodCall(String objectName, FunctionCall method) {
		this.objectName = objectName;
		this.method = method;
	}
	
	@Override
	public Object eval(Environment env) {
		MyObject o = (MyObject) env.getValue(objectName);
		return method.evalMethod(o.env);
	}
	
	public String toString() {
		return objectName + "." + method.funcName + "(" + method.argExprs + ")";
	}
}

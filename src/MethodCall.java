
public class MethodCall extends Expression {
	String objectName;
	FunctionCall method;
	
	public MethodCall(String objectName, FunctionCall method) {
		this.objectName = objectName;
		this.method = method;
	}
	
	public Object eval(Environment env) {
		MyObject o = (MyObject) env.getValue(objectName);
		MyClass c = (MyClass) o.env.getClass(o.className);
		Environment newEnv = o.env.copyEnv();
		MyFunction f = (MyFunction) c.environment.getValue(method.funcName);
		return f.eval(newEnv);
	}
	
	public String toString() {
		return objectName + "." + method.funcName + "(" + method.argExprs + ")";
	}
}

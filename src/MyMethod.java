import java.util.List;

/**
 * Represents a Method
 * @param methodName The name of the method
 * @param body The body of the method
 * @param args The ordered list of the method's parameters
 */
public class MyMethod {
	String methodName;
	Program body;
	List<Variable> args;
	
	public MyMethod(String methodName, Program body, List<Variable> args) {
		this.methodName = methodName;
		this.body = body;
		this.args = args;
	}

	public Object eval(Environment env) {
		Object returnValue = null;
		try {
			returnValue = body.eval(env);
		}
		catch (ReturnException foundReturn) {
			returnValue = foundReturn.value;
		}
		return returnValue;
	}

	public String toString() {
		return methodName + "(" + args + ") { " + body + " }";
	}

}

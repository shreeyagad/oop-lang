import java.util.List;

/**
 * Represents a Function
 * @param funcName The name of the function
 * @param body The body of the function
 * @param args The ordered list of the function's parameters
 */
public class MyFunction extends Expression {
	String funcName;
	Program body;
	List<Variable> args;
	
	public MyFunction(String funcName, Program body, List<Variable> args) {
		this.funcName = funcName;
		this.body = body;
		this.args = args;
	}

	@Override
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
		return funcName + "(" + args + ") { " + body + " }";
	}

}

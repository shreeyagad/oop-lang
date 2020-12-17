import java.util.List;

/**
 * Represents a FunctionDeclaration expression
 * @param funcName The name of the function
 * @param body The body of the function
 * @param args The ordered list of the function's parameters
 */
public class FunctionDeclaration extends Expression {
	String funcName;
	Program body;
	List<Variable> args;
	
	public FunctionDeclaration(String funcName, Program body, List<Variable> args) {
		this.funcName = funcName;
		this.body = body;
		this.args = args;
	}
	
	@Override
	public Object eval(Environment env) {
		Object f = null;
		f = new MyFunction(funcName, body, args);
		env.addVariable(funcName, f);
		return null;
	}
	
	public String toString() {
		return "function " + funcName + "(" + args + ") { " + body + "}";
	}
	
}

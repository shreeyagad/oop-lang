import java.util.List;

public class FunctionDeclaration extends Expression {
	String funcName;
	Program body;
	List<Variable> args;
	
	public FunctionDeclaration(String funcName, Program body, List<Variable> args) {
		this.funcName = funcName;
		this.body = body;
		this.args = args;
	}
	
	public Object eval(Environment env) {
		MyFunction f = new MyFunction(funcName, body, args);
		env.addVariable(funcName, f);
		return null;
	}
	
	public String toString() {
		return "function " + funcName + "(" + args + ") { " + body + "}";
	}
	
}

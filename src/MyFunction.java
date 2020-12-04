import java.util.List;

public class MyFunction {
	String funcName;
	Program body;
	List<Variable> args;
	
	public MyFunction(String funcName, Program body, List<Variable> args) {
		this.funcName = funcName;
		this.body = body;
		this.args = args;
	}
	
	public Object eval(Environment env) {
		return body.eval(env);
	}
}

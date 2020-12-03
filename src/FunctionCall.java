import java.util.ArrayList;
import java.util.function.Function;

public class FunctionCall extends Expression {
	Expression funcName;
	ArrayList<Expression> args;
	
	public FunctionCall(Expression funcName, ArrayList<Expression> args) {
		this.funcName = funcName;
		this.args = args;
	}
	
	public Object eval(Environment env) {
		String s = funcName.eval(env).toString();
		Function function = (Function) env.getValue(s);
		return function.apply(args);
	}
}

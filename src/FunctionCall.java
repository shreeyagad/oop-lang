import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Represents a FunctionCall expression
 * @param funcName The name of the function
 * @param argExprs The ordered list of expressions corresponding to the 
 * 				   function's parameters
 */
public class FunctionCall extends Expression {
	String funcName;
	List<Expression> argExprs;
	
	public FunctionCall(String funcName, List<Expression> argExprs) {
		this.funcName = funcName;
		this.argExprs = argExprs;
	}
	
	public Object eval(Environment env) {
		MyFunction f = (MyFunction) env.getValue(funcName);
		Environment newEnv = env.copyEnv();
		if (f.args.size() == argExprs.size()) {
			Iterator<Variable> argsIt = f.args.iterator();
			Iterator<Expression> argExprsIt = argExprs.iterator();

			while (argsIt.hasNext() && argExprsIt.hasNext()) {
				String varName = argsIt.next().getName();
				Object argValue = argExprsIt.next().eval(env);
				newEnv.addVariable(varName, argValue);
			}
			
			return f.eval(newEnv);
			
		}
		else {
			return null;
		}
	}
	
	public String toString() {
		return funcName + "(" + argExprs + ")";
	}
	
}

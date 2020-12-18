import java.util.Iterator;
import java.util.List;

/**
 * Represents a class constructor that creates a new object of that class
 * @param className The name of the function
 * @param argExprs The ordered list of expressions corresponding to the 
 * 				   constructor's parameters
 */
public class Constructor extends Expression {
	String className;
	List<Expression> argExprs;
	
	public Constructor(String className, List<Expression> argExprs) {
		this.className = className;
		this.argExprs = argExprs;
	}
	
	@Override
	public Object eval(Environment env) {
		MyClass c = env.getClass(className);
		Environment newEnv = env.copyEnv();
		MyObject o = new MyObject(className, newEnv);

		if (c.superClassName != null) {
			MyClass superClass = env.getClass(c.superClassName);
			newEnv.combineEnv(superClass.environment);
		}

		newEnv.combineEnv(c.environment);
		newEnv.addVariable("this", o);

		FunctionCall constructor = new FunctionCall(className, argExprs);
		System.out.println(argExprs);
		constructor.eval(newEnv);

		return o;
	}
	
	public String toString() {
		return "constructor of " + className;
	}

}

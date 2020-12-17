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
		Environment newEnv = env.copyEnv(); // Object environment
		MyObject o = new MyObject(className, newEnv); // Create object

		if (c.superClassName != null) {
			MyClass superClass = env.getClass(c.superClassName);
			newEnv.combineEnv(superClass.environment);
		}

		newEnv.combineEnv(c.environment);
		
		FunctionCall constructor = new FunctionCall(className, argExprs);
		constructor.evalMethod(newEnv);

		newEnv.addVariable("this", o);

		return o;
		
		// if (c.attrNames.size() == argExprs.size()) {
		// 	while (attrIt.hasNext() && argExprsIt.hasNext()) {
		// 		Object argValue = argExprsIt.next().eval(env);
		// 		String attrName = attrIt.next();
		// 		newEnv.addVariable(attrName, argValue);
		// 	}

		// 	while (methodIt.hasNext()) {
		// 		String methodName = methodIt.next();
		// 		MyFunction f = (MyFunction) c.environment.getValue(methodName);
		// 		newEnv.addVariable(methodName, f);
		// 	}
			
		// 	return new MyObject(className, newEnv);
		// }
		// else {
		// 	return null;
		// }
		
	}
	
	public String toString() {
		return "constructor of " + className;
	}

}

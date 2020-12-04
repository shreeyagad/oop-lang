import java.util.Iterator;
import java.util.List;

public class Constructor extends Expression {
	String className;
	List<Expression> argExprs;
	
	
	public Constructor(String className, List<Expression> argExprs) {
		this.className = className;
		this.argExprs = argExprs;
	}
	
	public Object eval(Environment env) {
		MyClass c = (MyClass) env.getClass(className);
		Environment newEnv = env.copyEnv();
		Iterator<Expression> argExprsIt = argExprs.iterator();
		Iterator<Variable> attrIt = c.attributes.iterator();
		if (c.attributes.size() == argExprs.size()) {
			while (attrIt.hasNext() && argExprsIt.hasNext()) {
				Object argValue = argExprsIt.next().eval(env);
				String attrName = attrIt.next().name;
				newEnv.addVariable(attrName, argValue);
			}
			
			return new MyObject(className, newEnv);
		}
		else {
			return null;
		}
		
	}
	
	public String toString() {
		return "constructor of " + className;
	}


}

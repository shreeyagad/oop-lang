import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a ClassDeclaration expression
 * @param className The name of the class
 * @param methods The list of function declarations corresponding to the class's methods
 * @param attributes The list of variables corresponding to the class's attributes
 */
public class ClassDeclaration extends Expression {
	String className;
	String superClassName;
	List<FunctionDeclaration> methods;
	List<Variable> attributes;
	
	public ClassDeclaration(String className, String superClassName, 
			List<Variable> attributes, List<FunctionDeclaration> methods) {
		this.className = className;
		this.superClassName = superClassName;
		this.attributes = attributes;
		this.methods = methods;
	}

	
	@Override
	public Object eval(Environment env) {
		
		Environment newEnv = env.copyEnv();
		
		if (superClassName != null) {
			MyClass superClass = env.getClass(superClassName);
			newEnv = newEnv.combineEnv(superClass.environment);
			MyFunction superConstructor = (MyFunction) newEnv.getValue(superClassName);
			newEnv.addVariable("super", superConstructor);
		}
		
		Iterator<Variable> attributeIt = attributes.iterator();
		Iterator<FunctionDeclaration> methodIt = methods.iterator();
		List<String> attrNames = new LinkedList<>();
		List<String> methodNames = new LinkedList<>();

		while(attributeIt.hasNext()) {
			Variable attribute = attributeIt.next();
			attrNames.add(attribute.name);
			newEnv.addVariable(attribute.name, null);
		}
		
		while(methodIt.hasNext()) {
			FunctionDeclaration method = methodIt.next();
			methodNames.add(method.funcName);
			method.eval(newEnv);
		}
		
		MyClass newClass = new MyClass(className, superClassName, attrNames, methodNames, newEnv);
		env.addClass(className, newClass);
		
		MyFunction constructor = (MyFunction) newEnv.getValue(className);
		env.addVariable(className, constructor);
		
		return null;
	}

	public String toString() {
		return "class " + className + " { " + attributes + methods + " }";
	}

}

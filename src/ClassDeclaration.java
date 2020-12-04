import java.util.Iterator;
import java.util.List;

public class ClassDeclaration extends Expression {
	String className;
	List<FunctionDeclaration> methods;
	List<Variable> attributes;
	
	public ClassDeclaration(String className, List<Variable> attributes, List<FunctionDeclaration> methods) {
		this.className = className;
		this.attributes = attributes;
		this.methods = methods;

	}
	
	
	@Override
	public Object eval(Environment env) {
		
		Environment newEnv = new Environment();
		
		Iterator<Variable> attributeIt = attributes.iterator();
		Iterator<FunctionDeclaration> methodIt = methods.iterator();
		
		while(attributeIt.hasNext()) {
			newEnv.addVariable(attributeIt.next().name, null);
		}
		
		while(methodIt.hasNext()) {
			methodIt.next().eval(newEnv);
		}
		
		MyClass newClass = new MyClass(className, newEnv);
		env.addVariable(className, newClass);
		
		return null;
	}


}

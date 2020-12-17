import java.util.List;

/**
 * Represents a class
 * @param className The name of the class
 * @param environment The environment where the attributes and methods of the class are stored
 * @param attrNames The list of names of attributes of the class
 * @param methodNames The list of names of methods of the class
*/
public class MyClass {
	
	String className;
	String superClassName;
	Environment environment;
	List<String> attrNames;
	List<String> methodNames;

	public MyClass(String className, String superClassName, List<String> attrNames, 
			List<String> methodNames, Environment environment) {
		this.className = className;
		this.superClassName = superClassName;
		this.attrNames = attrNames;
		this.methodNames = methodNames;
		this.environment = environment;
	}
	
	public Environment copyEnvironment() {
		return environment.copyEnv();
	}
	
	public String toString() {
		return "className: " + className;
	}

}

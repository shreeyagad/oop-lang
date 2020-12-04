import java.util.List;

public class MyClass {
	
	String className;
	Environment environment;
	List<Variable> attributes;
	
	public MyClass(String className, List<Variable> attributes, Environment environment) {
		this.className = className;
		this.attributes = attributes;
		this.environment = environment;
	}
	
	public Environment copyEnvironment() {
		return environment.copyEnv();
	}
	
	public String toString() {
		return "className: " + className + "; environment: " + environment;
	}

}

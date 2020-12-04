public class MyClass {
	
	String className;
	Environment environment;
	
	public MyClass(String className, Environment environment) {
		this.className = className;
		this.environment = environment;
	}
	
	public Environment copyEnvironment() {
		return environment.copyEnv();
	}

}


public class MyObject {
	String className;
	Environment env;
	
	public MyObject(String className, Environment env) {
		this.className = className;
		this.env = env;	
	}
	
	public String toString() {
		return "Object of type " + className;
	}
}

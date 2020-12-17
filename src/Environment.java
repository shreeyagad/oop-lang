import java.util.HashMap;

/**
 * Represents an Environment for a Program
 */
public class Environment {
	HashMap<String, Object> mappings;
	HashMap<String, MyClass> classes;
	
	public Environment() {
		mappings = new HashMap<>();
		classes = new HashMap<>();
	}
	
	public void addVariable(String name, Object value) {
		mappings.put(name, value);
	}
	
	public void changeVariable(String name, Object value) throws Exception {
		if (mappings.containsKey(name)) {
			mappings.put(name, value);
		}
		else {
			throw new Exception("Variable has not been initialized.");
		}
	}
	
	public Object getValue(String name) {
		return mappings.get(name);
	}
	
	public void addClass(String className, MyClass myClass) {
		classes.put(className, myClass);
	}
	
	public MyClass getClass(String className) {
		return classes.get(className);
	}
	
	public Environment copyEnv() {
		Environment newEnv = new Environment();
		newEnv.mappings = (HashMap<String, Object>) (mappings.clone());
		newEnv.classes = (HashMap<String, MyClass>) (classes.clone());
		return newEnv;
	}
	
	public Environment combineEnv(Environment env2) {
		this.mappings.putAll(env2.mappings);
		this.classes.putAll(env2.classes);
		return this;
	}
	
	public String toString() {
		return "mappings: " + mappings + ";\nclasses: " + classes;
	}

}

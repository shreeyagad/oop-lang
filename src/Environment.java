import java.util.HashMap;
public class Environment {
	HashMap<String, Object> mappings;
	public Environment() {
		mappings = new HashMap<String, Object>();
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
	
	public Environment copyEnv() {
		Environment newEnv = new Environment();
		newEnv.mappings = (HashMap<String, Object>) mappings.clone();
		return newEnv;
	}
}

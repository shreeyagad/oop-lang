import java.util.HashMap;
public class Environment {
	HashMap<String, Variable> mappings;
	public Environment() {
		mappings = new HashMap<String, Variable>();
	}
	
	public void addVariable(String name, Object value) {
		mappings.put(name, new Variable(name, value));
	}
}

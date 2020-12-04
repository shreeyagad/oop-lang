/**
 * Represents a Variable
 * @param name The name of the variable
 * @param type The type of the variable
 */
public class Variable extends Expression {
	String name;
	String type;

	public Variable(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public Object eval(Environment env) {
		return env.getValue(name);
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return name;
	}
	
}

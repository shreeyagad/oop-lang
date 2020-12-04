/**
 * Represents an Identifier expression, which maps to a value in the 
 * current Environment
 * @param name The name of the identifier
 */
public class Identifier extends Expression {
	String name;

	public Identifier(String name) {
		this.name = name;
	}
	
	public Object eval(Environment env) {
		return env.getValue(name);
	}
	
	public String toString() {
		return name;
	}
	
}

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
	
	public String getName() {
		return name;
	}
	
	@Override
	public Object eval(Environment env) {
		return env.getValue(name);
	}
	
	public String toString() {
		return "identifier w/ name: " + name;
	}
	
}

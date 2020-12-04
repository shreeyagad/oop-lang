
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

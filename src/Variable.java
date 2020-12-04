
public class Variable extends Expression {
	String name;
	String type;

	public Variable(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
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

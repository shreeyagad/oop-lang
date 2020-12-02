
public class Variable extends Expression {
	//TODO: String type
	String name;

	public Variable(String name) {
		this.name = name;
	}
	
	public Object eval(Environment env) {
		return env.getValue(name);
	}
}

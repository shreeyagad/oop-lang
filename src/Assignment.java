
public class Assignment extends Expression {
	String name;
	String type;
	Expression expr;
	Boolean reassignment;
	
	public Assignment(String type, String name, Expression expr, Boolean reassignment) {
		this.type = type;
		this.name = name;
		this.expr = expr;
		this.reassignment = reassignment;
	}
	
	public Object eval(Environment env) {
		Object value = expr.eval(env);
		if (!reassignment) {
			env.addVariable(name, value);
		}
		else {
			try {
				env.changeVariable(name, value);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public String toString() {
		return type + " " + name + " := " + expr;
	}

}


public class Assignment extends Statement {
	String name;
	String type;
	Expression expr;
	
	public Assignment(String type, String name, Expression expr) {
		this.type = type;
		this.name = name;
		this.expr = expr;
	}
	
	public void eval(Environment env) {
		env.addVariable(name, expr.eval());
	}

}

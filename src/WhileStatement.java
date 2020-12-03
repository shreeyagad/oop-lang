
public class WhileStatement extends Expression {
	Expression condition;
	Program body;
	
	public WhileStatement(Expression c, Program b) {
		this.condition = c;
		this.body = b;
	}
	
	public Object eval(Environment env) {
		Object p = null;
		while ((boolean) condition.eval(env)) {
			p = body.eval(env);
		}
		return p;
	}
	
	public String toString() {
		return "while (" + condition + ") { " + body + " }";
	}
}

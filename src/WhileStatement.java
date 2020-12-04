/**
 * Represents a WhileStatement, which executes the True branch while the provided
 * condition is true
 * @param condition The condition that determines whether the body of the While 
 * 					is executed
 * @param tBody The body of the While
 */
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

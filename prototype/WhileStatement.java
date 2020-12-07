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
	
	@Override
	public Object eval(Environment env) {
		while ((boolean) condition.eval(env)) {
			try {
				body.eval(env);
			}
			catch (BreakException b) {
				break;
			}
		}
		return null;
	}
	
	public String toString() {
		return "while (" + condition + ") { " + body + " }";
	}
	
}

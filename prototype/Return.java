/**
 * Represents a Return Statement
 * @param e The expression that will be evaluated and returned when the Return 
 *          Statement is evaluated
 */
public class Return extends Expression {

	Expression e;

	public Return(Expression e) {
		this.e = e;
	}

	@Override
	public Exception eval(Environment env) {
		throw new ReturnException(this.e, env);
	}

	public String toString() {
		return "return " + e + "";
	}
}

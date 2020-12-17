/**
 * Represents a Unary Expression with a NOT operator
 * @param e The expression that will be negated
 */
public class NotUnary extends Expression {
	Expression e;
	
	public NotUnary(Expression e) {
		this.e = e;
	}
	
	@Override
	public Object eval(Environment env) {
		return !((boolean) e.eval(env));
	}
	
}

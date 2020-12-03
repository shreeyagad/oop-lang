
public class NotUnary extends Expression {
	Expression e;
	
	public NotUnary(Expression e) {
		this.e = e;
	}
	
	public Object eval(Environment env) {
		return !((boolean) e.eval(env));
	}
}

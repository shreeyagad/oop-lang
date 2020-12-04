/**
 * Represents a Print Statement
 * @param value The expression that will be evaluated and printed
 */
public class Print extends Expression {
	Expression e;
	
	public Print(Expression e) {
		this.e = e;
	}
	
	public Object eval(Environment env) {
		System.out.println(e.eval(env));
		return null;
	}
	
	public String toString() {
		return "print " + e.toString(); 
	}
	
}

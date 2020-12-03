
public class IfElseStatement extends Expression {
	Expression condition;
	Program tBody;
	Program fBody;
	public IfElseStatement(Expression c, Program t, Program f) {
		condition = c;
		tBody = t;
		fBody = f;
	}
	
	public Object eval(Environment env) {
		if ((boolean) condition.eval(env)) {
			return tBody.eval(env);
		} 
		else if (!fBody.equals(null)) {
			return fBody.eval(env);
		}
		else {
			return null;
		}
	}
	
	public String toString() {
		return "if " + condition + "{ " + tBody + " } else { " + fBody + " }";
	}
	

}

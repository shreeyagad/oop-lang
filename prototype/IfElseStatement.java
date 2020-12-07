/**
 * Represents an IfElseStatement, which executes the True branch if the provided
 * condition is true and an optional False branch otherwise
 * @param condition Condition that determines which branch is executed
 * @param tBody Body of the True branch
 * @param fBody Body of the False branch
 */
public class IfElseStatement extends Expression {
	Expression condition;
	Program tBody;
	Program fBody;
	
	public IfElseStatement(Expression condition, Program tBody, Program fBody) {
		this.condition = condition;
		this.tBody = tBody;
		this.fBody = fBody;
	}

	@Override
	public Object eval(Environment env) {
		if ((boolean) condition.eval(env)) {
			return tBody.eval(env);
		} 
		else if (fBody != null) {
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


public class IfElseStatement extends Expression {
	Expression condition;
	Program tBody;
	Program fBody;
	public IfElseStatement(Expression c, Program t, Program f) {
		condition = c;
		tBody = t;
		fBody = f;
	}
	
	

}

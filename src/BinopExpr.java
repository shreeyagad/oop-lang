/**
 * Represents a Binary expression
 * @param op The binary operator
 * @param leftExpr The expression to the left of the operator
 * @param rightExpr The expression to the right of the operator
 */
public class BinopExpr extends Expression {
	private Token.TokenType op;
	private Expression leftExpr;
	private Expression rightExpr;

	public BinopExpr(Token.TokenType op, Expression leftExpr, Expression rightExpr) {
		this.op = op;
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}

	public Token.TokenType getOp() {
		return op;
	}

	public Expression getLeftExpr() {
		return leftExpr;
	}

	public Expression getRightExpr() {
		return rightExpr;
	}
	
	@Override
	public Object eval(Environment env) {
		switch (op) {
		case PLUS:
			if (leftExpr.eval(env) instanceof String) 
				return (String) leftExpr.eval(env) + (String) rightExpr.eval(env);
			return (int) leftExpr.eval(env) + (int) rightExpr.eval(env);
		case MINUS:
			return (int) leftExpr.eval(env) - (int) rightExpr.eval(env);
		case MULTIPLY:
			return (int) leftExpr.eval(env) * (int) rightExpr.eval(env);
		case DIVIDE:
			return (int) leftExpr.eval(env) / (int) rightExpr.eval(env);
		case LESS:
			return (int) leftExpr.eval(env) < (int) rightExpr.eval(env);
		case GREATER: 
			return (int) leftExpr.eval(env) > (int) rightExpr.eval(env);
		case EQUALS:
			return leftExpr.eval(env).equals(rightExpr.eval(env));
		case LESSEQ:
			return (int) leftExpr.eval(env) <= (int) rightExpr.eval(env);
		case GREATEREQ: 
			return (int) leftExpr.eval(env) >= (int) rightExpr.eval(env);
		case OR:
			return (boolean) leftExpr.eval(env) || (boolean) rightExpr.eval(env);
		case AND:
			return ((boolean) leftExpr.eval(env) && (boolean) rightExpr.eval(env));
		case NOTEQ:
			return !(leftExpr.eval(env).equals(rightExpr.eval(env)));
		default:
			return null;
		}
	}

	public boolean equals(Object o) {
		BinopExpr e = (BinopExpr) o;
		return this.op.equals(e.getOp()) 
				&& this.leftExpr.equals(e.getLeftExpr())
				&& this.rightExpr.equals(e.getRightExpr());
	}

	public String toString() {
		return "(" + leftExpr + " " + op + " " + rightExpr + ")";
	}

}
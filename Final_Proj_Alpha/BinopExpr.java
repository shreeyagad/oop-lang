/**
 * A class to represent a binary expression
 *
 */

public class BinopExpr extends Expression {
	// private Token.TokenType op;
	private Token.TokenType op;
	private Expression leftExpr;
	private Expression rightExpr;

	public BinopExpr(Token.TokenType op, Expression left, Expression right) {
		this.op = op;
		this.leftExpr = left;
		this.rightExpr = right;
	}

	public String toString() {
		return "(" + leftExpr + ")" + " " + op + " (" + rightExpr + ")";
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

	public Object eval() {
		switch (op) {
		case PLUS:
			if (leftExpr.eval() instanceof String) return (String) leftExpr.eval() + (String) rightExpr.eval();
			return (int) leftExpr.eval() + (int) rightExpr.eval();
		case MINUS:
			return (int) leftExpr.eval() - (int) rightExpr.eval();
		case MULTIPLY:
			return (int) leftExpr.eval() * (int) rightExpr.eval();
		case DIVIDE:
			return (int) leftExpr.eval() / (int) rightExpr.eval();
		case LESS:
			return (int) leftExpr.eval() < (int) rightExpr.eval();
		case GREATER: 
			return (int) leftExpr.eval() > (int) rightExpr.eval();
		case EQUALS:
			return leftExpr.eval().equals(rightExpr.eval());
		case LESSEQ:
			return (int) leftExpr.eval() <= (int) rightExpr.eval();
		case GREATEREQ: 
			return (int) leftExpr.eval() >= (int) rightExpr.eval();
		case OR:
			return (boolean) leftExpr.eval() || (boolean) rightExpr.eval();
		case AND:
			return (boolean) leftExpr.eval() && (boolean) rightExpr.eval();
		case NOTEQ:
			return !(leftExpr.eval().equals(rightExpr.eval()));
		default:
			return null;
		}
	}

	public boolean equals(Object o) {
		BinopExpr e = (BinopExpr) o;
		return this.op.equals(e.getOp()) && this.leftExpr.equals(e.getLeftExpr())
				&& this.rightExpr.equals(e.getRightExpr());
	}

}
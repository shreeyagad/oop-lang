public class BinopExpr extends Expression {
	// private Token.TokenType op;
	private Token op;
	private Expression leftExpr;
	private Expression rightExpr;
	
	public BinopExpr(Token op, Expression left, Expression right) {
		super(false, null);
		this.op = op;
		this.leftExpr = left;
		this.rightExpr = right;
	}

	public String toString() {
		return "(" + PrettyPrinter.printExpression(leftExpr) + ")" + 
		" " + op.getLexeme() + " (" + PrettyPrinter.printExpression(rightExpr) + ")";
	}

}
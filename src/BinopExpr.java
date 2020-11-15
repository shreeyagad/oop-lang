public class BinopExpr extends Expression {
	private Token.TokenType op;
	private Expression leftExpr;
	private Expression rightExpr;
	
	public BinopExpr(Token.TokenType op, Expression left, Expression right) {
		this.op = op;
		this.leftExpr = left;
		this.rightExpr = right;
	}

}
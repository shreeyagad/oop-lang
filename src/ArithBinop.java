public class ArithBinop extends ArithExpr {
	public static enum Operator {
		PLUS, MINUS, MULTIPLY
	}
	
	private Operator op;
	private ArithExpr leftExpr;
	private ArithExpr rightExpr;
	
	public ArithBinop(Operator op, ArithExpr left, ArithExpr right) {
		this.op = op;
		this.leftExpr = left;
		this.rightExpr = right;
	}
}

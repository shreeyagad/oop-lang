import java.util.List;

public class FunctionDeclaration extends Expression {
	Program body;
	List<Expression> args;
	public FunctionDeclaration(List<Expression> args) {
		this.args = args;
	}
}

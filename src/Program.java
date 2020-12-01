import java.util.LinkedList;

public class Program {
	
	LinkedList<Statement> statements;
	
	public Program(LinkedList<Statement> lst) {
		statements = lst;
	}

	public void eval() {
		for (Statement statement: statements) {
			statement.eval();
		}
	}
}

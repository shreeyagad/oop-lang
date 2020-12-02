import java.util.LinkedList;

public class Program {
	
	LinkedList<Statement> statements;
	
	public Program(LinkedList<Statement> lst) {
		statements = lst;
	}

	public void eval(Environment env) {
		for (Statement statement: statements) {
			statement.eval(env);
		}
	}
	
	public String toString() {
		return statements.toString();
	}
}

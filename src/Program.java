import java.util.LinkedList;
import java.util.Iterator;

public class Program {
	
	LinkedList<Statement> statements;
	
	public Program(LinkedList<Statement> lst) {
		statements = lst;
	}

	public Object eval(Environment env) {
		Iterator<Statement> it = statements.iterator();
		Object p = null;
		while (it.hasNext()) {
			p = (it.next()).eval(env);
		}
		return p;
	}
	
	public String toString() {
		return statements.toString();
	}
}

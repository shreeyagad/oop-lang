import java.util.List;
import java.util.Iterator;
/**
 * Represents a Program
 * @param statements The list of statements that constitute a Program
 */
public class Program {
	
	List<Statement> statements;
	
	public Program(List<Statement> statements) {
		this.statements = statements;
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

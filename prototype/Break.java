/**
 * Represents a Break Statement
 */
public class Break extends Expression {
    
    @Override
    public Exception eval(Environment env) {
		throw new BreakException();
	}

}
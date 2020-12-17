/**
 * Represents a Return Exception, that will break the control flow and return 
 * from the function body
 * @param value The value that will be returned
 */
public class ReturnException extends RuntimeException {
	Object value;

	public ReturnException(Expression e, Environment env) {
		this.value = e.eval(env);
	}

	@Override
	public String toString() {
		return "returnException " + value;
	}
}
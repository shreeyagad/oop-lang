/**
 * Represents an Interpreter that tokenizes, parses, and evaluates the given
 * source code
 */
public class Interpreter {
	
	public static Object evaluate(String source) throws Exception {
		Tokenizer tokenizer = new Tokenizer(source);
		Parser parser = new Parser(tokenizer);
		Program e = parser.parseTokens();
		Environment env = new Environment();
		return e.eval(env);
	}

}


public class Interpreter {
	public static void evaluate(String source) {
		Tokenizer tokenizer = new Tokenizer(source);
		Parser parser = new Parser(tokenizer);
		Program e = parser.parseTokens();
		Environment env = new Environment();
		e.eval(env);
	}
}

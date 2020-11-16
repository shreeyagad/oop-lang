
public class Interpreter {
	public static Object evaluate(String source) {
		Tokenizer tokenizer = new Tokenizer(source);
		Parser parser = new Parser(tokenizer);
		Expression e = parser.parseTokens();
		return e.eval();
	}
}

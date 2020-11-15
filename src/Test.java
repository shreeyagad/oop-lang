import java.util.List;
// import static org.junit.jupiter.api.Assertions.assertEquals;
public class Test {

	// public static void test0() {
	// 	String source = "123 + 23";
	// 	Tokenizer tokenizer = new Tokenizer(source);
	// 	List<Token> lst = tokenizer.tokenize();

	// 	assertEquals((lst.get(0)).toString(), "123");
	// 	assertEquals(lst.get(1).toString(), "+");
	// 	assertEquals(lst.get(2).toString(), "23");
	// }

	public static void test1() {
		String source = "123 + 23";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Expression e = parser.parseTokens();
		System.out.println("This is the parse tree: " + e);
		PrettyPrinter.printExpression(e);
	}

	public static void test2() {
		String source = "123 + 456 + 789";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Expression e = parser.parseTokens();
		System.out.println("This is the parse tree: " + e);
		PrettyPrinter.printExpression(e);
	}

	public static void test3() {
		String source = "123 * 456 + 789";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Expression e = parser.parseTokens();
		System.out.println("This is the parse tree: " + e);
		PrettyPrinter.printExpression(e);
	}

	public static void main(String[] args) {
		// test0();
		// test1();
		// test2();
		test3();
	}
}

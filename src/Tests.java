import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Tests {
	

	@Test
	public void test0() {
		String source = "123 + 23";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();

		assertEquals((lst.get(0)).toString(), "123");
		assertEquals(lst.get(1).toString(), "+");
		assertEquals(lst.get(2).toString(), "23");
	}

	
	@Test
	public void test1() {
		String source = "123 + 23";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Expression e = parser.parseTokens();
		System.out.println("This is the parse tree: " + e);
		Expression e2 = new BinopExpr(Token.TokenType.PLUS, new MyNumber(123), new MyNumber(23));

		assertEquals(e, e2);
	}

	@Test
	public void test2() {
		String source = "123 + 456 + 789";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Expression e = parser.parseTokens();
		System.out.println("This is the parse tree: " + e);
	}

	@Test
	public void test3() {
		String source = "123 * 456 + 789";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Expression e = parser.parseTokens();
		assertEquals(e.toString(), "((123) MULTIPLY (456)) PLUS (789)");
		System.out.println("This is the parse tree: " + e);
	}

	@Test
	public void test4() {
		String source = "3 * 4 + 5";
		assertEquals(Interpreter.evaluate(source), 17);
	}

	@Test
	public void test5() {
		String source = "4 * 3 == 2 * 6";
		assertEquals(Interpreter.evaluate(source), true);
	}
	
	
	@Test
	public void test6() {
		String source = "3 > 2 - 2 & true";
		assertEquals(Interpreter.evaluate(source), true);
		
	}
}

import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Tests {
	

	@Test
	public void test0() {
		System.out.println("test0");
		String source = "123 + 23;";
		System.out.println("This is the list of source: " + source);
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();

		assertEquals((lst.get(0)).toString(), "123");
		assertEquals(lst.get(1).toString(), "+");
		assertEquals(lst.get(2).toString(), "23");
	}

	
	@Test
	public void test1() throws Exception {
		System.out.println("test1");
		String source = "123 + 23;";
		System.out.println("This is the list of source: " + source);
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Program e = parser.parseTokens();
		System.out.println("This is the parse tree: " + e);
		
		Expression real = (Expression) e.statements.get(0);
		Expression expected = new BinopExpr(Token.TokenType.PLUS, new MyNumber(123), new MyNumber(23));

		assertEquals(real, expected);
	}

	@Test
	public void test2() throws Exception {
		System.out.println("test2");
		String source = "123 + 456 + 789;";
		System.out.println("This is the list of source: " + source);
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Program e = parser.parseTokens();
		assertEquals(e.toString(), "[(123 PLUS (456 PLUS 789))]");
		System.out.println("This is the parse tree: " + e);
	}

	@Test
	public void test3() throws Exception {
		System.out.println("test3");
		String source = "123 * 456 + 789;";
		System.out.println("This is the list of source: " + source);
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("This is the list of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Program e = parser.parseTokens();
		assertEquals(e.toString(), "[((123 MULTIPLY 456) PLUS 789)]");
		System.out.println("This is the parse tree: " + e);
	}

	@Test
	public void test4() throws Exception {
		System.out.println("test4");
		String source = "3 * 4 + 5;";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), 17);
	}

	@Test
	public void test5() throws Exception {
		System.out.println("test5");
		String source = "4 * 3 == 2 * 6;";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
	}
	
	
	@Test
	public void test6() throws Exception {
		System.out.println("test6");
		String source = "3 > 2 - 2 && true;";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
		
	}
	
	@Test
	public void test7() throws Exception {
		System.out.println("test7");
		String source = "\"hello\" + \" world\";";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), "hello world");
		
	}
	
	@Test
	public void test8() throws Exception {
		System.out.println("test8");
		String source = "5 * 2 >= 10;";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
	}
	
	@Test
	public void test9() throws Exception {
		System.out.println("test9");
		String source = "true && false || false || true && true;";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
	}
	
	@Test
	public void test10() throws Exception {
		System.out.println("test10");
		String source = "10 + 2 != 3 * 4;";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), false);
	}
	
	@Test
	public void test11() throws Exception {
		System.out.println("test11");
		String source = "true && false;";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), false);
	}
	
	@Test
	public void test12() throws Exception {
		System.out.println("test11");
		String source = "(if (true) {5;} else {6;}) > (if (false) {5;} else {6;});";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), false);
	}
	
	@Test
	public void test13() throws Exception {
		System.out.println("test11");
		String source = "!!!true;";
		System.out.println(source + " evaluates to "  + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), false);
	}
	
}
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Tests {

	@Test
	public void test0() {
		System.out.println("test0");
		String source = "123 + 23;";
		System.out.println("Source: " + source);
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();

		assertEquals(lst.get(0).toString(), "123");
		assertEquals(lst.get(1).toString(), "+");
		assertEquals(lst.get(2).toString(), "23");
	}

	@Test
	public void test1() throws Exception {
		System.out.println("test1");
		String source = "123 + 23;";
		System.out.println("Source: " + source);
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("List of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Program e = parser.parseTokens();
		System.out.println("Parse tree: " + e);

		Expression real = (Expression) e.statements.get(0);
		Expression expected = new BinopExpr(Token.TokenType.PLUS, new MyNumber(123), new MyNumber(23));

		assertEquals(real, expected);
	}

	@Test
	public void test2() throws Exception {
		System.out.println("test2");
		String source = "123 + 456 + 789;";
		System.out.println("Source: " + source);
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("List of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Program e = parser.parseTokens();
		assertEquals(e.toString(), "[(123 PLUS (456 PLUS 789))]");
		System.out.println("Parse tree: " + e);
	}

	@Test
	public void test3() throws Exception {
		System.out.println("test3");
		String source = "123 * 456 + 789;";
		System.out.println("Source: " + source);
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println("List of tokens: " + lst);
		Parser parser = new Parser(tokenizer);
		Program e = parser.parseTokens();
		assertEquals(e.toString(), "[((123 MULTIPLY 456) PLUS 789)]");
		System.out.println("Parse tree: " + e);
	}

	@Test
	public void test4() throws Exception {
		System.out.println("test4");
		String source = "3 * 4 + 5;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), 17);
	}

	@Test
	public void test5() throws Exception {
		System.out.println("test5");
		String source = "4 * 3 == 2 * 6;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
	}

	@Test
	public void test6() throws Exception {
		System.out.println("test6");
		String source = "3 > 2 - 2 && true;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
	}

	@Test
	public void test7() throws Exception {
		System.out.println("test7");
		String source = "\"hello\" + \" world\";";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), "hello world");
	}

	@Test
	public void test8() throws Exception {
		System.out.println("test8");
		String source = "5 * 2 >= 10;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
	}

	@Test
	public void test9() throws Exception {
		System.out.println("test9");
		String source = "true && false || false || true && true;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
	}

	@Test
	public void test10() throws Exception {
		System.out.println("test10");
		String source = "10 + 2 != 3 * 4;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), false);
	}

	@Test
	public void test11() throws Exception {
		System.out.println("test11");
		String source = "true && false;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), false);
	}

	@Test
	public void test12() throws Exception {
		System.out.println("test12");
		String source = "(if (true) {5;} else {6;}) > (if (false) {5;} else {6;});";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), false);
	}

	@Test
	public void test13() throws Exception {
		System.out.println("test13");
		String source = "!true;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), false);
	}

	@Test
	public void test14() throws Exception {
		System.out.println("test14");
		String source = "int n = 10; function decrementNum(int n) { while(n >= 0) { n = n - 1; }; }; n;";
		System.out.println(Interpreter.evaluate(source));
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), 10);
	}

	@Test
	public void test15() throws Exception {
		System.out.println("test15");
		String source = "int n = 5; int sum = 0; while(n >= 0) { sum = sum + n; n = n - 1; }; sum;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), 15);
	}

	@Test
	public void test16() throws Exception {
		System.out.println("test16");
		String source = "class myClass { " + "int num; boolean bool; " + "function myClass(int n, boolean b) {"
				+ "num = n; bool = b; " + "}; " + "function addAttr() { return num + num; }; " + "}; "
				+ "myClass c = new myClass(2, true); " + "myClass c2 = new myClass(3, false); " + "c.addAttr();";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), 4);
	}

	@Test
	public void test17() throws Exception {
		System.out.println("test17");
		String source = "class vector { " + "int num1; int num2;" + "function myClass(int n, int n2) {"
				+ "num = n; num2 = n2; " + "}; " + "function sum() { return num1 + num2; }; " + "}; "
				+ "vector v = new vector(2, 4); " + "v.sum();";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), 6);
	}

	@Test
	public void test18() throws Exception {
		System.out.println("test18");
		String source = "2 + (2 + 3) * 4;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), 22);
	}

	@Test
	public void test19() throws Exception {
		System.out.println("test19");
		String source = "int num = 5; while (num > 0) { num = num - 1; break; num = num - 1; }; num;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), 4);
	}

	@Test
	public void test20() throws Exception {
		System.out.println("test20");
		String source = "int num = 4; boolean b = num == 4; int b2 = num * 2 >= 16; b && b;";
		System.out.println(source + " evaluates to " + Interpreter.evaluate(source));
		assertEquals(Interpreter.evaluate(source), true);
	}
}

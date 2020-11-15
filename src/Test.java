import java.util.List;
public class Test {


	// public static void test0() {
	// 	String source = "123 + 23 \"hi\"";
	// 	Tokenizer tokenizer = new Tokenizer(source);
	// 	List<Token> lst = tokenizer.tokenize();
	// 	System.out.println(lst);

	// 	assertEquals(lst.get(0).toString(), "123");
	// 	assertEquals(lst.get(1).toString(), "+");
	// 	assertEquals(lst.get(2).toString(), "23");
	// 	assertEquals(lst.get(3).toString(), "\"hi\"");
	// }
	public static void test1() {
		String source = "123 + 23";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println(lst);
		Parser parser = new Parser(tokenizer);
		Expression e = parser.parseTokens();
		System.out.println(e);

	}

	public static void main(String[] args) {
		// test0();
		test1();
		
//		String str = "\" hello";
//		
//		System.out.println(str.charAt(0) == '\"');
	}

}

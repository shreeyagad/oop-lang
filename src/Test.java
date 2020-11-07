import java.util.List;
import static org.junit.Assert.assertEquals;
public class Test {


	public static void test0() {
		String source = "123 + 23 \"hi\"";
		Tokenizer tokenizer = new Tokenizer(source);
		List<Token> lst = tokenizer.tokenize();
		System.out.println(lst);

		assertEquals(lst.get(0).toString(), "123");
		assertEquals(lst.get(1).toString(), "+");
		assertEquals(lst.get(2).toString(), "23");
		assertEquals(lst.get(3).toString(), "\"hi\"");
	}
	public void test1() {

	}
	public void test2() {

	}
	

	public static void main(String[] args) {
		test0();
		
//		String str = "\" hello";
//		
//		System.out.println(str.charAt(0) == '\"');
	}

}

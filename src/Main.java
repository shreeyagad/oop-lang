import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// System.out.println("OOPLang Interpreter\n");
		// Scanner sc = new Scanner(System.in);
		// System.out.print(">> ");
		// String input = sc.nextLine();
		// while (!input.equals("end")) {
		// 	try {
		// 		if (!input.equals("")) System.out.println(Interpreter.evaluate(input));
		// 	} catch (Exception e) {
		// 		System.out.println("Invalid Expression!");
		// 	}
		// 	System.out.println();
		// 	System.out.print(">> ");
		// 	input = sc.nextLine();
		// }

		// String source = "int n = 10; function printNum(int n) { while(n >= 0) { print n; n = n - 1; }; }; printNum(n);";
		String source = "print(10);";
		Tokenizer t = new Tokenizer(source);
		List<Token> tokens = t.tokenize();
		System.out.println(tokens);
		Parser p = new Parser(t);
		System.out.println(p.parseTokens());
		System.out.println(Interpreter.evaluate(source));
		
		
		
		
		

	}

}

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("OOPLang Interpreter\n");
		Scanner sc = new Scanner(System.in);
		System.out.print(">> ");
		String input = sc.nextLine();
		Environment env = new Environment();
		while (!input.equals("end")) {
			try {
				if (!input.equals("")) System.out.println(Interpreter.evaluate(input, env));
			} catch (Exception e) {
				System.out.println("Invalid Expression!");
			}
			System.out.println();
			System.out.print(">> ");
			input = sc.nextLine();
		}

		// String source = "";
		// Tokenizer t = new Tokenizer(source);
		// List<Token> tokens = t.tokenize();
		// System.out.println(tokens);
		// Parser p = new Parser(t);
		// System.out.println(p.parseTokens());
		// Environment env = new Environment();
		// System.out.println(Interpreter.evaluate(source, env));
		
		
		
		
		

	}

}

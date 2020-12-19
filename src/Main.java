import java.util.Scanner;
import java.nio.file.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("\nWelcome to OOPLang!\n");
		System.out.println("If you would like to execute a .oop file, enter 1. If you would like to proceed to the interpreter, enter 2.\n");
		Scanner in = new Scanner(System.in);
		int a = Integer.parseInt(in.nextLine());
		Environment env = new Environment();
		if (a == 1) {
			System.out.print("\nEnter the path to the file: ");
			String fileName = in.nextLine();
			String sourceCode = new String(Files.readAllBytes(Paths.get(fileName))); 
			Interpreter.evaluate(sourceCode, env);
		}
		else {
			System.out.println("\nOOPLang Interpreter\n");
			Scanner sc = new Scanner(System.in);
			System.out.print(">> ");
			while (true) {
				StringBuilder sb = new StringBuilder(sc.nextLine());
				while (sb.length() > 0 && !(sb.substring(sb.length()-2).equals(";;")))
					sb.append(sc.nextLine());
				if (sb.toString().equals("end;;"))
					break;
				try {
					if (!(sb.toString().equals(""))) {
						Object o = Interpreter.evaluate(sb.substring(0, sb.length()-1), env);
						if (o != null) System.out.println(Interpreter.evaluate(sb.substring(0, sb.length()-1), env));
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.print("\n>> ");
			}

			sc.close();
		}
		in.close();
		
		// String source = "class Person { String name; function Person(String name) { this.name = name; }; }; Person p = new Person(“Emily”); p.name;"
		// 		+ "	\"The dog \" + d.name + \" has owner \" + d.owner.name + \".\";";
		// Tokenizer t = new Tokenizer(source);
		// Parser p = new Parser(t);
		// System.out.println(p.parseTokens());
		// System.out.println(Interpreter.evaluate(source));
		
	}

}

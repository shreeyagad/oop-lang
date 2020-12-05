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
				System.out.println(e.getMessage());
			}
			System.out.println();
			System.out.print(">> ");
			input = sc.nextLine();
		}
	}

}

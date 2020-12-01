import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("OOPLang Interpreter\n");
		Scanner sc = new Scanner(System.in);
		System.out.print(">> ");
		String input = sc.nextLine();
		while (!input.equals("end")) {
			try {
				if (!input.equals("")) System.out.println(Interpreter.evaluate(input));
			} catch (Exception e) {
				System.out.println("Invalid Expression!");
			}
			System.out.println();
			System.out.print(">> ");
			input = sc.nextLine();
		}

	}

}

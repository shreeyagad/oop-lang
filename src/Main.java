import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("\nOOPLang Interpreter\n");
		Scanner sc = new Scanner(System.in);
		System.out.print(">> ");
		Environment env = new Environment();

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

}

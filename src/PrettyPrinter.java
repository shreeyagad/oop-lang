public class PrettyPrinter {

    public static String printExpression(Expression e) {
        if (e.isToken) {
            return e.lexeme;
        }
        else {
            return e.toString();
        }
    }
}
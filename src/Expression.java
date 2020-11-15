public abstract class Expression {

    boolean isToken;
    String lexeme;

    protected Expression(boolean isToken, String lexeme) {
        this.isToken = isToken;
        this.lexeme = lexeme;
    }

}

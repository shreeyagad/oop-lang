/**
 * Represents a value with null type
 */
public class MyNull extends Expression {

    @Override
    public Object eval(Environment env) {
        return null;
    }

    public String toString() {
        return "null";
    }
}
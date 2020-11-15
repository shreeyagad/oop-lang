public class MyString extends Expression {
    String value;

    public MyString(String s) {
        super(true, s);
        this.value = s;
    }

}
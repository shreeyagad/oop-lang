/**
 * Represents a Number
 * @param value The value given to the Number (can only be type int)
 */
public class MyNumber extends Expression {
	int value;
	
	public MyNumber(int value) {
		this.value = value;
	}

    public boolean equals(Object e) {
    	return this.value == ((MyNumber) e).value;
	}
	
	@Override
    public Integer eval(Environment env) {
    	return value;
	}
	
    public String toString() {
    	return Integer.toString(value);
	}
	
}

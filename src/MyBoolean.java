/**
 * Represents a Boolean
 * @param value The value given to the Boolean (true or false)
 */
public class MyBoolean extends Expression {
    boolean value;
	
	public MyBoolean(boolean value) {
		this.value = value;
	}
	 
	public boolean equals(Object e) {
		return this.value == ((MyBoolean) e).value;
	}
	
	@Override
	public Boolean eval(Environment env) {
		return value;
	}
	public String toString() {
		return Boolean.toString(value);
	}

}


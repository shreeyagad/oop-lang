/**
 * Represents a Boolean
 * @param value The value given to the boolean (true or false)
 */
public class MyString extends Expression {
	String value;

	public MyString(String value) {
		this.value = value;
	}

	public boolean equals(Object e) {
		return this.value.equals(((MyString) e).value);
	}

	public String eval(Environment env) {
		return value;
	}

	public String toString() {
		return '"' + value + '"';
	}

}
/**
 * A custom String class to represent Strings
 *
 */

public class MyString extends Expression {
	String value;

	public MyString(String s) {
		this.value = s;
	}

	public String toString() {
		return value;
	}

	public boolean equals(Object e) {
		return this.value.equals(((MyString) e).value);
	}

	public String eval() {
		return value;
	}

}
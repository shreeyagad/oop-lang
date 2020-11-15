/**
 * A custom Boolean class to represent Booleans
 *
 */


public class MyBoolean extends Expression {
    boolean value;
	
	public MyBoolean(boolean bool) {
		this.value = bool;
	}
	
	 public String toString() {
	    return Boolean.toString(value);
	 }
	 
	 public boolean equals(Object e) {
		 return this.value == ((MyBoolean) e).value;
	 }
	 
	 public Boolean eval() {
	    return value;
	 }

}


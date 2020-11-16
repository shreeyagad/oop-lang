/**
 * A custom Number class to represent Numbers
 *
 */

public class MyNumber extends Expression {
	int value;
	
	public MyNumber(int value) {
		this.value = value;
	}
	
    public String toString() {
    	return Integer.toString(value);
    }
    

    public boolean equals(Object e) {
    	return this.value == ((MyNumber) e).value;
    }
    
    public Integer eval() {
    	return value;
    }

}

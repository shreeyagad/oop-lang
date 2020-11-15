public class MyBoolean extends Expression {
    boolean value;
	
	public MyBoolean(boolean bool) {
		super(true, Boolean.toString(bool));
		this.value = bool;
	}

}


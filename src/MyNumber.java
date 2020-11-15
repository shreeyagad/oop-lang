public class MyNumber extends Expression {
	int value;
	
	public MyNumber(int value) {
		super(true, Integer.toString(value));
		this.value = value;
	}

}

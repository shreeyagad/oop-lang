
public class NewObject extends Expression {
	MyClass myClass;
	Environment environment;
	
	public NewObject(MyClass myClass) {
		this.myClass = myClass;
		this.environment = myClass.copyEnvironment();
	}

}

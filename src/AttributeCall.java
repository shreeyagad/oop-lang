
public class AttributeCall extends Expression {
	String objectName;
	String attrName;
	
	public AttributeCall(String objectName, String attrName) {
		this.objectName = objectName;
		this.attrName = attrName;
	}
	
	public Object eval(Environment env) {
		MyObject o = (MyObject) env.getValue(objectName);
		Object value = o.env.getValue(attrName);
		return value;
	}
	
	public String toString() {
		return objectName + "." + attrName;
	}

}

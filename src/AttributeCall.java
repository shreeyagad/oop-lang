/**
 * Represents a AttributeCall expression
 * @param objectName The name of the object
 * @param attrName The name of the attribute
 */
public class AttributeCall extends Expression {
	String objectName;
	String attrName;
	
	public AttributeCall(String objectName, String attrName) {
		this.objectName = objectName;
		this.attrName = attrName;
	}
	
	@Override
	public Object eval(Environment env) {
		MyObject o = (MyObject) env.getValue(objectName);
		return o.env.getValue(attrName);
	}
	
	public String toString() {
		return objectName + "." + attrName;
	}

}

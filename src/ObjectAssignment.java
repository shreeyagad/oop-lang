/**
 * Represents an ObjectAssignment Expression
 * @param object The object whose attribute will be assigned a value
 * @param attribute The attribute of the object that will be assigned a value
 * @param expr The expression whose value that will be assigned to the attribute
 */
public class ObjectAssignment extends Expression {
	Attribute object;
	Identifier attribute;
	Expression expr;
	
	public ObjectAssignment(Attribute object, Identifier attribute, Expression expr) {
		this.object = object;
		this.attribute = attribute;
		this.expr = expr;
	}
	
	@Override
	public Object eval(Environment env) { 
		MyObject o = (MyObject) object.eval(env);
		Object rightExpression = expr.eval(env);
		o.env.addVariable(attribute.name, rightExpression);
		
		return null;
	}
    
}
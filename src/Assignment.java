/**
 * Represents an Assignment Expression
 * @param var The variable that will store the expression
 * @param expr The expression that will be assigned to the variable
 * @param reassignment The boolean representing whether the variable has been 
 * 					   previously declared
 */
public class Assignment extends Expression {
	Variable var;
	Expression expr;
	boolean reassignment;
	
	public Assignment(Variable var, Expression expr, boolean reassignment) {
		this.var = var;
		this.expr = expr;
		this.reassignment = reassignment;
	}

	@Override
	public Object eval(Environment env) {
		Object value = expr.eval(env);
		try {
			if (reassignment) {
				Object prevValue = env.getValue(var.getName());
				if (value != null) {
					if (prevValue instanceof MyObject) {
						String prevClassName = ((MyObject) prevValue).className;
						if (value instanceof MyObject) {
							String className = ((MyObject) value).className;
							if (!(prevClassName.equals(className))) {
								throw new Exception("Type of object variables does not match value");
							}
						}
						else {
							throw new Exception("Type of object variables does not match value");
						}
					}
					else if (!(prevValue.getClass().equals(value.getClass()))) {
						throw new Exception("Type of variable does not match value");
					}
				}
				env.changeVariable(var.getName(), value);
			}
			else {
				if (value != null) {
					switch (var.getType()) {
						case "int":
							if (!(value instanceof Integer)) {
								throw new Exception("Can only assign integer values to int variables");
							}
							break;
						case "boolean":
							if (!(value instanceof Boolean)) {
								throw new Exception("Can only assign boolean values to boolean variables");
							}
							break;
						case "String":
							if (!(value instanceof String)) {
								throw new Exception("Can only assign String values to String variables");
							}
							break;
						default:
							if (value instanceof MyObject) {
								String className = ((MyObject) value).className;
								if (!(var.getType().equals(className))) {
									throw new Exception("Type of object variables does not match value");
								}
							}
							else {
								throw new Exception("Type of object variables does not match value");
							}
					}
				}
				env.addVariable(var.getName(), value);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String toString() {
		return var.getType() + " " + var.getName() + " := " + expr;
	}

}

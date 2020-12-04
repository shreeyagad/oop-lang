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
	Boolean reassignment;
	
	public Assignment(Variable var, Expression expr, Boolean reassignment) {
		this.var = var;
		this.expr = expr;
		this.reassignment = reassignment;
	}
	
	public Object eval(Environment env) {
		//TODO: Type check assignment statements
		Object value = expr.eval(env);
		if (!reassignment) {
			env.addVariable(var.getName(), value);
		}
		else {
			try {
				env.changeVariable(var.getName(), value);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public String toString() {
		return var.getType() + " " + var.getName() + " := " + expr;
	}

}


public class Assignment extends Expression {
	Variable var;
	Expression expr;
	Boolean reassignment;
	
	public Assignment(Variable v, Expression expr, Boolean reassignment) {
		this.var = v;
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

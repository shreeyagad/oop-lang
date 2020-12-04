import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * Represents a Parser that creates an abstract syntax tree from a list of tokens
 * @param t The Tokenizer which contains the tokens that the Parser will parse
 */
public class Parser {

	private final List<Token> tokens;
	private int current;

	Parser(Tokenizer t) {
		this.tokens = t.tokenize();
		this.current = 0;
	}

	private Token getCurrentToken() {
		return tokens.get(current);
	}

	private Token getPreviousToken() {
		return tokens.get(current - 1);
	}

	private Token getNextToken() {
		return tokens.get(current + 1);
	}
	
	private Token.TokenType getCurrentTokenType() {
		return getCurrentToken().getType();
	}

	private boolean atEOF() {
		return (getCurrentTokenType() == Token.TokenType.EOF);
	}

	private void increment() {
		if (!atEOF()) {
			current++;
		}
	}

	private boolean matchesType(List<Token.TokenType> tokenTypes) {
		if (atEOF()) {
			return false;
		}
		Token.TokenType currentTokenType = getCurrentTokenType();
		return (tokenTypes.contains(currentTokenType));
	}

	public Program parseTokens() throws Exception {
		LinkedList<Statement> lst = new LinkedList<>();
		return statements(lst);
	}

	private Program statements(LinkedList<Statement> lst) throws Exception {
		lst.add(expression());
		consume(Token.TokenType.SEMICOLON, 
				"Every statement must be followed by a semicolon.");

		while (!atEOF() && !getCurrentTokenType().equals(Token.TokenType.RBRACKET)) {
			statements(lst);
		}
		return new Program(lst);
	}

	private Expression expression() throws Exception {
		return disjunction();
	}

	// OR
	private Expression disjunction() throws Exception {
		Expression e = conjunction();
		List<Token.TokenType> allowedTypes = Arrays.asList(Token.TokenType.OR);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
			Expression right = disjunction();
			e = new BinopExpr(op, left, right);
		}

		return e;
	}

	// AND
	private Expression conjunction() throws Exception {
		Expression e = equality();
		List<Token.TokenType> allowedTypes = Arrays.asList(Token.TokenType.AND);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
			Expression right = conjunction();
			e = new BinopExpr(op, left, right);
		}

		return e;
	}

	// EQUALS, NOTEQ
	private Expression equality() throws Exception {
		Expression e = comparison();

		List<Token.TokenType> allowedTypes = 
				Arrays.asList(Token.TokenType.EQUALS, Token.TokenType.NOTEQ);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
			Expression right = equality();
			e = new BinopExpr(op, left, right);
		}

		return e;
	}

	// LESS, GREATER, LESSEQ, GREATEREQ
	private Expression comparison() throws Exception {
		Expression e = term();

		List<Token.TokenType> allowedTypes = 
				Arrays.asList(Token.TokenType.GREATER, Token.TokenType.LESS,
				Token.TokenType.GREATEREQ, Token.TokenType.LESSEQ);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
			Expression right = comparison();
			e = new BinopExpr(op, left, right);
		}

		return e;
	}
	
	// PLUS, MINUS
	private Expression term() throws Exception {
		Expression e = factor();

		List<Token.TokenType> allowedTypes = 
				Arrays.asList(Token.TokenType.PLUS, Token.TokenType.MINUS);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
			Expression right = term();
			e = new BinopExpr(op, left, right);
		}

		return e;
	}

	// MULTIPLY, DIVIDE
	private Expression factor() throws Exception {
		Expression e = unary();

		List<Token.TokenType> allowedTypes = 
				Arrays.asList(Token.TokenType.MULTIPLY, Token.TokenType.DIVIDE);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
			Expression right = factor();
			e = new BinopExpr(op, left, right);
		}

		return e;
	}

	// NOT
	private Expression unary() throws Exception {
		if (getCurrentTokenType() == Token.TokenType.NOT) {
			increment();
			Expression e = literal();
			return new NotUnary(e);
		} else {
			return literal();
		}
	}

	// NUMBER, BOOLEAN, STRING
	private Expression literal() throws Exception {
		Token currToken = getCurrentToken();
		Token.TokenType currTokenType = currToken.getType();
		Object literal = currToken.getLiteral();

		if (currTokenType == Token.TokenType.NUMBER) {
			increment();
			return new MyNumber((Integer) literal);
		} else if (currTokenType == Token.TokenType.BOOLEAN) {
			increment();
			return new MyBoolean((Boolean) literal);
		} else if (currTokenType == Token.TokenType.STRING) {
			increment();
			return new MyString((String) literal);
		} else if (currTokenType == Token.TokenType.IDENTIFIER) {
			return identifier();
		} else if (currTokenType == Token.TokenType.LPAREN) {
			increment();
			Expression e = expression();
			consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
			return e;
		} else if (currTokenType == Token.TokenType.PRINT) {
			increment();
			consume(Token.TokenType.LPAREN, "Expecting token of type LPAREN");
			Expression e = expression();
			consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
			return new Print(e);
		} else if (currTokenType == Token.TokenType.RETURN) {
			increment();
			Expression e = expression();
			return new Return(e);
		} else if (currTokenType == Token.TokenType.FUNCTION) {
			return newFunction();
		} else if (currTokenType == Token.TokenType.IF) {
			return ifStatement();
		} else if (currTokenType == Token.TokenType.WHILE) {
			return whileStatement();
		} else if (currTokenType == Token.TokenType.VARTYPE) {
			return assignment();
		} else if (currTokenType == Token.TokenType.CLASS) {
			return classDeclaration();
		} else if (currTokenType == Token.TokenType.NEW) {
			return newObject();
		}
		else {
			return null;
		}
	}

	

	// WHILE
	private Expression whileStatement() throws Exception {
		increment();
		consume(Token.TokenType.LPAREN, "Expecting token of type LPAREN");
		Expression condition = expression();
		System.out.println(condition);
		consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
		consume(Token.TokenType.LBRACKET, "Expecting token of type LBRACKET");
		Program body = statements(new LinkedList<>());
		System.out.println(body);
		consume(Token.TokenType.RBRACKET, "Expecting token of type RBRACKET");

		return new WhileStatement(condition, body);
	}

	// IF, ELIF, ELSE
	private Expression ifStatement() throws Exception {
		increment();
		consume(Token.TokenType.LPAREN, "Expecting token of type LPAREN");
		Expression condition = expression();
		consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
		consume(Token.TokenType.LBRACKET, "Expecting token of type LBRACKET");
		Program tBody = statements(new LinkedList<>());
		consume(Token.TokenType.RBRACKET, "Expecting token of type RBRACKET");
		Program fBody = null;
		if (getCurrentTokenType() == Token.TokenType.ELSE) {
			increment();
			consume(Token.TokenType.LBRACKET, "Expecting token of type LBRACKET");
			fBody = statements(new LinkedList<>());
			consume(Token.TokenType.RBRACKET, "Expecting token of type RBRACKET");
		}
		return new IfElseStatement(condition, tBody, fBody);
	}

	// ASSIGN 
	private Expression assignment() throws Exception {
		String type = (String) getCurrentToken().getLiteral();
		
		boolean newVar = (type.equals("int") 
				|| type.equals("boolean") 
				|| type.equals("String"));
		if (newVar) {
			increment();
		}
		String identifier = (String) getCurrentToken().getLiteral();
		Variable v = new Variable(identifier, type);
		increment();
		if (getCurrentTokenType() == Token.TokenType.ASSIGN) {
			increment();
			Expression e = expression();
			return new Assignment(v, e, !newVar);
		}
		return null;
	}

	// FUNCTION CALL
	public Expression call(String funcName) throws Exception {
		consume(Token.TokenType.LPAREN, "Expecting token of type LPAREN");
		List<Expression> args = new LinkedList<>();
		if (getCurrentTokenType() != Token.TokenType.RPAREN) {
			args.add(expression());
			while (getCurrentTokenType() == Token.TokenType.COMMA) {
				increment();
				args.add(expression());
			}
		}
		consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
		return new FunctionCall(funcName, args);
	}

	// IDENTIFIER	
	public Expression identifier() throws Exception {
		if (getNextToken().getType() == Token.TokenType.ASSIGN) {
			return assignment();
		} else if (getNextToken().getType() == Token.TokenType.LPAREN) {
			String funcName = (String) getCurrentToken().getLiteral();
			increment();
			return call(funcName);
		} else {
			String name = (String) getCurrentToken().getLiteral();
			increment();
			return new Identifier(name);
		}
	}
	
	// FUNCTION DECLARATION
	private Expression newFunction() throws Exception {
		increment();
		String funcName = (String) getCurrentToken().getLiteral();
		increment();
		
		consume(Token.TokenType.LPAREN, "Expecting token of type LPAREN");
		
		List<Variable> args = new LinkedList<>();
		if (getCurrentTokenType() != Token.TokenType.RPAREN) {
			do {
				if (getCurrentTokenType() == Token.TokenType.COMMA) {
					increment();
				}
				String argType = (String) consume(Token.TokenType.VARTYPE, 
						"Expecting token of type VARTYPE");
				String identifier = (String) consume(Token.TokenType.IDENTIFIER, 
						"Expecting token of type IDENTIFIER");
				args.add(new Variable(identifier, argType));
			}
			while (getCurrentTokenType() == Token.TokenType.COMMA);
		}
		consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
		consume(Token.TokenType.LBRACKET, "Expecting token of type LBRACKET");
		Program body = parseTokens();
		consume(Token.TokenType.RBRACKET, "Expecting token of type RBRACKET");
		return new FunctionDeclaration(funcName, body, args);
	}
	
	
	//Class Declaration
	private Expression classDeclaration() throws Exception {
		increment();
		String className = (String) getCurrentToken().getLiteral();
		increment();
		consume(Token.TokenType.LBRACKET, "Expecting token of type LBRACKET");
		List<Variable> attributes = new LinkedList<>();
		while (getCurrentTokenType() != Token.TokenType.FUNCTION) {
			String type = (String) consume(Token.TokenType.VARTYPE, "Expecting Token of type VARTYPE");
			String identifier = (String) consume(Token.TokenType.IDENTIFIER, 
					"Expecting token of type IDENTIFIER");
			attributes.add(new Variable(identifier, type));
			consume(Token.TokenType.SEMICOLON, "Expecting Semicolon after attribute declaration");
		}
		
		List<FunctionDeclaration> methods = new LinkedList<>();

		while (getCurrentTokenType() != Token.TokenType.RBRACKET) {


			FunctionDeclaration f = (FunctionDeclaration) newFunction();

			methods.add(f);
			consume(Token.TokenType.SEMICOLON, "Expecting Semicolon after attribute declaration");

		}
		
		consume(Token.TokenType.RBRACKET, "Expecting Token of type RBRACKET");

		
		return new ClassDeclaration(className, attributes, methods);
	} 	
	
	public Expression newObject() {
		
	}
	

	public Object consume(Token.TokenType type, String errorMessage) throws Exception {
		if (getCurrentTokenType() == type) {
			increment();
			return getPreviousToken().getLiteral();
		} else {
			throw new Exception(errorMessage);
		}
	}

}

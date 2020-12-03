import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A Parser class to create an abstract syntax tree from a list of tokens
 *  *
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
	
	private boolean atEOF() {
		return (getCurrentToken().getType() == Token.TokenType.EOF);
	}

	private void increment() {
		if (!atEOF()) {
			current++;
		}
	}

	private boolean matchesType(List<Token.TokenType> tokenTypes) throws Exception {
		if (atEOF()) {
			return false;
		}
		Token.TokenType currentTokenType = getCurrentToken().getType();
		return (tokenTypes.contains(currentTokenType));
	}

	public Program parseTokens() throws Exception {
		LinkedList<Statement> lst = new LinkedList<Statement>();
		return statements(lst);
	}
	
	private Program statements(LinkedList<Statement> lst) throws Exception {
		lst.add(expression());
		consume(Token.TokenType.SEMICOLON, "Every statement must be followed by a semicolon.");

		while (!atEOF() && !getCurrentToken().getType().equals(Token.TokenType.RBRACKET)){
			statements(lst);
		}
		return new Program(lst);
	}
	

	private Expression expression() throws Exception {
		return disjunction();
	}
	
	
	//Or
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
	
	//And
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

	private Expression equality() throws Exception {
		// EQUALS
		Expression e = comparison();

		List<Token.TokenType> allowedTypes = Arrays.asList(Token.TokenType.EQUALS, Token.TokenType.NOTEQ);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
			Expression right = equality();
			e = new BinopExpr(op, left, right);
		}

		return e;

	}

	private Expression comparison() throws Exception {
		// LESS, GREATER
		Expression e = term();

		List<Token.TokenType> allowedTypes = Arrays.asList(Token.TokenType.GREATER, Token.TokenType.LESS,
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

	private Expression term() throws Exception {
		// PLUS, MINUS
		Expression e = factor();

		List<Token.TokenType> allowedTypes = Arrays.asList(Token.TokenType.PLUS, Token.TokenType.MINUS);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
//			Token op = getPreviousToken();
			Expression right = term();
			e = new BinopExpr(op, left, right);
		}

		return e;

	}

	private Expression factor() throws Exception {
		// MULTIPLY, DIVIDE

		Expression e = unary();

		List<Token.TokenType> allowedTypes = Arrays.asList(Token.TokenType.MULTIPLY, Token.TokenType.DIVIDE);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
			Expression right = factor();
			e = new BinopExpr(op, left, right);
		}

		return e;

	}
	
	private Expression unary() throws Exception {
		// NOT
		if (getCurrentToken().getType() == Token.TokenType.NOT)  {
			increment();
			Expression e = expression();
			return new NotUnary(e);
		}
		else {
			return function();
		}
	}
	
	private Expression function() throws Exception {
		// FUNCTION 
		Expression e = literal();
		
		while (getCurrentToken().getType() == Token.TokenType.LPAREN) {
			increment();
			return call(e);
		}
		
		return e;
	}
	

	private Expression literal() throws Exception {
		// NUMBER, BOOLEAN, STRING

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
			if (getNextToken().getType() == Token.TokenType.ASSIGN) {
				return assignment();
			}
			else {
				increment();
				return new Variable((String) literal);
			}
		} else if (currTokenType == Token.TokenType.LPAREN) {
			increment();
			Expression e = expression();
			if (getCurrentToken().getType() == Token.TokenType.RPAREN) {
				increment();
				return e;
			}
			return null;
		} else if (currTokenType == Token.TokenType.FUNCTION) {
			increment();
			Expression e = expression();
			return new Print(e);
		} else if (currTokenType == Token.TokenType.IF) {
			return ifStatement();
		} else if (currTokenType == Token.TokenType.WHILE) {
			return whileStatement();
		} else if (currTokenType == Token.TokenType.VARTYPE) {
			return assignment();
		} else {
			return null;
		}
		
	}
	
	private Expression whileStatement() throws Exception {
		increment();
		consume(Token.TokenType.LPAREN, "Expecting token of type LPAREN");
		Expression condition = expression();
		consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
		consume(Token.TokenType.LBRACKET, "Expecting token of type LBRACKET");
		Program body = statements(new LinkedList<Statement>());
		consume(Token.TokenType.RBRACKET, "Expecting token of type RBRACKET");

		return new WhileStatement(condition, body);
	}
	
	
	private Expression ifStatement() throws Exception {
		increment();
		consume(Token.TokenType.LPAREN, "Expecting token of type LPAREN");
		Expression condition = expression();
		consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
		consume(Token.TokenType.LBRACKET, "Expecting token of type LBRACKET");
		Program tBody = statements(new LinkedList<Statement>());
		consume(Token.TokenType.RBRACKET, "Expecting token of type RBRACKET");
		Program fBody = null;
		if (getCurrentToken().getType() == Token.TokenType.ELSE) {
			increment();
			consume(Token.TokenType.LBRACKET, "Expecting token of type LBRACKET");
			fBody = statements(new LinkedList<Statement>());
			consume(Token.TokenType.RBRACKET, "Expecting token of type RBRACKET");
		}
		return new IfElseStatement(condition, tBody, fBody);
	}
	
	private Expression assignment() throws Exception {
		String type = (String) getCurrentToken().getLiteral();
		boolean newVar = (type.equals("int") || type.equals("boolean") || type.equals("String"));
		if (newVar) {
			increment();
		}
		String identifier = (String) getCurrentToken().getLiteral();
		increment();
		if (getCurrentToken().getType() == Token.TokenType.ASSIGN) {
			increment();
			Expression e = expression();
			Assignment assignment = new Assignment(type, identifier, e, !newVar);
			return assignment;
		}
		return null;
	}
	
	public Expression call(Expression funcName) throws Exception {
		List<Expression> args = new ArrayList<>();
		if (!(getCurrentToken().getType() == Token.TokenType.RPAREN)) {
			args.add(expression());
			while (getCurrentToken().getType() == Token.TokenType.COMMA) {
				increment();
				args.add(expression());
			}
		}
		consume(Token.TokenType.RPAREN, "Expecting token of type RPAREN");
		return new FunctionCall(funcName, args);
	}
	
	
	
	public void consume(Token.TokenType type, String errorMessage) throws Exception {
		if (getCurrentToken().getType() == type) {
			increment();
		} else {
			throw new Exception(errorMessage);
		}
	}

}

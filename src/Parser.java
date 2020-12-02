import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * A Parser class to create an abstract syntax tree from a list of tokens
 *  *
 */
public class Parser {
	// expression -- equality
	// equality -- comparison (EQUALS) comparison
	// comparison -- term (LESS | GREATER ) term
	// term -- factor (PLUS | SUBTRACT) factor
	// factor -- literal (MULTIPLY | DIVIDE) literal
	// literal -- NUMBER | STRING | BOOLEAN

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

	private boolean atEOF() {
		return (getCurrentToken().getType() == Token.TokenType.EOF);
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
		Token.TokenType currentTokenType = getCurrentToken().getType();
		return (tokenTypes.contains(currentTokenType));
	}

	public Program parseTokens() {
		LinkedList<Statement> lst = new LinkedList<Statement>();
		return statements(lst);
	}
	
	private Program statements(LinkedList<Statement> lst) {
		if (getCurrentToken().getType() == Token.TokenType.VARTYPE) {
			lst.add(assignment());
		} else {
			lst.add(expression());
		}
		
		
		List<Token.TokenType> allowedTypes = Arrays.asList(Token.TokenType.SEMICOLON);
		while (matchesType(allowedTypes)) {
			increment();
			if (!atEOF()) {statements(lst);}
		}
		return new Program(lst);
	}
	
	private Assignment assignment() {
		String type = (String) getCurrentToken().getLiteral();
		boolean newVar = (type == "int" || type == "boolean" || type == "String");
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
	

	private Expression expression() {
		return disjunction();
	}
	
	
	//Or
	private Expression disjunction() {
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
	private Expression conjunction() {
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

	private Expression equality() {
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

	private Expression comparison() {
		// LESS, GREATER
		Expression e = term();

		List<Token.TokenType> allowedTypes = Arrays.asList(Token.TokenType.GREATER, Token.TokenType.LESS,
				Token.TokenType.GREATEREQ, Token.TokenType.LESSEQ);

		while (matchesType(allowedTypes)) {
			increment();
			Expression left = e;
			Token.TokenType op = getPreviousToken().getType();
//            Token op = getPreviousToken();
			Expression right = comparison();
			e = new BinopExpr(op, left, right);
		}

		return e;

	}

	private Expression term() {
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

	private Expression factor() {
		// MULTIPLY, DIVIDE

		Expression e = literal();

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

	private Expression literal() {
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
			increment();
			return new Variable((String) literal);
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
		} else {
			return null;
		}
	}

}

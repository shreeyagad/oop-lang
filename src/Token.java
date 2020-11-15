/**
 * Represents a token for a program
 *
 */
public class Token {
	
	TokenType type;
	String lexeme;
	Object literal;
	int line;
	
	public Token(TokenType type, String lexeme, Object literal, int line) {
		this.type = type;
		this.lexeme = lexeme;
		this.literal = literal;
		this.line = line;
	}
	
	static enum TokenType {
		
		//Arithmetic Operators
		PLUS, MINUS, MULTIPLY, DIVIDE, 
		
		//Assignment
		ASSIGN,
		
		//Comparison
		LESS, GREATER, EQUALS, GREATEREQ, LESSEQ, NOTEQ,
		
		//Boolean Operators
		AND, OR, NOT,
		
		//Literals
		NUMBER, BOOLEAN, STRING, 
		
		
		EOF
	}
	

	public TokenType getType() {
		return type;
	}
	
	public String getLexeme() {
		return lexeme;
	}

	public Object getLiteral() {
		return literal;
	}

	public int getLineNumber() {
		return line;
	}
	
	public String toString() {
		return lexeme;
	}
	
	public boolean equals(Object t2) {
		return (this.lexeme).equals(((Token) t2).lexeme);
	}

}

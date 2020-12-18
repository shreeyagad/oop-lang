/**
 * Represents a token in the program
 * @param type The type of the Token
 * @param lexeme The string representation of the Token in the source code
 * @param literal The primitive value of the Token
 * @param line The line # in the source code where the Token is found
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
		//Unary Operator
		NOT,
		
		//Arithmetic Operators
		PLUS, MINUS, MULTIPLY, DIVIDE, 
		
		//Comparison Operators
		LESS, GREATER, EQUALS, GREATEREQ, LESSEQ, NOTEQ,
		
		//Boolean Operators
		AND, OR,
		
		//Assignment
		ASSIGN,
		
		//Punctuation
		SEMICOLON, COMMA,
		
		//Literals
		NUMBER, BOOLEAN, STRING, NULL,
			
		// Variables
		VARTYPE, IDENTIFIER,
	
		//Parentheses
		LPAREN, RPAREN,
		
		//Brackets
		LBRACKET, RBRACKET,
		
		//Function Declaration
		FUNCTION,

		//Built-in Functions
		PRINT, RETURN,
		
		//If, Else
		IF, ELIF, ELSE,
		
		//Loops
		WHILE, BREAK,
		
		//Class
		CLASS, DOT, NEW, EXTENDS,
		
		//EOF
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

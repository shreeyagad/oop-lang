
import java.util.ArrayList;
import java.util.List;

/**
 * Tokenizer class that creates a list of tokens from a source string
 *
 */
public class Tokenizer {
	private final String source;
	private final ArrayList<Token> tokens;
	private int startOfToken = 0;
	private int current = 0;
	private int line = 1;

	Tokenizer(String source) {
		this.source = source;
		this.tokens = new ArrayList<>();

	}

	public List<Token> tokenize() {

		while (current < source.length()) {
			startOfToken = current;
			try {
			scanToken();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		tokens.add(new Token(Token.TokenType.EOF, "", null, line));
		return tokens;
	}


	private void scanToken() throws Exception {
		char c = nextChar();
		switch (c) {
		//Whitespace
		case ' ':
		case '\t': break;
		case '\n': line++; break;

		//Operators
		case '-': addToken(Token.TokenType.MINUS); break;
		case '+': addToken(Token.TokenType.PLUS); break;
		case '*': addToken(Token.TokenType.MULTIPLY); break;
		case '&': if (peek() == '&') { current++; addToken(Token.TokenType.AND); break; }
		case '|': if (peek() == '|') { current++; addToken(Token.TokenType.OR); break; }
		case '<': 
			if (peek() == '=') { current++; addToken(Token.TokenType.LESSEQ); }
			else addToken(Token.TokenType.LESS);
			break;
		case '>':
			if (peek() == '=') { current++; addToken(Token.TokenType.GREATEREQ); }
			else addToken(Token.TokenType.GREATER);
			break;
		case '=': 
			if (peek() == '=') { current++; addToken(Token.TokenType.EQUALS); }
			else addToken(Token.TokenType.ASSIGN);
			break;
		case '!': 
			if (peek() == '=') { current++; addToken(Token.TokenType.NOTEQ); }
			else addToken(Token.TokenType.NOT);
			break;
			
		//Boolean literals
		case 't':
			scanTrue(); break;
		case 'f':
			scanFalse(); break;
			
		//String

		case '\"': scanString(); break;

		default:
			//Numbers
			if (Character.isDigit(c)) {
				scanNumber();
			}  else {
				throw new Exception("Unrecognized character");
			}
		}

	}

	private char nextChar() {
		current++;
		return source.charAt(current - 1);
	}

	private char peek() {
		return source.charAt(current);
	}
	
	private String peekN(int n) {
		if (current + n >= source.length()) return source.substring(current);
		return source.substring(current, current + n);
	}

	private void addToken(Token.TokenType tokenType) {
		addToken(tokenType, null);
	}


	private void addToken(Token.TokenType type, Object literal) {
		String text = source.substring(startOfToken, current);
		tokens.add(new Token(type, text, literal, line));
	}

	private void scanTrue() {
		if (peekN(3).equals("rue")) {
			current+=3;
			addToken(Token.TokenType.BOOLEAN, true);
		}
	}
	
	private void scanFalse() {
		if (peekN(4).equals("alse")) {
			current+=4;
			addToken(Token.TokenType.BOOLEAN, false);
		}
	}

	private void scanNumber() {
		while ((current < source.length()) && Character.isDigit(peek())) {
			current++;
		}
		addToken(Token.TokenType.NUMBER, Integer.parseInt(source.substring(startOfToken, current)));
	}

	
	private void scanString() {
		
		while ((current < source.length()) && peek() != '\"') {
			current++;
		}
		current++;

		//TODO: Escape sequences
		addToken(Token.TokenType.STRING, source.substring(startOfToken + 1, current-1));
	}
}

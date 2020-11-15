
import java.util.ArrayList;
import java.util.List;


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

	private void addToken(Token.TokenType tokenType) {
		addToken(tokenType, null);
	}


	private void addToken(Token.TokenType type, Object literal) {
		String text = source.substring(startOfToken, current);
		tokens.add(new Token(type, text, literal, line));
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
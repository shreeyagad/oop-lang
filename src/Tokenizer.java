
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

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
	private final HashMap<String, Token.TokenType> keywords;

	Tokenizer(String source) {
		this.source = source;
		this.tokens = new ArrayList<>();
		this.keywords = new HashMap<String, Token.TokenType>();
		keywords.put("false", Token.TokenType.BOOLEAN);
		keywords.put("true", Token.TokenType.BOOLEAN);
		keywords.put("int", Token.TokenType.VARTYPE);
		keywords.put("boolean", Token.TokenType.VARTYPE);
		keywords.put("String", Token.TokenType.VARTYPE);
		keywords.put("print", Token.TokenType.FUNCTION);

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
		
		//
		case ';': addToken(Token.TokenType.SEMICOLON); break;
		
		//Parentheses
		case '(': addToken(Token.TokenType.LPAREN); break;
		case ')': addToken(Token.TokenType.RPAREN); break;
		
		//Brackets
		case '{': addToken(Token.TokenType.LBRACKET); break;
		case '}': addToken(Token.TokenType.RBRACKET); break;
		
		//Operators
		case '-': addToken(Token.TokenType.MINUS); break;
		case '+': addToken(Token.TokenType.PLUS); break;
		case '*': addToken(Token.TokenType.MULTIPLY); break;
		case '/': addToken(Token.TokenType.DIVIDE); break;
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
			
		//String

		case '\"': scanString(); break;

		default:
			//Numbers
			if (Character.isDigit(c)) {
				scanNumber();
			} else if (Character.isLetter(c)) {
				scanKeyword();
			}
			
			else {
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
	
	private boolean validNameChars() {
		Character curr = peek();
		return (Character.isLetterOrDigit(curr) || curr == '_' || curr == '$');
	}


	private void scanKeyword() {
		while ((current < source.length()) && validNameChars()) {
			current++;
		}
		String word = source.substring(startOfToken, current);
		if (keywords.containsKey(word)) {
			switch (keywords.get(word)) {
			case BOOLEAN:
				addToken(Token.TokenType.BOOLEAN, Boolean.parseBoolean(word));
				break;
			case VARTYPE:
				addToken(Token.TokenType.VARTYPE, word);
				break;
			case FUNCTION:
				addToken(Token.TokenType.FUNCTION, word);
				break;
			default:
				System.out.println("Unrecognized token");
			}
		} else {
			addToken(Token.TokenType.IDENTIFIER, word);
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

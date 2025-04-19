/* Author: Hunter Cottrill
 * I acknowledge that my work complies with the academic integrity policy
 */
package unit;

public class Lexer extends model.AbstractLexer {
	// TODO: do NOT redefine superclass fields

	private char[] S;
	private int i;
	
	@Override
	public void initialize(char[] sentence) {
		// TODO: implement this method stub
		S = sentence;
		i = -1;
	}

	@Override
	public void lex() {
		// TODO: implement this method stub
		TOKEN = null;
		LEXEME = null;
		boolean unexpected = true;
		boolean var = true;
		
		if (S == null || i >= S.length)
			return;
		
		do {
			i++;
			if (i >= S.length)
				return;
		} while (S[i] == ' ');
		
		if (S[i] == '=') {
			TOKEN = Token.EQUAL;
			unexpected = false;
		}
		else if (S[i] == ',') {
			TOKEN = Token.COMMA;
			unexpected = false;
		}
		else if (S[i] == '?') {
			TOKEN = Token.QUESTION;
			unexpected = false;
		}
		else if (S[i] == '<') {
			if (i+1 < S.length && S[i+1] == '-') {
				if (i+2 < S.length && S[i+2] == '>') {
					TOKEN = Token.DOUBLE_ARROW;
					unexpected = false;
					i += 2;
				}
				else
					throw new UnexpectedTokenException();
			}
			else
				throw new UnexpectedTokenException();
		}
		else if (S[i] == '-') {
			if (i+1 < S.length && S[i+1] == '>') {
				TOKEN = Token.ARROW;
				unexpected = false;
				i += 1;
			}
			else
				throw new UnexpectedTokenException();
		}
		else if (S[i] == '^') {
			TOKEN = Token.CARET;
			unexpected = false;
		}
		else if (S[i] == '\'') {
			TOKEN = Token.APOSTROPHE;
			unexpected = false;
		}
		else if (S[i] == '(') {
			TOKEN = Token.OPEN_PAREN;
			unexpected = false;
		}
		else if (S[i] == ')') {
			TOKEN = Token.CLOSE_PAREN;
			unexpected = false;
		}
		else if (S[i] == '1') {
			TOKEN = Token.TRUE_LITERAL;
			unexpected = false;
		}
		else if (S[i] == '0') {
			TOKEN = Token.FALSE_LITERAL;
			unexpected = false;
		}
		else if (S[i] == 'l' || S[i] == 'L') {
			if (i+1 < S.length && (S[i+1] == 'e' || S[i+1] == 'E')) {
				if (i+2 < S.length && (S[i+2] == 't' || S[i+2] == 'T')) {
					if (i+3 < S.length) {
						if ((S[i+3] < 'A' || S[i+3] > 'Z') && (S[i+3] < 'a' || S[i+3] > 'z')) {
							TOKEN = Token.LET_KEYWORD;
							i += 2;
							unexpected = false;
							var = false;
						}
					}
					else {
						TOKEN = Token.LET_KEYWORD;
						unexpected = false;
						var = false;
					}
				}
			}
		}
		else if (S[i] == 'e' || S[i] == 'E') {
			if (i+1 < S.length && (S[i+1] == 'v' || S[i+1] == 'V')) {
				if (i+2 < S.length && (S[i+2] == 'a' || S[i+2] == 'A')) {
					if (i+3 < S.length && (S[i+3] == 'l' || S[i+3] == 'L')) {
						if (i+4 < S.length) {
							if ((S[i+4] < 'A' || S[i+4] > 'Z') && (S[i+4] < 'a' || S[i+4] > 'z')) {
								TOKEN = Token.EVAL_KEYWORD;
								i += 3;
								unexpected = false;
								var = false;

							}
						}
						else {
							TOKEN = Token.EVAL_KEYWORD;
							unexpected = false;
							var = false;
						}
					}
				}
			}
		}
		else if (S[i] == 'v' || S[i] == 'V') {
			if (i+1 < S.length) {
				if ((S[i+1] < 'A' || S[i+1] > 'Z') && (S[i+1] < 'a' || S[i+1] > 'z')) {
					TOKEN = Token.VEE;
					unexpected = false;
					var = false;
				}
			}
			else {
				TOKEN = Token.VEE;
				unexpected = false;
				var = false;
			}
		}
		if (var == true && ((S[i] >= 'A' && S[i] <= 'Z') || (S[i] >= 'a' && S[i] <= 'z'))) {
			int length = 1;

			while (
					i+length < S.length &&
					((S[i+length] >= 'A' && S[i+length] <= 'Z') ||
					(S[i+length] >= 'a' && S[i+length] <= 'z'))
			) length++;

			TOKEN = Token.VARIABLE_NAME;
			unexpected = false;
			LEXEME = new char[length];
			for (int j = 0; j < length; j++)
				LEXEME[j] = S[i+j];


			i += length - 1;
		}
		if (unexpected == true)
			throw new UnexpectedTokenException();

		return;
	}
}

/* Author: Hunter Cottrill
 * I acknowledge that my work complies with the academic integrity policy
 */
package unit;

import model.AbstractLexer.Token;

public class Parser extends model.AbstractParser {
	// TODO: do NOT redefine superclass fields

	Lexer lexer = new Lexer();
	char[][] lookupVar;
	boolean[] lookupBool;
	int count;
	
	@Override
	public boolean interpret(char[] sentence) {
		// TODO: implement this method stub
		lexer.initialize(sentence);
		lexer.lex();
		boolean value = program();
		return value;
	}

	@Override
	public boolean accept(Token token) {
		// TODO: implement this method stub
		if (lexer.TOKEN == token) {
			lexer.lex();
			return true;
		}
		else return false;
	}

	@Override
	public boolean peek(Token token) {
		// TODO: implement this method stub (optional)
		if (lexer.TOKEN == token) {
			return true;
		}
		else return false;
	}

	@Override
	public void expect(Token token) {
		// TODO: implement this method stub
		if (lexer.TOKEN == token) {
			lexer.lex();
		}
		else throw new RuntimeException();
	}
	
	public boolean equal(char[] a, char[] b) {
		if (a.length == b.length) {
			for (int i = 0; i < a.length; i++) {
				if (a[i] != b[i])
					return false;
			}
			return true;
		}
		return false;
	}
	
	private boolean program() {
//		trace_open("Program");
		lookupVar = new char[8][];
		lookupBool = new boolean[8];
		boolean value;
		
		while (accept(Lexer.Token.LET_KEYWORD)) {
			assignment();
		}
		if (accept(Lexer.Token.EVAL_KEYWORD)) {
			value = evaluation();
			expect(Lexer.Token.QUESTION);
		}
		else throw new InputException();
//		trace_close("Program", value);
		return value;
	}
	
	private void assignment() {
		char[] var = variable();
		count = 0;
		if (lookupVar[7] != null)
			throw new VariableException();
		for (int i = 0; i < lookupVar.length; i++) {
			if (lookupVar[i] != null) {
				if (equal(lookupVar[i],var)) {
					throw new VariableException();
				}
				count++;
			}
		}
		lookupVar[count] = new char[var.length];
		for (int j = 0; j < var.length; j++)
			lookupVar[count][j] = var[j];
		expect(Lexer.Token.EQUAL);
		lookupBool[count] = equivalence();
		expect(Lexer.Token.COMMA);
	}
	
	private boolean evaluation() {
//		trace_open("Evaluation");
		return equivalence();
	}
	
	private boolean equivalence() {
//		trace_open("Equivalence");
		boolean value = implication();
		while (accept(Lexer.Token.DOUBLE_ARROW)) {
			boolean value2 = implication();
			if (value == value2)
				value = true;
			else
				value = false;
		}
//		trace_close("Equivalence",value);
		return value;
	}
	
	private boolean implication() {
//		trace_open("Implication");
		boolean value = disjunction();
		if (accept(Lexer.Token.ARROW)) {
			boolean value2 = implication();
			if (value)
				return value2;
			else
				return true;
		}
//		trace_close("Implication",value);
		return value;
	}
	
	private boolean disjunction() {
//		trace_open("Disjunction");
		boolean value = conjunction();
		while (accept(Lexer.Token.VEE)) {
			boolean value2 = conjunction();
			if (value || value2)
				value = true;
			else
				value = false;
		}
//		trace_close("Disjunction",value);
		return value;
	}
	
	private boolean conjunction() {
//		trace_open("Conjunction");
		boolean value = negation();
		while (accept(Lexer.Token.CARET)) {
			boolean value2 = negation();
			if (value && value2)
				value = true;
			else
				value = false;
		}
//		trace_close("Conjunction",value);
		return value;
	}
	
	private boolean negation() {
//		trace_open("Negation");
		boolean value = expression();
		if (accept(Lexer.Token.APOSTROPHE)) {
			value = !value;
		}
//		trace_close("Negation",value);
		return value;
	}
	
	private boolean expression() {
//		trace_open("Expression");
		boolean value;
		if (accept(Lexer.Token.OPEN_PAREN)) {
			value = equivalence();
			expect(Lexer.Token.CLOSE_PAREN);
		}
		else value = bool();
//		trace_close("Expression",value);
		return value;
	}
	
	private boolean bool() {
//		trace_open("Boolean");
		if (accept(Lexer.Token.TRUE_LITERAL)) {
//			trace_close("Boolean",true);
			return true;
		}
		else if (accept(Lexer.Token.FALSE_LITERAL)) {
//			trace_close("Boolean",false);
			return false;
		}
		else {
			for (int i = 0; i < lookupVar.length; i++) {
				if (lookupVar[i] != null) {
					if (equal(lookupVar[i],lexer.LEXEME)) {
						variable();
//						trace_close("Boolean",lookupBool[i]);
						return lookupBool[i];
					}
				}
			}
			throw new VariableException();
		}
	}
	
	private char[] variable() {
//		trace_open("Variable");
		char[] lexeme = lexer.LEXEME;
		if (peek(Lexer.Token.VARIABLE_NAME))
			expect(Lexer.Token.VARIABLE_NAME);
//		trace_close("Variable",lexeme[0]);
		return lexeme;
	}
}
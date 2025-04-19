# CS 310 Fall 2021 Units 1 & 2 Lexer & Parser 
This document covers two cumulative units. Complete each unit by its respective due date listed on the course calendar. 
Follow the instructions using Java code and documentation. Submit the indicated Java source files and PDF documents (📎) via the 
assignment in the eCampus lecture section by the due date on the course calendar. For each source file, include a comment with 
your full name as author and a statement acknowledging that your work complies with the academic integrity policy. 
# UNIT 1 | LEXER 
Topics: Finite state automata (FSA), lexical analysis (lexer), tokens, lexemes 
  FINITE STATE AUTOMATON 
  A Diagram a finite state automaton which tokenizes the terminals of the grammar in this document (see the appendix). 
  B Illustrate the diagram using professional software and publish it as a 📎 PDF document. 
  C Label each final state with the corresponding token. 
  D Do not include pseudocode, regular expressions, or more than one diagram or try to diagram syntax rules. 
  LEXER 
  E Implement a lexer equivalent to your FSA (revising each as necessary to maintain that equivalence). 
  F Code the class 📎 unit.Lexer as Java source. 
  G Extend the provided abstract class model.AbstractLexer and comply with its JavaDoc comments. 
  H Pass the provided unit tests in grade.LexerTests (each test is weighted equally). 
# UNIT 2 | PARSER 
Topics: Railroad diagrams (syntax diagrams), LL(1) parsing, recursive descent parsing, precedence, associativity 
  RAILROAD DIAGRAMS 
  A Diagram a set of railroad diagrams equivalent to the grammar in this document (see the appendix). 
  B Illustrate the diagrams using professional software and publish them as a 📎 PDF document. 
  C Each production is a separate diagram, but all diagrams are in one document. 
  PARSER 
  D Implement an LL(1) recursive descent parser equivalent to your railroad diagram (revising each as necessary). 
  E Code the class 📎 unit.Parser using Java source. 
  F Extend the provided abstract class model.AbstractParser and comply with its JavaDoc comments. 
  G Left associativity must be implemented with iteration. 
  H Right associativity must be implemented with recursion. 
  I Utilize your previously implemented 📎 unit.Lexer for lexical analysis (revised if necessary). 
  J Pass the provided unit tests in grade.ParserTests (each test is weighted equally). 
  CODE RESTRICTIONS 
  The following code restrictions apply to both units. 
  A Do not modify any code in the grade or model packages. 
  B Only these types and their API are permitted in your code. 
  1 Primitive variables of types int, boolean, and char, enum types, null references, and arrays thereof up to 2 dimensions. 
  2 Subclasses of RuntimeException required by this document or the Javadoc comments. 
  3 The types provided in the grade and model packages. 
  4 Any original types you implement in the unit package. 
  C By omission from the permitted types above, these types and their API are forbidden in your code. 
  1 Non-primitive objects of types Integer, Boolean, and Character. 
  2 Strings, except when passed as parameters to exception constructors or to tracing framework methods. 
  3 Any unoriginal recognizers, such as scanners and regular expressions. 
  rev September 21 – page 1 / 2 
  4 Any unoriginal abstract data types, such as lists, sets, and maps. 
  APPENDIX 1 | GRAMMAR 
  This EBNF grammar with informal semantics defines a propositional logic with variable assignments language. 
  A Terminal symbols are in blue text and are tokenized by the lexer. 
  B Metasymbols are in black text. 
  C Whitespace (space, tab, new line, and carriage return) is not a lexeme and can only appear between lexemes, not within. 
  D The keywords let and eval and the operator v are case insensitive reserved words. 
  E Variable names are case sensitive and must not conflict with any reserved words. 
  Syntax 
  Semantics 
  <program> → 
  { <assignment> }* <evaluation> 
  Initializes a data structure called the lookup table which 
  associates variable names with boolean values for the 
  lifetime of the program, up to a limit of 8 variables 
  Executes each given <assignment> 
  Returns the boolean result of <evaluation>, or throws an 
  InputException if the program is syntactically invalid 
  <assignment> → let <variable> = <equivalence> , 
  Associates the new variable name <variable> with the 
  boolean value <equivalence> in the lookup table, or throws 
  a VariableException if the variable name already exists or 
  if the lookup table is already at its limit of variables 
  Returns a void 
  <evaluation> → 
  eval <equivalence> ? 
  Returns the boolean result of <equivalence>  
  <equivalence> → <implication> { <-> <implication> }* Returns the boolean left-associative logical equivalence of 
  <implication>1 through <implication>n 
  <implication> → 
  <disjunction> { -> <disjunction> }*  
  Returns the boolean right-associative logical implication of 
  <disjunction>1 through <disjunction>n 
  <disjunction> → <conjunction> { v <conjunction> }* 
  Returns the boolean left-associative logical disjunction of 
  <conjunction>1 through <conjunction>n 
  <conjunction> → 
  <negation> { ^ <negation> }* 
  Returns the boolean left-associative logical conjunction of 
  <negation>1 through <negation>n 
  <negation> 
  → <expression> [ ' ] 
  Returns the boolean value of <expression> or, if the 
  optional terminal is given, the logical negation thereof 
  <expression> → 
  ( <equivalence> ) | <boolean> 
  Returns the boolean value of the given <equivalence> or of 
  the given <boolean> 
  <boolean> 
  → 1 | 0 | <variable> 
  Returns the boolean value true if the literal 1 is given or 
  false if the literal 0 is given 
  Otherwise, returns the boolean value associated with the 
  existing <variable> name in the lookup table, or throws a 
  VariableException if the name does not exist 
  <variable> → 
  { A | B | … | Z | a | b | … | z }+ 
  Returns the given variable name

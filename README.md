# CS 310 Fall 2021 Units 1 & 2 Lexer & Parser 
This document covers two cumulative units. Complete each unit by its respective due date listed on the course calendar. 
Follow the instructions using Java code and documentation. Submit the indicated Java source files and PDF documents (📎) via the 
assignment in the eCampus lecture section by the due date on the course calendar. For each source file, include a comment with 
your full name as author and a statement acknowledging that your work complies with the academic integrity policy. 
# UNIT 1 | LEXER 
Topics: Finite state automata (FSA), lexical analysis (lexer), tokens, lexemes <br/>
  FINITE STATE AUTOMATON <br/>
  A Diagram a finite state automaton which tokenizes the terminals of the grammar in this document (see the appendix). <br/>
  B Illustrate the diagram using professional software and publish it as a 📎 PDF document. <br/>
  C Label each final state with the corresponding token. <br/>
  D Do not include pseudocode, regular expressions, or more than one diagram or try to diagram syntax rules. <br/>
  LEXER <br/>
  E Implement a lexer equivalent to your FSA (revising each as necessary to maintain that equivalence). <br/>
  F Code the class 📎 unit.Lexer as Java source. <br/>
  G Extend the provided abstract class model.AbstractLexer and comply with its JavaDoc comments. <br/>
  H Pass the provided unit tests in grade.LexerTests (each test is weighted equally). <br/>
# UNIT 2 | PARSER <br/>
Topics: Railroad diagrams (syntax diagrams), LL(1) parsing, recursive descent parsing, precedence, associativity <br/>
  RAILROAD DIAGRAMS <br/>
  A Diagram a set of railroad diagrams equivalent to the grammar in this document (see the appendix). <br/>
  B Illustrate the diagrams using professional software and publish them as a 📎 PDF document. <br/>
  C Each production is a separate diagram, but all diagrams are in one document. <br/>
  PARSER <br/>
  D Implement an LL(1) recursive descent parser equivalent to your railroad diagram (revising each as necessary). <br/>
  E Code the class 📎 unit.Parser using Java source. <br/>
  F Extend the provided abstract class model.AbstractParser and comply with its JavaDoc comments. <br/>
  G Left associativity must be implemented with iteration. <br/>
  H Right associativity must be implemented with recursion. <br/>
  I Utilize your previously implemented 📎 unit.Lexer for lexical analysis (revised if necessary). <br/>
  J Pass the provided unit tests in grade.ParserTests (each test is weighted equally). <br/>
  CODE RESTRICTIONS <br/>
  The following code restrictions apply to both units. <br/>
  A Do not modify any code in the grade or model packages. <br/>
  B Only these types and their API are permitted in your code. <br/>
  1 Primitive variables of types int, boolean, and char, enum types, null references, and arrays thereof up to 2 dimensions. <br/>
  2 Subclasses of RuntimeException required by this document or the Javadoc comments. <br/>
  3 The types provided in the grade and model packages. <br/>
  4 Any original types you implement in the unit package. <br/>
  C By omission from the permitted types above, these types and their API are forbidden in your code. <br/>
  1 Non-primitive objects of types Integer, Boolean, and Character. <br/>
  2 Strings, except when passed as parameters to exception constructors or to tracing framework methods. <br/>
  3 Any unoriginal recognizers, such as scanners and regular expressions. <br/>
  
  4 Any unoriginal abstract data types, such as lists, sets, and maps. <br/>
  APPENDIX 1 | GRAMMAR <br/>
  This EBNF grammar with informal semantics defines a propositional logic with variable assignments language. <br/>
  A Terminal symbols are in blue text and are tokenized by the lexer. <br/>
  B Metasymbols are in black text. <br/>
  C Whitespace (space, tab, new line, and carriage return) is not a lexeme and can only appear between lexemes, not within. <br/>
  D The keywords let and eval and the operator v are case insensitive reserved words. <br/>
  E Variable names are case sensitive and must not conflict with any reserved words. <br/>
  Syntax <br/>
  Semantics <br/>
  <program> → <br/>
  { <assignment> }* <evaluation> <br/>
  Initializes a data structure called the lookup table which <br/>
  associates variable names with boolean values for the <br/>
  lifetime of the program, up to a limit of 8 variables <br/>
  Executes each given <assignment> <br/>
  Returns the boolean result of <evaluation>, or throws an <br/>
  InputException if the program is syntactically invalid <br/>
  <assignment> → let <variable> = <equivalence> , <br/>
  Associates the new variable name <variable> with the <br/>
  boolean value <equivalence> in the lookup table, or throws<br/> 
  a VariableException if the variable name already exists or <br/>
  if the lookup table is already at its limit of variables <br/>
  Returns a void <br/>
  <evaluation> → <br/>
  eval <equivalence> ? <br/>
  Returns the boolean result of <equivalence>  <br/>
  <equivalence> → <implication> { <-> <implication> }* Returns the boolean left-associative logical equivalence of <br/>
  <implication>1 through <implication>n <br/>
  <implication> → <br/>
  <disjunction> { -> <disjunction> }*  <br/>
  Returns the boolean right-associative logical implication of <br/>
  <disjunction>1 through <disjunction>n <br/>
  <disjunction> → <conjunction> { v <conjunction> }* <br/>
  Returns the boolean left-associative logical disjunction of <br/>
  <conjunction>1 through <conjunction>n <br/>
  <conjunction> → <br/>
  <negation> { ^ <negation> }* <br/>
  Returns the boolean left-associative logical conjunction of <br/>
  <negation>1 through <negation>n <br/>
  <negation> <br/>
  → <expression> [ ' ] <br/>
  Returns the boolean value of <expression> or, if the <br/>
  optional terminal is given, the logical negation thereof <br/>
  <expression> → <br/>
  ( <equivalence> ) | <boolean> <br/>
  Returns the boolean value of the given <equivalence> or of <br/>
  the given <boolean> <br/>
  <boolean> <br/>
  → 1 | 0 | <variable> <br/>
  Returns the boolean value true if the literal 1 is given or <br/>
  false if the literal 0 is given <br/>
  Otherwise, returns the boolean value associated with the <br/>
  existing <variable> name in the lookup table, or throws a <br/>
  VariableException if the name does not exist <br/>
  <variable> → <br/>
  { A | B | … | Z | a | b | … | z }+ <br/>
  Returns the given variable name<br/>

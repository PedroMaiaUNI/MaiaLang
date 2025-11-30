import java_cup.runtime.*;

%%

%class scanner
%unicode
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

// REGEX

WHITESPACE = [ \t\n\r]+
LINE_COMMENT = "//" [^\n]*
BLOCK_COMMENT = "/*" [^*]* ("*"+ [^/*][^*]*)* "*"+"/" 
LETTER = [a-zA-Z]
DIGIT = [0-9]
UNDERSCORE = [_]
FLOAT_SUFFIX = ([Ee] [+-]? {DIGIT}+)
INTEGER = {DIGIT}+
FLOAT = {DIGIT}+ "." {DIGIT}+ {FLOAT_SUFFIX}? | {DIGIT}+ {FLOAT_SUFFIX}

%%

<YYINITIAL> {
    {WHITESPACE}    { /* ignore */ }
    {LINE_COMMENT}  { /* ignore */ }
    {BLOCK_COMMENT} { /* ignore */ }
    
    // KEYWORDS
    "if"            { return symbol(sym.IF); }
    "else"          { return symbol(sym.ELSE); }
    "while"         { return symbol(sym.WHILE); }
    "return"        { return symbol(sym.RETURN); }
    "int"           { return symbol(sym.INT); }
    "float"         { return symbol(sym.FLOAT); }
    "void"          { return symbol(sym.VOID); }

    // OPERADORES
    ";"             { return symbol(sym.SEMI); }
    ","             { return symbol(sym.COMMA); }
    "{"             { return symbol(sym.LBRACE); }
    "}"             { return symbol(sym.RBRACE); }
    "("             { return symbol(sym.LPAREN); }
    ")"             { return symbol(sym.RPAREN); }
    "="             { return symbol(sym.ASSIGN); }

    // COMPARADORES
    "=="            { return symbol(sym.EQ); }
    "!="            { return symbol(sym.NEQ); }
    "<="            { return symbol(sym.LE); }
    ">="            { return symbol(sym.GE); }
    "<"             { return symbol(sym.LT); }
    ">"             { return symbol(sym.GT); }

    // ARITMETICA
    "+"             { return symbol(sym.PLUS); }
    "-"             { return symbol(sym.MINUS); }
    "*"             { return symbol(sym.TIMES); }
    "/"             { return symbol(sym.DIVIDE); }

    // TIPOS
    {FLOAT}         { return symbol(sym.FLOAT_CONST, Float.parseFloat(yytext())); }
    {INTEGER}       { return symbol(sym.INT_CONST, Integer.parseInt(yytext())); }

    // IDS
    {LETTER} ({LETTER}|{DIGIT}|{UNDERSCORE})* { return symbol(sym.ID, yytext()); }

    // Erro
    .               { System.err.println("Caractere Ilegal: " + yytext() + " na linha " + (yyline+1) + ", coluna " + (yycolumn+1)); }
}
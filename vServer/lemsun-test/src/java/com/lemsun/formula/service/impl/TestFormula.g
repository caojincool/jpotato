grammar TestFormula;


value
	:	IDENTIFIER
	|	STRING_LITERAL
	|	Number
	|	CODE
	;


Remote 	: '!'
	;

Local	: '#'
	;


LP	:	'('
	;

RP	:	')'
	;


CA	:	','
	;

AS	:	('A' 'a')('S' 's')
	;

IDENTIFIER
	:	(LETTER)(LETTER|'0'..'9')*
	|	'*'
	;


Operator
	:	'='
	|	'>'
	|	'<'
	|	'>='
	|	'<='
	|	'!='
	|	'<>'
	;

Condition
	:	'&'
	|	'and'
	|	'|'
	|	'or'
	|	'!'
	|	'not'
	;



STRING_LITERAL
	:  '"' ( ~('\\'|'"') )* '"'
	;


Number
	: Digit+ ('.' Digit+)
	;

CODE
	: (LETTER | Digit)+ ('.' (LETTER | Digit)+)*
	;


WS  :  (' '|'\r'|'\t'|'\u000C'|'\n'|';') { skip(); }
    ;


fragment EscapeSequence
	:   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
	|   OctalEscape
	;

fragment OctalEscape
	:   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
	|   '\\' ('0'..'7') ('0'..'7')
	|   '\\' ('0'..'7')
	;

fragment LETTER
	:	'$'
	|	'A'..'Z'
	|	'a'..'z'
	|	'_'
	|	CHINESECHAR
	;


fragment CHINESECHAR
	:	'\u4E00'..'\u9FA5' | '\uF900'..'\uFA2D'
	;

fragment Digit
	: '0'..'9'
	;

fragment HexDigit
	: ('0'..'9'|'a'..'f'|'A'..'F')
	;

fragment UnicodeEscape
	:   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    	;
package com.lemsun.client.formula.parser;// $ANTLR 3.5.1 E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g 2014-02-15 10:52:07

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ServerFormulaLexer extends Lexer {
	public static final int EOF=-1;
	public static final int ALIAS=4;
	public static final int ARRAYVALUE=5;
	public static final int AS=6;
	public static final int ASC=7;
	public static final int CA=8;
	public static final int CHINESECHAR=9;
	public static final int CODE=10;
	public static final int COL=11;
	public static final int COLRANGE=12;
	public static final int COLS=13;
	public static final int COMMENT=14;
	public static final int Condition=15;
	public static final int DESC=16;
	public static final int Digit=17;
	public static final int EXPRE=18;
	public static final int EXPRES=19;
	public static final int Eq=20;
	public static final int FORMULA=21;
	public static final int FUN=22;
	public static final int Gp=23;
	public static final int GpEq=24;
	public static final int IDENTIFIER=25;
	public static final int JOIN=26;
	public static final int JoinFull=27;
	public static final int JoinRight=28;
	public static final int LC=29;
	public static final int LETTER=30;
	public static final int LP=31;
	public static final int Like=32;
	public static final int Local=33;
	public static final int Ls=34;
	public static final int LsEq=35;
	public static final int NAME=36;
	public static final int NewLine=37;
	public static final int Not=38;
	public static final int NotLike=39;
	public static final int NotOin=40;
	public static final int Number=41;
	public static final int OIn=42;
	public static final int OPERATOR=43;
	public static final int Point=44;
	public static final int RC=45;
	public static final int REF=46;
	public static final int REFS=47;
	public static final int RP=48;
	public static final int Ref=49;
	public static final int Remote=50;
	public static final int SOFT=51;
	public static final int STATEMENT=52;
	public static final int STATEMENTS=53;
	public static final int STRING_LITERAL=54;
	public static final int Start=55;
	public static final int VALUE=56;
	public static final int WS=57;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public ServerFormulaLexer() {} 
	public ServerFormulaLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public ServerFormulaLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g"; }

	// $ANTLR start "Point"
	public final void mPoint() throws RecognitionException {
		try {
			int _type = Point;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:149:7: ( '\\.' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:149:9: '\\.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Point"

	// $ANTLR start "AS"
	public final void mAS() throws RecognitionException {
		try {
			int _type = AS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:152:4: ( '->' | ( 'A' | 'a' ) ( 'S' | 's' ) )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0=='-') ) {
				alt1=1;
			}
			else if ( (LA1_0=='A'||LA1_0=='a') ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:152:7: '->'
					{
					match("->"); 

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:153:4: ( 'A' | 'a' ) ( 'S' | 's' )
					{
					if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AS"

	// $ANTLR start "Remote"
	public final void mRemote() throws RecognitionException {
		try {
			int _type = Remote;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:156:9: ( '!' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:156:11: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Remote"

	// $ANTLR start "Local"
	public final void mLocal() throws RecognitionException {
		try {
			int _type = Local;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:159:7: ( '#' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:159:9: '#'
			{
			match('#'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Local"

	// $ANTLR start "Ref"
	public final void mRef() throws RecognitionException {
		try {
			int _type = Ref;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:162:5: ( '@' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:162:7: '@'
			{
			match('@'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Ref"

	// $ANTLR start "Start"
	public final void mStart() throws RecognitionException {
		try {
			int _type = Start;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:167:2: ( '*' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:167:4: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Start"

	// $ANTLR start "LC"
	public final void mLC() throws RecognitionException {
		try {
			int _type = LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:171:6: ( '[' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:171:10: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC"

	// $ANTLR start "RC"
	public final void mRC() throws RecognitionException {
		try {
			int _type = RC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:174:6: ( ']' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:174:10: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RC"

	// $ANTLR start "LP"
	public final void mLP() throws RecognitionException {
		try {
			int _type = LP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:177:4: ( '(' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:177:6: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LP"

	// $ANTLR start "RP"
	public final void mRP() throws RecognitionException {
		try {
			int _type = RP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:180:4: ( ')' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:180:6: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RP"

	// $ANTLR start "CA"
	public final void mCA() throws RecognitionException {
		try {
			int _type = CA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:184:4: ( ',' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:184:6: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CA"

	// $ANTLR start "ASC"
	public final void mASC() throws RecognitionException {
		try {
			int _type = ASC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:188:2: ( '-' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:188:5: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASC"

	// $ANTLR start "DESC"
	public final void mDESC() throws RecognitionException {
		try {
			int _type = DESC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:192:2: ( '+' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:192:4: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DESC"

	// $ANTLR start "JoinFull"
	public final void mJoinFull() throws RecognitionException {
		try {
			int _type = JoinFull;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:196:2: ( '<=>' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:196:4: '<=>'
			{
			match("<=>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "JoinFull"

	// $ANTLR start "JoinRight"
	public final void mJoinRight() throws RecognitionException {
		try {
			int _type = JoinRight;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:200:2: ( '=>' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:200:4: '=>'
			{
			match("=>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "JoinRight"

	// $ANTLR start "GpEq"
	public final void mGpEq() throws RecognitionException {
		try {
			int _type = GpEq;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:205:2: ( '>=' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:205:4: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GpEq"

	// $ANTLR start "LsEq"
	public final void mLsEq() throws RecognitionException {
		try {
			int _type = LsEq;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:209:2: ( '<=' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:209:4: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LsEq"

	// $ANTLR start "Not"
	public final void mNot() throws RecognitionException {
		try {
			int _type = Not;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:213:2: ( '!=' | '<>' )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='!') ) {
				alt2=1;
			}
			else if ( (LA2_0=='<') ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:213:4: '!='
					{
					match("!="); 

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:214:4: '<>'
					{
					match("<>"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Not"

	// $ANTLR start "Gp"
	public final void mGp() throws RecognitionException {
		try {
			int _type = Gp;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:217:2: ( '>' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:217:4: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Gp"

	// $ANTLR start "Ls"
	public final void mLs() throws RecognitionException {
		try {
			int _type = Ls;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:220:2: ( '<' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:220:4: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Ls"

	// $ANTLR start "Eq"
	public final void mEq() throws RecognitionException {
		try {
			int _type = Eq;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:224:2: ( '=' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:224:4: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Eq"

	// $ANTLR start "Like"
	public final void mLike() throws RecognitionException {
		try {
			int _type = Like;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:228:2: ( '~' | ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0=='~') ) {
				alt3=1;
			}
			else if ( (LA3_0=='L'||LA3_0=='l') ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:228:4: '~'
					{
					match('~'); 
					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:229:4: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
					{
					if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Like"

	// $ANTLR start "NotLike"
	public final void mNotLike() throws RecognitionException {
		try {
			int _type = NotLike;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:233:2: ( '!~' | ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' ) )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='!') ) {
				alt4=1;
			}
			else if ( (LA4_0=='U'||LA4_0=='u') ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:233:4: '!~'
					{
					match("!~"); 

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:234:4: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'K' | 'k' ) ( 'E' | 'e' )
					{
					if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NotLike"

	// $ANTLR start "NewLine"
	public final void mNewLine() throws RecognitionException {
		try {
			int _type = NewLine;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:238:2: ( ';' | '\\n' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:
			{
			if ( input.LA(1)=='\n'||input.LA(1)==';' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NewLine"

	// $ANTLR start "OIn"
	public final void mOIn() throws RecognitionException {
		try {
			int _type = OIn;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:243:2: ( ( 'I' | 'i' ) ( 'N' | 'n' ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:243:4: ( 'I' | 'i' ) ( 'N' | 'n' )
			{
			if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OIn"

	// $ANTLR start "NotOin"
	public final void mNotOin() throws RecognitionException {
		try {
			int _type = NotOin;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:247:2: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:247:4: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'N' | 'n' )
			{
			if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NotOin"

	// $ANTLR start "Condition"
	public final void mCondition() throws RecognitionException {
		try {
			int _type = Condition;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:251:2: ( '&' | ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' ) | '|' | ( 'O' | 'o' ) ( 'R' | 'r' ) | '!' | ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
			int alt5=6;
			switch ( input.LA(1) ) {
			case '&':
				{
				alt5=1;
				}
				break;
			case 'A':
			case 'a':
				{
				alt5=2;
				}
				break;
			case '|':
				{
				alt5=3;
				}
				break;
			case 'O':
			case 'o':
				{
				alt5=4;
				}
				break;
			case '!':
				{
				alt5=5;
				}
				break;
			case 'N':
			case 'n':
				{
				alt5=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:251:4: '&'
					{
					match('&'); 
					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:252:4: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'D' | 'd' )
					{
					if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 3 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:253:4: '|'
					{
					match('|'); 
					}
					break;
				case 4 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:254:4: ( 'O' | 'o' ) ( 'R' | 'r' )
					{
					if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 5 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:255:4: '!'
					{
					match('!'); 
					}
					break;
				case 6 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:256:4: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' )
					{
					if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Condition"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			int _type = STRING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:261:2: ( '\"' (~ ( '\\\\' | '\"' ) )* '\"' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:261:5: '\"' (~ ( '\\\\' | '\"' ) )* '\"'
			{
			match('\"'); 
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:261:9: (~ ( '\\\\' | '\"' ) )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '\u0000' && LA6_0 <= '!')||(LA6_0 >= '#' && LA6_0 <= '[')||(LA6_0 >= ']' && LA6_0 <= '\uFFFF')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop6;
				}
			}

			match('\"'); 

					setText(getText().substring(1, getText().length() -1));
				
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "Number"
	public final void mNumber() throws RecognitionException {
		try {
			int _type = Number;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:269:2: ( ( Digit )+ )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:269:4: ( Digit )+
			{
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:269:4: ( Digit )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Number"

	// $ANTLR start "IDENTIFIER"
	public final void mIDENTIFIER() throws RecognitionException {
		try {
			int _type = IDENTIFIER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:273:2: ( ( LETTER ) ( LETTER | Digit )* )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:273:4: ( LETTER ) ( LETTER | Digit )*
			{
			if ( (input.LA(1) >= '$' && input.LA(1) <= '%')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:273:12: ( LETTER | Digit )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( ((LA8_0 >= '$' && LA8_0 <= '%')||(LA8_0 >= '0' && LA8_0 <= '9')||(LA8_0 >= 'A' && LA8_0 <= 'Z')||LA8_0=='_'||(LA8_0 >= 'a' && LA8_0 <= 'z')||(LA8_0 >= '\u4E00' && LA8_0 <= '\u9FA5')||(LA8_0 >= '\uF900' && LA8_0 <= '\uFA2D')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:
					{
					if ( (input.LA(1) >= '$' && input.LA(1) <= '%')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop8;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENTIFIER"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:279:2: ( ( ' ' | '\\r' | '\\t' | '\\u000C' ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:279:5: ( ' ' | '\\r' | '\\t' | '\\u000C' )
			{
			if ( input.LA(1)=='\t'||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:283:5: ( '/**' ( options {greedy=false; } : . )* '**/' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:283:9: '/**' ( options {greedy=false; } : . )* '**/'
			{
			match("/**"); 

			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:283:15: ( options {greedy=false; } : . )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0=='*') ) {
					int LA9_1 = input.LA(2);
					if ( (LA9_1=='*') ) {
						int LA9_3 = input.LA(3);
						if ( (LA9_3=='/') ) {
							alt9=2;
						}
						else if ( ((LA9_3 >= '\u0000' && LA9_3 <= '.')||(LA9_3 >= '0' && LA9_3 <= '\uFFFF')) ) {
							alt9=1;
						}

					}
					else if ( ((LA9_1 >= '\u0000' && LA9_1 <= ')')||(LA9_1 >= '+' && LA9_1 <= '\uFFFF')) ) {
						alt9=1;
					}

				}
				else if ( ((LA9_0 >= '\u0000' && LA9_0 <= ')')||(LA9_0 >= '+' && LA9_0 <= '\uFFFF')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:283:43: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop9;
				}
			}

			match("**/"); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:288:2: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' | '%' | CHINESECHAR )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:
			{
			if ( (input.LA(1) >= '$' && input.LA(1) <= '%')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	// $ANTLR start "CHINESECHAR"
	public final void mCHINESECHAR() throws RecognitionException {
		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:298:2: ( '\\u4E00' .. '\\u9FA5' | '\\uF900' .. '\\uFA2D' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:
			{
			if ( (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CHINESECHAR"

	// $ANTLR start "Digit"
	public final void mDigit() throws RecognitionException {
		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:302:2: ( '0' .. '9' )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Digit"

	@Override
	public void mTokens() throws RecognitionException {
		// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:8: ( Point | AS | Remote | Local | Ref | Start | LC | RC | LP | RP | CA | ASC | DESC | JoinFull | JoinRight | GpEq | LsEq | Not | Gp | Ls | Eq | Like | NotLike | NewLine | OIn | NotOin | Condition | STRING_LITERAL | Number | IDENTIFIER | WS | COMMENT )
		int alt10=32;
		alt10 = dfa10.predict(input);
		switch (alt10) {
			case 1 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:10: Point
				{
				mPoint(); 

				}
				break;
			case 2 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:16: AS
				{
				mAS(); 

				}
				break;
			case 3 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:19: Remote
				{
				mRemote(); 

				}
				break;
			case 4 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:26: Local
				{
				mLocal(); 

				}
				break;
			case 5 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:32: Ref
				{
				mRef(); 

				}
				break;
			case 6 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:36: Start
				{
				mStart(); 

				}
				break;
			case 7 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:42: LC
				{
				mLC(); 

				}
				break;
			case 8 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:45: RC
				{
				mRC(); 

				}
				break;
			case 9 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:48: LP
				{
				mLP(); 

				}
				break;
			case 10 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:51: RP
				{
				mRP(); 

				}
				break;
			case 11 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:54: CA
				{
				mCA(); 

				}
				break;
			case 12 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:57: ASC
				{
				mASC(); 

				}
				break;
			case 13 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:61: DESC
				{
				mDESC(); 

				}
				break;
			case 14 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:66: JoinFull
				{
				mJoinFull(); 

				}
				break;
			case 15 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:75: JoinRight
				{
				mJoinRight(); 

				}
				break;
			case 16 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:85: GpEq
				{
				mGpEq(); 

				}
				break;
			case 17 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:90: LsEq
				{
				mLsEq(); 

				}
				break;
			case 18 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:95: Not
				{
				mNot(); 

				}
				break;
			case 19 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:99: Gp
				{
				mGp(); 

				}
				break;
			case 20 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:102: Ls
				{
				mLs(); 

				}
				break;
			case 21 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:105: Eq
				{
				mEq(); 

				}
				break;
			case 22 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:108: Like
				{
				mLike(); 

				}
				break;
			case 23 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:113: NotLike
				{
				mNotLike(); 

				}
				break;
			case 24 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:121: NewLine
				{
				mNewLine(); 

				}
				break;
			case 25 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:129: OIn
				{
				mOIn(); 

				}
				break;
			case 26 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:133: NotOin
				{
				mNotOin(); 

				}
				break;
			case 27 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:140: Condition
				{
				mCondition(); 

				}
				break;
			case 28 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:150: STRING_LITERAL
				{
				mSTRING_LITERAL(); 

				}
				break;
			case 29 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:165: Number
				{
				mNumber(); 

				}
				break;
			case 30 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:172: IDENTIFIER
				{
				mIDENTIFIER(); 

				}
				break;
			case 31 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:183: WS
				{
				mWS(); 

				}
				break;
			case 32 :
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:1:186: COMMENT
				{
				mCOMMENT(); 

				}
				break;

		}
	}


	protected DFA10 dfa10 = new DFA10(this);
	static final String DFA10_eotS =
		"\2\uffff\1\37\1\33\1\44\11\uffff\1\46\1\50\1\52\1\uffff\2\33\1\uffff\1"+
		"\33\1\uffff\2\33\7\uffff\1\36\1\33\3\uffff\1\62\5\uffff\2\33\1\66\1\26"+
		"\1\33\1\26\2\uffff\3\33\1\uffff\1\26\1\21\1\33\1\74\1\33\1\uffff\1\43";
	static final String DFA10_eofS =
		"\76\uffff";
	static final String DFA10_minS =
		"\1\11\1\uffff\1\76\1\116\1\75\11\uffff\1\75\1\76\1\75\1\uffff\1\111\1"+
		"\116\1\uffff\1\116\1\uffff\1\122\1\117\7\uffff\1\44\1\104\3\uffff\1\76"+
		"\5\uffff\1\113\1\111\2\44\1\124\1\44\2\uffff\1\105\1\111\1\116\1\uffff"+
		"\2\44\1\113\1\44\1\105\1\uffff\1\44";
	static final String DFA10_maxS =
		"\1\ufa2d\1\uffff\1\76\1\163\1\176\11\uffff\2\76\1\75\1\uffff\1\151\1\156"+
		"\1\uffff\1\156\1\uffff\1\162\1\157\7\uffff\1\ufa2d\1\144\3\uffff\1\76"+
		"\5\uffff\1\153\1\154\2\ufa2d\1\164\1\ufa2d\2\uffff\1\145\1\151\1\156\1"+
		"\uffff\2\ufa2d\1\153\1\ufa2d\1\145\1\uffff\1\ufa2d";
	static final String DFA10_acceptS =
		"\1\uffff\1\1\3\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\15\3\uffff"+
		"\1\26\2\uffff\1\30\1\uffff\1\33\2\uffff\1\34\1\35\1\36\1\37\1\40\1\2\1"+
		"\14\2\uffff\1\22\1\27\1\3\1\uffff\1\24\1\17\1\25\1\20\1\23\6\uffff\1\16"+
		"\1\21\3\uffff\1\31\5\uffff\1\32\1\uffff";
	static final String DFA10_specialS =
		"\76\uffff}>";
	static final String[] DFA10_transitionS = {
			"\1\34\1\24\1\uffff\2\34\22\uffff\1\34\1\4\1\31\1\5\2\33\1\26\1\uffff"+
			"\1\12\1\13\1\7\1\15\1\14\1\2\1\1\1\35\12\32\1\uffff\1\24\1\16\1\17\1"+
			"\20\1\uffff\1\6\1\3\7\33\1\25\2\33\1\22\1\33\1\30\1\27\5\33\1\23\5\33"+
			"\1\10\1\uffff\1\11\1\uffff\1\33\1\uffff\1\3\7\33\1\25\2\33\1\22\1\33"+
			"\1\30\1\27\5\33\1\23\5\33\1\uffff\1\26\1\uffff\1\21\u4d81\uffff\u51a6"+
			"\33\u595a\uffff\u012e\33",
			"",
			"\1\36",
			"\1\41\4\uffff\1\40\32\uffff\1\41\4\uffff\1\40",
			"\1\42\100\uffff\1\43",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\45\1\42",
			"\1\47",
			"\1\51",
			"",
			"\1\53\37\uffff\1\53",
			"\1\54\37\uffff\1\54",
			"",
			"\1\55\37\uffff\1\55",
			"",
			"\1\56\37\uffff\1\56",
			"\1\57\37\uffff\1\57",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\2\33\12\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33\u4d85"+
			"\uffff\u51a6\33\u595a\uffff\u012e\33",
			"\1\60\37\uffff\1\60",
			"",
			"",
			"",
			"\1\61",
			"",
			"",
			"",
			"",
			"",
			"\1\63\37\uffff\1\63",
			"\1\65\2\uffff\1\64\34\uffff\1\65\2\uffff\1\64",
			"\2\33\12\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33\u4d85"+
			"\uffff\u51a6\33\u595a\uffff\u012e\33",
			"\2\33\12\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33\u4d85"+
			"\uffff\u51a6\33\u595a\uffff\u012e\33",
			"\1\67\37\uffff\1\67",
			"\2\33\12\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33\u4d85"+
			"\uffff\u51a6\33\u595a\uffff\u012e\33",
			"",
			"",
			"\1\70\37\uffff\1\70",
			"\1\71\37\uffff\1\71",
			"\1\72\37\uffff\1\72",
			"",
			"\2\33\12\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33\u4d85"+
			"\uffff\u51a6\33\u595a\uffff\u012e\33",
			"\2\33\12\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33\u4d85"+
			"\uffff\u51a6\33\u595a\uffff\u012e\33",
			"\1\73\37\uffff\1\73",
			"\2\33\12\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33\u4d85"+
			"\uffff\u51a6\33\u595a\uffff\u012e\33",
			"\1\75\37\uffff\1\75",
			"",
			"\2\33\12\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33\u4d85"+
			"\uffff\u51a6\33\u595a\uffff\u012e\33"
	};

	static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
	static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
	static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
	static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
	static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
	static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
	static final short[][] DFA10_transition;

	static {
		int numStates = DFA10_transitionS.length;
		DFA10_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
		}
	}

	protected class DFA10 extends DFA {

		public DFA10(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 10;
			this.eot = DFA10_eot;
			this.eof = DFA10_eof;
			this.min = DFA10_min;
			this.max = DFA10_max;
			this.accept = DFA10_accept;
			this.special = DFA10_special;
			this.transition = DFA10_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( Point | AS | Remote | Local | Ref | Start | LC | RC | LP | RP | CA | ASC | DESC | JoinFull | JoinRight | GpEq | LsEq | Not | Gp | Ls | Eq | Like | NotLike | NewLine | OIn | NotOin | Condition | STRING_LITERAL | Number | IDENTIFIER | WS | COMMENT );";
		}
	}

}

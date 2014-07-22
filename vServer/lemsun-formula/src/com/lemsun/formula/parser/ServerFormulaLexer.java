package com.lemsun.formula.parser;// $ANTLR 3.2 Sep 23, 2009 12:02:23 E:\\Lemsun\\formula\\ServerFormula.g 2013-04-26 18:22:23

import org.antlr.runtime.*;

public class ServerFormulaLexer extends Lexer {
    public static final int Condition = 15;
    public static final int T__29 = 29;
    public static final int FORMULA = 4;
    public static final int Operator = 16;
    public static final int COLRANGE = 7;
    public static final int EXPRE = 8;
    public static final int RP = 14;
    public static final int LETTER = 20;
    public static final int LP = 13;
    public static final int Digit = 21;
    public static final int EOF = -1;
    public static final int HexDigit = 27;
    public static final int REF = 5;
    public static final int CODE = 19;
    public static final int T__30 = 30;
    public static final int T__31 = 31;
    public static final int EXPRES = 9;
    public static final int T__32 = 32;
    public static final int CHINESECHAR = 26;
    public static final int STRING_LITERAL = 17;
    public static final int WS = 22;
    public static final int Number = 18;
    public static final int IDENTIFIER = 11;
    public static final int UnicodeEscape = 28;
    public static final int SP = 12;
    public static final int COLS = 6;
    public static final int FUN = 10;
    public static final int COMMENT = 23;
    public static final int OctalEscape = 24;
    public static final int EscapeSequence = 25;

    // delegates
    // delegators

    public ServerFormulaLexer() {
        ;
    }

    public ServerFormulaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }

    public ServerFormulaLexer(CharStream input, RecognizerSharedState state) {
        super(input, state);

    }

    public String getGrammarFileName() {
        return "E:\\Lemsun\\formula\\ServerFormula.g";
    }

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:3:7: ( ',' )
            // E:\\Lemsun\\formula\\ServerFormula.g:3:9: ','
            {
                match(',');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:4:7: ( '-' )
            // E:\\Lemsun\\formula\\ServerFormula.g:4:9: '-'
            {
                match('-');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:5:7: ( '$' )
            // E:\\Lemsun\\formula\\ServerFormula.g:5:9: '$'
            {
                match('$');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:6:7: ( '@' )
            // E:\\Lemsun\\formula\\ServerFormula.g:6:9: '@'
            {
                match('@');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "SP"
    public final void mSP() throws RecognitionException {
        try {
            int _type = SP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:66:2: ( '!' )
            // E:\\Lemsun\\formula\\ServerFormula.g:66:4: '!'
            {
                match('!');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SP"

    // $ANTLR start "LP"
    public final void mLP() throws RecognitionException {
        try {
            int _type = LP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:70:4: ( '(' )
            // E:\\Lemsun\\formula\\ServerFormula.g:70:6: '('
            {
                match('(');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "LP"

    // $ANTLR start "RP"
    public final void mRP() throws RecognitionException {
        try {
            int _type = RP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:73:4: ( ')' )
            // E:\\Lemsun\\formula\\ServerFormula.g:73:6: ')'
            {
                match(')');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RP"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:77:2: ( ( LETTER ) ( LETTER | '0' .. '9' )* )
            // E:\\Lemsun\\formula\\ServerFormula.g:77:4: ( LETTER ) ( LETTER | '0' .. '9' )*
            {
                // E:\\Lemsun\\formula\\ServerFormula.g:77:4: ( LETTER )
                // E:\\Lemsun\\formula\\ServerFormula.g:77:5: LETTER
                {
                    mLETTER();

                }

                // E:\\Lemsun\\formula\\ServerFormula.g:77:12: ( LETTER | '0' .. '9' )*
                loop1:
                do {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);

                    if ((LA1_0 == '$' || (LA1_0 >= '0' && LA1_0 <= '9') || (LA1_0 >= 'A' && LA1_0 <= 'Z') || LA1_0 == '_' || (LA1_0 >= 'a' && LA1_0 <= 'z') || (LA1_0 >= '\u4E00' && LA1_0 <= '\u9FA5') || (LA1_0 >= '\uF900' && LA1_0 <= '\uFA2D'))) {
                        alt1 = 1;
                    }


                    switch (alt1) {
                        case 1:
                            // E:\\Lemsun\\formula\\ServerFormula.g:
                        {
                            if (input.LA(1) == '$' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z') || (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5') || (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D')) {
                                input.consume();

                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop1;
                    }
                } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "Operator"
    public final void mOperator() throws RecognitionException {
        try {
            int _type = Operator;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:83:2: ( '=' | '>' | '<' | '>=' | '<=' | '!=' | '<>' )
            int alt2 = 7;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1:
                    // E:\\Lemsun\\formula\\ServerFormula.g:83:4: '='
                {
                    match('=');

                }
                break;
                case 2:
                    // E:\\Lemsun\\formula\\ServerFormula.g:84:4: '>'
                {
                    match('>');

                }
                break;
                case 3:
                    // E:\\Lemsun\\formula\\ServerFormula.g:85:4: '<'
                {
                    match('<');

                }
                break;
                case 4:
                    // E:\\Lemsun\\formula\\ServerFormula.g:86:4: '>='
                {
                    match(">=");


                }
                break;
                case 5:
                    // E:\\Lemsun\\formula\\ServerFormula.g:87:4: '<='
                {
                    match("<=");


                }
                break;
                case 6:
                    // E:\\Lemsun\\formula\\ServerFormula.g:88:4: '!='
                {
                    match("!=");


                }
                break;
                case 7:
                    // E:\\Lemsun\\formula\\ServerFormula.g:89:4: '<>'
                {
                    match("<>");


                }
                break;

            }
            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "Operator"

    // $ANTLR start "Condition"
    public final void mCondition() throws RecognitionException {
        try {
            int _type = Condition;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:93:2: ( '&' | 'and' | '|' | 'or' | '!' | 'not' )
            int alt3 = 6;
            switch (input.LA(1)) {
                case '&': {
                    alt3 = 1;
                }
                break;
                case 'a': {
                    alt3 = 2;
                }
                break;
                case '|': {
                    alt3 = 3;
                }
                break;
                case 'o': {
                    alt3 = 4;
                }
                break;
                case '!': {
                    alt3 = 5;
                }
                break;
                case 'n': {
                    alt3 = 6;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 3, 0, input);

                    throw nvae;
            }

            switch (alt3) {
                case 1:
                    // E:\\Lemsun\\formula\\ServerFormula.g:93:4: '&'
                {
                    match('&');

                }
                break;
                case 2:
                    // E:\\Lemsun\\formula\\ServerFormula.g:94:4: 'and'
                {
                    match("and");


                }
                break;
                case 3:
                    // E:\\Lemsun\\formula\\ServerFormula.g:95:4: '|'
                {
                    match('|');

                }
                break;
                case 4:
                    // E:\\Lemsun\\formula\\ServerFormula.g:96:4: 'or'
                {
                    match("or");


                }
                break;
                case 5:
                    // E:\\Lemsun\\formula\\ServerFormula.g:97:4: '!'
                {
                    match('!');

                }
                break;
                case 6:
                    // E:\\Lemsun\\formula\\ServerFormula.g:98:4: 'not'
                {
                    match("not");


                }
                break;

            }
            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "Condition"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:108:2: ( '\"' (~ ( '\\\\' | '\"' ) )* '\"' )
            // E:\\Lemsun\\formula\\ServerFormula.g:108:5: '\"' (~ ( '\\\\' | '\"' ) )* '\"'
            {
                match('\"');
                // E:\\Lemsun\\formula\\ServerFormula.g:108:9: (~ ( '\\\\' | '\"' ) )*
                loop4:
                do {
                    int alt4 = 2;
                    int LA4_0 = input.LA(1);

                    if (((LA4_0 >= '\u0000' && LA4_0 <= '!') || (LA4_0 >= '#' && LA4_0 <= '[') || (LA4_0 >= ']' && LA4_0 <= '\uFFFF'))) {
                        alt4 = 1;
                    }


                    switch (alt4) {
                        case 1:
                            // E:\\Lemsun\\formula\\ServerFormula.g:108:11: ~ ( '\\\\' | '\"' )
                        {
                            if ((input.LA(1) >= '\u0000' && input.LA(1) <= '!') || (input.LA(1) >= '#' && input.LA(1) <= '[') || (input.LA(1) >= ']' && input.LA(1) <= '\uFFFF')) {
                                input.consume();

                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop4;
                    }
                } while (true);

                match('\"');

            }

            state.type = _type;
            state.channel = _channel;

            String text = getText();
            setText(text.substring(1, text.length() - 1));
        } finally {
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "Number"
    public final void mNumber() throws RecognitionException {
        try {
            int _type = Number;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:112:2: ( ( Digit )+ ( '.' ( Digit )+ ) )
            // E:\\Lemsun\\formula\\ServerFormula.g:112:4: ( Digit )+ ( '.' ( Digit )+ )
            {
                // E:\\Lemsun\\formula\\ServerFormula.g:112:4: ( Digit )+
                int cnt5 = 0;
                loop5:
                do {
                    int alt5 = 2;
                    int LA5_0 = input.LA(1);

                    if (((LA5_0 >= '0' && LA5_0 <= '9'))) {
                        alt5 = 1;
                    }


                    switch (alt5) {
                        case 1:
                            // E:\\Lemsun\\formula\\ServerFormula.g:112:4: Digit
                        {
                            mDigit();

                        }
                        break;

                        default:
                            if (cnt5 >= 1) break loop5;
                            EarlyExitException eee =
                                    new EarlyExitException(5, input);
                            throw eee;
                    }
                    cnt5++;
                } while (true);

                // E:\\Lemsun\\formula\\ServerFormula.g:112:11: ( '.' ( Digit )+ )
                // E:\\Lemsun\\formula\\ServerFormula.g:112:12: '.' ( Digit )+
                {
                    match('.');
                    // E:\\Lemsun\\formula\\ServerFormula.g:112:16: ( Digit )+
                    int cnt6 = 0;
                    loop6:
                    do {
                        int alt6 = 2;
                        int LA6_0 = input.LA(1);

                        if (((LA6_0 >= '0' && LA6_0 <= '9'))) {
                            alt6 = 1;
                        }


                        switch (alt6) {
                            case 1:
                                // E:\\Lemsun\\formula\\ServerFormula.g:112:16: Digit
                            {
                                mDigit();

                            }
                            break;

                            default:
                                if (cnt6 >= 1) break loop6;
                                EarlyExitException eee =
                                        new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);


                }


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "Number"

    // $ANTLR start "CODE"
    public final void mCODE() throws RecognitionException {
        try {
            int _type = CODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:116:2: ( ( LETTER | Digit )+ ( '.' ( LETTER | Digit )+ )* )
            // E:\\Lemsun\\formula\\ServerFormula.g:116:4: ( LETTER | Digit )+ ( '.' ( LETTER | Digit )+ )*
            {
                // E:\\Lemsun\\formula\\ServerFormula.g:116:4: ( LETTER | Digit )+
                int cnt7 = 0;
                loop7:
                do {
                    int alt7 = 2;
                    int LA7_0 = input.LA(1);

                    if ((LA7_0 == '$' || (LA7_0 >= '0' && LA7_0 <= '9') || (LA7_0 >= 'A' && LA7_0 <= 'Z') || LA7_0 == '_' || (LA7_0 >= 'a' && LA7_0 <= 'z') || (LA7_0 >= '\u4E00' && LA7_0 <= '\u9FA5') || (LA7_0 >= '\uF900' && LA7_0 <= '\uFA2D'))) {
                        alt7 = 1;
                    }


                    switch (alt7) {
                        case 1:
                            // E:\\Lemsun\\formula\\ServerFormula.g:
                        {
                            if (input.LA(1) == '$' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z') || (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5') || (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D')) {
                                input.consume();

                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt7 >= 1) break loop7;
                            EarlyExitException eee =
                                    new EarlyExitException(7, input);
                            throw eee;
                    }
                    cnt7++;
                } while (true);

                // E:\\Lemsun\\formula\\ServerFormula.g:116:22: ( '.' ( LETTER | Digit )+ )*
                loop9:
                do {
                    int alt9 = 2;
                    int LA9_0 = input.LA(1);

                    if ((LA9_0 == '.')) {
                        alt9 = 1;
                    }


                    switch (alt9) {
                        case 1:
                            // E:\\Lemsun\\formula\\ServerFormula.g:116:23: '.' ( LETTER | Digit )+
                        {
                            match('.');
                            // E:\\Lemsun\\formula\\ServerFormula.g:116:27: ( LETTER | Digit )+
                            int cnt8 = 0;
                            loop8:
                            do {
                                int alt8 = 2;
                                int LA8_0 = input.LA(1);

                                if ((LA8_0 == '$' || (LA8_0 >= '0' && LA8_0 <= '9') || (LA8_0 >= 'A' && LA8_0 <= 'Z') || LA8_0 == '_' || (LA8_0 >= 'a' && LA8_0 <= 'z') || (LA8_0 >= '\u4E00' && LA8_0 <= '\u9FA5') || (LA8_0 >= '\uF900' && LA8_0 <= '\uFA2D'))) {
                                    alt8 = 1;
                                }


                                switch (alt8) {
                                    case 1:
                                        // E:\\Lemsun\\formula\\ServerFormula.g:
                                    {
                                        if (input.LA(1) == '$' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z') || (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5') || (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D')) {
                                            input.consume();

                                        } else {
                                            MismatchedSetException mse = new MismatchedSetException(null, input);
                                            recover(mse);
                                            throw mse;
                                        }


                                    }
                                    break;

                                    default:
                                        if (cnt8 >= 1) break loop8;
                                        EarlyExitException eee =
                                                new EarlyExitException(8, input);
                                        throw eee;
                                }
                                cnt8++;
                            } while (true);


                        }
                        break;

                        default:
                            break loop9;
                    }
                } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "CODE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:120:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // E:\\Lemsun\\formula\\ServerFormula.g:120:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
                if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || (input.LA(1) >= '\f' && input.LA(1) <= '\r') || input.LA(1) == ' ') {
                    input.consume();

                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }

                _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Lemsun\\formula\\ServerFormula.g:124:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // E:\\Lemsun\\formula\\ServerFormula.g:124:9: '/*' ( options {greedy=false; } : . )* '*/'
            {
                match("/*");

                // E:\\Lemsun\\formula\\ServerFormula.g:124:14: ( options {greedy=false; } : . )*
                loop10:
                do {
                    int alt10 = 2;
                    int LA10_0 = input.LA(1);

                    if ((LA10_0 == '*')) {
                        int LA10_1 = input.LA(2);

                        if ((LA10_1 == '/')) {
                            alt10 = 2;
                        } else if (((LA10_1 >= '\u0000' && LA10_1 <= '.') || (LA10_1 >= '0' && LA10_1 <= '\uFFFF'))) {
                            alt10 = 1;
                        }


                    } else if (((LA10_0 >= '\u0000' && LA10_0 <= ')') || (LA10_0 >= '+' && LA10_0 <= '\uFFFF'))) {
                        alt10 = 1;
                    }


                    switch (alt10) {
                        case 1:
                            // E:\\Lemsun\\formula\\ServerFormula.g:124:42: .
                        {
                            matchAny();

                        }
                        break;

                        default:
                            break loop10;
                    }
                } while (true);

                match("*/");

                _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:129:2: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | OctalEscape )
            int alt11 = 2;
            int LA11_0 = input.LA(1);

            if ((LA11_0 == '\\')) {
                int LA11_1 = input.LA(2);

                if ((LA11_1 == '\"' || LA11_1 == '\'' || LA11_1 == '\\' || LA11_1 == 'b' || LA11_1 == 'f' || LA11_1 == 'n' || LA11_1 == 'r' || LA11_1 == 't')) {
                    alt11 = 1;
                } else if (((LA11_1 >= '0' && LA11_1 <= '7'))) {
                    alt11 = 2;
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 11, 1, input);

                    throw nvae;
                }
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1:
                    // E:\\Lemsun\\formula\\ServerFormula.g:129:6: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                {
                    match('\\');
                    if (input.LA(1) == '\"' || input.LA(1) == '\'' || input.LA(1) == '\\' || input.LA(1) == 'b' || input.LA(1) == 'f' || input.LA(1) == 'n' || input.LA(1) == 'r' || input.LA(1) == 't') {
                        input.consume();

                    } else {
                        MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                    }


                }
                break;
                case 2:
                    // E:\\Lemsun\\formula\\ServerFormula.g:130:6: OctalEscape
                {
                    mOctalEscape();

                }
                break;

            }
        } finally {
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "OctalEscape"
    public final void mOctalEscape() throws RecognitionException {
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:134:2: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt12 = 3;
            int LA12_0 = input.LA(1);

            if ((LA12_0 == '\\')) {
                int LA12_1 = input.LA(2);

                if (((LA12_1 >= '0' && LA12_1 <= '3'))) {
                    int LA12_2 = input.LA(3);

                    if (((LA12_2 >= '0' && LA12_2 <= '7'))) {
                        int LA12_4 = input.LA(4);

                        if (((LA12_4 >= '0' && LA12_4 <= '7'))) {
                            alt12 = 1;
                        } else {
                            alt12 = 2;
                        }
                    } else {
                        alt12 = 3;
                    }
                } else if (((LA12_1 >= '4' && LA12_1 <= '7'))) {
                    int LA12_3 = input.LA(3);

                    if (((LA12_3 >= '0' && LA12_3 <= '7'))) {
                        alt12 = 2;
                    } else {
                        alt12 = 3;
                    }
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1:
                    // E:\\Lemsun\\formula\\ServerFormula.g:134:6: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                {
                    match('\\');
                    // E:\\Lemsun\\formula\\ServerFormula.g:134:11: ( '0' .. '3' )
                    // E:\\Lemsun\\formula\\ServerFormula.g:134:12: '0' .. '3'
                    {
                        matchRange('0', '3');

                    }

                    // E:\\Lemsun\\formula\\ServerFormula.g:134:22: ( '0' .. '7' )
                    // E:\\Lemsun\\formula\\ServerFormula.g:134:23: '0' .. '7'
                    {
                        matchRange('0', '7');

                    }

                    // E:\\Lemsun\\formula\\ServerFormula.g:134:33: ( '0' .. '7' )
                    // E:\\Lemsun\\formula\\ServerFormula.g:134:34: '0' .. '7'
                    {
                        matchRange('0', '7');

                    }


                }
                break;
                case 2:
                    // E:\\Lemsun\\formula\\ServerFormula.g:135:6: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                {
                    match('\\');
                    // E:\\Lemsun\\formula\\ServerFormula.g:135:11: ( '0' .. '7' )
                    // E:\\Lemsun\\formula\\ServerFormula.g:135:12: '0' .. '7'
                    {
                        matchRange('0', '7');

                    }

                    // E:\\Lemsun\\formula\\ServerFormula.g:135:22: ( '0' .. '7' )
                    // E:\\Lemsun\\formula\\ServerFormula.g:135:23: '0' .. '7'
                    {
                        matchRange('0', '7');

                    }


                }
                break;
                case 3:
                    // E:\\Lemsun\\formula\\ServerFormula.g:136:6: '\\\\' ( '0' .. '7' )
                {
                    match('\\');
                    // E:\\Lemsun\\formula\\ServerFormula.g:136:11: ( '0' .. '7' )
                    // E:\\Lemsun\\formula\\ServerFormula.g:136:12: '0' .. '7'
                    {
                        matchRange('0', '7');

                    }


                }
                break;

            }
        } finally {
        }
    }
    // $ANTLR end "OctalEscape"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:140:2: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' | CHINESECHAR )
            // E:\\Lemsun\\formula\\ServerFormula.g:
            {
                if (input.LA(1) == '$' || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z') || (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5') || (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D')) {
                    input.consume();

                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }

        } finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "CHINESECHAR"
    public final void mCHINESECHAR() throws RecognitionException {
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:149:2: ( '\\u4E00' .. '\\u9FA5' | '\\uF900' .. '\\uFA2D' )
            // E:\\Lemsun\\formula\\ServerFormula.g:
            {
                if ((input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FA5') || (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFA2D')) {
                    input.consume();

                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }

        } finally {
        }
    }
    // $ANTLR end "CHINESECHAR"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:153:2: ( '0' .. '9' )
            // E:\\Lemsun\\formula\\ServerFormula.g:153:4: '0' .. '9'
            {
                matchRange('0', '9');

            }

        } finally {
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:157:2: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // E:\\Lemsun\\formula\\ServerFormula.g:157:4: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
                if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'F') || (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
                    input.consume();

                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }

        } finally {
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "UnicodeEscape"
    public final void mUnicodeEscape() throws RecognitionException {
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:161:2: ( '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit )
            // E:\\Lemsun\\formula\\ServerFormula.g:161:6: '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit
            {
                match('\\');
                match('u');
                mHexDigit();
                mHexDigit();
                mHexDigit();
                mHexDigit();

            }

        } finally {
        }
    }
    // $ANTLR end "UnicodeEscape"

    public void mTokens() throws RecognitionException {
        // E:\\Lemsun\\formula\\ServerFormula.g:1:8: ( T__29 | T__30 | T__31 | T__32 | SP | LP | RP | IDENTIFIER | Operator | Condition | STRING_LITERAL | Number | CODE | WS | COMMENT )
        int alt13 = 15;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:10: T__29
            {
                mT__29();

            }
            break;
            case 2:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:16: T__30
            {
                mT__30();

            }
            break;
            case 3:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:22: T__31
            {
                mT__31();

            }
            break;
            case 4:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:28: T__32
            {
                mT__32();

            }
            break;
            case 5:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:34: SP
            {
                mSP();

            }
            break;
            case 6:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:37: LP
            {
                mLP();

            }
            break;
            case 7:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:40: RP
            {
                mRP();

            }
            break;
            case 8:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:43: IDENTIFIER
            {
                mIDENTIFIER();

            }
            break;
            case 9:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:54: Operator
            {
                mOperator();

            }
            break;
            case 10:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:63: Condition
            {
                mCondition();

            }
            break;
            case 11:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:73: STRING_LITERAL
            {
                mSTRING_LITERAL();

            }
            break;
            case 12:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:88: Number
            {
                mNumber();

            }
            break;
            case 13:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:95: CODE
            {
                mCODE();

            }
            break;
            case 14:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:100: WS
            {
                mWS();

            }
            break;
            case 15:
                // E:\\Lemsun\\formula\\ServerFormula.g:1:103: COMMENT
            {
                mCOMMENT();

            }
            break;

        }

    }


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA2_eotS =
            "\2\uffff\1\6\1\11\6\uffff";
    static final String DFA2_eofS =
            "\12\uffff";
    static final String DFA2_minS =
            "\1\41\1\uffff\2\75\6\uffff";
    static final String DFA2_maxS =
            "\1\76\1\uffff\1\75\1\76\6\uffff";
    static final String DFA2_acceptS =
            "\1\uffff\1\1\2\uffff\1\6\1\4\1\2\1\5\1\7\1\3";
    static final String DFA2_specialS =
            "\12\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\4\32\uffff\1\3\1\1\1\2",
            "",
            "\1\5",
            "\1\7\1\10",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }

        public String getDescription() {
            return "82:1: Operator : ( '=' | '>' | '<' | '>=' | '<=' | '!=' | '<>' );";
        }
    }

    static final String DFA13_eotS =
            "\3\uffff\1\23\1\uffff\1\25\2\uffff\1\27\2\uffff\3\27\1\uffff\1" +
                    "\22\4\uffff\1\27\1\uffff\1\27\1\uffff\2\27\1\uffff\2\27\1\36\1\uffff";
    static final String DFA13_eofS =
            "\37\uffff";
    static final String DFA13_minS =
            "\1\11\2\uffff\1\44\1\uffff\1\75\2\uffff\1\44\2\uffff\3\44\1\uffff" +
                    "\1\56\4\uffff\1\44\1\uffff\1\44\1\uffff\6\44\1\uffff";
    static final String DFA13_maxS =
            "\1\ufa2d\2\uffff\1\ufa2d\1\uffff\1\75\2\uffff\1\ufa2d\2\uffff\3" +
                    "\ufa2d\1\uffff\1\71\4\uffff\1\ufa2d\1\uffff\1\ufa2d\1\uffff\6\ufa2d" +
                    "\1\uffff";
    static final String DFA13_acceptS =
            "\1\uffff\1\1\1\2\1\uffff\1\4\1\uffff\1\6\1\7\1\uffff\1\11\1\12" +
                    "\3\uffff\1\13\1\uffff\1\16\1\17\1\15\1\3\1\uffff\1\5\1\uffff\1\10" +
                    "\6\uffff\1\14";
    static final String DFA13_specialS =
            "\37\uffff}>";
    static final String[] DFA13_transitionS = {
            "\2\20\1\uffff\2\20\22\uffff\1\20\1\5\1\16\1\uffff\1\3\1\uffff" +
                    "\1\12\1\uffff\1\6\1\7\2\uffff\1\1\1\2\1\uffff\1\21\12\17\2\uffff" +
                    "\3\11\1\uffff\1\4\32\15\4\uffff\1\15\1\uffff\1\10\14\15\1\14" +
                    "\1\13\13\15\1\uffff\1\12\u4d83\uffff\u51a6\15\u595a\uffff\u012e" +
                    "\15",
            "",
            "",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\32\24\u4d85\uffff\u51a6\24\u595a\uffff\u012e\24",
            "",
            "\1\11",
            "",
            "",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\15\24\1\26\14\24\u4d85\uffff\u51a6\24\u595a\uffff" +
                    "\u012e\24",
            "",
            "",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\21\24\1\30\10\24\u4d85\uffff\u51a6\24\u595a\uffff" +
                    "\u012e\24",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\16\24\1\31\13\24\u4d85\uffff\u51a6\24\u595a\uffff" +
                    "\u012e\24",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\32\24\u4d85\uffff\u51a6\24\u595a\uffff\u012e\24",
            "",
            "\1\32\1\uffff\12\17",
            "",
            "",
            "",
            "",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\32\24\u4d85\uffff\u51a6\24\u595a\uffff\u012e\24",
            "",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\3\24\1\33\26\24\u4d85\uffff\u51a6\24\u595a\uffff" +
                    "\u012e\24",
            "",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\32\24\u4d85\uffff\u51a6\24\u595a\uffff\u012e\24",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\23\24\1\34\6\24\u4d85\uffff\u51a6\24\u595a\uffff" +
                    "\u012e\24",
            "\1\22\13\uffff\12\35\7\uffff\32\22\4\uffff\1\22\1\uffff\32" +
                    "\22\u4d85\uffff\u51a6\22\u595a\uffff\u012e\22",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\32\24\u4d85\uffff\u51a6\24\u595a\uffff\u012e\24",
            "\1\24\11\uffff\1\22\1\uffff\12\24\7\uffff\32\24\4\uffff\1" +
                    "\24\1\uffff\32\24\u4d85\uffff\u51a6\24\u595a\uffff\u012e\24",
            "\1\22\11\uffff\1\22\1\uffff\12\35\7\uffff\32\22\4\uffff\1" +
                    "\22\1\uffff\32\22\u4d85\uffff\u51a6\22\u595a\uffff\u012e\22",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }

        public String getDescription() {
            return "1:1: Tokens : ( T__29 | T__30 | T__31 | T__32 | SP | LP | RP | IDENTIFIER | Operator | Condition | STRING_LITERAL | Number | CODE | WS | COMMENT );";
        }
    }


}
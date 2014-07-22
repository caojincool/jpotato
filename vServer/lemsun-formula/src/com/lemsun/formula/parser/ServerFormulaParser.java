package com.lemsun.formula.parser;// $ANTLR 3.2 Sep 23, 2009 12:02:23 E:\\Lemsun\\formula\\ServerFormula.g 2013-04-26 18:22:23

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class ServerFormulaParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "FORMULA", "REF", "COLS", "COLRANGE", "EXPRE", "EXPRES", "FUN", "IDENTIFIER", "SP", "LP", "RP", "Condition", "Operator", "STRING_LITERAL", "Number", "CODE", "LETTER", "Digit", "WS", "COMMENT", "OctalEscape", "EscapeSequence", "CHINESECHAR", "HexDigit", "UnicodeEscape", "','", "'-'", "'$'", "'@'"
    };
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
    public static final int CHINESECHAR = 26;
    public static final int T__32 = 32;
    public static final int EXPRES = 9;
    public static final int WS = 22;
    public static final int STRING_LITERAL = 17;
    public static final int Number = 18;
    public static final int UnicodeEscape = 28;
    public static final int IDENTIFIER = 11;
    public static final int SP = 12;
    public static final int COLS = 6;
    public static final int COMMENT = 23;
    public static final int FUN = 10;
    public static final int EscapeSequence = 25;
    public static final int OctalEscape = 24;

    // delegates
    // delegators


    public ServerFormulaParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public ServerFormulaParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);

    }

    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() {
        return ServerFormulaParser.tokenNames;
    }

    public String getGrammarFileName() {
        return "E:\\Lemsun\\formula\\ServerFormula.g";
    }


    public static class statement_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "statement"
    // E:\\Lemsun\\formula\\ServerFormula.g:17:1: statement : formula EOF ;
    public final statement_return statement() throws RecognitionException {
        statement_return retval = new statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF2 = null;
        formula_return formula1 = null;


        Object EOF2_tree = null;

        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:18:2: ( formula EOF )
            // E:\\Lemsun\\formula\\ServerFormula.g:18:4: formula EOF
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_formula_in_statement56);
                formula1 = formula();

                state._fsp--;

                adaptor.addChild(root_0, formula1.getTree());
                EOF2 = (Token) match(input, EOF, FOLLOW_EOF_in_statement58);
                EOF2_tree = (Object) adaptor.create(EOF2);
                adaptor.addChild(root_0, EOF2_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class formula_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "formula"
    // E:\\Lemsun\\formula\\ServerFormula.g:21:1: formula : id cols ( expres )? -> ^( FORMULA id cols ( expres )? ) ;
    public final formula_return formula() throws RecognitionException {
        formula_return retval = new formula_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        id_return id3 = null;

        cols_return cols4 = null;

        expres_return expres5 = null;


        RewriteRuleSubtreeStream stream_id = new RewriteRuleSubtreeStream(adaptor, "rule id");
        RewriteRuleSubtreeStream stream_cols = new RewriteRuleSubtreeStream(adaptor, "rule cols");
        RewriteRuleSubtreeStream stream_expres = new RewriteRuleSubtreeStream(adaptor, "rule expres");
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:22:2: ( id cols ( expres )? -> ^( FORMULA id cols ( expres )? ) )
            // E:\\Lemsun\\formula\\ServerFormula.g:22:5: id cols ( expres )?
            {
                pushFollow(FOLLOW_id_in_formula70);
                id3 = id();

                state._fsp--;

                stream_id.add(id3.getTree());
                pushFollow(FOLLOW_cols_in_formula72);
                cols4 = cols();

                state._fsp--;

                stream_cols.add(cols4.getTree());
                // E:\\Lemsun\\formula\\ServerFormula.g:22:13: ( expres )?
                int alt1 = 2;
                int LA1_0 = input.LA(1);

                if ((LA1_0 == LP)) {
                    alt1 = 1;
                }
                switch (alt1) {
                    case 1:
                        // E:\\Lemsun\\formula\\ServerFormula.g:22:13: expres
                    {
                        pushFollow(FOLLOW_expres_in_formula74);
                        expres5 = expres();

                        state._fsp--;

                        stream_expres.add(expres5.getTree());

                    }
                    break;

                }


                // AST REWRITE
                // elements: expres, cols, id
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (Object) adaptor.nil();
                // 22:21: -> ^( FORMULA id cols ( expres )? )
                {
                    // E:\\Lemsun\\formula\\ServerFormula.g:22:24: ^( FORMULA id cols ( expres )? )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FORMULA, "FORMULA"), root_1);

                        adaptor.addChild(root_1, stream_id.nextTree());
                        adaptor.addChild(root_1, stream_cols.nextTree());
                        // E:\\Lemsun\\formula\\ServerFormula.g:22:42: ( expres )?
                        if (stream_expres.hasNext()) {
                            adaptor.addChild(root_1, stream_expres.nextTree());

                        }
                        stream_expres.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "formula"

    public static class id_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "id"
    // E:\\Lemsun\\formula\\ServerFormula.g:25:1: id : refid= IDENTIFIER SP -> ^( REF $refid) ;
    public final id_return id() throws RecognitionException {
        id_return retval = new id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token refid = null;
        Token SP6 = null;

        Object refid_tree = null;
        Object SP6_tree = null;
        RewriteRuleTokenStream stream_IDENTIFIER = new RewriteRuleTokenStream(adaptor, "token IDENTIFIER");
        RewriteRuleTokenStream stream_SP = new RewriteRuleTokenStream(adaptor, "token SP");

        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:26:2: (refid= IDENTIFIER SP -> ^( REF $refid) )
            // E:\\Lemsun\\formula\\ServerFormula.g:26:4: refid= IDENTIFIER SP
            {
                refid = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_id104);
                stream_IDENTIFIER.add(refid);

                SP6 = (Token) match(input, SP, FOLLOW_SP_in_id106);
                stream_SP.add(SP6);


                // AST REWRITE
                // elements: refid
                // token labels: refid
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleTokenStream stream_refid = new RewriteRuleTokenStream(adaptor, "token refid", refid);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (Object) adaptor.nil();
                // 26:26: -> ^( REF $refid)
                {
                    // E:\\Lemsun\\formula\\ServerFormula.g:26:29: ^( REF $refid)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(REF, "REF"), root_1);

                        adaptor.addChild(root_1, stream_refid.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "id"

    public static class cols_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "cols"
    // E:\\Lemsun\\formula\\ServerFormula.g:29:1: cols : ( col ( ',' col )* -> ^( COLS ( col )+ ) | col '-' col -> ^( COLRANGE ( col )+ ) | LP col RP -> ^( COLS col ) | LP col '-' col RP -> ^( COLRANGE ( col )+ ) | LP col ( ',' col )+ RP -> ^( COLS ( col )+ ) );
    public final cols_return cols() throws RecognitionException {
        cols_return retval = new cols_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal8 = null;
        Token char_literal11 = null;
        Token LP13 = null;
        Token RP15 = null;
        Token LP16 = null;
        Token char_literal18 = null;
        Token RP20 = null;
        Token LP21 = null;
        Token char_literal23 = null;
        Token RP25 = null;
        col_return col7 = null;

        col_return col9 = null;

        col_return col10 = null;

        col_return col12 = null;

        col_return col14 = null;

        col_return col17 = null;

        col_return col19 = null;

        col_return col22 = null;

        col_return col24 = null;


        Object char_literal8_tree = null;
        Object char_literal11_tree = null;
        Object LP13_tree = null;
        Object RP15_tree = null;
        Object LP16_tree = null;
        Object char_literal18_tree = null;
        Object RP20_tree = null;
        Object LP21_tree = null;
        Object char_literal23_tree = null;
        Object RP25_tree = null;
        RewriteRuleTokenStream stream_30 = new RewriteRuleTokenStream(adaptor, "token 30");
        RewriteRuleTokenStream stream_RP = new RewriteRuleTokenStream(adaptor, "token RP");
        RewriteRuleTokenStream stream_LP = new RewriteRuleTokenStream(adaptor, "token LP");
        RewriteRuleTokenStream stream_29 = new RewriteRuleTokenStream(adaptor, "token 29");
        RewriteRuleSubtreeStream stream_col = new RewriteRuleSubtreeStream(adaptor, "rule col");
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:30:2: ( col ( ',' col )* -> ^( COLS ( col )+ ) | col '-' col -> ^( COLRANGE ( col )+ ) | LP col RP -> ^( COLS col ) | LP col '-' col RP -> ^( COLRANGE ( col )+ ) | LP col ( ',' col )+ RP -> ^( COLS ( col )+ ) )
            int alt4 = 5;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1:
                    // E:\\Lemsun\\formula\\ServerFormula.g:30:4: col ( ',' col )*
                {
                    pushFollow(FOLLOW_col_in_cols126);
                    col7 = col();

                    state._fsp--;

                    stream_col.add(col7.getTree());
                    // E:\\Lemsun\\formula\\ServerFormula.g:30:8: ( ',' col )*
                    loop2:
                    do {
                        int alt2 = 2;
                        int LA2_0 = input.LA(1);

                        if ((LA2_0 == 29)) {
                            alt2 = 1;
                        }


                        switch (alt2) {
                            case 1:
                                // E:\\Lemsun\\formula\\ServerFormula.g:30:9: ',' col
                            {
                                char_literal8 = (Token) match(input, 29, FOLLOW_29_in_cols129);
                                stream_29.add(char_literal8);

                                pushFollow(FOLLOW_col_in_cols131);
                                col9 = col();

                                state._fsp--;

                                stream_col.add(col9.getTree());

                            }
                            break;

                            default:
                                break loop2;
                        }
                    } while (true);


                    // AST REWRITE
                    // elements: col
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (Object) adaptor.nil();
                    // 30:20: -> ^( COLS ( col )+ )
                    {
                        // E:\\Lemsun\\formula\\ServerFormula.g:30:23: ^( COLS ( col )+ )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(COLS, "COLS"), root_1);

                            if (!(stream_col.hasNext())) {
                                throw new RewriteEarlyExitException();
                            }
                            while (stream_col.hasNext()) {
                                adaptor.addChild(root_1, stream_col.nextTree());

                            }
                            stream_col.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 2:
                    // E:\\Lemsun\\formula\\ServerFormula.g:31:4: col '-' col
                {
                    pushFollow(FOLLOW_col_in_cols148);
                    col10 = col();

                    state._fsp--;

                    stream_col.add(col10.getTree());
                    char_literal11 = (Token) match(input, 30, FOLLOW_30_in_cols150);
                    stream_30.add(char_literal11);

                    pushFollow(FOLLOW_col_in_cols152);
                    col12 = col();

                    state._fsp--;

                    stream_col.add(col12.getTree());


                    // AST REWRITE
                    // elements: col
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (Object) adaptor.nil();
                    // 31:16: -> ^( COLRANGE ( col )+ )
                    {
                        // E:\\Lemsun\\formula\\ServerFormula.g:31:19: ^( COLRANGE ( col )+ )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(COLRANGE, "COLRANGE"), root_1);

                            if (!(stream_col.hasNext())) {
                                throw new RewriteEarlyExitException();
                            }
                            while (stream_col.hasNext()) {
                                adaptor.addChild(root_1, stream_col.nextTree());

                            }
                            stream_col.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 3:
                    // E:\\Lemsun\\formula\\ServerFormula.g:32:4: LP col RP
                {
                    LP13 = (Token) match(input, LP, FOLLOW_LP_in_cols166);
                    stream_LP.add(LP13);

                    pushFollow(FOLLOW_col_in_cols168);
                    col14 = col();

                    state._fsp--;

                    stream_col.add(col14.getTree());
                    RP15 = (Token) match(input, RP, FOLLOW_RP_in_cols170);
                    stream_RP.add(RP15);


                    // AST REWRITE
                    // elements: col
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (Object) adaptor.nil();
                    // 32:14: -> ^( COLS col )
                    {
                        // E:\\Lemsun\\formula\\ServerFormula.g:32:17: ^( COLS col )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(COLS, "COLS"), root_1);

                            adaptor.addChild(root_1, stream_col.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 4:
                    // E:\\Lemsun\\formula\\ServerFormula.g:33:4: LP col '-' col RP
                {
                    LP16 = (Token) match(input, LP, FOLLOW_LP_in_cols183);
                    stream_LP.add(LP16);

                    pushFollow(FOLLOW_col_in_cols185);
                    col17 = col();

                    state._fsp--;

                    stream_col.add(col17.getTree());
                    char_literal18 = (Token) match(input, 30, FOLLOW_30_in_cols187);
                    stream_30.add(char_literal18);

                    pushFollow(FOLLOW_col_in_cols189);
                    col19 = col();

                    state._fsp--;

                    stream_col.add(col19.getTree());
                    RP20 = (Token) match(input, RP, FOLLOW_RP_in_cols191);
                    stream_RP.add(RP20);


                    // AST REWRITE
                    // elements: col
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (Object) adaptor.nil();
                    // 33:22: -> ^( COLRANGE ( col )+ )
                    {
                        // E:\\Lemsun\\formula\\ServerFormula.g:33:25: ^( COLRANGE ( col )+ )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(COLRANGE, "COLRANGE"), root_1);

                            if (!(stream_col.hasNext())) {
                                throw new RewriteEarlyExitException();
                            }
                            while (stream_col.hasNext()) {
                                adaptor.addChild(root_1, stream_col.nextTree());

                            }
                            stream_col.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;
                case 5:
                    // E:\\Lemsun\\formula\\ServerFormula.g:34:4: LP col ( ',' col )+ RP
                {
                    LP21 = (Token) match(input, LP, FOLLOW_LP_in_cols205);
                    stream_LP.add(LP21);

                    pushFollow(FOLLOW_col_in_cols207);
                    col22 = col();

                    state._fsp--;

                    stream_col.add(col22.getTree());
                    // E:\\Lemsun\\formula\\ServerFormula.g:34:11: ( ',' col )+
                    int cnt3 = 0;
                    loop3:
                    do {
                        int alt3 = 2;
                        int LA3_0 = input.LA(1);

                        if ((LA3_0 == 29)) {
                            alt3 = 1;
                        }


                        switch (alt3) {
                            case 1:
                                // E:\\Lemsun\\formula\\ServerFormula.g:34:12: ',' col
                            {
                                char_literal23 = (Token) match(input, 29, FOLLOW_29_in_cols210);
                                stream_29.add(char_literal23);

                                pushFollow(FOLLOW_col_in_cols212);
                                col24 = col();

                                state._fsp--;

                                stream_col.add(col24.getTree());

                            }
                            break;

                            default:
                                if (cnt3 >= 1) break loop3;
                                EarlyExitException eee =
                                        new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);

                    RP25 = (Token) match(input, RP, FOLLOW_RP_in_cols216);
                    stream_RP.add(RP25);


                    // AST REWRITE
                    // elements: col
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                    root_0 = (Object) adaptor.nil();
                    // 34:25: -> ^( COLS ( col )+ )
                    {
                        // E:\\Lemsun\\formula\\ServerFormula.g:34:28: ^( COLS ( col )+ )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(COLS, "COLS"), root_1);

                            if (!(stream_col.hasNext())) {
                                throw new RewriteEarlyExitException();
                            }
                            while (stream_col.hasNext()) {
                                adaptor.addChild(root_1, stream_col.nextTree());

                            }
                            stream_col.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "cols"

    public static class col_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "col"
    // E:\\Lemsun\\formula\\ServerFormula.g:38:1: col : ( fun | IDENTIFIER );
    public final col_return col() throws RecognitionException {
        col_return retval = new col_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENTIFIER27 = null;
        fun_return fun26 = null;


        Object IDENTIFIER27_tree = null;

        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:39:2: ( fun | IDENTIFIER )
            int alt5 = 2;
            int LA5_0 = input.LA(1);

            if ((LA5_0 == 31)) {
                alt5 = 1;
            } else if ((LA5_0 == IDENTIFIER)) {
                alt5 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1:
                    // E:\\Lemsun\\formula\\ServerFormula.g:39:4: fun
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fun_in_col237);
                    fun26 = fun();

                    state._fsp--;

                    adaptor.addChild(root_0, fun26.getTree());

                }
                break;
                case 2:
                    // E:\\Lemsun\\formula\\ServerFormula.g:40:4: IDENTIFIER
                {
                    root_0 = (Object) adaptor.nil();

                    IDENTIFIER27 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_col242);
                    IDENTIFIER27_tree = (Object) adaptor.create(IDENTIFIER27);
                    adaptor.addChild(root_0, IDENTIFIER27_tree);


                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "col"

    public static class fun_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "fun"
    // E:\\Lemsun\\formula\\ServerFormula.g:44:1: fun : '$' IDENTIFIER LP IDENTIFIER ( ',' IDENTIFIER )* RP -> ^( FUN ( IDENTIFIER )+ ) ;
    public final fun_return fun() throws RecognitionException {
        fun_return retval = new fun_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal28 = null;
        Token IDENTIFIER29 = null;
        Token LP30 = null;
        Token IDENTIFIER31 = null;
        Token char_literal32 = null;
        Token IDENTIFIER33 = null;
        Token RP34 = null;

        Object char_literal28_tree = null;
        Object IDENTIFIER29_tree = null;
        Object LP30_tree = null;
        Object IDENTIFIER31_tree = null;
        Object char_literal32_tree = null;
        Object IDENTIFIER33_tree = null;
        Object RP34_tree = null;
        RewriteRuleTokenStream stream_31 = new RewriteRuleTokenStream(adaptor, "token 31");
        RewriteRuleTokenStream stream_RP = new RewriteRuleTokenStream(adaptor, "token RP");
        RewriteRuleTokenStream stream_IDENTIFIER = new RewriteRuleTokenStream(adaptor, "token IDENTIFIER");
        RewriteRuleTokenStream stream_LP = new RewriteRuleTokenStream(adaptor, "token LP");
        RewriteRuleTokenStream stream_29 = new RewriteRuleTokenStream(adaptor, "token 29");

        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:44:5: ( '$' IDENTIFIER LP IDENTIFIER ( ',' IDENTIFIER )* RP -> ^( FUN ( IDENTIFIER )+ ) )
            // E:\\Lemsun\\formula\\ServerFormula.g:44:7: '$' IDENTIFIER LP IDENTIFIER ( ',' IDENTIFIER )* RP
            {
                char_literal28 = (Token) match(input, 31, FOLLOW_31_in_fun254);
                stream_31.add(char_literal28);

                IDENTIFIER29 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_fun256);
                stream_IDENTIFIER.add(IDENTIFIER29);

                LP30 = (Token) match(input, LP, FOLLOW_LP_in_fun258);
                stream_LP.add(LP30);

                IDENTIFIER31 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_fun260);
                stream_IDENTIFIER.add(IDENTIFIER31);

                // E:\\Lemsun\\formula\\ServerFormula.g:44:36: ( ',' IDENTIFIER )*
                loop6:
                do {
                    int alt6 = 2;
                    int LA6_0 = input.LA(1);

                    if ((LA6_0 == 29)) {
                        alt6 = 1;
                    }


                    switch (alt6) {
                        case 1:
                            // E:\\Lemsun\\formula\\ServerFormula.g:44:37: ',' IDENTIFIER
                        {
                            char_literal32 = (Token) match(input, 29, FOLLOW_29_in_fun263);
                            stream_29.add(char_literal32);

                            IDENTIFIER33 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_fun265);
                            stream_IDENTIFIER.add(IDENTIFIER33);


                        }
                        break;

                        default:
                            break loop6;
                    }
                } while (true);

                RP34 = (Token) match(input, RP, FOLLOW_RP_in_fun269);
                stream_RP.add(RP34);


                // AST REWRITE
                // elements: IDENTIFIER
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (Object) adaptor.nil();
                // 44:57: -> ^( FUN ( IDENTIFIER )+ )
                {
                    // E:\\Lemsun\\formula\\ServerFormula.g:44:60: ^( FUN ( IDENTIFIER )+ )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FUN, "FUN"), root_1);

                        if (!(stream_IDENTIFIER.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_IDENTIFIER.hasNext()) {
                            adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());

                        }
                        stream_IDENTIFIER.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "fun"

    public static class expres_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "expres"
    // E:\\Lemsun\\formula\\ServerFormula.g:48:1: expres : LP expre ( Condition expre )* RP -> ^( EXPRES expre ( Condition expre )* ) ;
    public final expres_return expres() throws RecognitionException {
        expres_return retval = new expres_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LP35 = null;
        Token Condition37 = null;
        Token RP39 = null;
        expre_return expre36 = null;

        expre_return expre38 = null;


        Object LP35_tree = null;
        Object Condition37_tree = null;
        Object RP39_tree = null;
        RewriteRuleTokenStream stream_Condition = new RewriteRuleTokenStream(adaptor, "token Condition");
        RewriteRuleTokenStream stream_RP = new RewriteRuleTokenStream(adaptor, "token RP");
        RewriteRuleTokenStream stream_LP = new RewriteRuleTokenStream(adaptor, "token LP");
        RewriteRuleSubtreeStream stream_expre = new RewriteRuleSubtreeStream(adaptor, "rule expre");
        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:49:2: ( LP expre ( Condition expre )* RP -> ^( EXPRES expre ( Condition expre )* ) )
            // E:\\Lemsun\\formula\\ServerFormula.g:49:4: LP expre ( Condition expre )* RP
            {
                LP35 = (Token) match(input, LP, FOLLOW_LP_in_expres291);
                stream_LP.add(LP35);

                pushFollow(FOLLOW_expre_in_expres293);
                expre36 = expre();

                state._fsp--;

                stream_expre.add(expre36.getTree());
                // E:\\Lemsun\\formula\\ServerFormula.g:49:13: ( Condition expre )*
                loop7:
                do {
                    int alt7 = 2;
                    int LA7_0 = input.LA(1);

                    if ((LA7_0 == Condition)) {
                        alt7 = 1;
                    }


                    switch (alt7) {
                        case 1:
                            // E:\\Lemsun\\formula\\ServerFormula.g:49:14: Condition expre
                        {
                            Condition37 = (Token) match(input, Condition, FOLLOW_Condition_in_expres296);
                            stream_Condition.add(Condition37);

                            pushFollow(FOLLOW_expre_in_expres298);
                            expre38 = expre();

                            state._fsp--;

                            stream_expre.add(expre38.getTree());

                        }
                        break;

                        default:
                            break loop7;
                    }
                } while (true);

                RP39 = (Token) match(input, RP, FOLLOW_RP_in_expres302);
                stream_RP.add(RP39);


                // AST REWRITE
                // elements: expre, expre, Condition
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.tree : null);

                root_0 = (Object) adaptor.nil();
                // 49:35: -> ^( EXPRES expre ( Condition expre )* )
                {
                    // E:\\Lemsun\\formula\\ServerFormula.g:49:38: ^( EXPRES expre ( Condition expre )* )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(EXPRES, "EXPRES"), root_1);

                        adaptor.addChild(root_1, stream_expre.nextTree());
                        // E:\\Lemsun\\formula\\ServerFormula.g:49:53: ( Condition expre )*
                        while (stream_expre.hasNext() || stream_Condition.hasNext()) {
                            adaptor.addChild(root_1, stream_Condition.nextNode());
                            adaptor.addChild(root_1, stream_expre.nextTree());

                        }
                        stream_expre.reset();
                        stream_Condition.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "expres"

    public static class expre_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "expre"
    // E:\\Lemsun\\formula\\ServerFormula.g:52:1: expre : ( col Operator value | value );
    public final expre_return expre() throws RecognitionException {
        expre_return retval = new expre_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Operator41 = null;
        col_return col40 = null;

        value_return value42 = null;

        value_return value43 = null;


        Object Operator41_tree = null;

        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:53:2: ( col Operator value | value )
            int alt8 = 2;
            switch (input.LA(1)) {
                case 31: {
                    alt8 = 1;
                }
                break;
                case IDENTIFIER: {
                    int LA8_2 = input.LA(2);

                    if (((LA8_2 >= RP && LA8_2 <= Condition))) {
                        alt8 = 2;
                    } else if ((LA8_2 == Operator)) {
                        alt8 = 1;
                    } else {
                        NoViableAltException nvae =
                                new NoViableAltException("", 8, 2, input);

                        throw nvae;
                    }
                }
                break;
                case STRING_LITERAL:
                case Number:
                case CODE:
                case 32: {
                    alt8 = 2;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 8, 0, input);

                    throw nvae;
            }

            switch (alt8) {
                case 1:
                    // E:\\Lemsun\\formula\\ServerFormula.g:53:4: col Operator value
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_col_in_expre329);
                    col40 = col();

                    state._fsp--;

                    adaptor.addChild(root_0, col40.getTree());
                    Operator41 = (Token) match(input, Operator, FOLLOW_Operator_in_expre331);
                    Operator41_tree = (Object) adaptor.create(Operator41);
                    root_0 = (Object) adaptor.becomeRoot(Operator41_tree, root_0);

                    pushFollow(FOLLOW_value_in_expre334);
                    value42 = value();

                    state._fsp--;

                    adaptor.addChild(root_0, value42.getTree());

                }
                break;
                case 2:
                    // E:\\Lemsun\\formula\\ServerFormula.g:54:4: value
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_value_in_expre339);
                    value43 = value();

                    state._fsp--;

                    adaptor.addChild(root_0, value43.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "expre"

    public static class value_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    }

    ;

    // $ANTLR start "value"
    // E:\\Lemsun\\formula\\ServerFormula.g:57:1: value : ( IDENTIFIER | STRING_LITERAL | Number | CODE | '@' formula );
    public final value_return value() throws RecognitionException {
        value_return retval = new value_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENTIFIER44 = null;
        Token STRING_LITERAL45 = null;
        Token Number46 = null;
        Token CODE47 = null;
        Token char_literal48 = null;
        formula_return formula49 = null;


        Object IDENTIFIER44_tree = null;
        Object STRING_LITERAL45_tree = null;
        Object Number46_tree = null;
        Object CODE47_tree = null;
        Object char_literal48_tree = null;

        try {
            // E:\\Lemsun\\formula\\ServerFormula.g:58:2: ( IDENTIFIER | STRING_LITERAL | Number | CODE | '@' formula )
            int alt9 = 5;
            switch (input.LA(1)) {
                case IDENTIFIER: {
                    alt9 = 1;
                }
                break;
                case STRING_LITERAL: {
                    alt9 = 2;
                }
                break;
                case Number: {
                    alt9 = 3;
                }
                break;
                case CODE: {
                    alt9 = 4;
                }
                break;
                case 32: {
                    alt9 = 5;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 9, 0, input);

                    throw nvae;
            }

            switch (alt9) {
                case 1:
                    // E:\\Lemsun\\formula\\ServerFormula.g:58:4: IDENTIFIER
                {
                    root_0 = (Object) adaptor.nil();

                    IDENTIFIER44 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_value350);
                    IDENTIFIER44_tree = (Object) adaptor.create(IDENTIFIER44);
                    adaptor.addChild(root_0, IDENTIFIER44_tree);


                }
                break;
                case 2:
                    // E:\\Lemsun\\formula\\ServerFormula.g:59:4: STRING_LITERAL
                {
                    root_0 = (Object) adaptor.nil();

                    STRING_LITERAL45 = (Token) match(input, STRING_LITERAL, FOLLOW_STRING_LITERAL_in_value355);
                    STRING_LITERAL45_tree = (Object) adaptor.create(STRING_LITERAL45);
                    adaptor.addChild(root_0, STRING_LITERAL45_tree);


                }
                break;
                case 3:
                    // E:\\Lemsun\\formula\\ServerFormula.g:60:4: Number
                {
                    root_0 = (Object) adaptor.nil();

                    Number46 = (Token) match(input, Number, FOLLOW_Number_in_value360);
                    Number46_tree = (Object) adaptor.create(Number46);
                    adaptor.addChild(root_0, Number46_tree);


                }
                break;
                case 4:
                    // E:\\Lemsun\\formula\\ServerFormula.g:61:4: CODE
                {
                    root_0 = (Object) adaptor.nil();

                    CODE47 = (Token) match(input, CODE, FOLLOW_CODE_in_value365);
                    CODE47_tree = (Object) adaptor.create(CODE47);
                    adaptor.addChild(root_0, CODE47_tree);


                }
                break;
                case 5:
                    // E:\\Lemsun\\formula\\ServerFormula.g:62:4: '@' formula
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal48 = (Token) match(input, 32, FOLLOW_32_in_value370);
                    pushFollow(FOLLOW_formula_in_value373);
                    formula49 = formula();

                    state._fsp--;

                    adaptor.addChild(root_0, formula49.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
        }
        return retval;
    }
    // $ANTLR end "value"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
            "\27\uffff";
    static final String DFA4_eofS =
            "\2\uffff\1\5\16\uffff\1\5\5\uffff";
    static final String DFA4_minS =
            "\2\13\1\15\1\13\1\15\2\uffff\1\13\1\16\1\13\1\15\3\uffff\1\16\2" +
                    "\13\1\15\2\16\1\13\2\16";
    static final String DFA4_maxS =
            "\1\37\1\13\1\36\1\37\1\15\2\uffff\1\13\1\36\1\13\1\15\3\uffff\1" +
                    "\35\2\13\1\36\2\35\1\13\1\36\1\35";
    static final String DFA4_acceptS =
            "\5\uffff\1\1\1\2\4\uffff\1\4\1\3\1\5\11\uffff";
    static final String DFA4_specialS =
            "\27\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\2\1\uffff\1\3\21\uffff\1\1",
            "\1\4",
            "\3\5\15\uffff\1\5\1\6",
            "\1\10\23\uffff\1\7",
            "\1\11",
            "",
            "",
            "\1\12",
            "\1\14\16\uffff\1\15\1\13",
            "\1\16",
            "\1\17",
            "",
            "",
            "",
            "\1\21\16\uffff\1\20",
            "\1\22",
            "\1\23",
            "\3\5\15\uffff\1\5\1\6",
            "\1\25\16\uffff\1\24",
            "\1\21\16\uffff\1\20",
            "\1\26",
            "\1\14\16\uffff\1\15\1\13",
            "\1\25\16\uffff\1\24"
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }

        public String getDescription() {
            return "29:1: cols : ( col ( ',' col )* -> ^( COLS ( col )+ ) | col '-' col -> ^( COLRANGE ( col )+ ) | LP col RP -> ^( COLS col ) | LP col '-' col RP -> ^( COLRANGE ( col )+ ) | LP col ( ',' col )+ RP -> ^( COLS ( col )+ ) );";
        }
    }


    public static final BitSet FOLLOW_formula_in_statement56 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_formula70 = new BitSet(new long[]{0x0000000080002800L});
    public static final BitSet FOLLOW_cols_in_formula72 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expres_in_formula74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_id104 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SP_in_id106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_col_in_cols126 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_cols129 = new BitSet(new long[]{0x0000000080000800L});
    public static final BitSet FOLLOW_col_in_cols131 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_col_in_cols148 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_cols150 = new BitSet(new long[]{0x0000000080000800L});
    public static final BitSet FOLLOW_col_in_cols152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LP_in_cols166 = new BitSet(new long[]{0x0000000080000800L});
    public static final BitSet FOLLOW_col_in_cols168 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RP_in_cols170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LP_in_cols183 = new BitSet(new long[]{0x0000000080000800L});
    public static final BitSet FOLLOW_col_in_cols185 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_cols187 = new BitSet(new long[]{0x0000000080000800L});
    public static final BitSet FOLLOW_col_in_cols189 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RP_in_cols191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LP_in_cols205 = new BitSet(new long[]{0x0000000080000800L});
    public static final BitSet FOLLOW_col_in_cols207 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_cols210 = new BitSet(new long[]{0x0000000080000800L});
    public static final BitSet FOLLOW_col_in_cols212 = new BitSet(new long[]{0x0000000020004000L});
    public static final BitSet FOLLOW_RP_in_cols216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fun_in_col237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_col242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_fun254 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_IDENTIFIER_in_fun256 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LP_in_fun258 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_IDENTIFIER_in_fun260 = new BitSet(new long[]{0x0000000020004000L});
    public static final BitSet FOLLOW_29_in_fun263 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_IDENTIFIER_in_fun265 = new BitSet(new long[]{0x0000000020004000L});
    public static final BitSet FOLLOW_RP_in_fun269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LP_in_expres291 = new BitSet(new long[]{0x00000001800E0800L});
    public static final BitSet FOLLOW_expre_in_expres293 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_Condition_in_expres296 = new BitSet(new long[]{0x00000001800E0800L});
    public static final BitSet FOLLOW_expre_in_expres298 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_RP_in_expres302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_col_in_expre329 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_Operator_in_expre331 = new BitSet(new long[]{0x00000001800E0800L});
    public static final BitSet FOLLOW_value_in_expre334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_expre339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_value350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_value355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_value360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CODE_in_value365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_value370 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_formula_in_value373 = new BitSet(new long[]{0x0000000000000002L});

}
package com.lemsun.client.formula.parser;// $ANTLR 3.5.1 E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g 2014-02-15 10:52:07

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class ServerFormulaParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALIAS", "ARRAYVALUE", "AS", "ASC", 
		"CA", "CHINESECHAR", "CODE", "COL", "COLRANGE", "COLS", "COMMENT", "Condition", 
		"DESC", "Digit", "EXPRE", "EXPRES", "Eq", "FORMULA", "FUN", "Gp", "GpEq", 
		"IDENTIFIER", "JOIN", "JoinFull", "JoinRight", "LC", "LETTER", "LP", "Like", 
		"Local", "Ls", "LsEq", "NAME", "NewLine", "Not", "NotLike", "NotOin", 
		"Number", "OIn", "OPERATOR", "Point", "RC", "REF", "REFS", "RP", "Ref", 
		"Remote", "SOFT", "STATEMENT", "STATEMENTS", "STRING_LITERAL", "Start", 
		"VALUE", "WS"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

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
	@Override public String[] getTokenNames() { return ServerFormulaParser.tokenNames; }
	@Override public String getGrammarFileName() { return "E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g"; }


	public static class statements_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statements"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:30:1: statements : statement ( ( NewLine )+ statement )* EOF -> ^( STATEMENTS ( statement )+ ) ;
	public final statements_return statements() throws RecognitionException {
		statements_return retval = new statements_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NewLine2=null;
		Token EOF4=null;
		ParserRuleReturnScope statement1 =null;
		ParserRuleReturnScope statement3 =null;

		Object NewLine2_tree=null;
		Object EOF4_tree=null;
		RewriteRuleTokenStream stream_NewLine=new RewriteRuleTokenStream(adaptor,"token NewLine");
		RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
		RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:31:2: ( statement ( ( NewLine )+ statement )* EOF -> ^( STATEMENTS ( statement )+ ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:31:5: statement ( ( NewLine )+ statement )* EOF
			{
			pushFollow(FOLLOW_statement_in_statements110);
			statement1=statement();
			state._fsp--;

			stream_statement.add(statement1.getTree());
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:31:15: ( ( NewLine )+ statement )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==NewLine) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:31:16: ( NewLine )+ statement
					{
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:31:16: ( NewLine )+
					int cnt1=0;
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( (LA1_0==NewLine) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:31:16: NewLine
							{
							NewLine2=(Token)match(input,NewLine,FOLLOW_NewLine_in_statements113);  
							stream_NewLine.add(NewLine2);

							}
							break;

						default :
							if ( cnt1 >= 1 ) break loop1;
							EarlyExitException eee = new EarlyExitException(1, input);
							throw eee;
						}
						cnt1++;
					}

					pushFollow(FOLLOW_statement_in_statements116);
					statement3=statement();
					state._fsp--;

					stream_statement.add(statement3.getTree());
					}
					break;

				default :
					break loop2;
				}
			}

			EOF4=(Token)match(input,EOF,FOLLOW_EOF_in_statements120);  
			stream_EOF.add(EOF4);

			// AST REWRITE
			// elements: statement
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 31:41: -> ^( STATEMENTS ( statement )+ )
			{
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:31:44: ^( STATEMENTS ( statement )+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATEMENTS, "STATEMENTS"), root_1);
				if ( !(stream_statement.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_statement.hasNext() ) {
					adaptor.addChild(root_1, stream_statement.nextTree());
				}
				stream_statement.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statements"


	public static class statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:34:1: statement : formula operator formula -> ^( STATEMENT formula operator formula ) ;
	public final statement_return statement() throws RecognitionException {
		statement_return retval = new statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope formula5 =null;
		ParserRuleReturnScope operator6 =null;
		ParserRuleReturnScope formula7 =null;

		RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
		RewriteRuleSubtreeStream stream_formula=new RewriteRuleSubtreeStream(adaptor,"rule formula");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:35:2: ( formula operator formula -> ^( STATEMENT formula operator formula ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:35:5: formula operator formula
			{
			pushFollow(FOLLOW_formula_in_statement141);
			formula5=formula();
			state._fsp--;

			stream_formula.add(formula5.getTree());
			pushFollow(FOLLOW_operator_in_statement143);
			operator6=operator();
			state._fsp--;

			stream_operator.add(operator6.getTree());
			pushFollow(FOLLOW_formula_in_statement145);
			formula7=formula();
			state._fsp--;

			stream_formula.add(formula7.getTree());
			// AST REWRITE
			// elements: formula, operator, formula
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 35:30: -> ^( STATEMENT formula operator formula )
			{
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:35:33: ^( STATEMENT formula operator formula )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATEMENT, "STATEMENT"), root_1);
				adaptor.addChild(root_1, stream_formula.nextTree());
				adaptor.addChild(root_1, stream_operator.nextTree());
				adaptor.addChild(root_1, stream_formula.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class formulaEof_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "formulaEof"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:38:1: formulaEof : formula EOF ;
	public final formulaEof_return formulaEof() throws RecognitionException {
		formulaEof_return retval = new formulaEof_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EOF9=null;
		ParserRuleReturnScope formula8 =null;

		Object EOF9_tree=null;

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:39:2: ( formula EOF )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:39:4: formula EOF
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_formula_in_formulaEof168);
			formula8=formula();
			state._fsp--;

			adaptor.addChild(root_0, formula8.getTree());

			EOF9=(Token)match(input,EOF,FOLLOW_EOF_in_formulaEof170); 
			EOF9_tree = (Object)adaptor.create(EOF9);
			adaptor.addChild(root_0, EOF9_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "formulaEof"


	public static class formula_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "formula"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:42:1: formula : ( refs r= ( Remote | Local ) cols ( expres )? ( softs )? -> ^( FORMULA refs $r cols ( expres )? ( softs )? ) | ref -> ^( FORMULA ref ) );
	public final formula_return formula() throws RecognitionException {
		formula_return retval = new formula_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token r=null;
		ParserRuleReturnScope refs10 =null;
		ParserRuleReturnScope cols11 =null;
		ParserRuleReturnScope expres12 =null;
		ParserRuleReturnScope softs13 =null;
		ParserRuleReturnScope ref14 =null;

		Object r_tree=null;
		RewriteRuleTokenStream stream_Local=new RewriteRuleTokenStream(adaptor,"token Local");
		RewriteRuleTokenStream stream_Remote=new RewriteRuleTokenStream(adaptor,"token Remote");
		RewriteRuleSubtreeStream stream_ref=new RewriteRuleSubtreeStream(adaptor,"rule ref");
		RewriteRuleSubtreeStream stream_cols=new RewriteRuleSubtreeStream(adaptor,"rule cols");
		RewriteRuleSubtreeStream stream_refs=new RewriteRuleSubtreeStream(adaptor,"rule refs");
		RewriteRuleSubtreeStream stream_expres=new RewriteRuleSubtreeStream(adaptor,"rule expres");
		RewriteRuleSubtreeStream stream_softs=new RewriteRuleSubtreeStream(adaptor,"rule softs");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:2: ( refs r= ( Remote | Local ) cols ( expres )? ( softs )? -> ^( FORMULA refs $r cols ( expres )? ( softs )? ) | ref -> ^( FORMULA ref ) )
			int alt6=2;
			alt6 = dfa6.predict(input);
			switch (alt6) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:4: refs r= ( Remote | Local ) cols ( expres )? ( softs )?
					{
					pushFollow(FOLLOW_refs_in_formula181);
					refs10=refs();
					state._fsp--;

					stream_refs.add(refs10.getTree());
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:13: ( Remote | Local )
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==Remote) ) {
						alt3=1;
					}
					else if ( (LA3_0==Local) ) {
						alt3=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 3, 0, input);
						throw nvae;
					}

					switch (alt3) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:14: Remote
							{
							r=(Token)match(input,Remote,FOLLOW_Remote_in_formula188);  
							stream_Remote.add(r);

							}
							break;
						case 2 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:21: Local
							{
							r=(Token)match(input,Local,FOLLOW_Local_in_formula190);  
							stream_Local.add(r);

							}
							break;

					}

					pushFollow(FOLLOW_cols_in_formula193);
					cols11=cols();
					state._fsp--;

					stream_cols.add(cols11.getTree());
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:33: ( expres )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==LP) ) {
						int LA4_1 = input.LA(2);
						if ( (LA4_1==IDENTIFIER) ) {
							switch ( input.LA(3) ) {
								case Point:
									{
									int LA4_5 = input.LA(4);
									if ( (LA4_5==IDENTIFIER) ) {
										int LA4_7 = input.LA(5);
										if ( (LA4_7==Eq||(LA4_7 >= Gp && LA4_7 <= GpEq)||LA4_7==Like||(LA4_7 >= Ls && LA4_7 <= LsEq)||(LA4_7 >= Not && LA4_7 <= NotOin)||LA4_7==OIn||LA4_7==Point||LA4_7==RP) ) {
											alt4=1;
										}
									}
									else if ( (LA4_5==Number) ) {
										alt4=1;
									}
									}
									break;
								case Eq:
								case Gp:
								case GpEq:
								case Like:
								case Ls:
								case LsEq:
								case Not:
								case NotLike:
								case NotOin:
								case OIn:
									{
									alt4=1;
									}
									break;
								case RP:
									{
									alt4=1;
									}
									break;
							}
						}
						else if ( (LA4_1==LC||LA4_1==Number||LA4_1==Ref||LA4_1==STRING_LITERAL) ) {
							alt4=1;
						}
					}
					switch (alt4) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:33: expres
							{
							pushFollow(FOLLOW_expres_in_formula195);
							expres12=expres();
							state._fsp--;

							stream_expres.add(expres12.getTree());
							}
							break;

					}

					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:41: ( softs )?
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( (LA5_0==LP) ) {
						alt5=1;
					}
					switch (alt5) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:41: softs
							{
							pushFollow(FOLLOW_softs_in_formula198);
							softs13=softs();
							state._fsp--;

							stream_softs.add(softs13.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: softs, refs, r, cols, expres
					// token labels: r
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleTokenStream stream_r=new RewriteRuleTokenStream(adaptor,"token r",r);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 43:48: -> ^( FORMULA refs $r cols ( expres )? ( softs )? )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:51: ^( FORMULA refs $r cols ( expres )? ( softs )? )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FORMULA, "FORMULA"), root_1);
						adaptor.addChild(root_1, stream_refs.nextTree());
						adaptor.addChild(root_1, stream_r.nextNode());
						adaptor.addChild(root_1, stream_cols.nextTree());
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:74: ( expres )?
						if ( stream_expres.hasNext() ) {
							adaptor.addChild(root_1, stream_expres.nextTree());
						}
						stream_expres.reset();

						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:43:82: ( softs )?
						if ( stream_softs.hasNext() ) {
							adaptor.addChild(root_1, stream_softs.nextTree());
						}
						stream_softs.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:44:5: ref
					{
					pushFollow(FOLLOW_ref_in_formula224);
					ref14=ref();
					state._fsp--;

					stream_ref.add(ref14.getTree());
					// AST REWRITE
					// elements: ref
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 44:9: -> ^( FORMULA ref )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:44:12: ^( FORMULA ref )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FORMULA, "FORMULA"), root_1);
						adaptor.addChild(root_1, stream_ref.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "formula"


	public static class refs_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "refs"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:48:1: refs : ref ( ( JoinFull | LsEq | JoinRight | Eq ) ref ( join )? )* ;
	public final refs_return refs() throws RecognitionException {
		refs_return retval = new refs_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set16=null;
		ParserRuleReturnScope ref15 =null;
		ParserRuleReturnScope ref17 =null;
		ParserRuleReturnScope join18 =null;

		Object set16_tree=null;

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:49:2: ( ref ( ( JoinFull | LsEq | JoinRight | Eq ) ref ( join )? )* )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:49:4: ref ( ( JoinFull | LsEq | JoinRight | Eq ) ref ( join )? )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_ref_in_refs245);
			ref15=ref();
			state._fsp--;

			adaptor.addChild(root_0, ref15.getTree());

			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:49:8: ( ( JoinFull | LsEq | JoinRight | Eq ) ref ( join )? )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==Eq||(LA8_0 >= JoinFull && LA8_0 <= JoinRight)||LA8_0==LsEq) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:49:9: ( JoinFull | LsEq | JoinRight | Eq ) ref ( join )?
					{
					set16=input.LT(1);
					if ( input.LA(1)==Eq||(input.LA(1) >= JoinFull && input.LA(1) <= JoinRight)||input.LA(1)==LsEq ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set16));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_ref_in_refs264);
					ref17=ref();
					state._fsp--;

					adaptor.addChild(root_0, ref17.getTree());

					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:49:48: ( join )?
					int alt7=2;
					int LA7_0 = input.LA(1);
					if ( (LA7_0==LC) ) {
						alt7=1;
					}
					switch (alt7) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:49:48: join
							{
							pushFollow(FOLLOW_join_in_refs266);
							join18=join();
							state._fsp--;

							adaptor.addChild(root_0, join18.getTree());

							}
							break;

					}

					}
					break;

				default :
					break loop8;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "refs"


	public static class ref_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ref"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:52:1: ref : n= refname ( AS alias= IDENTIFIER )? -> ^( REF $n ( $alias)? ) ;
	public final ref_return ref() throws RecognitionException {
		ref_return retval = new ref_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token alias=null;
		Token AS19=null;
		ParserRuleReturnScope n =null;

		Object alias_tree=null;
		Object AS19_tree=null;
		RewriteRuleTokenStream stream_AS=new RewriteRuleTokenStream(adaptor,"token AS");
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleSubtreeStream stream_refname=new RewriteRuleSubtreeStream(adaptor,"rule refname");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:53:2: (n= refname ( AS alias= IDENTIFIER )? -> ^( REF $n ( $alias)? ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:53:5: n= refname ( AS alias= IDENTIFIER )?
			{
			pushFollow(FOLLOW_refname_in_ref286);
			n=refname();
			state._fsp--;

			stream_refname.add(n.getTree());
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:53:17: ( AS alias= IDENTIFIER )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==AS) ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:53:18: AS alias= IDENTIFIER
					{
					AS19=(Token)match(input,AS,FOLLOW_AS_in_ref289);  
					stream_AS.add(AS19);

					alias=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_ref295);  
					stream_IDENTIFIER.add(alias);

					}
					break;

			}

			// AST REWRITE
			// elements: alias, n
			// token labels: alias
			// rule labels: retval, n
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleTokenStream stream_alias=new RewriteRuleTokenStream(adaptor,"token alias",alias);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_n=new RewriteRuleSubtreeStream(adaptor,"rule n",n!=null?n.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 53:42: -> ^( REF $n ( $alias)? )
			{
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:53:45: ^( REF $n ( $alias)? )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(REF, "REF"), root_1);
				adaptor.addChild(root_1, stream_n.nextTree());
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:53:55: ( $alias)?
				if ( stream_alias.hasNext() ) {
					adaptor.addChild(root_1, stream_alias.nextNode());
				}
				stream_alias.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ref"


	public static class join_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "join"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:56:1: join : LC refexpre ( Condition refexpre )* RC -> ^( JOIN refexpre ( Condition refexpre )* ) ;
	public final join_return join() throws RecognitionException {
		join_return retval = new join_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LC20=null;
		Token Condition22=null;
		Token RC24=null;
		ParserRuleReturnScope refexpre21 =null;
		ParserRuleReturnScope refexpre23 =null;

		Object LC20_tree=null;
		Object Condition22_tree=null;
		Object RC24_tree=null;
		RewriteRuleTokenStream stream_Condition=new RewriteRuleTokenStream(adaptor,"token Condition");
		RewriteRuleTokenStream stream_LC=new RewriteRuleTokenStream(adaptor,"token LC");
		RewriteRuleTokenStream stream_RC=new RewriteRuleTokenStream(adaptor,"token RC");
		RewriteRuleSubtreeStream stream_refexpre=new RewriteRuleSubtreeStream(adaptor,"rule refexpre");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:57:2: ( LC refexpre ( Condition refexpre )* RC -> ^( JOIN refexpre ( Condition refexpre )* ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:57:4: LC refexpre ( Condition refexpre )* RC
			{
			LC20=(Token)match(input,LC,FOLLOW_LC_in_join321);  
			stream_LC.add(LC20);

			pushFollow(FOLLOW_refexpre_in_join323);
			refexpre21=refexpre();
			state._fsp--;

			stream_refexpre.add(refexpre21.getTree());
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:57:16: ( Condition refexpre )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==Condition) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:57:17: Condition refexpre
					{
					Condition22=(Token)match(input,Condition,FOLLOW_Condition_in_join326);  
					stream_Condition.add(Condition22);

					pushFollow(FOLLOW_refexpre_in_join328);
					refexpre23=refexpre();
					state._fsp--;

					stream_refexpre.add(refexpre23.getTree());
					}
					break;

				default :
					break loop10;
				}
			}

			RC24=(Token)match(input,RC,FOLLOW_RC_in_join332);  
			stream_RC.add(RC24);

			// AST REWRITE
			// elements: Condition, refexpre, refexpre
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 57:41: -> ^( JOIN refexpre ( Condition refexpre )* )
			{
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:57:44: ^( JOIN refexpre ( Condition refexpre )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(JOIN, "JOIN"), root_1);
				adaptor.addChild(root_1, stream_refexpre.nextTree());
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:57:60: ( Condition refexpre )*
				while ( stream_Condition.hasNext()||stream_refexpre.hasNext() ) {
					adaptor.addChild(root_1, stream_Condition.nextNode());
					adaptor.addChild(root_1, stream_refexpre.nextTree());
				}
				stream_Condition.reset();
				stream_refexpre.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "join"


	public static class refexpre_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "refexpre"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:60:1: refexpre : name operator name -> ^( EXPRE name operator name ) ;
	public final refexpre_return refexpre() throws RecognitionException {
		refexpre_return retval = new refexpre_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope name25 =null;
		ParserRuleReturnScope operator26 =null;
		ParserRuleReturnScope name27 =null;

		RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");
		RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:61:2: ( name operator name -> ^( EXPRE name operator name ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:61:4: name operator name
			{
			pushFollow(FOLLOW_name_in_refexpre358);
			name25=name();
			state._fsp--;

			stream_name.add(name25.getTree());
			pushFollow(FOLLOW_operator_in_refexpre360);
			operator26=operator();
			state._fsp--;

			stream_operator.add(operator26.getTree());
			pushFollow(FOLLOW_name_in_refexpre362);
			name27=name();
			state._fsp--;

			stream_name.add(name27.getTree());
			// AST REWRITE
			// elements: name, name, operator
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 61:23: -> ^( EXPRE name operator name )
			{
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:61:26: ^( EXPRE name operator name )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXPRE, "EXPRE"), root_1);
				adaptor.addChild(root_1, stream_name.nextTree());
				adaptor.addChild(root_1, stream_operator.nextTree());
				adaptor.addChild(root_1, stream_name.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "refexpre"


	protected static class refname_scope {
		String value;
	}
	protected Stack<refname_scope> refname_stack = new Stack<refname_scope>();

	public static class refname_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "refname"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:65:1: refname : r= ( IDENTIFIER | Number ) (p= Point n= ( IDENTIFIER | Number ) )* ->;
	public final refname_return refname() throws RecognitionException {
		refname_stack.push(new refname_scope());
		refname_return retval = new refname_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token r=null;
		Token p=null;
		Token n=null;

		Object r_tree=null;
		Object p_tree=null;
		Object n_tree=null;
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleTokenStream stream_Point=new RewriteRuleTokenStream(adaptor,"token Point");


			refname_stack.peek().value = "";

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:2: (r= ( IDENTIFIER | Number ) (p= Point n= ( IDENTIFIER | Number ) )* ->)
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:4: r= ( IDENTIFIER | Number ) (p= Point n= ( IDENTIFIER | Number ) )*
			{
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:8: ( IDENTIFIER | Number )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==IDENTIFIER) ) {
				alt11=1;
			}
			else if ( (LA11_0==Number) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:9: IDENTIFIER
					{
					r=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_refname403);  
					stream_IDENTIFIER.add(r);

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:20: Number
					{
					r=(Token)match(input,Number,FOLLOW_Number_in_refname405);  
					stream_Number.add(r);

					}
					break;

			}

			 refname_stack.peek().value += r.getText(); 
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:65: (p= Point n= ( IDENTIFIER | Number ) )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==Point) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:66: p= Point n= ( IDENTIFIER | Number )
					{
					p=(Token)match(input,Point,FOLLOW_Point_in_refname415);  
					stream_Point.add(p);

					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:80: ( IDENTIFIER | Number )
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==IDENTIFIER) ) {
						alt12=1;
					}
					else if ( (LA12_0==Number) ) {
						alt12=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 12, 0, input);
						throw nvae;
					}

					switch (alt12) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:81: IDENTIFIER
							{
							n=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_refname422);  
							stream_IDENTIFIER.add(n);

							}
							break;
						case 2 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:73:92: Number
							{
							n=(Token)match(input,Number,FOLLOW_Number_in_refname424);  
							stream_Number.add(n);

							}
							break;

					}

					 refname_stack.peek().value += p.getText() + n.getText(); 
					}
					break;

				default :
					break loop13;
				}
			}

			// AST REWRITE
			// elements: 
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 74:3: ->
			{
				adaptor.addChild(root_0,  getTreeAdaptor().create(NAME, refname_stack.peek().value) );
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			refname_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "refname"


	public static class cols_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cols"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:77:1: cols : ( LP Start RP -> ^( COLS Start ) | LP col ( CA col )* RP -> ^( COLS ( col )+ ) | LP col '-' col RP -> ^( COLRANGE ( col )+ ) | Start -> ^( COLS Start ) | col ( CA col )* -> ^( COLS ( col )+ ) | col '-' col -> ^( COLRANGE ( col )+ ) );
	public final cols_return cols() throws RecognitionException {
		cols_return retval = new cols_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LP28=null;
		Token Start29=null;
		Token RP30=null;
		Token LP31=null;
		Token CA33=null;
		Token RP35=null;
		Token LP36=null;
		Token char_literal38=null;
		Token RP40=null;
		Token Start41=null;
		Token CA43=null;
		Token char_literal46=null;
		ParserRuleReturnScope col32 =null;
		ParserRuleReturnScope col34 =null;
		ParserRuleReturnScope col37 =null;
		ParserRuleReturnScope col39 =null;
		ParserRuleReturnScope col42 =null;
		ParserRuleReturnScope col44 =null;
		ParserRuleReturnScope col45 =null;
		ParserRuleReturnScope col47 =null;

		Object LP28_tree=null;
		Object Start29_tree=null;
		Object RP30_tree=null;
		Object LP31_tree=null;
		Object CA33_tree=null;
		Object RP35_tree=null;
		Object LP36_tree=null;
		Object char_literal38_tree=null;
		Object RP40_tree=null;
		Object Start41_tree=null;
		Object CA43_tree=null;
		Object char_literal46_tree=null;
		RewriteRuleTokenStream stream_Start=new RewriteRuleTokenStream(adaptor,"token Start");
		RewriteRuleTokenStream stream_ASC=new RewriteRuleTokenStream(adaptor,"token ASC");
		RewriteRuleTokenStream stream_RP=new RewriteRuleTokenStream(adaptor,"token RP");
		RewriteRuleTokenStream stream_CA=new RewriteRuleTokenStream(adaptor,"token CA");
		RewriteRuleTokenStream stream_LP=new RewriteRuleTokenStream(adaptor,"token LP");
		RewriteRuleSubtreeStream stream_col=new RewriteRuleSubtreeStream(adaptor,"rule col");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:78:2: ( LP Start RP -> ^( COLS Start ) | LP col ( CA col )* RP -> ^( COLS ( col )+ ) | LP col '-' col RP -> ^( COLRANGE ( col )+ ) | Start -> ^( COLS Start ) | col ( CA col )* -> ^( COLS ( col )+ ) | col '-' col -> ^( COLRANGE ( col )+ ) )
			int alt16=6;
			alt16 = dfa16.predict(input);
			switch (alt16) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:78:5: LP Start RP
					{
					LP28=(Token)match(input,LP,FOLLOW_LP_in_cols449);  
					stream_LP.add(LP28);

					Start29=(Token)match(input,Start,FOLLOW_Start_in_cols451);  
					stream_Start.add(Start29);

					RP30=(Token)match(input,RP,FOLLOW_RP_in_cols453);  
					stream_RP.add(RP30);

					// AST REWRITE
					// elements: Start
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 78:18: -> ^( COLS Start )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:78:21: ^( COLS Start )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COLS, "COLS"), root_1);
						adaptor.addChild(root_1, stream_Start.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:79:4: LP col ( CA col )* RP
					{
					LP31=(Token)match(input,LP,FOLLOW_LP_in_cols468);  
					stream_LP.add(LP31);

					pushFollow(FOLLOW_col_in_cols470);
					col32=col();
					state._fsp--;

					stream_col.add(col32.getTree());
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:79:11: ( CA col )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==CA) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:79:12: CA col
							{
							CA33=(Token)match(input,CA,FOLLOW_CA_in_cols473);  
							stream_CA.add(CA33);

							pushFollow(FOLLOW_col_in_cols475);
							col34=col();
							state._fsp--;

							stream_col.add(col34.getTree());
							}
							break;

						default :
							break loop14;
						}
					}

					RP35=(Token)match(input,RP,FOLLOW_RP_in_cols479);  
					stream_RP.add(RP35);

					// AST REWRITE
					// elements: col
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 79:25: -> ^( COLS ( col )+ )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:79:28: ^( COLS ( col )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COLS, "COLS"), root_1);
						if ( !(stream_col.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_col.hasNext() ) {
							adaptor.addChild(root_1, stream_col.nextTree());
						}
						stream_col.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:80:4: LP col '-' col RP
					{
					LP36=(Token)match(input,LP,FOLLOW_LP_in_cols494);  
					stream_LP.add(LP36);

					pushFollow(FOLLOW_col_in_cols496);
					col37=col();
					state._fsp--;

					stream_col.add(col37.getTree());
					char_literal38=(Token)match(input,ASC,FOLLOW_ASC_in_cols498);  
					stream_ASC.add(char_literal38);

					pushFollow(FOLLOW_col_in_cols500);
					col39=col();
					state._fsp--;

					stream_col.add(col39.getTree());
					RP40=(Token)match(input,RP,FOLLOW_RP_in_cols502);  
					stream_RP.add(RP40);

					// AST REWRITE
					// elements: col
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 80:22: -> ^( COLRANGE ( col )+ )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:80:25: ^( COLRANGE ( col )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COLRANGE, "COLRANGE"), root_1);
						if ( !(stream_col.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_col.hasNext() ) {
							adaptor.addChild(root_1, stream_col.nextTree());
						}
						stream_col.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:81:4: Start
					{
					Start41=(Token)match(input,Start,FOLLOW_Start_in_cols516);  
					stream_Start.add(Start41);

					// AST REWRITE
					// elements: Start
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 81:12: -> ^( COLS Start )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:81:15: ^( COLS Start )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COLS, "COLS"), root_1);
						adaptor.addChild(root_1, stream_Start.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:82:4: col ( CA col )*
					{
					pushFollow(FOLLOW_col_in_cols531);
					col42=col();
					state._fsp--;

					stream_col.add(col42.getTree());
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:82:8: ( CA col )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==CA) ) {
							int LA15_2 = input.LA(2);
							if ( (LA15_2==IDENTIFIER) ) {
								alt15=1;
							}
							else if ( (LA15_2==LC) ) {
								int LA15_4 = input.LA(3);
								if ( (LA15_4==IDENTIFIER) ) {
									int LA15_5 = input.LA(4);
									if ( (LA15_5==Point) ) {
										int LA15_6 = input.LA(5);
										if ( (LA15_6==IDENTIFIER) ) {
											int LA15_8 = input.LA(6);
											if ( (LA15_8==RC) ) {
												alt15=1;
											}

										}

									}
									else if ( (LA15_5==RC) ) {
										alt15=1;
									}

								}

							}

						}

						switch (alt15) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:82:9: CA col
							{
							CA43=(Token)match(input,CA,FOLLOW_CA_in_cols534);  
							stream_CA.add(CA43);

							pushFollow(FOLLOW_col_in_cols536);
							col44=col();
							state._fsp--;

							stream_col.add(col44.getTree());
							}
							break;

						default :
							break loop15;
						}
					}

					// AST REWRITE
					// elements: col
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 82:20: -> ^( COLS ( col )+ )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:82:23: ^( COLS ( col )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COLS, "COLS"), root_1);
						if ( !(stream_col.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_col.hasNext() ) {
							adaptor.addChild(root_1, stream_col.nextTree());
						}
						stream_col.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 6 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:83:4: col '-' col
					{
					pushFollow(FOLLOW_col_in_cols554);
					col45=col();
					state._fsp--;

					stream_col.add(col45.getTree());
					char_literal46=(Token)match(input,ASC,FOLLOW_ASC_in_cols556);  
					stream_ASC.add(char_literal46);

					pushFollow(FOLLOW_col_in_cols558);
					col47=col();
					state._fsp--;

					stream_col.add(col47.getTree());
					// AST REWRITE
					// elements: col
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 83:18: -> ^( COLRANGE ( col )+ )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:83:21: ^( COLRANGE ( col )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COLRANGE, "COLRANGE"), root_1);
						if ( !(stream_col.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_col.hasNext() ) {
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

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "cols"


	public static class col_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "col"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:86:1: col : ( funcols | colname );
	public final col_return col() throws RecognitionException {
		col_return retval = new col_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope funcols48 =null;
		ParserRuleReturnScope colname49 =null;


		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:86:5: ( funcols | colname )
			int alt17=2;
			alt17 = dfa17.predict(input);
			switch (alt17) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:86:8: funcols
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_funcols_in_col580);
					funcols48=funcols();
					state._fsp--;

					adaptor.addChild(root_0, funcols48.getTree());

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:87:4: colname
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_colname_in_col585);
					colname49=colname();
					state._fsp--;

					adaptor.addChild(root_0, colname49.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "col"


	public static class funcols_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "funcols"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:90:1: funcols : n= IDENTIFIER LP colname ( CA colname )* RP -> ^( FUN $n ( colname )+ ) ;
	public final funcols_return funcols() throws RecognitionException {
		funcols_return retval = new funcols_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token LP50=null;
		Token CA52=null;
		Token RP54=null;
		ParserRuleReturnScope colname51 =null;
		ParserRuleReturnScope colname53 =null;

		Object n_tree=null;
		Object LP50_tree=null;
		Object CA52_tree=null;
		Object RP54_tree=null;
		RewriteRuleTokenStream stream_RP=new RewriteRuleTokenStream(adaptor,"token RP");
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleTokenStream stream_CA=new RewriteRuleTokenStream(adaptor,"token CA");
		RewriteRuleTokenStream stream_LP=new RewriteRuleTokenStream(adaptor,"token LP");
		RewriteRuleSubtreeStream stream_colname=new RewriteRuleSubtreeStream(adaptor,"rule colname");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:90:9: (n= IDENTIFIER LP colname ( CA colname )* RP -> ^( FUN $n ( colname )+ ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:90:13: n= IDENTIFIER LP colname ( CA colname )* RP
			{
			n=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_funcols601);  
			stream_IDENTIFIER.add(n);

			LP50=(Token)match(input,LP,FOLLOW_LP_in_funcols603);  
			stream_LP.add(LP50);

			pushFollow(FOLLOW_colname_in_funcols605);
			colname51=colname();
			state._fsp--;

			stream_colname.add(colname51.getTree());
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:90:39: ( CA colname )*
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==CA) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:90:40: CA colname
					{
					CA52=(Token)match(input,CA,FOLLOW_CA_in_funcols608);  
					stream_CA.add(CA52);

					pushFollow(FOLLOW_colname_in_funcols610);
					colname53=colname();
					state._fsp--;

					stream_colname.add(colname53.getTree());
					}
					break;

				default :
					break loop18;
				}
			}

			RP54=(Token)match(input,RP,FOLLOW_RP_in_funcols614);  
			stream_RP.add(RP54);

			// AST REWRITE
			// elements: colname, n
			// token labels: n
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleTokenStream stream_n=new RewriteRuleTokenStream(adaptor,"token n",n);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 90:56: -> ^( FUN $n ( colname )+ )
			{
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:90:59: ^( FUN $n ( colname )+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUN, "FUN"), root_1);
				adaptor.addChild(root_1, stream_n.nextNode());
				if ( !(stream_colname.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_colname.hasNext() ) {
					adaptor.addChild(root_1, stream_colname.nextTree());
				}
				stream_colname.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "funcols"


	public static class colname_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "colname"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:93:1: colname : ( LC ! name RC ! ( AS ! IDENTIFIER )? | name ( AS ! IDENTIFIER )? );
	public final colname_return colname() throws RecognitionException {
		colname_return retval = new colname_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LC55=null;
		Token RC57=null;
		Token AS58=null;
		Token IDENTIFIER59=null;
		Token AS61=null;
		Token IDENTIFIER62=null;
		ParserRuleReturnScope name56 =null;
		ParserRuleReturnScope name60 =null;

		Object LC55_tree=null;
		Object RC57_tree=null;
		Object AS58_tree=null;
		Object IDENTIFIER59_tree=null;
		Object AS61_tree=null;
		Object IDENTIFIER62_tree=null;

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:94:2: ( LC ! name RC ! ( AS ! IDENTIFIER )? | name ( AS ! IDENTIFIER )? )
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==LC) ) {
				alt21=1;
			}
			else if ( (LA21_0==IDENTIFIER) ) {
				alt21=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}

			switch (alt21) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:94:5: LC ! name RC ! ( AS ! IDENTIFIER )?
					{
					root_0 = (Object)adaptor.nil();


					LC55=(Token)match(input,LC,FOLLOW_LC_in_colname639); 
					pushFollow(FOLLOW_name_in_colname642);
					name56=name();
					state._fsp--;

					adaptor.addChild(root_0, name56.getTree());

					RC57=(Token)match(input,RC,FOLLOW_RC_in_colname644); 
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:94:18: ( AS ! IDENTIFIER )?
					int alt19=2;
					int LA19_0 = input.LA(1);
					if ( (LA19_0==AS) ) {
						alt19=1;
					}
					switch (alt19) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:94:19: AS ! IDENTIFIER
							{
							AS58=(Token)match(input,AS,FOLLOW_AS_in_colname648); 
							IDENTIFIER59=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_colname651); 
							IDENTIFIER59_tree = (Object)adaptor.create(IDENTIFIER59);
							adaptor.addChild(root_0, IDENTIFIER59_tree);

							}
							break;

					}

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:95:4: name ( AS ! IDENTIFIER )?
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_name_in_colname658);
					name60=name();
					state._fsp--;

					adaptor.addChild(root_0, name60.getTree());

					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:95:9: ( AS ! IDENTIFIER )?
					int alt20=2;
					int LA20_0 = input.LA(1);
					if ( (LA20_0==AS) ) {
						alt20=1;
					}
					switch (alt20) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:95:10: AS ! IDENTIFIER
							{
							AS61=(Token)match(input,AS,FOLLOW_AS_in_colname661); 
							IDENTIFIER62=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_colname664); 
							IDENTIFIER62_tree = (Object)adaptor.create(IDENTIFIER62);
							adaptor.addChild(root_0, IDENTIFIER62_tree);

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "colname"


	public static class expres_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expres"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:98:1: expres : ( LP expre ( Condition expre )* RP -> ^( EXPRES expre ( Condition expre )* ) | LP value RP -> ^( EXPRES value ) );
	public final expres_return expres() throws RecognitionException {
		expres_return retval = new expres_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LP63=null;
		Token Condition65=null;
		Token RP67=null;
		Token LP68=null;
		Token RP70=null;
		ParserRuleReturnScope expre64 =null;
		ParserRuleReturnScope expre66 =null;
		ParserRuleReturnScope value69 =null;

		Object LP63_tree=null;
		Object Condition65_tree=null;
		Object RP67_tree=null;
		Object LP68_tree=null;
		Object RP70_tree=null;
		RewriteRuleTokenStream stream_Condition=new RewriteRuleTokenStream(adaptor,"token Condition");
		RewriteRuleTokenStream stream_RP=new RewriteRuleTokenStream(adaptor,"token RP");
		RewriteRuleTokenStream stream_LP=new RewriteRuleTokenStream(adaptor,"token LP");
		RewriteRuleSubtreeStream stream_expre=new RewriteRuleSubtreeStream(adaptor,"rule expre");
		RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:99:2: ( LP expre ( Condition expre )* RP -> ^( EXPRES expre ( Condition expre )* ) | LP value RP -> ^( EXPRES value ) )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==LP) ) {
				int LA23_1 = input.LA(2);
				if ( (LA23_1==IDENTIFIER) ) {
					switch ( input.LA(3) ) {
					case Point:
						{
						int LA23_4 = input.LA(4);
						if ( (LA23_4==IDENTIFIER) ) {
							int LA23_6 = input.LA(5);
							if ( (LA23_6==Eq||(LA23_6 >= Gp && LA23_6 <= GpEq)||LA23_6==Like||(LA23_6 >= Ls && LA23_6 <= LsEq)||(LA23_6 >= Not && LA23_6 <= NotOin)||LA23_6==OIn) ) {
								alt23=1;
							}
							else if ( (LA23_6==Point||LA23_6==RP) ) {
								alt23=2;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 23, 6, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}
						else if ( (LA23_4==Number) ) {
							alt23=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 23, 4, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case Eq:
					case Gp:
					case GpEq:
					case Like:
					case Ls:
					case LsEq:
					case Not:
					case NotLike:
					case NotOin:
					case OIn:
						{
						alt23=1;
						}
						break;
					case RP:
						{
						alt23=2;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 23, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}
				else if ( (LA23_1==LC||LA23_1==Number||LA23_1==Ref||LA23_1==STRING_LITERAL) ) {
					alt23=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 23, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:99:4: LP expre ( Condition expre )* RP
					{
					LP63=(Token)match(input,LP,FOLLOW_LP_in_expres678);  
					stream_LP.add(LP63);

					pushFollow(FOLLOW_expre_in_expres680);
					expre64=expre();
					state._fsp--;

					stream_expre.add(expre64.getTree());
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:99:13: ( Condition expre )*
					loop22:
					while (true) {
						int alt22=2;
						int LA22_0 = input.LA(1);
						if ( (LA22_0==Condition) ) {
							alt22=1;
						}

						switch (alt22) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:99:14: Condition expre
							{
							Condition65=(Token)match(input,Condition,FOLLOW_Condition_in_expres683);  
							stream_Condition.add(Condition65);

							pushFollow(FOLLOW_expre_in_expres685);
							expre66=expre();
							state._fsp--;

							stream_expre.add(expre66.getTree());
							}
							break;

						default :
							break loop22;
						}
					}

					RP67=(Token)match(input,RP,FOLLOW_RP_in_expres689);  
					stream_RP.add(RP67);

					// AST REWRITE
					// elements: Condition, expre, expre
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 99:35: -> ^( EXPRES expre ( Condition expre )* )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:99:38: ^( EXPRES expre ( Condition expre )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXPRES, "EXPRES"), root_1);
						adaptor.addChild(root_1, stream_expre.nextTree());
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:99:53: ( Condition expre )*
						while ( stream_Condition.hasNext()||stream_expre.hasNext() ) {
							adaptor.addChild(root_1, stream_Condition.nextNode());
							adaptor.addChild(root_1, stream_expre.nextTree());
						}
						stream_Condition.reset();
						stream_expre.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:100:4: LP value RP
					{
					LP68=(Token)match(input,LP,FOLLOW_LP_in_expres709);  
					stream_LP.add(LP68);

					pushFollow(FOLLOW_value_in_expres711);
					value69=value();
					state._fsp--;

					stream_value.add(value69.getTree());
					RP70=(Token)match(input,RP,FOLLOW_RP_in_expres713);  
					stream_RP.add(RP70);

					// AST REWRITE
					// elements: value
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 100:16: -> ^( EXPRES value )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:100:19: ^( EXPRES value )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXPRES, "EXPRES"), root_1);
						adaptor.addChild(root_1, stream_value.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expres"


	public static class expre_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expre"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:103:1: expre : name operator ^ value ;
	public final expre_return expre() throws RecognitionException {
		expre_return retval = new expre_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope name71 =null;
		ParserRuleReturnScope operator72 =null;
		ParserRuleReturnScope value73 =null;


		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:104:2: ( name operator ^ value )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:104:4: name operator ^ value
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_name_in_expre732);
			name71=name();
			state._fsp--;

			adaptor.addChild(root_0, name71.getTree());

			pushFollow(FOLLOW_operator_in_expre734);
			operator72=operator();
			state._fsp--;

			root_0 = (Object)adaptor.becomeRoot(operator72.getTree(), root_0);
			pushFollow(FOLLOW_value_in_expre737);
			value73=value();
			state._fsp--;

			adaptor.addChild(root_0, value73.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expre"


	public static class arrayValue_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "arrayValue"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:107:1: arrayValue : LC value ( CA value )* RC -> ^( ARRAYVALUE value ( value )* ) ;
	public final arrayValue_return arrayValue() throws RecognitionException {
		arrayValue_return retval = new arrayValue_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LC74=null;
		Token CA76=null;
		Token RC78=null;
		ParserRuleReturnScope value75 =null;
		ParserRuleReturnScope value77 =null;

		Object LC74_tree=null;
		Object CA76_tree=null;
		Object RC78_tree=null;
		RewriteRuleTokenStream stream_LC=new RewriteRuleTokenStream(adaptor,"token LC");
		RewriteRuleTokenStream stream_RC=new RewriteRuleTokenStream(adaptor,"token RC");
		RewriteRuleTokenStream stream_CA=new RewriteRuleTokenStream(adaptor,"token CA");
		RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:108:2: ( LC value ( CA value )* RC -> ^( ARRAYVALUE value ( value )* ) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:108:4: LC value ( CA value )* RC
			{
			LC74=(Token)match(input,LC,FOLLOW_LC_in_arrayValue749);  
			stream_LC.add(LC74);

			pushFollow(FOLLOW_value_in_arrayValue751);
			value75=value();
			state._fsp--;

			stream_value.add(value75.getTree());
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:108:13: ( CA value )*
			loop24:
			while (true) {
				int alt24=2;
				int LA24_0 = input.LA(1);
				if ( (LA24_0==CA) ) {
					alt24=1;
				}

				switch (alt24) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:108:14: CA value
					{
					CA76=(Token)match(input,CA,FOLLOW_CA_in_arrayValue754);  
					stream_CA.add(CA76);

					pushFollow(FOLLOW_value_in_arrayValue756);
					value77=value();
					state._fsp--;

					stream_value.add(value77.getTree());
					}
					break;

				default :
					break loop24;
				}
			}

			RC78=(Token)match(input,RC,FOLLOW_RC_in_arrayValue760);  
			stream_RC.add(RC78);

			// AST REWRITE
			// elements: value, value
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 108:28: -> ^( ARRAYVALUE value ( value )* )
			{
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:108:31: ^( ARRAYVALUE value ( value )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARRAYVALUE, "ARRAYVALUE"), root_1);
				adaptor.addChild(root_1, stream_value.nextTree());
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:108:50: ( value )*
				while ( stream_value.hasNext() ) {
					adaptor.addChild(root_1, stream_value.nextTree());
				}
				stream_value.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arrayValue"


	public static class value_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "value"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:111:1: value : ( code | arrayValue | STRING_LITERAL | Ref ! formula );
	public final value_return value() throws RecognitionException {
		value_return retval = new value_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STRING_LITERAL81=null;
		Token Ref82=null;
		ParserRuleReturnScope code79 =null;
		ParserRuleReturnScope arrayValue80 =null;
		ParserRuleReturnScope formula83 =null;

		Object STRING_LITERAL81_tree=null;
		Object Ref82_tree=null;

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:112:2: ( code | arrayValue | STRING_LITERAL | Ref ! formula )
			int alt25=4;
			switch ( input.LA(1) ) {
			case IDENTIFIER:
			case Number:
				{
				alt25=1;
				}
				break;
			case LC:
				{
				alt25=2;
				}
				break;
			case STRING_LITERAL:
				{
				alt25=3;
				}
				break;
			case Ref:
				{
				alt25=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}
			switch (alt25) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:112:4: code
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_code_in_value783);
					code79=code();
					state._fsp--;

					adaptor.addChild(root_0, code79.getTree());

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:113:4: arrayValue
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_arrayValue_in_value788);
					arrayValue80=arrayValue();
					state._fsp--;

					adaptor.addChild(root_0, arrayValue80.getTree());

					}
					break;
				case 3 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:114:4: STRING_LITERAL
					{
					root_0 = (Object)adaptor.nil();


					STRING_LITERAL81=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_value793); 
					STRING_LITERAL81_tree = (Object)adaptor.create(STRING_LITERAL81);
					adaptor.addChild(root_0, STRING_LITERAL81_tree);

					}
					break;
				case 4 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:115:4: Ref ! formula
					{
					root_0 = (Object)adaptor.nil();


					Ref82=(Token)match(input,Ref,FOLLOW_Ref_in_value798); 
					pushFollow(FOLLOW_formula_in_value801);
					formula83=formula();
					state._fsp--;

					adaptor.addChild(root_0, formula83.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "value"


	public static class softs_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "softs"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:119:1: softs : LP soft ( CA soft )* RP -> ( soft )+ ;
	public final softs_return softs() throws RecognitionException {
		softs_return retval = new softs_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LP84=null;
		Token CA86=null;
		Token RP88=null;
		ParserRuleReturnScope soft85 =null;
		ParserRuleReturnScope soft87 =null;

		Object LP84_tree=null;
		Object CA86_tree=null;
		Object RP88_tree=null;
		RewriteRuleTokenStream stream_RP=new RewriteRuleTokenStream(adaptor,"token RP");
		RewriteRuleTokenStream stream_CA=new RewriteRuleTokenStream(adaptor,"token CA");
		RewriteRuleTokenStream stream_LP=new RewriteRuleTokenStream(adaptor,"token LP");
		RewriteRuleSubtreeStream stream_soft=new RewriteRuleSubtreeStream(adaptor,"rule soft");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:120:2: ( LP soft ( CA soft )* RP -> ( soft )+ )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:120:5: LP soft ( CA soft )* RP
			{
			LP84=(Token)match(input,LP,FOLLOW_LP_in_softs817);  
			stream_LP.add(LP84);

			pushFollow(FOLLOW_soft_in_softs819);
			soft85=soft();
			state._fsp--;

			stream_soft.add(soft85.getTree());
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:120:13: ( CA soft )*
			loop26:
			while (true) {
				int alt26=2;
				int LA26_0 = input.LA(1);
				if ( (LA26_0==CA) ) {
					alt26=1;
				}

				switch (alt26) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:120:14: CA soft
					{
					CA86=(Token)match(input,CA,FOLLOW_CA_in_softs822);  
					stream_CA.add(CA86);

					pushFollow(FOLLOW_soft_in_softs824);
					soft87=soft();
					state._fsp--;

					stream_soft.add(soft87.getTree());
					}
					break;

				default :
					break loop26;
				}
			}

			RP88=(Token)match(input,RP,FOLLOW_RP_in_softs828);  
			stream_RP.add(RP88);

			// AST REWRITE
			// elements: soft
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 120:27: -> ( soft )+
			{
				if ( !(stream_soft.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_soft.hasNext() ) {
					adaptor.addChild(root_0, stream_soft.nextTree());
				}
				stream_soft.reset();

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "softs"


	public static class soft_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "soft"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:123:1: soft : (f= ( ASC | DESC ) name -> ^( SOFT name $f) | name -> ^( SOFT name ) );
	public final soft_return soft() throws RecognitionException {
		soft_return retval = new soft_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token f=null;
		ParserRuleReturnScope name89 =null;
		ParserRuleReturnScope name90 =null;

		Object f_tree=null;
		RewriteRuleTokenStream stream_ASC=new RewriteRuleTokenStream(adaptor,"token ASC");
		RewriteRuleTokenStream stream_DESC=new RewriteRuleTokenStream(adaptor,"token DESC");
		RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:124:2: (f= ( ASC | DESC ) name -> ^( SOFT name $f) | name -> ^( SOFT name ) )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==ASC||LA28_0==DESC) ) {
				alt28=1;
			}
			else if ( (LA28_0==IDENTIFIER) ) {
				alt28=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:124:6: f= ( ASC | DESC ) name
					{
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:124:10: ( ASC | DESC )
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==ASC) ) {
						alt27=1;
					}
					else if ( (LA27_0==DESC) ) {
						alt27=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 27, 0, input);
						throw nvae;
					}

					switch (alt27) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:124:11: ASC
							{
							f=(Token)match(input,ASC,FOLLOW_ASC_in_soft852);  
							stream_ASC.add(f);

							}
							break;
						case 2 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:124:15: DESC
							{
							f=(Token)match(input,DESC,FOLLOW_DESC_in_soft854);  
							stream_DESC.add(f);

							}
							break;

					}

					pushFollow(FOLLOW_name_in_soft857);
					name89=name();
					state._fsp--;

					stream_name.add(name89.getTree());
					// AST REWRITE
					// elements: name, f
					// token labels: f
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleTokenStream stream_f=new RewriteRuleTokenStream(adaptor,"token f",f);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 124:26: -> ^( SOFT name $f)
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:124:29: ^( SOFT name $f)
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SOFT, "SOFT"), root_1);
						adaptor.addChild(root_1, stream_name.nextTree());
						adaptor.addChild(root_1, stream_f.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:125:4: name
					{
					pushFollow(FOLLOW_name_in_soft873);
					name90=name();
					state._fsp--;

					stream_name.add(name90.getTree());
					// AST REWRITE
					// elements: name
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 125:9: -> ^( SOFT name )
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:125:12: ^( SOFT name )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SOFT, "SOFT"), root_1);
						adaptor.addChild(root_1, stream_name.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "soft"


	protected static class code_scope {
		String value;
	}
	protected Stack<code_scope> code_stack = new Stack<code_scope>();

	public static class code_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "code"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:128:1: code : n= ( IDENTIFIER | Number ) (p= Point f= ( IDENTIFIER | Number ) )* ->;
	public final code_return code() throws RecognitionException {
		code_stack.push(new code_scope());
		code_return retval = new code_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token p=null;
		Token f=null;

		Object n_tree=null;
		Object p_tree=null;
		Object f_tree=null;
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleTokenStream stream_Point=new RewriteRuleTokenStream(adaptor,"token Point");


			code_stack.peek().value = "";

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:2: (n= ( IDENTIFIER | Number ) (p= Point f= ( IDENTIFIER | Number ) )* ->)
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:5: n= ( IDENTIFIER | Number ) (p= Point f= ( IDENTIFIER | Number ) )*
			{
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:9: ( IDENTIFIER | Number )
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==IDENTIFIER) ) {
				alt29=1;
			}
			else if ( (LA29_0==Number) ) {
				alt29=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}

			switch (alt29) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:10: IDENTIFIER
					{
					n=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_code910);  
					stream_IDENTIFIER.add(n);

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:23: Number
					{
					n=(Token)match(input,Number,FOLLOW_Number_in_code914);  
					stream_Number.add(n);

					}
					break;

			}

			 code_stack.peek().value += n.getText(); 
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:65: (p= Point f= ( IDENTIFIER | Number ) )*
			loop31:
			while (true) {
				int alt31=2;
				int LA31_0 = input.LA(1);
				if ( (LA31_0==Point) ) {
					alt31=1;
				}

				switch (alt31) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:66: p= Point f= ( IDENTIFIER | Number )
					{
					p=(Token)match(input,Point,FOLLOW_Point_in_code924);  
					stream_Point.add(p);

					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:80: ( IDENTIFIER | Number )
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==IDENTIFIER) ) {
						alt30=1;
					}
					else if ( (LA30_0==Number) ) {
						alt30=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 30, 0, input);
						throw nvae;
					}

					switch (alt30) {
						case 1 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:81: IDENTIFIER
							{
							f=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_code931);  
							stream_IDENTIFIER.add(f);

							}
							break;
						case 2 :
							// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:135:94: Number
							{
							f=(Token)match(input,Number,FOLLOW_Number_in_code935);  
							stream_Number.add(f);

							}
							break;

					}

					 code_stack.peek().value += p.getText() + f.getText(); 
					}
					break;

				default :
					break loop31;
				}
			}

			// AST REWRITE
			// elements: 
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 136:2: ->
			{
				adaptor.addChild(root_0,  getTreeAdaptor().create(CODE, code_stack.peek().value) );
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			code_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "code"


	public static class name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "name"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:139:1: name : (n= IDENTIFIER -> ^( NAME $n) |r= IDENTIFIER Point n= IDENTIFIER -> ^( NAME $n $r) );
	public final name_return name() throws RecognitionException {
		name_return retval = new name_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token r=null;
		Token Point91=null;

		Object n_tree=null;
		Object r_tree=null;
		Object Point91_tree=null;
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleTokenStream stream_Point=new RewriteRuleTokenStream(adaptor,"token Point");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:140:2: (n= IDENTIFIER -> ^( NAME $n) |r= IDENTIFIER Point n= IDENTIFIER -> ^( NAME $n $r) )
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==IDENTIFIER) ) {
				int LA32_1 = input.LA(2);
				if ( (LA32_1==Point) ) {
					alt32=2;
				}
				else if ( (LA32_1==EOF||(LA32_1 >= AS && LA32_1 <= CA)||LA32_1==Condition||LA32_1==Eq||(LA32_1 >= Gp && LA32_1 <= GpEq)||(LA32_1 >= LP && LA32_1 <= Like)||(LA32_1 >= Ls && LA32_1 <= LsEq)||(LA32_1 >= NewLine && LA32_1 <= NotOin)||LA32_1==OIn||LA32_1==RC||LA32_1==RP) ) {
					alt32=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}

			switch (alt32) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:140:5: n= IDENTIFIER
					{
					n=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_name963);  
					stream_IDENTIFIER.add(n);

					// AST REWRITE
					// elements: n
					// token labels: n
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleTokenStream stream_n=new RewriteRuleTokenStream(adaptor,"token n",n);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 140:20: -> ^( NAME $n)
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:140:23: ^( NAME $n)
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NAME, "NAME"), root_1);
						adaptor.addChild(root_1, stream_n.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:141:4: r= IDENTIFIER Point n= IDENTIFIER
					{
					r=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_name980);  
					stream_IDENTIFIER.add(r);

					Point91=(Token)match(input,Point,FOLLOW_Point_in_name982);  
					stream_Point.add(Point91);

					n=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_name988);  
					stream_IDENTIFIER.add(n);

					// AST REWRITE
					// elements: r, n
					// token labels: r, n
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleTokenStream stream_r=new RewriteRuleTokenStream(adaptor,"token r",r);
					RewriteRuleTokenStream stream_n=new RewriteRuleTokenStream(adaptor,"token n",n);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 141:39: -> ^( NAME $n $r)
					{
						// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:141:42: ^( NAME $n $r)
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NAME, "NAME"), root_1);
						adaptor.addChild(root_1, stream_n.nextNode());
						adaptor.addChild(root_1, stream_r.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "name"


	public static class operator_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "operator"
	// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:145:1: operator : n= ( GpEq | LsEq | NotOin | Not | NotLike | OIn | Like | Eq | Gp | Ls ) -> ^( OPERATOR $n) ;
	public final operator_return operator() throws RecognitionException {
		operator_return retval = new operator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;

		Object n_tree=null;
		RewriteRuleTokenStream stream_Eq=new RewriteRuleTokenStream(adaptor,"token Eq");
		RewriteRuleTokenStream stream_GpEq=new RewriteRuleTokenStream(adaptor,"token GpEq");
		RewriteRuleTokenStream stream_LsEq=new RewriteRuleTokenStream(adaptor,"token LsEq");
		RewriteRuleTokenStream stream_NotOin=new RewriteRuleTokenStream(adaptor,"token NotOin");
		RewriteRuleTokenStream stream_OIn=new RewriteRuleTokenStream(adaptor,"token OIn");
		RewriteRuleTokenStream stream_NotLike=new RewriteRuleTokenStream(adaptor,"token NotLike");
		RewriteRuleTokenStream stream_Like=new RewriteRuleTokenStream(adaptor,"token Like");
		RewriteRuleTokenStream stream_Gp=new RewriteRuleTokenStream(adaptor,"token Gp");
		RewriteRuleTokenStream stream_Ls=new RewriteRuleTokenStream(adaptor,"token Ls");
		RewriteRuleTokenStream stream_Not=new RewriteRuleTokenStream(adaptor,"token Not");

		try {
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:2: (n= ( GpEq | LsEq | NotOin | Not | NotLike | OIn | Like | Eq | Gp | Ls ) -> ^( OPERATOR $n) )
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:4: n= ( GpEq | LsEq | NotOin | Not | NotLike | OIn | Like | Eq | Gp | Ls )
			{
			// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:8: ( GpEq | LsEq | NotOin | Not | NotLike | OIn | Like | Eq | Gp | Ls )
			int alt33=10;
			switch ( input.LA(1) ) {
			case GpEq:
				{
				alt33=1;
				}
				break;
			case LsEq:
				{
				alt33=2;
				}
				break;
			case NotOin:
				{
				alt33=3;
				}
				break;
			case Not:
				{
				alt33=4;
				}
				break;
			case NotLike:
				{
				alt33=5;
				}
				break;
			case OIn:
				{
				alt33=6;
				}
				break;
			case Like:
				{
				alt33=7;
				}
				break;
			case Eq:
				{
				alt33=8;
				}
				break;
			case Gp:
				{
				alt33=9;
				}
				break;
			case Ls:
				{
				alt33=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}
			switch (alt33) {
				case 1 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:9: GpEq
					{
					n=(Token)match(input,GpEq,FOLLOW_GpEq_in_operator1019);  
					stream_GpEq.add(n);

					}
					break;
				case 2 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:16: LsEq
					{
					n=(Token)match(input,LsEq,FOLLOW_LsEq_in_operator1023);  
					stream_LsEq.add(n);

					}
					break;
				case 3 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:23: NotOin
					{
					n=(Token)match(input,NotOin,FOLLOW_NotOin_in_operator1027);  
					stream_NotOin.add(n);

					}
					break;
				case 4 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:32: Not
					{
					n=(Token)match(input,Not,FOLLOW_Not_in_operator1031);  
					stream_Not.add(n);

					}
					break;
				case 5 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:38: NotLike
					{
					n=(Token)match(input,NotLike,FOLLOW_NotLike_in_operator1035);  
					stream_NotLike.add(n);

					}
					break;
				case 6 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:48: OIn
					{
					n=(Token)match(input,OIn,FOLLOW_OIn_in_operator1039);  
					stream_OIn.add(n);

					}
					break;
				case 7 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:54: Like
					{
					n=(Token)match(input,Like,FOLLOW_Like_in_operator1043);  
					stream_Like.add(n);

					}
					break;
				case 8 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:61: Eq
					{
					n=(Token)match(input,Eq,FOLLOW_Eq_in_operator1047);  
					stream_Eq.add(n);

					}
					break;
				case 9 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:66: Gp
					{
					n=(Token)match(input,Gp,FOLLOW_Gp_in_operator1051);  
					stream_Gp.add(n);

					}
					break;
				case 10 :
					// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:71: Ls
					{
					n=(Token)match(input,Ls,FOLLOW_Ls_in_operator1055);  
					stream_Ls.add(n);

					}
					break;

			}

			// AST REWRITE
			// elements: n
			// token labels: n
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleTokenStream stream_n=new RewriteRuleTokenStream(adaptor,"token n",n);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 146:76: -> ^( OPERATOR $n)
			{
				// E:\\Lemsun\\LemsunApplication\\formula\\Server\\ServerFormula.g:146:79: ^( OPERATOR $n)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
				adaptor.addChild(root_1, stream_n.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "operator"

	// Delegated rules


	protected DFA6 dfa6 = new DFA6(this);
	protected DFA16 dfa16 = new DFA16(this);
	protected DFA17 dfa17 = new DFA17(this);
	static final String DFA6_eotS =
		"\16\uffff";
	static final String DFA6_eofS =
		"\1\uffff\2\7\6\uffff\3\7\2\uffff";
	static final String DFA6_minS =
		"\1\31\2\6\3\31\2\uffff\1\31\2\6\1\10\2\uffff";
	static final String DFA6_maxS =
		"\1\51\2\62\1\51\1\31\1\51\2\uffff\1\51\3\62\2\uffff";
	static final String DFA6_acceptS =
		"\6\uffff\1\1\1\2\4\uffff\2\1";
	static final String DFA6_specialS =
		"\16\uffff}>";
	static final String[] DFA6_transitionS = {
			"\1\1\17\uffff\1\2",
			"\1\4\1\uffff\1\7\6\uffff\1\7\4\uffff\1\10\2\uffff\2\7\2\uffff\2\6\3"+
			"\uffff\1\7\1\6\1\7\1\5\1\uffff\4\7\1\uffff\1\7\1\uffff\1\3\1\7\2\uffff"+
			"\1\7\1\uffff\1\6",
			"\1\4\1\uffff\1\7\6\uffff\1\7\4\uffff\1\10\2\uffff\2\7\2\uffff\2\6\3"+
			"\uffff\1\7\1\6\1\7\1\5\1\uffff\4\7\1\uffff\1\7\1\uffff\1\3\1\7\2\uffff"+
			"\1\7\1\uffff\1\6",
			"\1\11\17\uffff\1\12",
			"\1\13",
			"\1\14\17\uffff\1\15",
			"",
			"",
			"\1\14\17\uffff\1\15",
			"\1\4\1\uffff\1\7\6\uffff\1\7\4\uffff\1\10\2\uffff\2\7\2\uffff\2\15\3"+
			"\uffff\1\7\1\15\1\7\1\5\1\uffff\4\7\1\uffff\1\7\1\uffff\1\3\1\7\2\uffff"+
			"\1\7\1\uffff\1\6",
			"\1\4\1\uffff\1\7\6\uffff\1\7\4\uffff\1\10\2\uffff\2\7\2\uffff\2\15\3"+
			"\uffff\1\7\1\15\1\7\1\5\1\uffff\4\7\1\uffff\1\7\1\uffff\1\3\1\7\2\uffff"+
			"\1\7\1\uffff\1\6",
			"\1\7\6\uffff\1\7\4\uffff\1\10\2\uffff\2\7\2\uffff\2\15\3\uffff\1\7\1"+
			"\15\1\7\1\5\1\uffff\4\7\1\uffff\1\7\2\uffff\1\7\2\uffff\1\7\1\uffff\1"+
			"\6",
			"",
			""
	};

	static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
	static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
	static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
	static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
	static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
	static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
	static final short[][] DFA6_transition;

	static {
		int numStates = DFA6_transitionS.length;
		DFA6_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
		}
	}

	protected class DFA6 extends DFA {

		public DFA6(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 6;
			this.eot = DFA6_eot;
			this.eof = DFA6_eof;
			this.min = DFA6_min;
			this.max = DFA6_max;
			this.accept = DFA6_accept;
			this.special = DFA6_special;
			this.transition = DFA6_transition;
		}
		@Override
		public String getDescription() {
			return "42:1: formula : ( refs r= ( Remote | Local ) cols ( expres )? ( softs )? -> ^( FORMULA refs $r cols ( expres )? ( softs )? ) | ref -> ^( FORMULA ref ) );";
		}
	}

	static final String DFA16_eotS =
		"\135\uffff";
	static final String DFA16_eofS =
		"\3\uffff\1\13\22\uffff\2\13\1\uffff\1\13\12\uffff\1\13\17\uffff\1\13\12"+
		"\uffff\2\13\3\uffff\1\13\30\uffff";
	static final String DFA16_minS =
		"\2\31\1\uffff\1\6\1\31\1\uffff\1\6\1\31\1\7\2\31\2\uffff\1\54\3\31\2\uffff"+
		"\1\54\1\31\2\6\1\7\1\31\1\6\1\31\2\6\1\7\1\31\1\6\1\10\2\31\2\7\1\55\1"+
		"\31\1\54\3\31\1\7\1\55\2\31\2\6\1\10\1\31\1\6\1\7\1\31\2\6\1\10\1\31\1"+
		"\6\1\7\1\10\2\31\2\7\1\54\2\31\1\7\1\55\1\31\1\54\2\31\1\10\1\6\1\31\2"+
		"\6\2\10\1\31\2\6\1\10\1\31\1\55\1\31\1\55\1\31\1\6\2\10";
	static final String DFA16_maxS =
		"\2\67\1\uffff\1\60\1\31\1\uffff\1\60\1\31\1\66\2\31\2\uffff\1\55\1\35"+
		"\2\31\2\uffff\1\55\1\66\3\60\1\31\1\60\1\31\3\60\1\31\1\60\1\55\1\51\1"+
		"\31\1\35\1\60\1\55\1\31\1\55\2\31\1\35\1\60\1\55\1\31\1\51\3\60\1\31\2"+
		"\60\1\31\3\60\1\31\2\60\1\55\1\31\1\35\2\60\1\55\2\31\1\60\1\55\1\31\1"+
		"\55\2\31\2\60\1\31\4\60\1\31\3\60\1\31\1\55\1\31\1\55\1\31\3\60";
	static final String DFA16_acceptS =
		"\2\uffff\1\4\2\uffff\1\1\5\uffff\1\5\1\6\4\uffff\1\2\1\3\112\uffff";
	static final String DFA16_specialS =
		"\135\uffff}>";
	static final String[] DFA16_transitionS = {
			"\1\3\3\uffff\1\4\1\uffff\1\1\27\uffff\1\2",
			"\1\6\3\uffff\1\7\31\uffff\1\5",
			"",
			"\1\12\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\1\10"+
			"\1\13\1\uffff\2\13\1\uffff\4\13\1\uffff\1\13\1\uffff\1\11\1\13\2\uffff"+
			"\1\13",
			"\1\15",
			"",
			"\1\20\1\22\1\21\26\uffff\1\16\14\uffff\1\17\3\uffff\1\21",
			"\1\23",
			"\1\13\10\uffff\1\13\10\uffff\1\25\3\uffff\1\24\13\uffff\1\13\7\uffff"+
			"\1\13\4\uffff\1\13",
			"\1\26",
			"\1\27",
			"",
			"",
			"\1\30\1\31",
			"\1\33\3\uffff\1\32",
			"\1\34",
			"\1\35",
			"",
			"",
			"\1\36\1\37",
			"\1\40\3\uffff\1\13\13\uffff\1\13\7\uffff\1\13\4\uffff\1\13",
			"\1\42\1\uffff\1\43\13\uffff\1\13\2\uffff\2\13\7\uffff\1\13\1\uffff\2"+
			"\13\2\uffff\3\13\1\uffff\1\13\1\uffff\1\41\3\uffff\1\44",
			"\1\12\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\2\13"+
			"\1\uffff\2\13\1\uffff\4\13\1\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
			"\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\2\13\1\uffff"+
			"\2\13\1\uffff\4\13\1\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
			"\1\45",
			"\1\46\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\2\13"+
			"\1\uffff\2\13\1\uffff\4\13\1\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
			"\1\47",
			"\1\51\1\uffff\1\52\43\uffff\1\50\3\uffff\1\53",
			"\1\20\1\22\1\21\47\uffff\1\21",
			"\1\22\1\21\47\uffff\1\21",
			"\1\54",
			"\1\55\1\22\1\21\47\uffff\1\21",
			"\1\13\43\uffff\1\56\1\57",
			"\1\60\17\uffff\1\13",
			"\1\61",
			"\1\13\10\uffff\1\13\10\uffff\1\63\3\uffff\1\62",
			"\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\2\13\1\uffff"+
			"\2\13\1\uffff\4\13\1\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
			"\1\31",
			"\1\64",
			"\1\65\1\66",
			"\1\67",
			"\1\70",
			"\1\72\3\uffff\1\71",
			"\1\22\1\21\47\uffff\1\21",
			"\1\37",
			"\1\73",
			"\1\74\17\uffff\1\13",
			"\1\75\1\uffff\1\76\47\uffff\1\77",
			"\1\42\1\uffff\1\43\13\uffff\1\13\2\uffff\2\13\7\uffff\1\13\1\uffff\2"+
			"\13\2\uffff\3\13\1\uffff\1\13\1\uffff\1\13\3\uffff\1\44",
			"\1\76\47\uffff\1\100",
			"\1\101",
			"\1\103\1\uffff\1\43\43\uffff\1\102\3\uffff\1\104",
			"\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\2\13\1\uffff"+
			"\2\13\1\uffff\4\13\1\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
			"\1\105",
			"\1\106\1\uffff\1\52\47\uffff\1\53",
			"\1\51\1\uffff\1\52\47\uffff\1\53",
			"\1\52\47\uffff\1\53",
			"\1\107",
			"\1\111\1\uffff\1\52\43\uffff\1\110\3\uffff\1\53",
			"\1\22\1\21\47\uffff\1\21",
			"\1\13\43\uffff\1\13\1\57",
			"\1\112",
			"\1\113\3\uffff\1\62",
			"\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\2\13\1\uffff"+
			"\2\13\1\uffff\4\13\1\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
			"\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\2\13\1\uffff"+
			"\2\13\1\uffff\4\13\1\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
			"\1\114\1\115",
			"\1\116",
			"\1\117",
			"\1\14\1\13\6\uffff\1\13\4\uffff\1\13\2\uffff\2\13\6\uffff\2\13\1\uffff"+
			"\2\13\1\uffff\4\13\1\uffff\1\13\2\uffff\1\13\2\uffff\1\13",
			"\1\66",
			"\1\120",
			"\1\121\1\122",
			"\1\123",
			"\1\124",
			"\1\76\47\uffff\1\100",
			"\1\103\1\uffff\1\76\43\uffff\1\125\3\uffff\1\100",
			"\1\126",
			"\1\127\1\uffff\1\76\47\uffff\1\100",
			"\1\103\1\uffff\1\43\47\uffff\1\104",
			"\1\76\47\uffff\1\100",
			"\1\52\47\uffff\1\53",
			"\1\130",
			"\1\131\1\uffff\1\52\47\uffff\1\53",
			"\1\111\1\uffff\1\52\47\uffff\1\53",
			"\1\52\47\uffff\1\53",
			"\1\132",
			"\1\115",
			"\1\133",
			"\1\122",
			"\1\134",
			"\1\103\1\uffff\1\76\47\uffff\1\100",
			"\1\76\47\uffff\1\100",
			"\1\52\47\uffff\1\53"
	};

	static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
	static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
	static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
	static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
	static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
	static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
	static final short[][] DFA16_transition;

	static {
		int numStates = DFA16_transitionS.length;
		DFA16_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
		}
	}

	protected class DFA16 extends DFA {

		public DFA16(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 16;
			this.eot = DFA16_eot;
			this.eof = DFA16_eof;
			this.min = DFA16_min;
			this.max = DFA16_max;
			this.accept = DFA16_accept;
			this.special = DFA16_special;
			this.transition = DFA16_transition;
		}
		@Override
		public String getDescription() {
			return "77:1: cols : ( LP Start RP -> ^( COLS Start ) | LP col ( CA col )* RP -> ^( COLS ( col )+ ) | LP col '-' col RP -> ^( COLRANGE ( col )+ ) | Start -> ^( COLS Start ) | col ( CA col )* -> ^( COLS ( col )+ ) | col '-' col -> ^( COLRANGE ( col )+ ) );";
		}
	}

	static final String DFA17_eotS =
		"\24\uffff";
	static final String DFA17_eofS =
		"\1\uffff\1\2\22\uffff";
	static final String DFA17_minS =
		"\1\31\1\6\1\uffff\1\7\1\31\1\6\1\10\1\31\1\uffff\1\7\1\uffff\1\31\3\6"+
		"\1\10\1\uffff\1\31\1\uffff\1\6";
	static final String DFA17_maxS =
		"\1\35\1\60\1\uffff\2\66\1\60\1\55\1\51\1\uffff\1\35\1\uffff\1\51\3\60"+
		"\1\55\1\uffff\1\31\1\uffff\1\60";
	static final String DFA17_acceptS =
		"\2\uffff\1\2\5\uffff\1\1\1\uffff\1\1\5\uffff\1\1\1\uffff\1\1\1\uffff";
	static final String DFA17_specialS =
		"\24\uffff}>";
	static final String[] DFA17_transitionS = {
			"\1\1\3\uffff\1\2",
			"\3\2\6\uffff\1\2\4\uffff\1\2\2\uffff\2\2\6\uffff\1\3\1\2\1\uffff\2\2"+
			"\1\uffff\4\2\1\uffff\1\2\1\uffff\2\2\2\uffff\1\2",
			"",
			"\1\2\10\uffff\1\2\10\uffff\1\5\3\uffff\1\4\13\uffff\1\2\7\uffff\1\2"+
			"\4\uffff\1\2",
			"\1\6\3\uffff\1\2\13\uffff\1\2\7\uffff\1\2\4\uffff\1\2",
			"\1\10\1\uffff\1\11\13\uffff\1\2\2\uffff\2\2\7\uffff\1\2\1\uffff\2\2"+
			"\2\uffff\3\2\1\uffff\1\2\1\uffff\1\7\3\uffff\1\12",
			"\1\2\43\uffff\1\13\1\14",
			"\1\15\17\uffff\1\2",
			"",
			"\1\2\10\uffff\1\2\10\uffff\1\16\3\uffff\1\12",
			"",
			"\1\17\17\uffff\1\2",
			"\1\12\1\uffff\1\12\47\uffff\1\20",
			"\1\10\1\uffff\1\11\13\uffff\1\2\2\uffff\2\2\7\uffff\1\2\1\uffff\2\2"+
			"\2\uffff\3\2\1\uffff\1\2\1\uffff\1\2\3\uffff\1\12",
			"\1\20\1\uffff\1\11\43\uffff\1\21\3\uffff\1\22",
			"\1\2\43\uffff\1\2\1\14",
			"",
			"\1\23",
			"",
			"\1\22\1\uffff\1\11\47\uffff\1\22"
	};

	static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
	static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
	static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
	static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
	static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
	static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
	static final short[][] DFA17_transition;

	static {
		int numStates = DFA17_transitionS.length;
		DFA17_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
		}
	}

	protected class DFA17 extends DFA {

		public DFA17(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 17;
			this.eot = DFA17_eot;
			this.eof = DFA17_eof;
			this.min = DFA17_min;
			this.max = DFA17_max;
			this.accept = DFA17_accept;
			this.special = DFA17_special;
			this.transition = DFA17_transition;
		}
		@Override
		public String getDescription() {
			return "86:1: col : ( funcols | colname );";
		}
	}

	public static final BitSet FOLLOW_statement_in_statements110 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_NewLine_in_statements113 = new BitSet(new long[]{0x0000022002000000L});
	public static final BitSet FOLLOW_statement_in_statements116 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_EOF_in_statements120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_formula_in_statement141 = new BitSet(new long[]{0x000005CD01900000L});
	public static final BitSet FOLLOW_operator_in_statement143 = new BitSet(new long[]{0x0000020002000000L});
	public static final BitSet FOLLOW_formula_in_statement145 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_formula_in_formulaEof168 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_formulaEof170 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_refs_in_formula181 = new BitSet(new long[]{0x0004000200000000L});
	public static final BitSet FOLLOW_Remote_in_formula188 = new BitSet(new long[]{0x00800000A2000000L});
	public static final BitSet FOLLOW_Local_in_formula190 = new BitSet(new long[]{0x00800000A2000000L});
	public static final BitSet FOLLOW_cols_in_formula193 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_expres_in_formula195 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_softs_in_formula198 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ref_in_formula224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ref_in_refs245 = new BitSet(new long[]{0x0000000818100002L});
	public static final BitSet FOLLOW_set_in_refs248 = new BitSet(new long[]{0x0000020002000000L});
	public static final BitSet FOLLOW_ref_in_refs264 = new BitSet(new long[]{0x0000000838100002L});
	public static final BitSet FOLLOW_join_in_refs266 = new BitSet(new long[]{0x0000000818100002L});
	public static final BitSet FOLLOW_refname_in_ref286 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_AS_in_ref289 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_ref295 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LC_in_join321 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_refexpre_in_join323 = new BitSet(new long[]{0x0000200000008000L});
	public static final BitSet FOLLOW_Condition_in_join326 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_refexpre_in_join328 = new BitSet(new long[]{0x0000200000008000L});
	public static final BitSet FOLLOW_RC_in_join332 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_in_refexpre358 = new BitSet(new long[]{0x000005CD01900000L});
	public static final BitSet FOLLOW_operator_in_refexpre360 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_name_in_refexpre362 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_refname403 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_Number_in_refname405 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_Point_in_refname415 = new BitSet(new long[]{0x0000020002000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_refname422 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_Number_in_refname424 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_LP_in_cols449 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_Start_in_cols451 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_RP_in_cols453 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LP_in_cols468 = new BitSet(new long[]{0x0000000022000000L});
	public static final BitSet FOLLOW_col_in_cols470 = new BitSet(new long[]{0x0001000000000100L});
	public static final BitSet FOLLOW_CA_in_cols473 = new BitSet(new long[]{0x0000000022000000L});
	public static final BitSet FOLLOW_col_in_cols475 = new BitSet(new long[]{0x0001000000000100L});
	public static final BitSet FOLLOW_RP_in_cols479 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LP_in_cols494 = new BitSet(new long[]{0x0000000022000000L});
	public static final BitSet FOLLOW_col_in_cols496 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_ASC_in_cols498 = new BitSet(new long[]{0x0000000022000000L});
	public static final BitSet FOLLOW_col_in_cols500 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_RP_in_cols502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Start_in_cols516 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_col_in_cols531 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_CA_in_cols534 = new BitSet(new long[]{0x0000000022000000L});
	public static final BitSet FOLLOW_col_in_cols536 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_col_in_cols554 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_ASC_in_cols556 = new BitSet(new long[]{0x0000000022000000L});
	public static final BitSet FOLLOW_col_in_cols558 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_funcols_in_col580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_colname_in_col585 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_funcols601 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_LP_in_funcols603 = new BitSet(new long[]{0x0000000022000000L});
	public static final BitSet FOLLOW_colname_in_funcols605 = new BitSet(new long[]{0x0001000000000100L});
	public static final BitSet FOLLOW_CA_in_funcols608 = new BitSet(new long[]{0x0000000022000000L});
	public static final BitSet FOLLOW_colname_in_funcols610 = new BitSet(new long[]{0x0001000000000100L});
	public static final BitSet FOLLOW_RP_in_funcols614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LC_in_colname639 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_name_in_colname642 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RC_in_colname644 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_AS_in_colname648 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_colname651 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_in_colname658 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_AS_in_colname661 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_colname664 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LP_in_expres678 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_expre_in_expres680 = new BitSet(new long[]{0x0001000000008000L});
	public static final BitSet FOLLOW_Condition_in_expres683 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_expre_in_expres685 = new BitSet(new long[]{0x0001000000008000L});
	public static final BitSet FOLLOW_RP_in_expres689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LP_in_expres709 = new BitSet(new long[]{0x0042020022000000L});
	public static final BitSet FOLLOW_value_in_expres711 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_RP_in_expres713 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_in_expre732 = new BitSet(new long[]{0x000005CD01900000L});
	public static final BitSet FOLLOW_operator_in_expre734 = new BitSet(new long[]{0x0042020022000000L});
	public static final BitSet FOLLOW_value_in_expre737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LC_in_arrayValue749 = new BitSet(new long[]{0x0042020022000000L});
	public static final BitSet FOLLOW_value_in_arrayValue751 = new BitSet(new long[]{0x0000200000000100L});
	public static final BitSet FOLLOW_CA_in_arrayValue754 = new BitSet(new long[]{0x0042020022000000L});
	public static final BitSet FOLLOW_value_in_arrayValue756 = new BitSet(new long[]{0x0000200000000100L});
	public static final BitSet FOLLOW_RC_in_arrayValue760 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_code_in_value783 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arrayValue_in_value788 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_value793 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Ref_in_value798 = new BitSet(new long[]{0x0000020002000000L});
	public static final BitSet FOLLOW_formula_in_value801 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LP_in_softs817 = new BitSet(new long[]{0x0000000002010080L});
	public static final BitSet FOLLOW_soft_in_softs819 = new BitSet(new long[]{0x0001000000000100L});
	public static final BitSet FOLLOW_CA_in_softs822 = new BitSet(new long[]{0x0000000002010080L});
	public static final BitSet FOLLOW_soft_in_softs824 = new BitSet(new long[]{0x0001000000000100L});
	public static final BitSet FOLLOW_RP_in_softs828 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASC_in_soft852 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_DESC_in_soft854 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_name_in_soft857 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_in_soft873 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_code910 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_Number_in_code914 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_Point_in_code924 = new BitSet(new long[]{0x0000020002000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_code931 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_Number_in_code935 = new BitSet(new long[]{0x0000100000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_name963 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_name980 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_Point_in_name982 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_name988 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GpEq_in_operator1019 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LsEq_in_operator1023 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NotOin_in_operator1027 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Not_in_operator1031 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NotLike_in_operator1035 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OIn_in_operator1039 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Like_in_operator1043 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Eq_in_operator1047 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Gp_in_operator1051 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Ls_in_operator1055 = new BitSet(new long[]{0x0000000000000002L});
}

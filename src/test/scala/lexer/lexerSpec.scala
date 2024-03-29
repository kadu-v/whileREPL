package lexer

import org.scalatest._

class LexerSpec extends FlatSpec with Matchers {

  // 変数の字句解析
  "parse 'hoge'" should "Identifier(hoge)" in {
    val code = "hoge"
    WhileLexer.apply(code).right.get shouldBe (List(IDENTIFIER(code)))
  }

  "parse 'h0'" should "Identifier(h0)" in {
    val code = "h0"
    WhileLexer.apply(code).right.get shouldBe (List(IDENTIFIER("h0")))
  }

  // 数字の字句解析
  "parse 0" should "INT(0)" in {
    val code = "0"
    WhileLexer.apply(code).right.get shouldBe (List(INT(0)))
  }

  "parse -10" should "INT(-10)" in {
    val code = "-10"
    WhileLexer.apply(code).right.get shouldBe (List(INT(-10)))
  }

  // 演算子のテスト
  // ASSIGNのテスト
  "parse :=" should "ASSIGN" in {
    val code = ":="
    WhileLexer.apply(code).right.get shouldBe (List(ASSIGN))
  }

  // ADDのテスト
  "parse +" should "ADD" in {
    val code = "+"
    WhileLexer.apply(code).right.get shouldBe (List(ADD))
  }

  // SUBのテスト
  "parse -" should "SUB" in {
    val code = "-"
    WhileLexer.apply(code).right.get shouldBe (List(SUB))
  }

  // MULのテスト
  "parse *" should "MUL" in {
    val code = "*"
    WhileLexer.apply(code).right.get shouldBe (List(MUL))
  }

  // ANDのテスト
  "parse &&" should "AND" in {
    val code = "&&"
    WhileLexer.apply(code).right.get shouldBe (List(AND))
  }

  // NOTのテスト
  "parse !" should "NOT" in {
    val code = "!"
    WhileLexer.apply(code).right.get shouldBe (List(NOT))
  }

  // EQのテスト
  "parse =" should "EQ" in {
    val code = "="
    WhileLexer.apply(code).right.get shouldBe (List(EQ))
  }

  // LEQのテスト
  "parse <=" should "LEQ" in {
    val code = "<="
    WhileLexer.apply(code).right.get shouldBe (List(LEQ))
  }

  // SEMICOLONのテスト
  "parse :" should "SEMICOLON" in {
    val code = ";"
    WhileLexer.apply(code).right.get shouldBe (List(SEMICOLON))
  }

  // LPARENのテスト
  "parse (" should "LPAREN" in {
    val code = "("
    WhileLexer.apply(code).right.get shouldBe (List(LPAREN))
  }

  // RPARENのテスト
  "parse )" should "RPAREN" in {
    val code = ")"
    WhileLexer.apply(code).right.get shouldBe (List(RPAREN))
  }

  // WHILEのテスト
  "parse while" should "WHILE" in {
    val code = "while"
    WhileLexer.apply(code).right.get shouldBe (List(WHILE))
  }

  // DOのテスト
  "parse do" should "DO" in {
    val code = "do"
    WhileLexer.apply(code).right.get shouldBe (List(DO))
  }

  // ENDのテスト
  "parse end" should "END" in {
    val code = "end"
    WhileLexer.apply(code).right.get shouldBe (List(END))
  }

  // SKIPのテスト
  "parse skip" should "skip" in {
    val code = "skip"
    WhileLexer.apply(code).right.get shouldBe (List(SKIP))
  }

  // IFのテスト
  "parse if" should "IF" in {
    val code = "if"
    WhileLexer.apply(code).right.get shouldBe (List(IF))
  }

  // THENのテスト
  "parse then" should "THEN" in {
    val code = "then"
    WhileLexer.apply(code).right.get shouldBe (List(THEN))
  }

  // ELSEのテスト
  "parse else" should "ELSE" in {
    val code = "else"
    WhileLexer.apply(code).right.get shouldBe (List(ELSE))
  }

  // TRUEのテスト
  "parse true" should "TRUE" in {
    val code = "true"
    WhileLexer.apply(code).right.get shouldBe (List(TRUE))
  }

  // FALSEのテスト
  "parse false" should "FALSE" in {
    val code = "false"
    WhileLexer.apply(code).right.get shouldBe (List(FALSE))
  }

  // 大きめのテスト
  "big test case" should "ok" in {
    val code = """hoge := -1;
              | foo := (1+1);
              | true
              | false
              | x := 5;
              | y := 1;
              | while false do 
              | y := y * x;
              | x := x - 1;
              | end
              | if false then
              | x := 1;
              | else 
              | skip
              | end""".stripMargin
    val expect = List(
      IDENTIFIER("hoge"),
      ASSIGN,
      INT(-1),
      SEMICOLON,
      IDENTIFIER("foo"),
      ASSIGN,
      LPAREN,
      INT(1),
      ADD,
      INT(1),
      RPAREN,
      SEMICOLON,
      TRUE,
      FALSE,
      IDENTIFIER("x"),
      ASSIGN,
      INT(5),
      SEMICOLON,
      IDENTIFIER("y"),
      ASSIGN,
      INT(1),
      SEMICOLON,
      WHILE,
      FALSE,
      DO,
      IDENTIFIER("y"),
      ASSIGN,
      IDENTIFIER("y"),
      MUL,
      IDENTIFIER("x"),
      SEMICOLON,
      IDENTIFIER("x"),
      ASSIGN,
      IDENTIFIER("x"),
      SUB,
      INT(1),
      SEMICOLON,
      END,
      IF,
      FALSE,
      THEN,
      IDENTIFIER("x"),
      ASSIGN,
      INT(1),
      SEMICOLON,
      ELSE,
      SKIP,
      END
    )

    WhileLexer.apply(code).right.get shouldBe (expect)
  }
}

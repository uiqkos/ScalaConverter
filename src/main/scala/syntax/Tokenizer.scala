package syntax

import Syntax._

object Tokenizer {
  def tokenize[TToken <: Token, TObject <: Object, TValue <: Value](text: String, syntax: Syntax[TToken, TObject, TValue]): List[TToken] = {
    var token: Token = null;
    var tokens: List[TToken] = List();
    var value: String = "";

    for (char <- text) {
      if (!syntax.isIgnoredSymbol(char) || value.nonEmpty) {
        token = syntax.parseToken(char)

        if (token != null) {
          if (value.nonEmpty) {
            tokens :+= syntax.parseTokenValue(value)
            value = ""
          }
          tokens :+= token.asInstanceOf[TToken]

        } else {
          value += char
        }

      }
    }

    tokens
  }
}

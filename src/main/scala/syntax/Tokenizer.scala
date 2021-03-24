package syntax

import Syntax._

object Tokenizer {
  def tokenize(text: String, syntax: Syntax): List[Token] = {
    var token: Token = null;
    var tokens: List[Token] = List();
    var value: String = "";

    for (char <- text) {
      if (!syntax.isIgnoredSymbol(char) || value.nonEmpty) {
        token = syntax.parseToken(char)

        if (token != null) {
          if (value.nonEmpty) {
            tokens :+= syntax.parseTokenValue(value)
            value = ""
          }
          tokens :+= token

        } else {
          value += char
        }

      }
    }

    tokens
  }
}

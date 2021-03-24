package syntax.json

import syntax.Syntax.Token

object JsonUtils {
  implicit class Tokens(tokens: List[Token]) {
    def toJsonTokens: List[JsonToken] = tokens.map(_.asInstanceOf[JsonToken])
  }
}

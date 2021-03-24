package syntax.json

import syntax.Syntax._
import syntax.json.JsonTokenType.JsonTokenType

case class JsonToken(value: String, tokenType: JsonTokenType) extends Token


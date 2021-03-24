package syntax.json

import syntax.Token
import syntax.json.JsonTokenType.JsonTokenType

case class JsonToken(value: String, tokenType: JsonTokenType) extends Token


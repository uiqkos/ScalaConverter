package syntax.json

import syntax.json.JsonValueSingleType.{JsonValueSingleType, TYPED, UNTYPED}
import JsonTokenType._
import syntax.Syntax._

class JsonValueSingle(value: String, valueType: JsonValueSingleType) extends JsonValue {
  override def toString: String =
    if (valueType == TYPED) value
    else '"' + value + '"'
}

object JsonValueSingle {
  def fromTokens(tokens: List[JsonToken]): (JsonValueSingle, List[JsonToken]) = {
    if (tokens.head.tokenType == QUOTE)
      (new JsonValueSingle(
        tokens(1).value,
        UNTYPED
      ), tokens.drop(3))
    else (new JsonValueSingle(tokens.head.value, TYPED), tokens.drop(1))
  }
}

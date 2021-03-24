package syntax.json

import syntax.Token
import syntax.Tokenizer.tokenize
import syntax.Utils._
import syntax.json.JsonTokenType._


class JsonValueList(var values: List[JsonValue]) extends JsonValue {
  override def toString: String =
    "{\n " +
    f"${values.mkString(",\n")}"
    "}"
}

object JsonValueList {
  def fromTokens(tokens: List[JsonToken]): (JsonValueList, List[JsonToken]) = {
    //todo
  }
}

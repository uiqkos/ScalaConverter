package syntax.json

class JsonValueList(var values: List[JsonValue]) extends JsonValue {
  override def toString: String =
    "{\n " +
    f"${values.mkString(",\n")}"
    "}"
}

object JsonValueList {
  def fromTokens(tokens: List[JsonToken]): (JsonValueList, List[JsonToken]) = {
    //todo
    null
  }
}

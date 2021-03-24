package syntax.json

import JsonUtils.Tokens

class JsonValueBlock(var objects: List[JsonObject] = List()) extends JsonValue {
  override def toString: String =
    "{\n " +
    f"${objects.mkString(",\n")}\n" +
    "}"

}

object JsonValueBlock {
  def fromTokens(tokens: List[JsonToken]): (JsonValueBlock, List[JsonToken]) = {
    // todo: maybe recursion
    var objects: List[JsonObject] = List()
    var nextTokens = tokens.drop(1) // drop BLOCK_BEGIN
    while (true) {
      var (jsonObject, extraTokens) = JsonSyntax.parseObject(nextTokens)
      objects :+= jsonObject

      if (extraTokens.isEmpty || extraTokens.head.asInstanceOf[JsonToken].tokenType != JsonTokenType.DELIMITER)
        return (new JsonValueBlock(objects), extraTokens.drop(1).toJsonTokens) // drop BLOCK_END

      nextTokens = extraTokens.drop(1).toJsonTokens
    }
    (new JsonValueBlock(objects), List())
  }
}
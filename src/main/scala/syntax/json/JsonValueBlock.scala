package syntax.json

import syntax.Token
import syntax.Tokenizer.tokenize
import syntax.Utils.Custom
import syntax.json.JsonTokenType.DELIMITER

import scala.util.control.Breaks.break


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
    var nextTokens = tokens
    while (true) {
      var (jsonObject, extraTokens) = JsonSyntax.parseObject(nextTokens)
      objects :+= jsonObject

      if (extraTokens.isEmpty || extraTokens.head.tokenType != JsonTokenType.DELIMITER)
        return (new JsonValueBlock(objects), extraTokens)

      nextTokens = extraTokens.drop(1)
    }
    (new JsonValueBlock(objects), List())
  }
}
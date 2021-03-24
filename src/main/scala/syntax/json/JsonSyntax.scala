package syntax.json

import syntax.Syntax._
import syntax.json.JsonTokenType._
import syntax.json.JsonUtils.Tokens

object JsonSyntax extends Syntax[JsonToken, JsonObject, JsonValue] {
  private val ignored: List[Char] = List(' ', '\n', '\u0000', '\t')
  private val opened: List[JsonTokenType] = List(BLOCK_BEGIN, LIST_BEGIN, QUOTE)
  private val closed: List[JsonTokenType] = List(BLOCK_END, LIST_END, QUOTE)

  override def isIgnoredSymbol(char: Char): Boolean = ignored.contains(char)
  override def isOpened(token: JsonToken): Boolean = opened.contains(token.tokenType)
  override def isClosed(token: JsonToken): Boolean = closed.contains(token.tokenType)

  override def parseToken(char: Char): JsonToken = {
    val tokenType = parseTokenType(char)
    if (tokenType == UNKNOWN) return null
    JsonToken(char.toString, tokenType)
  }

  def parseTokenType(char: Char): JsonTokenType = char match {
    case '{' => BLOCK_BEGIN
    case '}' => BLOCK_END
    case '[' => LIST_BEGIN
    case ']' => LIST_END
    case '"' => QUOTE
    case ',' => DELIMITER
    case ':' => ASSIGMENT
    case _ => UNKNOWN
  }

  override def parseTokenValue(text: String): JsonToken = JsonToken(text, VALUE)

  override def parseObject(tokens: List[JsonToken]): (JsonObject, List[JsonToken]) = {
    val (name, (value, extraTokens)) = (tokens.toJsonTokens(1).value, parseValue(tokens.drop(4)))
    (JsonObject(name, value), extraTokens)
  }

  override def parseValue(tokens: List[JsonToken]): (JsonValue, List[JsonToken]) = {
    val jsonTokens = tokens.toJsonTokens

    jsonTokens.head.tokenType match {
      case BLOCK_BEGIN => JsonValueBlock.fromTokens(jsonTokens)
      case LIST_BEGIN => JsonValueList.fromTokens(jsonTokens)
      case _ => JsonValueSingle.fromTokens(jsonTokens)
    }
  }

  //todo wtf??
  override def isPairedTokens(token1: JsonToken, token2: JsonToken): Boolean =
    Tuple2(
      token1.asInstanceOf[JsonToken].tokenType,
      token2.asInstanceOf[JsonToken].tokenType
    ) match {
      case Tuple2(BLOCK_BEGIN, BLOCK_END) => true
      case Tuple2(BLOCK_END, BLOCK_BEGIN) => true
      case Tuple2(LIST_BEGIN, LIST_END) => true
      case Tuple2(LIST_END, LIST_BEGIN) => true
      case Tuple2(QUOTE, QUOTE) => true
      case _ => false
    }
}

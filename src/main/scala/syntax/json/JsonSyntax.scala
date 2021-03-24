package syntax.json

import syntax._
import JsonTokenType._
import JsonValueSingleType._
import Utils.Custom

import java.util.Collections
import scala.collection.mutable
import scala.collection.mutable.Stack

object JsonSyntax extends Syntax {
  private val ignored: List[Char] = List(' ', '\n', '\u0000', '\t')
  private val opened: List[JsonTokenType] = List(BLOCK_BEGIN, LIST_BEGIN, QUOTE)
  private val closed: List[JsonTokenType] = List(BLOCK_END, LIST_END, QUOTE)

  override def isIgnoredSymbol(char: Char): Boolean = ignored.contains(char)

  override def parseToken(char: Char): Token = {
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
//    case '@' => ATTRIBUTE
//    case '#' => ELEMENT
    case _ => UNKNOWN
  }

  override def parseTokenValue(text: String): Token = JsonToken(text, VALUE)

  /*override*/ def parseObject(tokens: List[JsonToken]): (JsonObject, List[JsonToken]) = {
    val jsonTokens = tokens.drop(4)
    val (jsonValue, nextTokens) = parseValue(jsonTokens)
    (JsonObject(jsonTokens(1).value, jsonValue), nextTokens)
  }

  def fromTo(tokens: List[JsonToken]): List[JsonToken] = {
    var stack: mutable.Stack[(Token, Int)] = new mutable.Stack()
    for ((token, i) <- tokens.zipWithIndex) {
      if (isClosed(token) && isPairedTokens(stack.top._1, token)) {
        val begin = stack.pop()
        if (stack.isEmpty) {
          return tokens.slice(begin._2 + 1, begin._2 + i)
        }
      } else if (isOpened(token))
        stack = stack.push((token, i))

    }
    println(tokens)
    tokens
  }

  /*override*/ def parseValue(tokens: List[Token]): (JsonValue, List[JsonToken]) = {
    val jsonTokens = tokens.map(_.asInstanceOf[JsonToken])

    jsonTokens.head.tokenType match {
      case BLOCK_BEGIN => JsonValueBlock.fromTokens(jsonTokens)
      case LIST_BEGIN => JsonValueList.fromTokens(jsonTokens)
      case _ => JsonValueSingle.fromTokens(jsonTokens)
    }

    /*var stack: mutable.Stack[(JsonToken, Int)] = new mutable.Stack()
    for ((token, i) <- tokens.zipWithIndex) {
      if (isClosed(token) && isPairedTokens(stack.top._1, token)) {
        val begin = stack.pop()
        if (stack.isEmpty) {
          return token.tokenType match {
            case QUOTE => JsonValueSingle.fromTokens()
            case BLOCK_BEGIN =>
          }
        }
      } else if (isOpened(token)) {
        stack = stack.push((token, i))
      }

    }
    println(tokens)
    tokens*/
  }

  //todo wtf??
  override def isPairedTokens(token1: Token, token2: Token): Boolean =
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


  def isOpened(token: Token): Boolean = opened.contains(token.asInstanceOf[JsonToken].tokenType)
  def isClosed(token: Token): Boolean = closed.contains(token.asInstanceOf[JsonToken].tokenType)
}

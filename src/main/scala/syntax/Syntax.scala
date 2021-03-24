package syntax

object Syntax {
  abstract class Syntax[TToken <: Token, TObject <: Object, TValue <: Value] {
    def isIgnoredSymbol(char: Char): Boolean
    def isPairedTokens(token1: TToken, token2: TToken): Boolean
    def isOpened(token: TToken): Boolean
    def isClosed(token: TToken): Boolean

    def parseToken(char: Char): TToken
    def parseTokenValue(text: String): TToken

    def parseObject(tokens: List[TToken]): (TObject, List[TToken])
    def parseValue(tokens: List[TToken]): (TValue, List[TToken])
  }

  abstract class Parsable {
    def toString: String
  }

  abstract class Value extends Parsable
  abstract class Object extends Parsable

  abstract class Token
}

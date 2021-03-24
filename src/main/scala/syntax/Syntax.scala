package syntax

abstract class  Syntax {
  def isIgnoredSymbol(char: Char): Boolean

  def parseToken(char: Char): Token
  def parseTokenValue(text: String): Token

  def parseObject(tokens: List[Token]): (Object, List[Token])
  def parseValue(tokens: List[Token]): (Value, List[Token])

  def isPairedTokens(token1: Token, token2: Token): Boolean
}
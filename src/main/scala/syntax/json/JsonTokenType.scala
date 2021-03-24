package syntax.json

object JsonTokenType extends Enumeration {
  type JsonTokenType = Value
  val QUOTE,
      ASSIGMENT,
      VALUE,
      BLOCK_BEGIN,
      BLOCK_END,
      LIST_BEGIN,
      LIST_END,
      DELIMITER,
      UNKNOWN = Value
}

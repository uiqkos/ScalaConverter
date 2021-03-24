package syntax.json

object JsonValueSingleType extends Enumeration {
  type JsonValueSingleType = Value
  val ELEMENT,
      ATTRIBUTE,
      TYPED,
      UNTYPED = Value
}

package syntax.json

case class JsonObject(var name: String, var value: JsonValue) extends syntax.Object {
  override def toString: String =
    "\"%s\" : %s" format(name, value)
}

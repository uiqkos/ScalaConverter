package syntax.json

import syntax.Syntax.Object

case class JsonObject(var name: String, var value: JsonValue) extends Object {
  override def toString: String =
    "\"%s\" : %s" format(name, value)
}

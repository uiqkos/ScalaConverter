import syntax.Tokenizer
import syntax.Tokenizer.tokenize
import syntax.json.{JsonObject, JsonSyntax, JsonToken, JsonValue}
import syntax.json.JsonSyntax.parseValue

import scala.io.Source
import scala.io.Source.fromResource
import scala.util.control.Breaks.break
import scala.util.control.ControlThrowable

object Main extends App {
//  println(JsonSyntax.parseValue(Tokenizer.tokenize[JsonToken, JsonObject, JsonValue](Source.fromResource("1.json").getLines.mkString, JsonSyntax)))

  throw new ControlThrowable() {}
}
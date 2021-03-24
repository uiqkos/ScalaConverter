import syntax.Tokenizer
import syntax.json.JsonSyntax

import scala.io.Source
import scala.reflect.io.File

object Main extends App {
  println(JsonSyntax.parseValue(Tokenizer.tokenize(Source.fromResource("1.json").getLines.mkString, JsonSyntax)))
}

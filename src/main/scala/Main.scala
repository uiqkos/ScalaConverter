import syntax.Tokenizer
import syntax.Tokenizer.tokenize
import syntax.json.JsonSyntax
import syntax.json.JsonSyntax.parseValue

import scala.io.Source
import scala.io.Source.fromResource
import scala.reflect.io.File

case class A(a: Int, b: Int)

object Custom {
  implicit class CustomA(a: A) extends Iterable[Int] {
    override def iterator: Iterator[Int] = List(a.a, a.b).iterator
  }
}

object Main extends App {
  println(JsonSyntax.parseValue(Tokenizer.tokenize(Source.fromResource("1.json").getLines.mkString, JsonSyntax)))
  new {
    new {
      new {
        new {
          new {

          }
        }
      }
    }
  }

  import Custom._

  A(2, 4).foreach(println)

}

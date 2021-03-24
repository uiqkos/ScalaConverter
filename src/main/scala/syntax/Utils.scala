package syntax

import scala.collection.Iterable

object Utils {

  implicit class Custom[T1, T2](list: Iterable[T1]) {
    def splitBy(delimiter: T2, converter: T1 => T2): Iterable[Iterable[T1]] = {
      var counter = 0
      list.groupBy(t =>
        if (converter(t) != delimiter) counter
        else {
          counter += 1
          -1
        }
      ).filter(_._1 != -1).values
    }
  }

}

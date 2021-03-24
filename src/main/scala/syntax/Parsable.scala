package syntax

abstract class  Parsable {
  def toString: String
}

abstract class  Value extends Parsable
abstract class  Object extends Parsable
package models

case class Name(firstName: String, lastName: String) {
  override def toString: String = s"$firstName $lastName"
}

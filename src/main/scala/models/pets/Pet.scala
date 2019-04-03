package models.pets

case class Pet(name: String, petType: PetType)

sealed trait PetType

case object Dog extends PetType
case object Cat extends PetType
case object Fish extends PetType
case object Snake extends PetType

package ProductTypes

import play.api.libs.json.JsValue

class Item (json: JsValue) {
  val name: String = new String

  val price: Double = new Double

  val ingredients: List[String] = List()

  val location: String = new String

  val nutrients: List[Map[String, String]] = List()
}
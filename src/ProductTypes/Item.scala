package ProductTypes

import play.api.libs.json.{JsArray, JsObject, JsValue, Json}

class Item (json: JsValue, user: Users.User) {
  var sku: String = json("sku").as[String]

  var name: String = json("name").as[String]

  var price: Double = {
    val url = "https://api.wegmans.io/products/" + sku + "/prices?api-version=2018-10-18&subscription-key=68527dbd17d345e18b45513c9a60782a"
    val data = Json.parse(scala.io.Source.fromURL(url).mkString)
    val stores = Json.arr(data("stores"))
    0
  }
  var ingredients: List[String] = json("ingredients").as[String].split(",").toList

  var location: String = new String

  var nutrients: List[Map[String, String]] = List()
}

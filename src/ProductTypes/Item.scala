package ProductTypes

import play.api.libs.json.{JsValue, Json}

class Item (json: JsValue) {
  val sku: String = json("sku").as[String]

  val name: String = json("name").as[String]

  val price: Double = {
    val url = "https://api.wegmans.io/products/" + sku + "/prices?api-version=2018-10-18&subscription-key=68527dbd17d345e18b45513c9a60782a"
    val data = Json.parse(scala.io.Source.fromURL(url).mkString)

    data("store").head("price").as[Double]
  }

  val ingredients: List[String] = json("ingredients").as[String].split(",").toList

  val location: String = new String

  val nutrients: List[Map[String, String]] = List()
}
//recommit
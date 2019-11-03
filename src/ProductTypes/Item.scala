package ProductTypes

import play.api.libs.json.{JsArray, JsObject, JsValue, Json}

class Item (json: JsValue, user: Users.User) {
  val name: String = json("name").as[String]

  var sku: String = (json \ "sku").as[Int].toString

  var price: Double = {
    val url = "https://api.wegmans.io/products/" + sku +
      "/prices?api-version=2018-10-18&subscription-key=68527dbd17d345e18b45513c9a60782a"
    val data = Json.parse(scala.io.Source.fromURL(url).mkString)
    val stores = (data \ "stores").as[Array[JsValue]]
    for (store <- stores) if (store("store").as[Int] == user.storeNum) price = store("price").as[Double]
    -1
  }

  var ingredients: Array[String] = (json \ "ingredients").as[Array[String]]


  var location: String = {
    val url = "https://api.wegmans.io/products/" + sku + "/locations/" +
      user.storeNum.toString + "?api-version=2018-10-18&subscription-key=68527dbd17d345e18b45513c9a60782a"
    val data = Json.parse(scala.io.Source.fromURL(url).mkString)
    val mapped = data.as[Map[String, JsValue]]
    if (mapped.contains("error")) "Sorry! This item is not in stock"
    else {
      val loc = mapped("locations")(0).as[Map[String, JsValue]]
      var ret = "Aisle Name: " + loc("name").toString
      if (loc("aisleSide") != null) ret += " Aisle Side: " + loc("aisleSide").toString
      ret += " Shelf Number: " + loc("shelfNumber").toString
      ret
    }
  }
}
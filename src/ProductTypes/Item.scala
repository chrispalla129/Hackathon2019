package ProductTypes

import play.api.libs.json.{JsArray, JsObject, JsValue, Json}

class Item (json: JsValue, user: Users.User) {
  var sku: String = json("sku").as[String]

  var name: String = json("name").as[String]

  var price: Double = {
    val url = "https://api.wegmans.io/products/" + sku + "/prices?api-version=2018-10-18&subscription-key=68527dbd17d345e18b45513c9a60782a"
    val data = Json.parse(scala.io.Source.fromURL(url).mkString)
    val stores = Json.arr(data("stores")).as[Array[JsValue]]
    for (store <- stores) if (store("store").as[Int] == user.storeNum) price = store("price").as[Double]
    -2
  }

  var ingredients: Array[String] = Json.arr(json("ingredients")).as[Array[String]]

  var location: String = {
    val url = "https://api.wegmans.io/products/" + sku + "/locations/" +
      user.storeNum.toString + "?api-version=2018-10-18&subscription-key=68527dbd17d345e18b45513c9a60782a"
    val data = Json.parse(scala.io.Source.fromURL(url).mkString)

    if (data.as[Map[String, JsValue]].contains("error")) "Sorry! This item is not in stock"
    else {
      val loc = data("locations")
      var ret = "Aisle Name: " + loc("name")
      if (loc("aisleSide") != Json.toJson(null)) ret += " Aisle Side: " + loc("aisleSide").toString
      ret += " Shelf Number: " + loc("shelfNumber").toString
      ret
    }
  }
}

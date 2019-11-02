package DataProcess
import ProductTypes.Item
import play.api.libs.json.{JsObject, Json}

import scala.collection.mutable
import scala.io.Source._


object ParseEmpData {
  def addItem(dMap: mutable.Map[String, Item], sku: String, name: String): Unit ={
    val url = "https://api.wegmans.io/products/" + sku + "?api-version=2018-10-18&Subscription-Key=da8f3095e1e94773add7ab4cb71eacc1"
    var data = scala.io.Source.fromURL(url).mkString
    dMap(name) = new Item(Json.parse(data))
  }
  def sendCoffeeData(): String = {
    var cItems = mutable.Map[String, Item]()

    addItem(cItems, "175410", "Hazelnut Syrup")
    addItem(cItems, "785244", "White Chocolate Syrup")


    return ""
  }
}

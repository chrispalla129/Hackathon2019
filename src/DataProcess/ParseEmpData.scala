package DataProcess
import ProductTypes.Item
import play.api.libs.json.{JsObject, Json}

import scala.collection.mutable
import scala.io.Source._


object ParseEmpData {
  def sendCoffeeData(): String = {
    val url = "https://api.wegmans.io/products/175410?api-version=2018-10-18&Subscription-Key=da8f3095e1e94773add7ab4cb71eacc1"
    var data = scala.io.Source.fromURL(url).mkString
    var cItems = mutable.Map[String, Item]()

    cItems("Hazelnut Syrup") = new Item(Json.parse(data))


    return ""
  }
}

package DataProcess
import ProductTypes.Item
import Users.User
import play.api.libs.json.{JsArray, JsNumber, JsObject, JsString, JsValue, Json}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer



object ParseEmpData {
  def addItem(dMap: mutable.Map[String, Item], sku: String, name: String, user: User): Unit ={
    val url = "https://api.wegmans.io/products/" + sku + "?api-version=2018-10-18&Subscription-Key=da8f3095e1e94773add7ab4cb71eacc1"
    var data = scala.io.Source.fromURL(url).mkString
    dMap(name) = new Item(Json.parse(data), user)
  }
  def sendCoffeeData(user: User): String = {
    var cItems = mutable.Map[String, Item]()

    addItem(cItems, "175410", "Hazelnut Syrup", user)
    addItem(cItems, "785244", "White Chocolate Syrup", user)
    addItem(cItems, "131543", "Whole Milk", user)
    addItem(cItems, "94598", "Whipped Cream", user)
    addItem(cItems, "788204", "Bourbon Pecan Syrup", user)

    addItem(cItems, "788204", "Espresso", user)
    cItems("Espresso").price = -1
    cItems("Espresso").sku = ""                                                           //Done manually as there is no data for employee-only items.
    cItems("Espresso").ingredients = Array.empty
    cItems("Espresso").location = "Kept under the espresso machine, more is in the back on the coffee shelf."

    var jList: ArrayBuffer[JsValue] = ArrayBuffer.empty

    var c = 0
    for(i <- cItems.values) {
      jList(0) = Json.obj(
        "sku" -> JsString(i.sku),
        "name" -> JsString(i.name),
        "price" -> JsNumber(i.price),
        "ingredients" -> Json.arr(i.ingredients)
      )
    }
    println(Json.stringify(JsArray(jList)))
    return Json.stringify(JsArray(jList))
  }
}

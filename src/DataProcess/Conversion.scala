package DataProcess

import ProductTypes.Item
import play.api.libs.json.{JsArray, JsNumber, JsString, JsValue, Json}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Conversion {
  def itemToJson(item: Item): String = {
    return Json.stringify(Json.obj(
      "sku" -> JsString(item.sku),
      "name" -> JsString(item.name),
      "price" -> JsNumber(item.price),
      "ingredients" -> Json.arr(item.ingredients)
    ))
  }
  def itemListToJson(cItems: mutable.Map[String, Item]): String = {
    var jList: ArrayBuffer[JsValue] = ArrayBuffer.empty

    for(i <- cItems.values) {
      jList += Json.obj(
        "sku" -> JsString(i.sku),
        "name" -> JsString(i.name),
        "price" -> JsNumber(i.price),
        "ingredients" -> Json.arr(i.ingredients)
      )
    }
    return Json.stringify(JsArray(jList))
  }

  def recipeToJson(): Unit ={

  }

}

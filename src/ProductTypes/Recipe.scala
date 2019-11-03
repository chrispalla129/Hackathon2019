package ProductTypes
import Users._
import play.api.libs.json.{JsArray, JsNull, JsObject, JsValue, Json}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Recipe(json: JsValue, User: Users.User) {
  var ingredients: List[(Int, Item)] = {
    val itemId = json("id").toString().replaceAll("\"","")
    val url: String = "https://api.wegmans.io/meals/recipes/" + itemId +
      "/?api-version=2018-10-18&subscription-key=da8f3095e1e94773add7ab4cb71eacc1"
    val data = scala.io.Source.fromURL(url).mkString

    val items = Json.parse(data)("ingredients").as[Array[JsValue]]
    var temp: List [(Int, Item)] = List()
    for (item <- items) {
      val num = item("skuQuantity").as[Int]
      val sku: String = item("sku").as[Int].toString.replace("\"","")
      val url: String = "https://api.wegmans.io/products/" + sku +
        "/?api-version=2018-10-18&subscription-key=da8f3095e1e94773add7ab4cb71eacc1"
      val data = scala.io.Source.fromURL(url).mkString
      val newItem = new Item(Json.parse(data), User)
      temp :+= (num, newItem)
    }
    temp
  }

  val cost: Double = {
    var acc: Double = 0
    for (item <- ingredients) acc += item._1 * item._2.price
    acc
  }
//please
  var instructions: Map[String, String] = {
    var temp: Map[String, String] = Map()
    val instruct = (json \ "instructions").as[Map[String, JsValue]]
    if (instruct.contains("testerTips") && instruct("testerTips")!=JsNull) {
      temp += ("Tester Tips" -> instruct("testerTips").as[String])
    }
    if (instruct.contains("equipment") && instruct("equipment")!=JsNull) {
      temp += ("Equipment" -> instruct("equipment").as[String])
    }
    if (instruct.contains("chefTips") && instruct("chefTips")!=JsNull) {
      temp += ("Chef Tips" -> instruct("chefTips").as[String])
    }
    if (instruct.contains("disclaimer") && instruct("disclaimer")!=JsNull) {
      temp += ("Disclaimer" -> instruct("disclaimer").as[String])
    }
    temp
  }

}
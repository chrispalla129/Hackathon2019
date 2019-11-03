package ProductTypes
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery
import com.ibm.watson.discovery.v1.model._
import com.ibm.cloud.sdk.core.http.HttpMediaType
import Users._
import play.api.libs.json.{JsArray, JsObject, JsValue, Json}

import scala.collection.mutable

class Recipe(User: User, string: JsValue) {
  var ingredients: List[(Int, Item)] = {
    val ingr: Array[JsValue] = Json.arr(string("ingredients")).as[Array[JsValue]]
    for (item <- ingr) {
      val num = item("skuQuantity").as[Int]

      val sku: String = item("sku").as[String]
      val url: String = "https://api.wegmans.io/products/" + sku + "?api-version=2018-10-18"
      val data = scala.io.Source.fromURL(url).mkString
      val newItem = new Item(Json.parse(data), User)
      ingredients :+= (num, newItem)
    }
    ingredients
  }

  val cost: Double = {
    var acc: Double = 0
    for (item <- ingredients) acc += item._1 * item._2.price
    acc
  }

  var instructions: Map[String, String] = {Map(
    "Directions" -> string("instructions")("directions").as[String])

    if (string("instructions")("testerTips")!= Json.toJson(null)) instructions += ("Tester Tips" -> string("instructions")("testerTips").as[String])
    if (string("instructions")("equipment")!= Json.toJson(null)) instructions += ("Tester Tips" -> string("instructions")("equipment").as[String])
    if (string("instructions")("chefTips") != Json.toJson(null)) instructions += ("Chef Tips" -> string("instructions")("chefTips").as[String])
    if (string("instructions")("disclaimer") != Json.toJson(null)) instructions += ("Disclaimer" -> string("instructions")("disclaimer").as[String])
    instructions
  }

}
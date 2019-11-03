package DataProcess

import ProductTypes.{Item, Recipe, ShoppingList}
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
  def stackItemToJson(sItem: List[(Int,Item)]): String ={
    var sList: ArrayBuffer[JsValue] = ArrayBuffer.empty
    for((i, j) <- sItem){
      sList += Json.obj(
        "quantity" -> JsNumber(i),
        "item" -> JsString(itemToJson(j))
      )
    }
    return Json.stringify(JsArray(sList))
  }
  def itemListToJson(cItems: mutable.Map[String, Item]): String = {
    var jList: ArrayBuffer[JsValue] = ArrayBuffer.empty
    for(i <- cItems.values)  jList += JsString(itemToJson(i))
    return Json.stringify(JsArray(jList))
  }

  def recipeToJson(recipe: Recipe): String = {
    var rList: ArrayBuffer[JsValue] = ArrayBuffer.empty
    for((i, j) <- recipe.ingredients){
      rList += Json.obj(
        "num" -> JsNumber(i),
        "item" -> JsString(itemToJson(j))
      )
    }
    return Json.stringify(JsArray(rList))
  }

  def recipeListToJson(rList: List[Recipe]): String = {
    var jList: ArrayBuffer[JsValue] = ArrayBuffer.empty
    for(i <- rList)  jList += JsString(recipeToJson(i))
    return Json.stringify(JsArray(jList))
  }

  def shoplistToJson(shoplist: ShoppingList): String = {
    var rList = recipeListToJson(shoplist.recipes)
    var iList = stackItemToJson(shoplist.items)
    return Json.stringify(Json.obj(
      "name" -> JsString(shoplist.name),
      "recipes" -> JsString(rList),
      "items" -> JsString(iList),
      "cost" -> JsNumber(shoplist.cost)
    ))
  }

}

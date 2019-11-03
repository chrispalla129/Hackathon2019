package DataProcess

import ProductTypes._
import Users._
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable
object Search {
  //return List[Item]
  var rNum: mutable.Map[String, Int] = mutable.Map[String, Int]()
  /*def suggestRecipe(item: String): List[Recipe] = {
    val url = "https://api.wegmans.io/meals/recipes/search?query=" + item + "&api-version=2018-10-18&results=10&page=1&subscription-key=da8f3095e1e94773add7ab4cb71eacc1"
    val data = Json.parse(get(url))
    val lData = (data \ "results").as[Array[JsValue]]
    for(i <- lData){
      val mapDat = i.as[Map[String, String]]
      val id = mapDat("id")
      if(rNum.contains(id)) rNum(id) += 1
      else rNum(id) = 1
    }

    val sorted = rNum.toArray.sortBy(_._2):_*
    var bigThree: (String, String, String) = ("", "", "")
    if(rNum.length > 3) bigThree = (sorted(sorted.length-3)._1, sorted(sorted.length-2)._1, sorted(sorted.length-1)._1)
    else return null

    val r1: Recipe = new Recipe(new Customer, Json.parse(get("https://api.wegmans.io/meals/recipes/" + bigThree._3 + "/?api-version=2018-10-18&subscription-key=da8f3095e1e94773add7ab4cb71eacc1")))
    val r2: Recipe =  new Recipe(new Customer, Json.parse(get("https://api.wegmans.io/meals/recipes/" + bigThree._2 + "/?api-version=2018-10-18&subscription-key=da8f3095e1e94773add7ab4cb71eacc1")))
    val r3: Recipe =  new Recipe(new Customer, Json.parse(get("https://api.wegmans.io/meals/recipes/" + bigThree._1 + "/?api-version=2018-10-18&subscription-key=da8f3095e1e94773add7ab4cb71eacc1")))
    return List(r1, r2, r3)
  }

   */
  def searchProduct(input: String):List[Item] = {
    val url = "https://api.wegmans.io/products/search?query="+ input +"&api-version=2018-10-18&results=10&subscription-key=da8f3095e1e94773add7ab4cb71eacc1"
    val data = Json.parse(get(url))
    val list = (data \ "results").as[Array[JsValue]]
    val customer = new Customer
    var ret: List[Item] = List()

    for(i<-list){
      val j = new Item(i, customer)
      ret :+= j
    }
    ret
  }
  def searchRecipe(input: String):List[Recipe] ={
    val url = "https://api.wegmans.io/meals/recipes/search?query="+ input +"&api-version=2018-10-18&results=100&page=1&subscription-key=da8f3095e1e94773add7ab4cb71eacc1"
    val data = Json.parse(get(url))
    val list = (data \ "results").as[Array[JsValue]]
    val customer = new Customer
    var ret: List[Recipe] = List()

    for(i<-list){
      val j = new Recipe(i, customer)
      ret :+= j
    }
    ret
  }
  def get(url: String,
          requestMethod: String = "GET"): String =
  {
    import java.net.{URL, HttpURLConnection}
    val connection = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
    connection.setRequestMethod(requestMethod)
    val inputStream = connection.getInputStream
    val content = io.Source.fromInputStream(inputStream).mkString
    if (inputStream != null) inputStream.close
    content
  }
}

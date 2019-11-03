package DataProcess

import ProductTypes._
import Users._
import play.api.libs.json.{JsValue, Json}

object Search {
  //return List[Item]
  def searchProduct(input: String):List[Item] ={
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
      val j = new Recipe(customer, i)
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

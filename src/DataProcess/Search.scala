package DataProcess

import ProductTypes._
import play.api.libs.json.{JsValue, Json}

object Search {
  //return List[Item]
  def search(input: String) ={
    val url = "https://api.wegmans.io/products/" + input + "&api-version=2018-10-18&subscription-key=da8f3095e1e94773add7ab4cb71eacc1"
    val data = Json.parse(scala.io.Source.fromURL(url).mkString)
    val list = (data \ "results")
    println(list)

  }
  def get(url: String,
          requestMethod: String = "GET") =
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

package ProductTypes
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery
import com.ibm.watson.discovery.v1.Discovery
import play.api.libs.json.{JsObject, JsValue}

class Recipe(json: JsValue){

  var items: Map[String, (Int, Item)] = Map()

  var cost: Double = {
    var acc = 0
    for (item <- this.items.values) acc += item._2.price
    acc
  }

  var directions: String = new String
  val authenticator = new IamAuthenticator("{apikey}")
  val discovery = new Discovery("{version}", authenticator)
  discovery.setServiceUrl("{url}")
}

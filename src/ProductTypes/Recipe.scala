package ProductTypes
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery
import com.ibm.watson.discovery.v1.model._
import com.ibm.cloud.sdk.core.http.HttpMediaType
import Users._
import play.api.libs.json.JsValue

import scala.collection.mutable

class Recipe(User: User, string: JsValue) {
  val ingredients: List[Item] = {
    List()
  }
}

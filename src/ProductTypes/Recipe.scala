package ProductTypes
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery

class Recipe {
  val key: String ="_NFPz15p1P1hKI19A5cW7xx-rZ0BClaOBl6A_s4SEnJN"
  val site: String = "https://gateway.watsonplatform.net/discovery/api"
  val authenticator = new IamAuthenticator(key)
  val discovery = new Discovery("2019-04-30", authenticator)
  discovery.setServiceUrl(site)
}

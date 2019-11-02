package ProductTypes
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery

import com.ibm.watson.discovery.v1.Discovery

class Recipe {



  val authenticator = new IamAuthenticator("{apikey}")
  val discovery = new Discovery("{version}", authenticator)
  discovery.setServiceUrl("{url}")
}

package ProductTypes

import Users._
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery
import com.ibm.watson.discovery.v1.model.{CreateEnvironmentOptions, Environment}

object truongTest {
  def main(args: Array[String]): Unit = {
    val fakeuse: User = new Users.User {
      override def userType: String = "Customer"
    }
    val test: Recipe = new Recipe(fakeuse)
    println(test.queryResponse.getResults)
  }
}

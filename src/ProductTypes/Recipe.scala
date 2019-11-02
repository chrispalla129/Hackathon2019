package ProductTypes
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery
import com.ibm.watson.discovery.v1.model._
import com.ibm.cloud.sdk.core.http.HttpMediaType
import Users._

import scala.collection.mutable

class Recipe(User: User) {
  val key: String ="_NFPz15p1P1hKI19A5cW7xx-rZ0BClaOBl6A_s4SEnJN"
  val site: String = "https://gateway.watsonplatform.net/discovery/api"
  val authenticator = new IamAuthenticator(key)
  val discovery = new Discovery("2019-04-30", authenticator)
  discovery.setServiceUrl(site)

  //environment builder
  val environmentName = "User"
  val environmentDesc = "Customer/Employee"
  val createOptionsBuilder = new CreateEnvironmentOptions.Builder(environmentName)
  createOptionsBuilder.description(environmentDesc)
  val environ: Environment = discovery.createEnvironment(createOptionsBuilder.build).execute.getResult

  //collections builder
  val createCollectionBuilder= new CreateCollectionOptions.Builder(environ.getEnvironmentId, "Main")
  val collect: Collection = discovery.createCollection(createCollectionBuilder.build).execute.getResult

//gets wegmans api
  val url = "https://api.wegmans.io/products/175410?api-version=2018-10-18&Subscription-Key=da8f3095e1e94773add7ab4cb71eacc1"
  var data: String = scala.io.Source.fromURL(url).mkString

  val builder = new AddDocumentOptions.Builder(environ.getEnvironmentId, collect.getCollectionId)
  builder.fileContentType(data)
  builder.fileContentType(HttpMediaType.APPLICATION_JSON)
  val response: DocumentAccepted = discovery.addDocument(builder.build).execute.getResult

  //query builder
  val queryBuilder = new QueryOptions.Builder(environ.getEnvironmentId, collect.getCollectionId)
  queryBuilder.query("{field}:{value}")
  val queryResponse: QueryResponse = discovery.query(queryBuilder.build).execute.getResult
}

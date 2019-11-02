package ProductTypes
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery
import com.ibm.watson.discovery.v1.model._
import com.ibm.cloud.sdk.core.http.HttpMediaType
import Users._
import play.api.libs.json.JsValue

import scala.collection.mutable

class Recipe(User: User) {
  val ingredients: List[List[Item]] = List()
  private val key: String ="_NFPz15p1P1hKI19A5cW7xx-rZ0BClaOBl6A_s4SEnJN"
  private val site: String = "https://gateway.watsonplatform.net/discovery/api"
  private val authenticator = new IamAuthenticator(key)
  private val discovery = new Discovery("2019-04-30", authenticator)
  discovery.setServiceUrl(site)

  //environment builder
  private val environmentName = "User"
  private val environmentDesc = "Customer/Employee"
  private val createOptionsBuilder = new CreateEnvironmentOptions.Builder(environmentName)
  createOptionsBuilder.description(environmentDesc)
  private val environ: Environment = discovery.createEnvironment(createOptionsBuilder.build).execute.getResult

  //collections builder
  private val createCollectionBuilder= new CreateCollectionOptions.Builder(environ.getEnvironmentId, "Main")
  private val collect: Collection = discovery.createCollection(createCollectionBuilder.build).execute.getResult

  //gets wegmans api
  private val url = "https://api.wegmans.io/meals/recipes?api-version=2018-10-18&Subscription-Key=da8f3095e1e94773add7ab4cb71eacc1"
  private val data: String = scala.io.Source.fromURL(url).mkString

  private val builder = new AddDocumentOptions.Builder(environ.getEnvironmentId, collect.getCollectionId)
  builder.fileContentType(data)
  builder.fileContentType(HttpMediaType.APPLICATION_JSON)
  private val response: DocumentAccepted = discovery.addDocument(builder.build).execute.getResult

  //query builder
  private val queryBuilder = new QueryOptions.Builder(environ.getEnvironmentId, collect.getCollectionId)
  private val recipename = "Spaghetti"
  queryBuilder.query("{name:{Spaghetti}")
  val queryResponse: QueryResponse = discovery.query(queryBuilder.build).execute.getResult

}

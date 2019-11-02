package ProductTypes
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.discovery.v1.Discovery
import com.ibm.watson.discovery.v1.model._
import com.ibm.cloud.sdk.core.http.HttpMediaType
import java.io.ByteArrayInputStream
import java.io.InputStream
import Users._

class Recipe(User: User) {
  val key: String ="_NFPz15p1P1hKI19A5cW7xx-rZ0BClaOBl6A_s4SEnJN"
  val site: String = "https://gateway.watsonplatform.net/discovery/api"
  val authenticator = new IamAuthenticator(key)
  val discovery = new Discovery("2019-04-30", authenticator)
  discovery.setServiceUrl(site)

  val environmentName = "User"
  val environmentDesc = "Customer/Employee"

  val createOptionsBuilder = new CreateEnvironmentOptions.Builder(environmentName)
  createOptionsBuilder.description(environmentDesc)
  val createResponse: Environment = discovery.createEnvironment(createOptionsBuilder.build).execute.getResult

//gets wegmans api

//  val environmentId = "{environment_id}"
//  val collectionId = "{collection_id}"
//  val documentId = "{document_id}"
//  val documentJson = "{\"field\":\"value\"}"
//  val documentStream = new ByteArrayInputStream(documentJson.getBytes)

  //gets document data

//  val builder = new AddDocumentOptions.Builder(environmentId, collectionId)
//  builder.file(documentStream)
//  builder.fileContentType(HttpMediaType.APPLICATION_JSON)
//  val response: DocumentAccepted = discovery.addDocument(builder.build).execute.getResult



  val queryBuilder = new QueryOptions.Builder(environmentId, collectionId)
  queryBuilder.query("{field}:{value}")
  val queryResponse: QueryResponse = discovery.query(queryBuilder.build).execute.getResult
}

package communication

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

class MessagesController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc)
    with play.api.i18n.I18nSupport {

  import play.api.data.Form
  import play.api.data.Forms._

  val userForm = Form(
    mapping(
      "name" -> text,
      "age"  -> number
    )(views.html.UserData.apply)(views.html.UserData.unapply)
  )

  def index = Action { implicit request =>
    Ok(views.html.user(userForm))
  }
}

package communication

import javax.inject.Inject
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class customerController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
  import play.api.data._
  import play.api.data.Form
  import play.api.data.Forms._

  def forms() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.customerForm.
  }

}

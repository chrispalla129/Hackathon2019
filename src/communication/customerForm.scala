package communication

import play.api.data._
import play.api.data.Forms._
import javax.inject.Inject
import play.api.data.validation.Constraints._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

case class customerForm(item: String, recipe: String)

object customerForm {
  val form: Form[customerForm] = Form (
    mapping(
      "item" -> text,
      "recipe" -> text
    ) (customerForm.apply)(customerForm.unapply)
  )
}

@Singleton
class BasicController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def forms() = Action {  implicit request: Request[AnyContent] =>
  }

}
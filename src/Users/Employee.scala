package Users
import ProductTypes.ShoppingList

class Employee extends User{
  override def userType: String = "Employee"

  override def storeNum: Int = 90

  override def shoppingLists: Map[String, ShoppingList] = Map()
}

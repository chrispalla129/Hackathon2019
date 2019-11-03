package Users
import ProductTypes.ShoppingList

class Customer extends User {
  override def userType: String = "Customer"

  override def storeNum: Int = 90

  override def shoppingLists: Map[String, ShoppingList] = Map()
}
//commit bruh
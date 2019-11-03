package Users
import ProductTypes.ShoppingList

import scala.collection.mutable

class Customer extends User {
  override def userType: String = "Customer"

  override def storeNum: Int = 90

  override def shoppingLists: mutable.Map[String, ShoppingList] = mutable.Map()
}
//commit bruh
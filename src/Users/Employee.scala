package Users
import ProductTypes.ShoppingList

import scala.collection.mutable

class Employee extends User{
  override def userType: String = "Employee"

  override def storeNum: Int = 90

  override def shoppingLists: mutable.Map[String, ShoppingList] = mutable.Map()
}

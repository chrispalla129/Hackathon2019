package Users

import ProductTypes.ShoppingList

abstract class User {
  def userType:String
  def storeNum: Int
  def shoppingLists: Map[String, ShoppingList]
  def saveList(shopping: ShoppingList,  name: String): Unit = {
    shopping.name = name
    this.shoppingLists += (shopping.name -> shopping)
  }
}

package Users

import ProductTypes.ShoppingList

import scala.collection.mutable

abstract class User {
  def userType:String
  def storeNum: Int
  def shoppingLists: mutable.Map[String, ShoppingList]
  def saveList(shopping: ShoppingList,  name: String): Unit = {
    shopping.name = name
    this.shoppingLists(shopping.name) = shopping
  }
}

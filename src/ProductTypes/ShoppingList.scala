package ProductTypes

class ShoppingList {
  var name:String = _

  var recipes: List[Recipe] = List()

  var items: List[(Int,Item)] = List()

  var cost: Double = 0

  def addRecipe(recipe: Recipe): Unit = {
    recipes :+= recipe
    for(item <- recipe.ingredients) {
      items :+= item
      cost += item._2.price
    }
  }

  def addItem (item: Item): Unit = {
    items :+= (1, item)
    cost += item.price
  }

  def clear(): Unit = {
    recipes =  List()
    items =  List()
    cost = 0
  }
}
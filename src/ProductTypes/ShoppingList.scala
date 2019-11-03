package ProductTypes

class ShoppingList(var name: String) {

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
    items :+= item
    cost += item.price
  }
}
package ProductTypes

class ShoppingList {

  var recipes: List[Recipe] = List()

  var items: List[Item] = List()

  var cost: Double = {
    var acc = 0
    0
  }
  def addRecipe(recipe: Recipe): Unit = {
    recipes :+= recipe

  }

  def addItem (item: Item): Unit = items :+= item
}

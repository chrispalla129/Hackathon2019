package ProductTypes

class ShoppingList {

  var recipes: List[Recipe] = List()

  var items: List[Item] = List()

  val cost: Int = 0 // sum of all cost
  def addRecipe(recipe: Recipe): Unit = {
    recipes :+= recipe

  }

  def addItem (item: Item): Unit = items :+= item
}

package test
import ProductTypes._
import org.scalatest.FlatSpec

class wTest extends FlatSpec {

  behavior of "item"

  it should "work" in {
    val items: List[Item] = DataProcess.Search.searchProduct("chicken")

    assert(items.head.name == "RV OfTov Chkn Brst Cutl")
    assert(items.head.price == 17.99)
    assert(items.head.sku == "745423")
  }

  behavior of "recipe"

  it should "work" in {
    val recipe: Recipe = DataProcess.Search.searchRecipe("chicken").head


  }
}
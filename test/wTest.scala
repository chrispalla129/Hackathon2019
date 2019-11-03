package test
import org.scalatest.FlatSpec
import src._

class wTest extends FlatSpec {

  behavior of "Project"

  it should "work" in {
    val items: List[Item] = DataProcess.Search.searchProduct("chicken")

    assert(items.head.name == "RV OfTov Chkn Brst Cutl")
  }

}
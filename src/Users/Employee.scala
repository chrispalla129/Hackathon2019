package Users

class Employee extends User{
  override def userType: String = "Employee"

  override def storeNum: Int = 90
}

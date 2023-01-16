package hotel.company

import io.kotest.core.spec.style.*
import io.kotest.matchers.*
import hotel.CompanyId
import hotel.EmployeeId

class EmployeeRepositorySpec: ShouldSpec({
   should("add employees") {
       val companyId1 = CompanyId(3)
       val companyId2 = CompanyId(5)
       val employeeId1 = EmployeeId(80)
       val employeeId2 = EmployeeId(85)
       val repository = EmployeeRepository()

       repository.add(companyId1, employeeId1)
       repository.add(companyId2, employeeId2)

       repository.findBy(employeeId1).company shouldBe companyId1
       repository.findBy(employeeId2).company shouldBe companyId2
   }
})

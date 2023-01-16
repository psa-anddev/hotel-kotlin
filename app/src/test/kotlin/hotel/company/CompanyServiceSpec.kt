package hotel.company

import io.kotest.core.spec.style.*
import io.mockk.*
import hotel.CompanyId
import hotel.EmployeeId

class CompanyServiceSpec: ShouldSpec({
   should("add an employee") {
       val repository = mockk<EmployeeRepository>()
       val service = CompanyService(repository)
       val company = CompanyId((1..Int.MAX_VALUE).random())
       val employee = EmployeeId((1..Int.MAX_VALUE).random())

       every { repository.add(any(), any()) } just Runs

       service.addEmployee(company, employee)

       verify { repository.add(company, employee) }
   }
})

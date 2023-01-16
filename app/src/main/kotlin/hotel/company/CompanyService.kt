package hotel.company

import hotel.CompanyId
import hotel.EmployeeId

class CompanyService(val repository: EmployeeRepository) {
   fun addEmployee(company: CompanyId, employee: EmployeeId) {
       repository.add(company, employee)
   } 
}

package hotel.policies

import io.kotest.matchers.*
import io.kotest.matchers.collections.*
import io.kotest.core.spec.style.*
import hotel.EmployeeId
import hotel.RoomType.*

class EmployeePoliciesRepositorySpec: ShouldSpec({
    should("return no policy if policy hasn't been defined") {
        val repository = EmployeePoliciesRepository()

        repository.findBy(EmployeeId((1..Int.MAX_VALUE).random())) should beEmpty()
    }

    should("return the set up policy if defined") {
        val repository = EmployeePoliciesRepository()
        val employee1 = EmployeeId((1..Int.MAX_VALUE).random())
        val employee2 = EmployeeId((1..Int.MAX_VALUE).random())
        val employee1Policy = listOf(SINGLE, JUNIOR_SUITE)
        val employee2Policy = listOf(DOUBLE, MASTER_SUITE)

        repository.add(employee1, employee1Policy)
        repository.add(employee2, employee2Policy)

        repository.findBy(employee1) should containOnly(employee1Policy)
        repository.findBy(employee2) should containOnly(employee2Policy)
    }
})

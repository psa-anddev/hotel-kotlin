package hotel.policies

import hotel.RoomType.*

import io.kotest.core.spec.style.*
import io.kotest.matchers.*
import io.kotest.matchers.collections.*
import hotel.CompanyId

class CompanyPoliciesRepositorySpec: ShouldSpec({
  should("return no policy if no policy has been defined") {
      val companyId = CompanyId((1..Int.MAX_VALUE).random())
      val repository = CompanyPoliciesRepository()

      repository.findBy(companyId) should beEmpty()
  }

  should("return the defined policy for the company") {
      val repository = CompanyPoliciesRepository()
      val companyId1 = CompanyId((1..Int.MAX_VALUE).random())
      val companyId2 = CompanyId((1..Int.MAX_VALUE).random())
      val companyId3 = CompanyId((1..Int.MAX_VALUE).random())

      repository.add(companyId1, listOf(SINGLE))
      repository.add(companyId2, listOf(DOUBLE, MASTER_SUITE))
      repository.add(companyId3, listOf(JUNIOR_SUITE, SINGLE))

      repository.findBy(companyId1) should containOnly(SINGLE)
  }
})

package hotel.hotel

import io.kotest.matchers.*
import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.*
import io.mockk.*

class HotelRepositorySpec: ShouldSpec({
  should("not return a non-existent hotel") {
      val repository = HotelRepository()

      repository.findBy(30) shouldBe null
  }

  should("return a hotel that exists") {
      val repository = HotelRepository()
      val id = 300
      val name = "New Hotel"

      repository.add(id, name)

      repository.findBy(id)!!.name shouldBe name
  }
})

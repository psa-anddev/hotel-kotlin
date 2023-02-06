package hotel.hotel

import io.kotest.matchers.*
import io.kotest.matchers.collections.*
import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.*
import io.mockk.*
import hotel.RoomType
import hotel.Room


class RoomRepositorySpec: ShouldSpec({
  should("not return any rooms if the hotel has none") {
      val repository = RoomRepository()

      repository.findBy(100).shouldBeEmpty()
  }

  should("return the rooms assigned to a hotel") {
      val repository = RoomRepository()

      repository.add(200, 101, RoomType.SINGLE)
      repository.add(320, 400, RoomType.JUNIOR_SUITE)
      repository.add(200, 402, RoomType.DOUBLE)
      repository.add(320, 102, RoomType.MASTER_SUITE)

      repository.findBy(200) should containAll(Room(101, RoomType.SINGLE), Room(402, RoomType.DOUBLE))
  }
})

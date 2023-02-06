package hotel.hotel

import io.kotest.matchers.*
import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.*
import io.mockk.*
import hotel.Hotel
import hotel.RoomType


class HotelServiceSpec: ShouldSpec({
  should("throw hotel exists error") {
      val hotelsRepository = mockk<HotelRepository>()
      val hotelsService = HotelService(hotelsRepository, mockk())
      every { hotelsRepository.findBy(10) } returns Hotel(10, "NH Barcelona")

      shouldThrowWithMessage<HotelExists>("Hotel with ID 10 already exists") {
        hotelsService.addHotel(10, "Whatever")
      }
  }

  should("add a hotel") {
      val hotelsRepository = mockk<HotelRepository>()
      val hotelsService = HotelService(hotelsRepository, mockk())

      every { hotelsRepository.findBy(300) } returns null
      every { hotelsRepository.add(300, "Ritz London") } just Runs

      hotelsService.addHotel(300, "Ritz London")

      verify { hotelsRepository.add(300, "Ritz London") }
  }

  should("throw hotel does not exist exception") {
      val hotelsRepository = mockk<HotelRepository>()
      val hotelsService = HotelService(hotelsRepository, mockk())

      every { hotelsRepository.findBy(250) } returns null

      shouldThrowWithMessage<HotelDoesNotExist>("Hotel with ID 250 does not exist") {
          hotelsService.setRoom(250, 401, RoomType.SINGLE)
      }
  }

  should("set a room for a hotel that exists") {
      val hotelsRepository = mockk<HotelRepository>()
      val roomsRepository = mockk<RoomRepository>()
      val hotelsService = HotelService(hotelsRepository, roomsRepository)

      every { hotelsRepository.findBy(360) } returns Hotel(360, "Round Hotel")
      every { roomsRepository.add(360, 520, RoomType.DOUBLE) } just Runs

      hotelsService.setRoom(360, 520, RoomType.DOUBLE)

      verify { roomsRepository.add(360, 520, RoomType.DOUBLE) }
  }
})

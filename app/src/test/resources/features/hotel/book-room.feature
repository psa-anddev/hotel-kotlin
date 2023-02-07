Feature: Book a room
    As an employee,
    I want to book a room,
    So that my hotel can host my guest.

    Scenario: Booking confirmation received upon successful booking
        Given "Diana Prince" works for "Meliá"
        And "Robert Johnson" is a hotel manager
        And "Robert Johnson" added "Hotel Los Lebreros"
        And "Hotel Los Lebreros" room 230 is a "single" room
        When "Diana Prince" books a "single" room in "Hotel Los Lebreros" from 10/02/2023 to 12/02/2023
        Then "Diana Prince" gets a booking confirmation for room 230 in "Hotel Los Lebreros" from 10/02/2023 to 12/02/2023

    Scenario: Check out date same as check in date 
        Given "Bruce Wayne" works for "NH"
        And "Rubin Monkey" is a hotel manager 
        And "Rubin Monkey" added "Hotel NH Barcelona"
        And "Hotel NH Barcelona" room 540 is a "junior suite" room 
        When "Bruce Wayne" books a "junior suite" room in "Hotel NH Barcelona" from 15/05/2023 to 15/05/2023 
        Then "Bruce Wayne" gets an invalid timeframe for booking error

Feature: Room booking policies
    As a company admin,
    I want to set booking policies based on room type
    So that I can control who is booking every room

    Scenario Outline: No policies defined
        Given "Ben Dover" works for "Meliá"
        And no company booking policy has been established for "Meliá"
        And no employee booking policy has been established for "Ben Dover"
        When I check if "Ben Dover" can book a "<room-type>" room
        Then "Ben Dover" is allowed to book a "<room-type>" room

        Examples:
            | room-type    |
            | single       |
            | double       |
            | junior suite |
            | master suite |

    Scenario Outline: Company policies defined 
        Given "Rubin Monkey" works for "NH"
        And "NH" employees can book "<allowed-room-type>"
        And no employee booking policy has been established for "Rubin Monkey"
        When I check if "Rubin Monkey" can book a "<room-type>" room
        Then "Rubin Monkey" is <result> to book a "<room-type>" room


        Examples:
            | allowed-room-type | room-type     | result   |
            | single            | single        | allowed  |
            | single            | double        | denied   |
            | single            | junior suite  | denied   |
            | single            | master suite  | denied   |
            | double            | single        | denied   |
            | double            | double        | allowed  |
            | double            | junior suite  | denied   |
            | double            | master suite  | denied   |
            | junior suite      | single        | denied   |
            | junior suite      | double        | denied   |
            | junior suite      | junior suite  | allowed  |
            | junior suite      | master suite  | denied   |
            | master suite      | single        | denied   |
            | master suite      | double        | denied   |
            | master suite      | junior suite  | denied   |
            | master suite      | master suite  | allowed  |

    Scenario Outline: Employee policies defined 
        Given "Clark Kent" works for "Premier Inn"
        And no company booking policy has been established for "Premier Inn"
        And "Clark Kent" can book "<allowed-room-type>" rooms
        When I check if "Clark Kent" can book a "<room-type>" room
        Then "Rubin Monkey" is <result> to book a "<room-type>" room


        Examples:
            | allowed-room-type | room-type     | result   |
            | single            | single        | allowed  |
            | single            | double        | denied   |
            | single            | junior suite  | denied   |
            | single            | master suite  | denied   |
            | double            | single        | denied   |
            | double            | double        | allowed  |
            | double            | junior suite  | denied   |
            | double            | master suite  | denied   |
            | junior suite      | single        | denied   |
            | junior suite      | double        | denied   |
            | junior suite      | junior suite  | allowed  |
            | junior suite      | master suite  | denied   |
            | master suite      | single        | denied   |
            | master suite      | double        | denied   |
            | master suite      | junior suite  | denied   |
            | master suite      | master suite  | allowed  |

    Scenario Outline: Both Company and employee policies defined
        Given "Aileen Dover" works for "Travelodge"
        And "Travelodge" employees can book "<company-allowed-room-type>"
        And "Aileen Dover" can book "<employee-allowed-room-type>" rooms
        When I check if "Aileen Dover" can book a "<room-type>" room
        Then "Aileen Dover" is <result> to book a "<room-type>" room

        Examples:
            | company-allowed-room-type | employee-allowed-room-type |    room-type     | result    |
            | single                    | single                     | single           | allowed   |
            | single                    | single                     | double           | denied    |
            | single                    | single                     | junior suite     | denied    |
            | single                    | single                     | master suite     | denied    |
            | single                    | double                     | single           | denied    |
            | single                    | double                     | double           | allowed   |
            | single                    | double                     | junior suite     | denied    |
            | single                    | double                     | master suite     | denied    |
            | single                    | junior suite               | single           | denied    |
            | single                    | junior suite               | double           | denied    |
            | single                    | junior suite               | junior suite     | allowed   |
            | single                    | junior suite               | master suite     | denied    |
            | single                    | master suite               | single           | denied    |
            | single                    | master suite               | double           | denied    |
            | single                    | master suite               | junior suite     | denied    |
            | single                    | master suite               | master suite     | allowed   |
            | double                    | single                     | single           | allowed   |
            | double                    | single                     | double           | denied    |
            | double                    | single                     | junior suite     | denied    |
            | double                    | single                     | master suite     | denied    |
            | double                    | double                     | single           | denied    |
            | double                    | double                     | double           | allowed   |
            | double                    | double                     | junior suite     | denied    |
            | double                    | double                     | master suite     | denied    |
            | double                    | junior suite               | single           | denied    |
            | double                    | junior suite               | double           | denied    |
            | double                    | junior suite               | junior suite     | allowed   |
            | double                    | junior suite               | master suite     | denied    |
            | double                    | master suite               | single           | denied    |
            | double                    | master suite               | double           | denied    |
            | double                    | master suite               | junior suite     | denied    |
            | double                    | master suite               | master suite     | allowed   |
            | junior suite              | single                     | single           | allowed   |
            | junior suite              | single                     | double           | denied    |
            | junior suite              | single                     | junior suite     | denied    |
            | junior suite              | single                     | master suite     | denied    |
            | junior suite              | double                     | single           | denied    |
            | junior suite              | double                     | double           | allowed   |
            | junior suite              | double                     | junior suite     | denied    |
            | junior suite              | double                     | master suite     | denied    |
            | junior suite              | junior suite               | single           | denied    |
            | junior suite              | junior suite               | double           | denied    |
            | junior suite              | junior suite               | junior suite     | allowed   |
            | junior suite              | junior suite               | master suite     | denied    |
            | junior suite              | master suite               | single           | denied    |
            | junior suite              | master suite               | double           | denied    |
            | junior suite              | master suite               | junior suite     | denied    |
            | junior suite              | master suite               | master suite     | allowed   |
            | master suite              | single                     | single           | allowed   |
            | master suite              | single                     | double           | denied    |
            | master suite              | single                     | junior suite     | denied    |
            | master suite              | single                     | master suite     | denied    |
            | master suite              | double                     | single           | denied    |
            | master suite              | double                     | double           | allowed   |
            | master suite              | double                     | junior suite     | denied    |
            | master suite              | double                     | master suite     | denied    |
            | master suite              | junior suite               | single           | denied    |
            | master suite              | junior suite               | double           | denied    |
            | master suite              | junior suite               | junior suite     | allowed   |
            | junior suite              | junior suite               | master suite     | denied    |
            | junior suite              | master suite               | single           | denied    |
            | junior suite              | master suite               | double           | denied    |
            | junior suite              | master suite               | junior suite     | denied    |
            | junior suite              | master suite               | master suite     | allowed   |

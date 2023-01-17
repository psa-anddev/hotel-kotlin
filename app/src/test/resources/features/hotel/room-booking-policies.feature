Feature: Room booking policies
    As a company admin,
    I want to set booking policies based on room type
    So that I can control who is booking every room

    Scenario Template: No policies defined
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

    Scenario Template: Company policies defined 
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

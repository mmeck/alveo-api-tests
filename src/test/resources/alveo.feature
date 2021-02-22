Feature: Api tests for POST, GET, DELETE

  Scenario Outline: create an order
    Given a pet that is "available" in the pet store
    And user places an order to be delivered "<deliveryDate>"
    When the http response code is "<orderStatus>"
    Then the http response code is "<expectedResponseCode>"
    Examples:
      | deliveryDate | orderStatus | expectedResponseCode |
      | tomorrow     | 200 | 200                  |
      | yesterday    | 400 | 400                  |

  Scenario Outline: retrieve an order
    Given a pet that is "<availability>" in the pet store
    And user places an order to be delivered "tomorrow"
    And the http response code is "<orderStatus>"
    When user retrieves the order
    Then the http response code is "<expectedResponseCode>"
    Examples:
      | availability| orderStatus | expectedResponseCode |
      | available   | 200 | 200                  |
      | not available|400 | 404                  |

  Scenario Outline: delete an order
    Given a pet that is "<availability>" in the pet store
    And user places an order to be delivered "tomorrow"
    And the http response code is "<orderStatus>"
    Then user deletes the order
    Then the http response code is "<expectedResponseCode>"
    Examples:
      | availability  | orderStatus |  expectedResponseCode |
      | available     | 200         |  200                  |
      | not available | 400         |  404                  |







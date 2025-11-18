Feature: SIM Card Activation

  Scenario: Successful SIM activation
    Given I have the ICCID "1255789453849037777"
    When I submit an activation request
    Then the activation should be successful for id 1

  Scenario: Failed SIM activation
    Given I have the ICCID "8944500102198304826"
    When I submit an activation request
    Then the activation should fail for id 2

Feature: validate solotodo webpage elements

  Background:
    Given I land on solotodo webpage

  @Solotodo
  Scenario Outline: Validate element in header
    Given Im in solotodo webpage
    When search for "<elementName>" button in header
    Then all of them are present

    Examples:
      | elementName            |
      | Tecnologia            |
      | Electro               |
      | Hardware              |
      | Perifericos           |
      | boton de perfil       |
      | boton de configuracion |


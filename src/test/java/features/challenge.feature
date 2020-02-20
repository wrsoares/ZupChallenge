
  Feature: Insercao de um produto no carrinho de compras no Petlove
    Eu como usuario devo ser capaz de inserir um produto ou mais produtos no carrinho de compras

    Background: Acesso ao site do Petlove
      Given que acessei o site do Petlove


    Scenario: Insercao de produto em carrinho de compras sem usuario logado
      When seleciono os produtos para inserir no carrinho de compras
      |Tigela Chalesco de Inox | 680 mL |
      |Antipulgas e Carrapatos MSD Bravecto para Cães de 4,5 a 10 Kg| 250 mg|
      Then valido que os produtos estao no carrinho de compras

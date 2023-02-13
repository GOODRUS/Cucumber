#language:ru


Функциональность: Авторизация

  # пример теста с одним набором параметров
  Сценарий: : Авторизация в личном кабинете (позитивный)
    Пусть открыта страница с формой авторизации "http://localhost:9999"
    Когда пользователь пытается авторизоваться с именем "vasya" и паролем "qwerty123"
    И пользователь вводит проверочный код 'из смс' "12345"
    Тогда происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'

  Сценарий: Перевод средств с карты номер один на карту номер два
    Когда пользователь в личном кабинете нажимает на кнопку пополнение счёта '5559 0000 0000 0001' {0}
    И указывает в поле сумму "5000" и номер карты списания "5559 0000 0000 0002"
    Тогда пользователь успешно производит перевод и попадает на страницу с 'Номерами карт'

  Сценарий: проверка
    Когда сумма на счету '5559 0000 0000 0001' равняется {15000}, сумма на счету '5559 0000 0000 0002' равняется {5000}
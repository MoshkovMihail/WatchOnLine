// Установка даты, до которой мы отсчитываем время to
var countDownDate = new Date("Jan 1, 2026 00:00:00").getTime();

// Обновить обратный отсчет каждую секунду
var x = setInterval(function() {

  // Получить сегодняшнюю дату и время
  var now = new Date().getTime();

  // Найти расстояние между текущим моментом и датой обратного отсчета
  var distance = countDownDate - now;

  // Расчеты времени для дней, часов, минут и секунд
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

  // Отображаем результат в элементе с id="time"
  document.getElementById("time").innerHTML ="Осталось до нг: " + days + "дней " + hours + "часов "
  + minutes + "Минут " + seconds + "секунд ";

  // Если отсчет завершен, напишите какой-нибудь текст
  if (distance < 0) {
    clearInterval(x);
    document.getElementById("time").innerHTML = "С Новым Годом!";
  }
}, 1000);


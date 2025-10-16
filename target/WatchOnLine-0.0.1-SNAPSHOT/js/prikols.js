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

var slideIndex = 1;
showSlides(slideIndex);

// Далее/назад элементы управления
function plusSlides(n) {
  showSlides(slideIndex += n);
}

// Элементы управления миниатюрами изображений
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("time");
  var captionText = document.getElementById("caption");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
  captionText.innerHTML = dots[slideIndex-1].alt;
}


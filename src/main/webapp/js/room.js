document.addEventListener("DOMContentLoaded", function () {
  // Находим все элементы таймеров
  const timers = document.querySelectorAll(".deadline-timer");

  if (timers.length === 0) {
    return; // нет задач с дедлайнами – нечего считать
  }

  // Для каждого таймера запускаем свой setInterval
  timers.forEach(function (span) {
    const deadlineStr = span.getAttribute("data-deadline"); // строка типа "2025-12-31T23:59:00"

    const deadline = new Date(deadlineStr);

    if (isNaN(deadline.getTime())) {
      // если дата не распарсилась
      span.textContent = "дедлайн не задан";
      return;
    }

    function updateTimer() {
      const now = new Date().getTime();
      const distance = deadline.getTime() - now;

      if (distance <= 0) {
        span.textContent = "дедлайн истёк";
        return;
      }

      const days = Math.floor(distance / (1000 * 60 * 60 * 24));
      const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
      const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
      const seconds = Math.floor((distance % (1000 * 60)) / 1000);

      span.textContent =
        " осталось: " +
        (days > 0 ? days + "д " : "") +
        String(hours).padStart(2, "0") + "ч " +
        String(minutes).padStart(2, "0") + "м " +
        String(seconds).padStart(2, "0") + "с";
    }

    updateTimer();
    setInterval(updateTimer, 1000);
  });
});

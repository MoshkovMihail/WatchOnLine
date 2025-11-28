



document.addEventListener('click', (e) => {
    const isDropdown = e.target.closest('.dropdown');
    const isThemeButton = e.target.id === 'buttonForChangeTheme';

    if (!isDropdown && !isThemeButton) {
        document.querySelectorAll('.dropdown-content.open')
            .forEach(d => d.classList.remove('open'));
    }
});

document.addEventListener("DOMContentLoaded", () => {
    const saved = localStorage.getItem("theme");

    if (saved === "light") {
        document.body.classList.add("light-theme");
        document.getElementById("buttonForChangeTheme").textContent = "☀️";
    } else {
        document.body.classList.remove("light-theme");
        document.getElementById("buttonForChangeTheme").textContent = "🌙";
    }
});

function changeTheme() {
    const isLight = document.body.classList.toggle("light-theme");
    const button = document.getElementById("buttonForChangeTheme");

    if (isLight) {
        button.textContent = "☀️";
        localStorage.setItem("theme", "light");
    } else {
        button.textContent = "🌙";
        localStorage.setItem("theme", "dark");
    }
}
//смена темы



/* Когда пользователь нажимает на кнопку,
переключение между скрытием и отображением раскрывающегося содержимого */
function accounts() {
  document.getElementById("accounts").classList.toggle("show");
}

function rooms() {
  document.getElementById("rooms").classList.toggle("show");
}
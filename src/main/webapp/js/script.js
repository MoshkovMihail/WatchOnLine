



// Смена темы //
function changeTheme() {
    const button = document.getElementById("buttonForChangeTheme");
    if (button.textContent === '🌙') {
        setLightTheme();
    } else {
        setDarkTheme();
    }

}

window.addEventListener('load', function() {
    const currentTheme = localStorage.getItem('theme');

    if (currentTheme === 'dark') {
        setDarkTheme();
    } else {
        setLightTheme();
    }

});

function setLightTheme() {
    const button = document.getElementById("buttonForChangeTheme");
    const paragraphs = document.querySelectorAll("p");

    button.textContent = '☀️';
    document.body.style.backgroundColor = 'aliceblue';
    
    paragraphs.forEach(p =>{
        p.style.color = 'rgb(29, 23, 35)';
    });

    localStorage.setItem('theme', 'light');
}

function setDarkTheme() {
    const button = document.getElementById("buttonForChangeTheme");
    const paragraphs = document.querySelectorAll("p");

    button.textContent = '🌙';
    document.body.style.backgroundColor =  'rgb(29, 23, 35)';

    paragraphs.forEach(p =>{
        p.style.color = 'aliceblue';
    });

    localStorage.setItem('theme', 'dark');
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
document.getElementById('regForm').onsubmit = function(e) {
e.preventDefault();

// Очищаем ошибки
document.querySelectorAll('.error').forEach(el => el.innerHTML = '');
document.getElementById('successMessage').innerHTML = '';

let valid = true;
const login = document.getElementById('login').value;
const email = document.getElementById('email').value;
const password = document.getElementById('password').value;

// Проверка логина
if (login.length < 3) {
    document.getElementById('loginError').innerHTML = 'Логин слишком короткий';
    valid = false;
}

// Проверка email
if (!email.includes('@')) {
    document.getElementById('emailError').innerHTML = 'Некорректный email';
    valid = false;
}

// Проверка пароля
if (password.length < 4) {
    document.getElementById('passwordError').innerHTML = 'Пароль слишком короткий';
    valid = false;
}

// Если все ок
if (valid) {
    document.getElementById('successMessage').innerHTML = 'Регистрация успешна!';
    document.getElementById('regForm').reset();
}
};
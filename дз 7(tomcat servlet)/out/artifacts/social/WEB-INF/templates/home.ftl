<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать</title>
    <link rel="stylesheet" href="/css/home/main.css">
</head>
<body>
<div>
    <h2 class="main-header">Выбери свой путь</h2>
    <div class="left-part">
        <h2>Войти</h2>
        <form method="post" action="/login">
            <input type="text" name="login" placeholder="Логин">
            <input type="text" name="password" placeholder="Пароль">
            <input type="submit" placeholder="Войти">
        </form>
    </div>
    <div class="right-part">
        <h2>Зарегистрироватся</h2>
        <form method="post" action="/register">
            <input type="text" name="login" placeholder="Логин">
            <input type="password" name="password" placeholder="Пароль">
            <input type="text" name="name" placeholder="Имя">
            <input type="text" name="surname" placeholder="Фамилия">
            <input type="date" name="birth" placeholder="Дата рождения">
            <input type="number" name="course" placeholder="курс">
            <input type="text" name="group" placeholder="группа">
            <input type="submit" placeholder="Регистрация">
        </form>
    </div>
</div>
</body>
</html>
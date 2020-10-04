<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="/css/register/">
</head>
<body>
<div>
    <div class="left-part">
        <h2>Зарегистрироватся</h2>
        <h3>Что-то было введено неверно.</h3>
        <form method="post" action="/register">
            <input type="text" name="login" placeholder="Логин" value="${login}">
            <input type="password" name="password" placeholder="Пароль">
            <input type="text" name="name" placeholder="Имя" value="${name}">
            <input type="text" name="surname" placeholder="Фамилия" value="${surname}">
            <input type="date" name="birth" placeholder="Дата рождения" value="${birth}">
            <input type="number" name="course" placeholder="курс" value="${course}">
            <input type="text" name="group" placeholder="группа" value="${group}">
            <input type="submit" placeholder="Регистрация">
        </form>
    </div>
</div>
</body>
</html>
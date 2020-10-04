<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="/css/register/main.css">
</head>
<body>
<div>
    <div class="left-part">
        <h2>Зарегистрироватся</h2>
        <#if message??><h3>${message}</h3></#if>
        <form method="post" action="/register">
            <input type="text" name="login" placeholder="Логин" <#if (login)??>value="${login}"</#if>>
            <input type="password" name="password" placeholder="Пароль">
            <input type="text" name="name" placeholder="Имя" <#if (name)??>value="${name}"</#if>>
            <input type="text" name="surname" placeholder="Фамилия" <#if (surname)??>value="${surname}"</#if>>
            <input type="date" name="birth" placeholder="Дата рождения" <#if (birth)??>value="${birth}"</#if>>
            <input type="number" name="course" placeholder="курс" <#if (course)??>value="${course}"</#if>>
            <input type="text" name="group" placeholder="группа" <#if (group)??>value="${group}"</#if>>
            <input type="submit" placeholder="Регистрация">
        </form>
    </div>
</div>
</body>
</html>
<html>
<head>
    <title>Car Rental Login</title>
    <link rel="stylesheet" href="${url.resourcesPath}/css/login.css" />
</head>
<body>
<div class="main-container">
<div class="login-container">
    <div class="logo">
        <img src="${url.resourcesPath}/img/logo.png" alt="Car Rental Logo" />
    </div>
    <div class="login-form">
        <h1>Wypożyczalnia samochodów</h1>
        <form action="${url.loginAction}" method="POST">
            <label for="username">Email</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required />
            <label for="password">Hasło</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required />
            <button type="submit" class="login-btn">Zaloguj się</button>
        </form>
    </div>
    <div class="login-footer">
        <a href="#">Zapomniałeś hasła?</a>
    </div>
</div>
</div>
</body>
</html>

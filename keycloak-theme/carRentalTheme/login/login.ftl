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
            <input type="text" id="username" name="username" placeholder="Wprowadź email" required />
            <label for="password">Hasło</label>
            <input type="password" id="password" name="password" placeholder="Wprowadź hasło" required />
            <div class="actions">
                <label for="rememberMe" class="rememberMe">
                    <input type="checkbox" id="rememberMe" name="rememberMe" />
                    Zapamiętaj mnie
                </label>

<#--            <a href="${url.loginResetCredentialsUrl}>Zapomniałeś hasła?</a>-->
            </div>
            <button type="submit" class="login-btn">Zaloguj się</button>
        </form>
    </div>
    <div class="login-footer">

        <a href="${url.registrationUrl}">Nie masz konta? Zarejestruj się</a>
    </div>

    <div class="social-login">
        <p>Lub</p>
        <a href="${url.resourcesPath}/realms/car-rental/protocol/openid-connect/auth?client_id=google" class="google-login-btn">Zaloguj się przez Google</a>
<#--        <a href="${url.resourcesPath}/realms/car-rental/protocol/openid-connect/auth?client_id=facebook" class="facebook-login-btn">Zaloguj się przez Facebook</a>-->
    </div>
</div>
</div>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
    <!--
    Haremos un formulario de inicio de sesión con dos campos de texto, uno para el nombre de usuario y otro para la contraseña.
    Cada uno de nosotros realizaremos una pestaña diferente para no tener probvlemas de merge y mezclar contenido para que una persona
    no realice todo el trabajo de un sector especifico.
        -> Yerai: Diapositivas impares.
        -> Eneko: Diapositivas pares.
    -->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <div class="contenedor">
        <div class="cajaLogo">
            <img src="https://i.postimg.cc/bYxfSGLg/logo-Wooden.png" id="logo" alt="Wooden Sticks">
        </div>
        <div class="cajaLogin">
            <div class="avatar">
                <img src= "https://i.postimg.cc/pTst147T/imagen-Usuario.png"alt="Avatar">
            </div>
            <h2>Inicio Sesión</h2>
            <form action="/autenticar" method="post">
                <label for="index-username">Usuario:</label>
                <input type="text" id="username" name="username" placeholder="username">
                <label for="index-password">Contraseña:</label>
                <input type="password" id="password" name="password" placeholder="********">
                <button type="submit">ENTRAR</button>
<#if error>
                <p class="mensajeError">*Contraseña o usuario incorrectos</p>
</#if>
            </form>
        </div>
    </div>
</body>
</html>
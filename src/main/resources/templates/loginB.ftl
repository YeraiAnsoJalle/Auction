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
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="contenedor">
        <div class="cajaLogo">
            <img src="img/logoWooden.png" id="logo" alt="Wooden Sticks">
        </div>
        <div class="cajaLogin">
            <div class="avatar">
                <img src= "img/imagenUsuario.png"alt="Avatar">
            </div>
            <h2>Inicio Sesión</h2>
            <form action="/autenticar" method="post">
                <label for="username">Usuario:</label>
                <input type="text" id="username" placeholder="username" value="${username}">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" placeholder="********" value="${password}">
                <button type="submit">ENTRAR</button>
<#if error>
                <p class="mensajeError">*Contraseña o usuario incorrectos</p>
</#if>
            </form>
        </div>
    </div>
</body>
</html>
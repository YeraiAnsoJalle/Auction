<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú</title>
    <link rel="icon" type="image/x-icon" href="/imgs/icons/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <div class="main-container">
        <#if isAdministrator>
            <h1>¡Bienvenido, admin!</h1>
            <div class="boton">
            <a href="validar-pujas.html"><button type="submit">Validar Pujas</button></a>
            <a href="items-con-pujas.html"><button type="submit">Items con pujas</button></a>
            <a href="resetear-subasta.html"><button type="submit">Resetear Subasta</button></a>
            <a href="mostrar-ganadores.html"><button type="submit">Mostrar Ganadores</button></a>
            </div>
        <#else>
            <h1>¡Bienvenido, ${username}!</h1>
            <div class="boton">
                <form action="/user/offer" method="post">
                    <button type="submit">Crear Subasta</button>
                </form>
                <form action="/user/offer-list" method="get">
                    <button type="submit">Pujas</button>
                </form>
                <a href="pujas.html"><button type="submit">Pujas</button></a>
                <a href="crear-puja.html"><button type="submit">Crear Subasta</button></a>
                <a href="historialpujas-user.html"><button type="submit">Historial Pujas</button></a>
            </div>
        </#if>
    </div>
</body>
</html>
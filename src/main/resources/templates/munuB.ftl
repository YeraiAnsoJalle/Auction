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
            <form action="/admin/validar-pujas" method="post">
                <a href="validar-pujas.html"><button type="submit">Validar Pujas</button></a>
            </form>
            <form action="/admin/listar-pujas" method="post">
                <a href="items-con-pujas.html"><button type="submit">Items con pujas</button></a>
            </form>
            <form action="/admin/reset" method="post">
                <a href="resetear-subasta.html"><button type="submit">Resetear Subasta</button></a>
            </form>
            <form action="/admin/ganadores" method="post">
                <a href="mostrar-ganadores.html"><button type="submit">Mostrar Ganadores</button></a>
            </form>
            <form action="/admin/gestionar-usuarios" method="post">
                <a href="gestionar-usuarios.html"><button type="submit">Gestionar Usuarios</button></a>
            </form>
            <form action="/exit" method="get">
                <button type="submit">Salir</button>
            </form>
        </div>
    <#else>
        <h1>¡Bienvenido, ${username}!</h1>
        <div class="boton">
            <form action="/user/crear-puja" method="post">
                <button type="submit">Crear Subasta</button>
            </form>
            <form action="/user/lista-pujas" method="get">
                <button type="submit">Pujas</button>
            </form>
            <form action="/user/historial-pujas" method="get">
                <a href="historialpujas-user.html"><button type="submit">Historial Pujas</button></a>
            </form>
        </div>
    </#if>
</div>
</body>
</html>


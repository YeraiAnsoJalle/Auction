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
        <h1>¡Bienvenido, admin!
            <div class="contenedor">
                <div class="boton">
                    <form action="/admin/validar-pujas" method="post">
                        <a href="validar-pujas.ftl"><button type="submit">Validar Pujas</button></a>
                    </form>
                    <form action="/admin/listar-pujas" method="post">
                        <a href="items-con-pujas.ftl"><button type="submit">Items con pujas</button></a>
                    </form>
                    <form action="/admin/reset" method="post">
                        <a href="resetear-subasta.ftl"><button type="submit">Resetear Subasta</button></a>
                    </form>
                    <form action="/admin/mostrar-ganadores" method="post">
                        <a href="mostrar-ganadores.ftl"><button type="submit">Mostrar Ganadores</button></a>
                    </form>
                    <form action="/admin/gestionar-usuarios" method="post">
                        <a href="gestionar-usuarios.html"><button type="submit">Gestionar Usuarios</button></a>
                    </form>
                    <form action="/exit" method="get">
                        <button type="submit">Salir</button>
                    </form>
                </div>
            </div>
    <#else>
        <h1>¡Bienvenido, usuario!</h1>
        <div class="contenedor">
            <div class="boton">
                <form action="/crear-puja" method="post">
                    <button type="submit">Crear Subasta</button>
                </form>
                <form action="pujas" method="get">
                    <button type="submit">Pujas</button>
                </form>
                <form action="historialpujas-user" method="get">
                    <a href="historialpujas-user"><button type="submit">Historial Pujas</button></a>
                </form>
            </div>
        </div>
    </#if>
</div>
</body>
</html>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial de Pujas</title>
    <link rel="stylesheet" href="/doc/web/css/style.css">
</head>
<body>
    <div class="botones">
        <div class="salir">
            <a href="/doc/web/index.html"><img src="/doc/web/img/iconoSalir.png" alt="iconoSalir"/></a>
        </div>
        <div class="salir">
            <a href="/doc/web/user/menu-user.html"><img src="/doc/web/img/flechaVolver.png" alt="iconoVolver"/></a>
        </div>
    </div>

    <div class="contenedorPujasGanadores">
        <h1>Mis Pujas:</h1>
        <#if pujasVigentesUsuario?size == 0>
            <p>No tienes pujas activas actualmente.</p>
        <#else>
            <#list pujasVigentesUsuario as puja>
                <div class="item">
                    <img src="${puja.urlImagen}" alt="${puja.nombreItem}">
                    <h2><strong>${puja.nombreItem}</strong></h2>
                    <p><span>Precio:</span> ${puja.precioInicio}€</p>
                    <p><span>Mi puja:</span> ${puja.precioPujado}€</p>
                </div>
            </#list>
        </#if>
    </div>
</body>
</html>
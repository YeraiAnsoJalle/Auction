<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mostrar-ganadores</title>
    <link rel="stylesheet" href="/doc/web/css/style.css">
</head>

<body>
<div class="botones">
    <div class="salir">
        <a href="/doc/web/index.html"><img src="/doc/web/img/iconoSalir.png" alt="iconoSalir"/></a>
    </div>
    <div class="salir">
        <a href="/doc/web/admin/menu-admin.html"><img src="/doc/web/img/flechaVolver.png" alt="iconoVolver"/></a>
    </div>
</div>

<div class="contenedorPujasGanadores">
    <h1>Ganadores Pujas</h1>

    <#list obtenerHistoricoGanadores as ganador>
        <div class="item">
            <img src="${ganador.urlImagen}" alt="${ganador.nombreItem}">
            <h2><strong>${ganador.nombreItem}</strong></h2>
            <p><span>Fecha:</span> ${ganador.instanteTiempo}</p>
            <p><span>Comprador:</span> ${ganador.nombreComprador}</p>
            <p><span>Precio vendido:</span> ${ganador.precioPujado}€</p>
        </div>
    </#list>

</div>
</body>

</html>

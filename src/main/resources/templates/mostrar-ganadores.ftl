<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mostrar-ganadores</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
<div class="botones">
    <div class="salir">
        <a href="/exit"><img src="https://i.postimg.cc/1tZPbDJg/icono-Salir.png" alt="iconoSalir"/></a>
    </div>
</div>

    <h1>Ganadores Pujas</h1>
        <#if obtenerHistoricoGanadores?size == 0>
            <p>No hay pujas terminadas actualmente.</p>
        <#else>
            <div class="item">
            <#list obtenerHistoricoGanadores as ganador>

                    <img src="${ganador.urlImagen}" alt="${ganador.nombreItem}">
                    <h2><strong>${ganador.nombreItem}</strong></h2>
                    <p><span>Fecha:</span> ${ganador.instanteTiempo}</p>
                    <p><span>Comprador:</span> ${ganador.nombreComprador}</p>
                    <p><span>Precio vendido:</span> ${ganador.precioPujado}€</p>

            </#list>
            </div>
        </#if>

</body>

</html>

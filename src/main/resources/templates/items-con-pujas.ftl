<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Items con Pujas</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="botones">
    <div class="salir">
        <a href="/doc/web/index.html"><img src="doc/web/img/iconoSalir.png" alt="iconoSalir"/></a>
    </div>
    <div class="salir">
        <a href="/doc/web/admin/menu-admin.html"><img src="doc/web/img/flechaVolver.png" alt="iconoVolver"/></a>
    </div>
</div>

<div class="contenedorPujasGanadores">
    <#list articulos as itemPujas>
        <div class="item">
            <img src="${itemPujas.item.imagen}" alt="${itemPujas.item.nombre}">
            <h2><strong>${itemPujas.item.nombre}</strong></h2>
            <p><span>Precio inicial:</span> ${itemPujas.item.precioInicial}€</p>
            <p><span>Descripción:</span> ${itemPujas.item.descripcion}</p>
            <p><span>Creador:</span> ${itemPujas.item.creador}</p>
            <h3>Pujas:</h3>
            <#if itemPujas.pujas?has_content>
                <table>
                    <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Cantidad</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list itemPujas.pujas as puja>
                        <tr>
                            <td>${puja.usuario}</td>
                            <td>${puja.cantidad}€</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            <#else>
                <p>No hay pujas.</p>
            </#if>
        </div>
    </#list>

</div>
</body>
</html>
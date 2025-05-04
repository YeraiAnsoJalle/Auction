<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>items-con-pujas</title>
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
        <#list obtenerArticulosConPujas as item>
            <div class="item">
                <img src="${item.imagen}" alt="imagen ${item.nombre}">
                <h2><strong>${item.nombre}</strong></h2>
                <p><span>Precio inicial:</span> ${item.precioInicial}€</p>
                <p><span>Descripción:</span> ${item.descripcion}</p>
                <p><span>Creador:</span> ${item.creador}</p>
                <h2><strong>Pujadores:</strong></h2>
                <table>
                    <thead>
                        <tr>
                            <th>Usuario</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list item.pujas as puja>
                            <tr>
                                <td>${puja.usuario}</td>
                                <td>${puja.cantidad}€</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </#list>
    </div>
</body>
</html>

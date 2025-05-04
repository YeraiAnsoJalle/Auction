<!DOCTYPE html>
<html lang="en">
        <!--DIAPOSITIVA 10, ENEKO
        Pestaña de administrador para validar o denegar la publicación de una puja opr un usuario-->

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>validar-pujas</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <div class="botones">
        <div class="salir">
            <a href="/exit"><img src="https://i.postimg.cc/1tZPbDJg/icono-Salir.png" alt="iconoSalir"/></a>
        </div>
    </div>

    <#if items?size == 0>
        <p>No hay subastas pendientes de validación.</p>
    <#else>
        <#list obtenerArticulosPendientes() as item>
            <div class="item">
                <img src="${item.imagen}" alt="imagen ${item.nombre}">
                <h2><strong>${item.nombre}</strong></h2>
                <p><span>Precio:</span> ${item.precio}€</p>
                <p><span>Descripción:</span> ${item.descripcion}</p>
                <p><span>Creador:</span> ${item.propietario}</p>

                <form method="post" action="/admin/validar-item">
                    <input type="hidden" name="itemId" value="${item.id}">
                    <input type="hidden" name="accion" value="ACEPTAR">
                    <button type="submit">VALIDAR</button>
                </form>

                <form method="post" action="/admin/validar-item">
                    <input type="hidden" name="itemId" value="${item.id}">
                    <input type="hidden" name="accion" value="DENEGAR">
                    <button type="submit">DENEGAR</button>
                </form>
            </div>
        </#list>
    </#if>

    </div>
</body>
</html>
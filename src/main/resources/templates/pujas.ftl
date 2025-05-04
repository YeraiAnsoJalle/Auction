<!DOCTYPE html>
<html lang="en">
        <!--DIAPOSITIVA 4, ENEKO
        Listado de todos los productos disponibles para pujar, con su validación, etc... Por parter del usuario-->
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Pujas</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>
        <body>
            <div class="botones">
                <div class="salir">
                    <a href="loginB.ftl"><img src="doc/web/img/iconoSalir.png" alt="iconoSalir"/></a>
                </div>
                <div class="salir">
                    <a href="menuB.ftl"><img src="doc/web/img/flechaVolver.png" alt="iconoVolver"/></a>
                </div>
            </div>

            <div class="contenedorPujasGanadores">
                <h1>Pujas Disponibles</h1>
                <div class="item">
                    <#list obtenerArticulosPujables as pujas>
                            <div class="producto">
                                <form action="/pujar" method="post">
                                    <img src="${pujas.urlImagen}" alt="${pujas.nombre}">
                                    <h2><strong>${pujas.nombre}</strong></h2>
                                    <p><span>Precio:</span> ${pujas.precioInicio}€</p>

                                    <input type="hidden" name="itemId" value="${pujas.id}">

                                    <label for="puja_${pujas.id}">Pujar:</label>
                                    <input type="number" name="puja" id="puja_${pujas.id}" placeholder="${pujas.precioInicio}€" min="${pujas.precioInicio}" required>

                                    <label for="username_${pujas.id}">Usuario:</label>
                                    <input type="text" name="username" id="username_${pujas.id}" placeholder="username" required>

                                    <label for="password_${pujas.id}">Contraseña:</label>
                                    <input type="password" name="password" id="password_${pujas.id}" placeholder="********" required>

                                    <button type="submit">PUJAR</button>
                                </form>
                            </div>
                    </#list>
                </div>
            </div>

        </body>
    </html>
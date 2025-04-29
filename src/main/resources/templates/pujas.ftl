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
                    <a href="/doc/web/index.html"><img src="/doc/web/img/iconoSalir.png" alt="iconoSalir"/></a>
                </div>
                <div class="salir">
                    <a href="/doc/web/user/menu-user.html"><img src="/doc/web/img/flechaVolver.png" alt="iconoVolver"/></a>
                </div>
            </div>
            
            <div class="contenedorPujasGanadores">
                <h1>Pujas Disponibles</h1>

                    <div class="item">
                        <#list obtenerArticulosPujables as pujas>
                        <div class="contenedor">
                            <div class="producto">
                                <img src="${pujas.urlImagen}" alt="${pujas.nombre}">
                                <h2><strong>${pujas.nombre}</strong></h2>
                                <p><span>Precio:</span> ${pujas.precioInicio}€</p>

                                <h2>Pujar:</h2>
                                <input type="number" id="puja" placeholder="${pujas.precioInicio}€" min="${pujas.precioInicio}" required>

                                <label for="username">Usuario:</label>
                                <input type="text" id="username" name="username" placeholder="username" required>

                                <label for="password">Contraseña:</label>
                                <input type="password" id="password" name="password" placeholder="********" required>

                                <button type="submit">PUJAR</button>
                            </div>
                        </div>
                        </#list>
                </div>
            </div>
        </body>
    </html>
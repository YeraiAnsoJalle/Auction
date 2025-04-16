<!DOCTYPE html>
<html lang="en">
        <!--DIAPOSITIVA 4, ENEKO
        Listado de todos los productos disponibles para pujar, con su validación, etc... Por parter del usuario-->
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Pujas</title>
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
                <h1>Pujas Disponibles</h1>
                <div class="item">
                    <img src="/doc/web/img/paloBayesta.jpeg" alt="Palo con forma de pico">
                    <h2><strong>Palo Bayesta</strong></h2>
                        <p><span>Precio:</span> 20€</p>
                        <h2>Pujar:</h2>
                        <input type="number" id="puja" placeholder="20€" min="20" required>
                        <label for="user">Usuario:</label>
                        <input type="text" id="user" placeholder="user" required>
                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" placeholder="********" required>
                        <button>PUJAR</button>
                </div>
                <div class="item">
                    <img src="/doc/web/img/paloHacha.jpg" alt="Palo con forma de varita">
                    <h2><strong>Palo Hacha</strong></h2>
                    <p><span>Precio:</span> 50€</p>
                    <h2>Pujar:</h2>
                    <input type="number" id="puja" placeholder="50€" min="50"required>
                    <label for="user">Usuario:</label>
                    <input type="text" id="user" placeholder="user" required>
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" placeholder="********" required>
                    <button>PUJAR</button>
                </div>
                <div class="item">
                    <img src="/doc/web/img/paloPistola.jpg" alt="Palo con forma de espada larga">
                    <h2><strong>Palo Pistola</strong></h2>
                    <p><span>Precio:</span> 150€</p>
                    <h2>Pujar:</h2>
                    <input type="number" id="puja" placeholder="150€" min="150"required>
                    <label for="user">Usuario:</label>
                    <input type="text" id="user" placeholder="user" required>
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" placeholder="********" required>
                    <button>PUJAR</button>
                </div>
                <div class="item">
                    <img src="/doc/web/img/paloMaza.jpg" alt="Palos con forma de baston">
                    <h2><strong>Palo Maza</strong></h2>
                    <p><span>Precio:</span> 120€</p>
                    <h2>Pujar:</h2>
                    <input type="number" id="puja" placeholder="120€" min="120"required>
                    <label for="user">Usuario:</label>
                    <input type="text" id="user" placeholder="user" required>
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" placeholder="********" required>
                    <button>PUJAR</button>
                </div>
            </div>
        </body>
    </html>
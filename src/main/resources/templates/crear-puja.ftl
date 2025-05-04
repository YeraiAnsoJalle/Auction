<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Puja</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="contenedor">
    <div class="botones">
        <div class="salir">
            <a href="/doc/web/index.html"><img src="doc/web/img/iconoSalir.png" alt="iconoSalir"/></a>
        </div>
        <div class="salir">
            <a href="/doc/web/user/menu-user.html"><img src="doc/web/img/flechaVolver.png" alt="iconoVolver"/></a>
        </div>
    </div>
    <h1>Crear Subasta</h1>
    <div class="item">
        <form action="/crear-puja" method="post">
            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" name="nombre" placeholder="nombre" required>

            <label for="imagen">URL Imagen</label>
            <input type="text" id="imagen" name="imagen"  min="1" placeholder="imagenURL" required>

            <label for="descripcion">Descripción</label>
            <input type="text" id="descripcion" name="descripcion" placeholder="descripcion" required>

            <label for="precio">Precio Inicial</label>
            <input type="number" id="precio" name="precio" placeholder="precio" required>

            <button type="submit" value="Solicitar">CREAR</button>
        </form>
    </div>
</div>
</body>
</html>

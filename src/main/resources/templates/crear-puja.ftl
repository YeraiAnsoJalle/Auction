<!DOCTYPE html>
<html lang="en">
        <!--DIAPOSITIVA 6, ENEKO
        Pestaña de previsualización para la creación de una puja por parte de un usuario-->

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crear-puja</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="contenedor">
        <div class="botones">
            <div class="salir">
                <a href="/doc/web/index.html"><img src="/doc/web/img/iconoSalir.png" alt="iconoSalir"/></a>
            </div>
            <div class="salir">
                <a href="/doc/web/user/menu-user.html"><img src="/doc/web/img/flechaVolver.png" alt="iconoVolver"/></a>
            </div>
        </div>
        <h1>Crear Subasta</h1>
        <form>
            <label for="title">Nombre</label>
            <input type="text" id="title" name="title" placeholder="titulo" required>

            <label for="imageURL">URL Imagen</label>
            <input type="text" id="imageURL" name="imageURL" placeholder="imagenURL" required>

            <label for="description">Descripción</label>
            <input type="text" id="description" name="description" placeholder="descripcion" required>

            <label for="price">Precio Inicial</label>
            <input type="number" id="price" name="price" placeholder="precio" required>

            <a href="crear-puja-prev.ftl"><button type="submit">CREAR</button></a>
        </form>
    </div>
</body>
</html>
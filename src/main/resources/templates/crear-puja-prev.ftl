<!DOCTYPE html>
<html lang="en">
<!--DIAPOSITIVA 6, ENEKO - Previsualización de la puja-->

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Previsualización Puja</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
<div class="contenedor">
    <div class="botones">
        <div class="salir">
            <a href="/crear-puja"><img src="doc/web/img/flechaVolver.png" alt="iconoVolver"/></a>
        </div>
    </div>

    <div class="item">
        <img src="${imageURL}" alt="${title}">
        <h2><strong>${title}</strong></h2>
        <p><span>Precio:</span> ${price}€</p>
        <p><span>Descripción:</span> ${description}</p>
        <button>PUJAR</button>
    </div>
</div>
</body>
</html>

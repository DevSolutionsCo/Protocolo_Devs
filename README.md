## DEVS Protocol

This is a simple protocol for encrypting and decrypting messages. It uses a combination of a Caesar cipher and a simple substitution cipher.

### How to use

To encrypt a message, you will need to:

1. Convert the message to lowercase.
2. Replace all instances of the letter "a" with the letter "x".
3. Replace all instances of the letter "b" with the letter "w".
4. Reverse the order of the letters in the message.
5. Apply a Caesar cipher with a shift of 5 to the reversed message.
6. Convert the message to base64.

To decrypt a message, you will need to:

1. Convert the message from base64.
2. Apply a Caesar cipher with a shift of -5 to the message.
3. Reverse the order of the letters in the message.
4. Replace all instances of the letter "x" with the letter "a".
5. Replace all instances of the letter "w" with the letter "b".

### Code

The following code implements the DEVS protocol:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <input type="file" id="archivoDecodificar" />
    <button onclick="decodificarMensaje()">Decodificar</button>
    <div id="resultadoDecodificacion"></div>
    <a href="index.html">Codificalooo</aZ>

    <script>
        function decodificarMensaje() {

        const claveCesar = 5;
        const archivo = document.getElementById("archivoDecodificar").files[0];

        if (archivo) {
          const lector = new FileReader();

          lector.onload = function (event) {
            var mensajeCifra = event.target.result;
            const clave = "clavesita";

            mensajeCifra = atob(mensajeCifra);

            mensajeCifra = mensajeCifra.split('').reverse().join('');


            let mensajeDescifra = '';
            for (let i = 0;
```

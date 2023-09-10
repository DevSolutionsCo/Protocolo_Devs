## DEVS Protocol

This is a simple protocol for encrypting and decrypting messages. It uses a combination of a Caesar cipher and a simple substitution cipher.

### How to use

Para encriptar el mensaje se realiza lo siguiente:

1. Convierte el mensaje a letras minusculas.
2. Reemplaza todas las letras 'a' por 'x'.
3. Remplaza todas las letras 'b' por 'w'.
4. Cambia la estructura del mensaje.
5. Se aplica el cifrado cesar que consiste en desplazar cada letra 5 espacios en el abecedario.
6. Se convierte el mensaje a base64.
7. Se descarga un archivo .txt con el mensaje cifrado

Para desencriptar el mensaje se realiza lo siguiente:

1. Carga el archivo .txt a descodificar.
2. Se convierte de ASCII a binario.
3. Cambia la estructura del mensaje.
4. Remplaza todas las letras 'w' por 'b'.
5. Reemplaza todas las letras 'x' por 'a'.
6. Desplaza cada letra -5 espacios en el abecedario para volverlo al estado original.
7. Se muestra el mensaje descodificado.

### Code

El siguiente codigo muestra como funciona el protocolo DEVS para la parte de encrtiptado

```html
<!DOCTYPE html>
<html lang="en">
  <script>
    function codificarMensaje() {
      const claveCesar = 5;
      var mensaje = document.getElementById("mensaje").value;

      mensaje = mensaje.replace(/a/g, "x").replace(/b/g, "w");

      mensaje = mensaje.split("").reverse().join("");

      let mensajeCifra = "";
      for (let i = 0; i < mensaje.length; i++) {
        let char = mensaje.charCodeAt(i);
        if (char >= 65 && char <= 90) {
          char = ((char - 65 + claveCesar) % 26) + 65;
        } else if (char >= 97 && char <= 122) {
          char = ((char - 97 + claveCesar) % 26) + 97;
        }
        mensajeCifra += String.fromCharCode(char);
      }
      mensajeCifra = btoa(mensajeCifra);

      const archivoCifrado = new Blob([mensajeCifra.toString()], {
        type: "text/plain",
      });

      const enlaceDescarga = document.createElement("a");
      enlaceDescarga.href = URL.createObjectURL(archivoCifrado);
      enlaceDescarga.download = "mensaje_cifrado.txt";
      enlaceDescarga.click();
    }
  </script>
</html>
```

El siguiente codigo muestra como funciona el protocolo DEVS para la parte de desencriptado

```html
<!DOCTYPE html>
<html lang="en">
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

          mensajeCifra = mensajeCifra.split("").reverse().join("");

          let mensajeDescifra = "";
          for (let i = 0; i < mensajeCifra.length; i++) {
            let char = mensajeCifra.charCodeAt(i);
            if (char >= 65 && char <= 90) {
              char = ((char - 65 - claveCesar + 26) % 26) + 65;
            } else if (char >= 97 && char <= 122) {
              char = ((char - 97 - claveCesar + 26) % 26) + 97;
            }
            mensajeDescifra += String.fromCharCode(char);
          }

          mensajeDescifra = mensajeDescifra
            .replace(/x/g, "a")
            .replace(/w/g, "b");

          document.getElementById("resultadoDecodificacion").textContent =
            "Mensaje Decodificado: " + mensajeDescifra;
        };

        lector.readAsText(archivo);
      }
    }
  </script>
</html>
```

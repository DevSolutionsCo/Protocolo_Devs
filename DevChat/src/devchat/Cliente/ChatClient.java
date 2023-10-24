/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devchat.Cliente;

/**
 *
 * @author Alumno
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

public class ChatClient {
    private static final String SERVER_IP = "192.168.100.13"; // Reemplaza con la dirección IP del servidor
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("conectado al servidor");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String msj1;
            while (true) {
                System.out.print("TU: ");
                
                
                
                msj1 = consoleInput.readLine();
                ChatClient obj = new ChatClient();
                
                
                out.println(obj.codificar(msj1));

                if ("exit".equalsIgnoreCase(msj1)) {
                    break;
                }

                
                
                String respuesta = in.readLine();
                System.out.println("EL OTRO: " + obj.decodificar(respuesta));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public String codificar(String msj){
        int claveCesar = 5;
        

        StringBuilder reversed = new StringBuilder(msj).reverse();

        StringBuilder mensajeCifra = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char character = reversed.charAt(i);
            if (Character.isUpperCase(character)) {
                character = (char) (((character - 'A' + claveCesar) % 26) + 'A');
            } else if (Character.isLowerCase(character)) {
                character = (char) (((character - 'a' + claveCesar) % 26) + 'a');
            }
            mensajeCifra.append(character);
        }

        String mensajeCifradoF = Base64.getEncoder().encodeToString(mensajeCifra.toString().getBytes());
        return mensajeCifradoF;
    }
    
    
    public String decodificar(String msj2){
        int claveCesar = 5;

        byte[] mensajeCifradoBytes = Base64.getDecoder().decode(msj2);
        String mensajeCifrado = new String(mensajeCifradoBytes);

        StringBuilder mensajeDescifrado = new StringBuilder();
        for (int i = 0; i < mensajeCifrado.length(); i++) {
            char character = mensajeCifrado.charAt(i);
            if (Character.isUpperCase(character)) {
                character = (char) (((character - 'A' - claveCesar + 26) % 26) + 'A');
            } else if (Character.isLowerCase(character)) {
                character = (char) (((character - 'a' - claveCesar + 26) % 26) + 'a');
            }
            mensajeDescifrado.append(character);
        }

        String mensajeOriginal = new StringBuilder(mensajeDescifrado.toString()).reverse().toString();

        return mensajeOriginal;
    }
}

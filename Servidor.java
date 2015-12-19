/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Servidor {

    static final int Puerto=2005;
    static ServerSocket skServidor;
    static String FILE_REQUIRED=""; 
    static FileInputStream fileoutputstream = null;
    static DataInputStream dataimputstream = null;  
    static DataOutputStream dataoutputstream = null;
    static Socket sCliente;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            skServidor = new ServerSocket(Puerto);

            while ((true) && !skServidor.isClosed()) {

                System.out.println("Escucho el puerto " + Puerto);
                // Espero a que se conecte un cliente y creo un nuevo socket para el cliente
                sCliente = skServidor.accept();
                InputStream aux = sCliente.getInputStream();
                dataimputstream = new DataInputStream(aux);
                FILE_REQUIRED = dataimputstream.readUTF();

                File myFile = new File(FILE_REQUIRED);
                OutputStream outputstream = sCliente.getOutputStream();
                dataoutputstream = new DataOutputStream(outputstream);

                if (myFile.exists()) {
                    dataimputstream = new DataInputStream(new FileInputStream(FILE_REQUIRED));
                    FileReader inputFile = new FileReader(FILE_REQUIRED);
                    String line;
                    BufferedReader bufferReader = new BufferedReader(inputFile);
                    while ((line = bufferReader.readLine()) != null) {
                        dataoutputstream.writeUTF(line);
                    }
                } else {

                    dataoutputstream.writeUTF("NO EXISTE EL ARCHIVO " + FILE_REQUIRED);

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sCliente.close();
                dataoutputstream.close();
                dataimputstream.close();
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


package cliente;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    static final String Host = "localhost"; 
    static final int Puerto=2005;
    static Socket sCliente=null;
    static String FILE_REQUIRED="";                                                      
    static OutputStream outputstream = null;
    static DataOutputStream dataoutputstream = null;
    static DataInputStream datainpustream = null;

    public static void main(String[] args) throws IOException {

        try {
            sCliente = new Socket(Host, Puerto);
            // SE PIDE EL ARCHIVO AL USUARIO
            Scanner scanner = new Scanner(System.in);
            System.out.print("¿ Qué archivo quiere traer del servidor desde cliente ?:\t");
            FILE_REQUIRED = scanner.nextLine();
            // 
            outputstream = sCliente.getOutputStream();
            dataoutputstream = new DataOutputStream(outputstream);
            dataoutputstream.writeUTF(FILE_REQUIRED);
            
            //enviamos el nombre del archivo                                    
            datainpustream = new DataInputStream(sCliente.getInputStream());
 
            do { 
 
                System.out.println(datainpustream.readUTF());
                
            } while ((true) && (datainpustream.readBoolean()));
 
            
        } finally {
            try {
                sCliente.close();
                outputstream.close();
                datainpustream.close();
            } catch (IOException ex) {
               Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}

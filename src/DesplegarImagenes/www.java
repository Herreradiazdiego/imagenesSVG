package DesplegarImagenes;

import java.io.*;

import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;
import java.util.Scanner;

public class www extends Object{
    static final String CLASS_NAME = www.class.getSimpleName();
    static final Logger LOG = Logger.getLogger(CLASS_NAME);
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        if (args.length != 1) {

            LOG.severe("Ingrese una URL de manera correcta");

            System.exit(1);
        }
        String url = args[0];
        System.out.println("Ingresando a " + url);
        System.out.println("Ingrese el archivo para iniciar descarga:");
        String archivo = sc.nextLine();
        System.out.println("Buscando a" + archivo);
        String carpeta = "C:\\Users\\DiegoHerrera\\IdeaProjects\\Despliegue de imagenes svg";

        File dir = new File(carpeta);
        File file = new File(carpeta + archivo);

        try {
            URLConnection log = new URL(url).openConnection();
            log.connect();
            InputStream in = log.getInputStream();
            OutputStream out = new FileOutputStream(archivo);
            int clasey = 0;
            while (clasey != -1) {
                clasey = in.read();
                if (clasey != -1)
                    out.write(clasey);
            }

            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Abriendo el svg imagen");
            String DIR = "C:\\Users\\DiegoHerrera\\IdeaProjects\\Despliegue de imagenes svg"+archivo+".svg";
            ProcessBuilder abre = new ProcessBuilder();
            abre.command("cmd.exe", "/c", DIR);
            abre.start();
        } catch (IOException ex) {
        }
    }
    protected www(URL url) {
    }
}
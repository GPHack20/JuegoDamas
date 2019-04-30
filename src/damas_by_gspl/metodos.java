package damas_by_gspl;

import java.io.*;
/*
 * @author GPHack20
 */
public class metodos {
    void v(String x){
        System.out.print(x);
    }
    void vl(String x){
        System.out.println(x);
    }
    void EscribirArchivo(File F, String C){
        try{
            if(!F.exists()){
                F.createNewFile();
            }
            try (BufferedWriter gescr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(F,true), "UTF-8"))) {
                gescr.write(C+"\r\n");
                gescr.close();
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    void LeerArchivoTexto(File t){ 
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(t));
            String linea;
            while((linea=entrada.readLine())!=null){
                vl(linea);
            }
            entrada.close();
        }catch (IOException e) { vl(""+e);}
    }  
    int NumeroLineas(File t){ 
        int nL=0;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(t));
            String linea;
            while((linea=entrada.readLine())!=null){
                nL=nL+1;
            }
            entrada.close();
        }catch (IOException e) { vl(""+e);}
        return nL;
    }  
    String []AlmacenarVectorLinea(File t, int nL){ 
        String Cl[]=new String[nL];
        int c=0;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(t));
            String linea;
            while((linea=entrada.readLine())!=null){
                Cl[c]=linea;
                c=c+1;
            }
            entrada.close();
        }catch (IOException e) { vl(""+e);}
        return Cl;
    }
    void CrearDirectorio(File A){        
        if(!A.isDirectory()){
            A.mkdir();
        }
    }
    boolean BusquedaSecuencial(boolean i,String []N,String D){
        for(int c=0;c<N.length;c=c+1){
            if(N[c].equals(D)){
                i=false;
            }
        }
        return i;
    }
}
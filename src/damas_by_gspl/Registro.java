package damas_by_gspl;
import java.io.*;
import java.util.logging.*;
/*
 * @author GPHack20
 */
public class Registro {
    public static metodos m=new metodos();
    void REGISTROS_JUEGO(){ //Funcion para obtener el registro de el numero de partidas jugadas, las finalizadas y las no finalizadas
        File dir=new File("Informacion.dat");
        if(dir.exists()&&dir.length()!=0){
            int nL=m.NumeroLineas(dir);
            String []VT=m.AlmacenarVectorLinea(dir, nL);
            String []Vt;
            m.vl("********************************************");
            m.vl("**    --Registro de partidas jugadas!--   **");
            m.vl("********************************************");
            m.vl("Numero de partidas jugadas: "+nL+"");
            m.vl("Partidas finalizadas:");
            m.vl("***********************************************************************************************************************************");
            for(int z=0;z<VT.length;z=z+1){
                Vt=VT[z].split("&");
                if(!Vt[Vt.length-2].equals("X")&&!Vt[Vt.length-2].equals("Y")&&!Vt[Vt.length-2].equals("O")&&!Vt[Vt.length-2].equals("D")){
                    if(!Vt[Vt.length-2].equals("EMPATE")){
                        m.vl("Nombre de la partida: "+Vt[0]+" Jugador ganador: "+Vt[Vt.length-2]+" Fecha de inicio: "+Vt[1]+" Nombres de jugadores: "+Vt[2]+" "+Vt[3]+" Fecha de finalizacion: "+Vt[Vt.length-1]);
                    }else{
                        m.vl("Nombre de la partida: "+Vt[0]+" Estado de la partida: "+Vt[Vt.length-2]+" Fecha de inicio: "+Vt[1]+" Nombres de jugadores: "+Vt[2]+" "+Vt[3]+" Fecha de finalizacion: "+Vt[Vt.length-1]);
                    }              
                }
            }
            m.vl("***********************************************************************************************************************************");
            m.vl("Paridas no finalizadas:");
            m.vl("***********************************************************************************************************************************");
            for(int g=0;g<VT.length;g=g+1){
                Vt=VT[g].split("&");
                if(Vt[Vt.length-2].equals("X")||Vt[Vt.length-2].equals("Y")||Vt[Vt.length-2].equals("O")||Vt[Vt.length-2].equals("D")){
                    m.vl("Nombre de la partida: "+Vt[0]+" Fecha de inicio: "+Vt[1]+" Nombres de jugadores: "+Vt[2]+" "+Vt[3]);
                }
            }
            m.vl("***********************************************************************************************************************************");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            m.vl("No se han encontrado registros!");
        }
    }
}
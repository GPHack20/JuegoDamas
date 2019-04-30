/*
 * Juego de Damas por Guido Peñaloza (GPHack20)!
 * 997 lineas aproximadamente!
 */
package damas_by_gspl;
import LecturaDatos.*;
import java.io.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Damas_by_GSPL {
    public static String X="X",W="W",Z="Z",Y="Y"; //Variables para representar las fichas del jugador uno (NEGRAS)
    public static String O="O",Q="Q",P="P",D="D"; //Variables para representar las fichas del jugador dos (BLANCAS)
    public static File dir=new File("Informacion.dat");
    public static ComprobarMovimientos CM = new ComprobarMovimientos();    //
    public static IdentificarPosiciones IP = new IdentificarPosiciones();  // LLAMADO A LOS METODOS
    public static metodos m = new metodos();                               // DESDE OTROS ARCHIVOS
    public static LlenarPosiciones LP = new LlenarPosiciones();            //
    public static void Tabla(String MDC[][]){ //Visualizacion de la tabla de posiciones y estado de las fichas
        m.vl("\t\t\t*********************************\t\t\t\t\t\t*********************************");
        m.vl("\t\t\t**    Tabla de Movimientos     **\t\t\t\t\t\t**        Tabla de fichas      **");
        m.vl("\t\t\t*********************************\t\t\t\t\t\t*********************************");
        for(int c0=0;c0<8;c0=c0+1){
            for(int c1=0;c1<8;c1=c1+1){
                m.v("\t["+c0+","+c1+"]");
            }
            m.v("\t\t");
            for(int c2=0;c2<8;c2=c2+1){
                m.v("\t["+MDC[c0][c2]+"]");
            }
            m.vl("\n");
        }
    }
    public static String ot(){
        Date d=new Date();
        String dia=Integer.toString(d.getDate()); //Dia
        String mes=Integer.toString(d.getMonth()); //Mes
        String año=Integer.toString(d.getYear()); //Año
        String segundo=Integer.toString(d.getSeconds()); //Segundo
        String minuto=Integer.toString(d.getMinutes()); //Minuto
        String hora=Integer.toString(d.getHours()); //Hora
        String t=dia+":"+mes+":"+año+":"+segundo+":"+minuto+":"+hora; //Cadena que contiene el tiempo actual
        return t;
    }
    public static void NuevaPartida(String [][]MDC){ //Funcion para iniciar una nueva partida
        boolean i=true;     
        String TI=ot();
        String nP="",nPJ="",J,nSJ="";
        while(i){
            m.vl("\n\n********************************************");
            m.vl("**  -Bienvenido al juego de Damas Chinas- **");
            m.vl("********************************************");
            m.v("Nombre de la partida: ");
            nP=LecturaDatos.LeerCadenaC();
            m.v("Nombre del primer jugador: ");
            nPJ=LecturaDatos.LeerCadenaC();
            m.v("Nombre del segundo jugador: ");
            nSJ=LecturaDatos.LeerCadenaC();
            int nL=m.NumeroLineas(dir);
            String []VLT=m.AlmacenarVectorLinea(dir, nL);
            String []VT;
            int a=0;
            for(int u=0;u<VLT.length;u=u+1){
                VT=VLT[u].split("&");
                if(VT[0].equals(nP)){
                    a=a+1;
                }
            }
            if(a>0){ //Condicion para verificar si el nombre de la partida es diferente a los ya existentes
                m.vl("ESE NOMBRE DE PARTIDA YA EXISTE!!!!");
            }
            else{              
                if(nPJ.equals(nSJ)){ //Condicion para verficar que los nombres de los jugadores sean diferentes
                    m.vl("INGRESE NOMBRES DE JUGADORES DIFERENTES!!!!");                   
            }
                else{
                    i=false;
                }        
            }            
        }  
        J=nPJ;
        LP.LlenarPosiciones(MDC);       //Llamado a la funcion para llenar las posiciones iniciales de la matriz        
        CambioJugadores(MDC,nP,nPJ,nSJ,J,TI); //Llamado a funcion de inercambio de jugadores
    }
    public static String [][]DM(String [][]MI,String [][]MF){ //Funcion para guardar datos de un matriz en otra
        for(int c=0;c<8;c=c+1){
            for(int c1=0;c1<8;c1=c1+1){
                MF[c][c1]=MI[c][c1];
            }
        }
        return MF;
    }
    public static void G_Reg(String [][]MDC,String NP,String TI,String J1,String J2,String NJG,String TF){//Funcion para guardar posiciones
        int y=0;
        for(int c=0;c<8;c=c+1){
            for(int c1=0;c1<8;c1=c1+1){
                if(!MDC[c][c1].equals(" ")){
                    y=y+1;
                }
            }
        }
        y=y+6;
        String []ES=new String [y];
        ES[0]=NP+"&";
        ES[1]=TI+"&";
        ES[2]=J1+"&";
        ES[3]=J2+"&";
        ES[ES.length-1]=TF+"&";
        ES[ES.length-2]=NJG+"&";
        y=4;
        for(int c=0;c<8;c=c+1){
            for(int c1=0;c1<8;c1=c1+1){
                if(!MDC[c][c1].equals(" ")){
                    ES[y]=c+"&"+c1+"&"+MDC[c][c1]+"&";
                    y=y+1;
                }
            }
        }
        String LaE="";
        for(int c=0;c<ES.length;c=c+1){
            LaE=LaE+ES[c];
        }    
        if(!dir.exists()||dir.length()==0){
            try {
                dir.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Damas_by_GSPL.class.getName()).log(Level.SEVERE, null, ex);
            }
            m.EscribirArchivo(dir, LaE);
        }
        else{
            int nL=m.NumeroLineas(dir);
            String []AT=m.AlmacenarVectorLinea(dir, nL);
            String []LJ;
            int lae=-1;
            for(int w=0;w<AT.length;w=w+1){
                LJ=AT[w].split("&");
                if(LJ[0].equals(NP)){
                    lae=w;
                    break;
                }
            }
            if(lae!=-1){
                AT[lae]=LaE; 
                dir.delete();
                try {
                    dir.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Damas_by_GSPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(int v=0;v<AT.length;v++){
                    m.EscribirArchivo(dir, AT[v]);
                }
            }
            else{
                m.EscribirArchivo(dir, LaE);
            }
        }
    }
    public static void CambioJugadores(String [][]MDC,String nP,String nPJ,String nSJ,String J,String TI){ //Funcion para alternar los jugadores
        boolean i0=true;
        String [][]MT=new String [8][8];
        String gp;
        Tabla(MDC);
        while(i0){
            m.vl("[X,Y]=[POS. X,POS. Y]");
            m.vl("PIEZAS NEGRAS\nX:peon-W:peon marcado-Y:dama-Z:dama marcada\n---------------------------------------------");
            m.vl("PIEZAS BLANCAS\nO:peon-Q:peon marcado-D:dama-P:dama marcada\n---------------------------------------------");
            if(J.equals(nPJ)){
                m.vl("Es su turno "+nPJ+" (X)");
                m.vl("Posicion de la ficha a mover!");
                J=Jugador(nPJ,nSJ,MDC,J); 
                Tabla(MDC);
                MT=DM(MDC,MT);
                gp=IdentificarGP(MDC,nPJ,nSJ); 
                MDC=DM(MT,MDC);
                G_Reg(MDC,nP,TI,nPJ,nSJ,J,"");
                if(gp.equals(nSJ)){
                    m.vl("El ganador es "+nSJ+"!");
                    G_Reg(MDC,nP,TI,nPJ,nSJ,nSJ,ot());
                    break;
                }
                if(gp.equals(nPJ)){
                    G_Reg(MDC,nP,TI,nPJ,nSJ,nPJ,ot());
                    m.vl("El ganador es "+nPJ+"!");
                    break;
                }
                i0=Empata(MDC);
                if(i0==false){
                    G_Reg(MDC,nP,TI,nPJ,nSJ,"EMPATE",ot());
                    m.vl("Empate!");
                    break;
                }
            }
            if(J.equals(nSJ)){    
                m.vl("Es su turno "+nSJ+" (O)");
                m.vl("Posicion de la ficha a mover!");
                J=Jugador(nPJ,nSJ,MDC,J);
                m.vl(""+J);
                Tabla(MDC);
                MT=DM(MDC,MT);
                gp=IdentificarGP(MDC,nPJ,nSJ); 
                MDC=DM(MT,MDC);
                G_Reg(MDC,nP,TI,nPJ,nSJ,J,"");
                if(gp.equals(nSJ)){
                    m.vl("El ganador es "+nSJ+"!");
                    G_Reg(MDC,nP,TI,nPJ,nSJ,nSJ,ot());
                    break;
                }
                if(gp.equals(nPJ)){
                    m.vl("El ganador es "+nPJ+"!");
                    G_Reg(MDC,nP,TI,nPJ,nSJ,nPJ,ot());
                    break;
                }
                i0=Empata(MDC);
                if(i0==false){
                    G_Reg(MDC,nP,TI,nPJ,nSJ,"EMPATE",ot());
                    m.vl("Empate!");
                    break;
                }
            }
        }
    }
    public static String Jugador(String nPJ,String nSJ,String [][]MDC,String J){ //Funcion para identificar y hacer los movientos de jugadores
        boolean i1=true;
        int Px=0,Py=0;
        while(i1){
            m.v("POS. X: ");
            Px=LecturaDatos.LeerI();
            m.v("POS. Y: ");
            Py=LecturaDatos.LeerI();
            if(Px>=0&&Px<=7&&Py>=0&&Py<=7){
                if(J.equals(nPJ)&&(MDC[Px][Py].equals(X)||MDC[Px][Py].equals(Y))){
                    i1=IP.IdentificarPosicion(MDC,Px,Py,i1,X,Y,W,Z,O,D,Q,P);
                    if(i1==false){
                        J=nSJ;
                        break;
                    }                   
                }
                if(J.equals(nSJ)&&(MDC[Px][Py].equals(O)||MDC[Px][Py].equals(D))){
                        i1=IP.IdentificarPosicion(MDC,Px,Py,i1,X,Y,W,Z,O,D,Q,P);
                        if(i1==false){
                            J=nPJ;
                            break;
                        }
                }
            }
        }        
        Tabla(MDC);
        m.vl("Posicion donde desea colocar la ficha!");
        boolean i2=true;
        while(i2){
            int tx=Px,ty=Py;
            m.v("Numero de jugadas: ");
            int li=LecturaDatos.LeerI();
            int PosMov[][]=new int[li][2];
            boolean cm;
            for(int c0=0;c0<li;c0=c0+1){
                m.v("POS X:");
                PosMov[c0][0]=LecturaDatos.LeerI();
                m.v("POS Y:");
                PosMov[c0][1]=LecturaDatos.LeerI();
            }  
            int t=0,t1=0;
            String MovF=" ";
            for(int i=0;i<li;i=i+1){
                if((MDC[Px][Py]).equals(W)){
                    if(PosMov[i][0]==7){
                        MovF=Y;
                    } 
                    else{
                        MovF=X;
                    }
                        cm=CM.CM(Px,Py,PosMov[i][0],PosMov[i][1],MDC,W,Y,O,D);
                        if(cm==true){
                            if(i==0){
                                if(((PosMov[i][0]==Px+1)&&(PosMov[i][1]==Py-1))||((PosMov[i][0]==Px+1)&&(PosMov[i][1]==Py+1))){   
                                    Px=PosMov[i][0];
                                    Py=PosMov[i][1];
                                    MDC[Px][Py]=W;
                                    t=t+1;   
                                }
                                if(((PosMov[i][0]==Px+2)&&(PosMov[i][1]==Py-2))||((PosMov[i][0]==Px+2)&&(PosMov[i][1]==Py+2))){                              
                                    Px=PosMov[i][0];
                                    Py=PosMov[i][1];
                                    MDC[Px][Py]=W;
                                    t=t+1;
                                }
                            }
                            if(i>0){
                                if(((PosMov[i][0]==Px+2)&&(PosMov[i][1]==Py-2))||((PosMov[i][0]==Px+2)&&(PosMov[i][1]==Py+2))){                              
                                    Px=PosMov[i][0];
                                    Py=PosMov[i][1];
                                    MDC[Px][Py]=W;
                                    t=t+1;
                                }
                            }
                        }
                        if (cm==false){
                            break;
                        }
                }
                if((MDC[Px][Py]).equals(Q)){
                    if(PosMov[i][0]==0){
                        MovF=D;
                    } 
                    else{
                        MovF=O;
                    }
                    cm=CM.CM(Px,Py,PosMov[i][0],PosMov[i][1],MDC,X,Y,Q,D);                  
                    if(cm==true){
                            if(i==0){
                                if(((PosMov[i][0]==Px-1)&&(PosMov[i][1]==Py+1))||((PosMov[i][0]==Px-1)&&(PosMov[i][1]==Py-1))){   
                                    Px=PosMov[i][0];
                                    Py=PosMov[i][1];
                                    MDC[Px][Py]=Q;
                                    t=t+1;   
                                }
                                if(((PosMov[i][0]==Px-2)&&(PosMov[i][1]==Py+2))||((PosMov[i][0]==Px-2)&&(PosMov[i][1]==Py-2))){                            
                                    Px=PosMov[i][0];
                                    Py=PosMov[i][1];
                                    MDC[Px][Py]=Q;
                                    t=t+1;
                                }
                            }
                            if(i>0){
                                if(((PosMov[i][0]==Px-2)&&(PosMov[i][1]==Py+2))||((PosMov[i][0]==Px-2)&&(PosMov[i][1]==Py-2))){                              
                                    Px=PosMov[i][0];
                                    Py=PosMov[i][1];
                                    MDC[Px][Py]=Q;
                                    t=t+1;
                                }
                            }
                        }
                        if (cm==false){
                            break;
                        }
                }
                if((MDC[Px][Py]).equals(Z)){
                    MovF=Y;
                    cm=CM.CM(Px,Py,PosMov[i][0],PosMov[i][1],MDC,W,Z,O,D);
                        if(cm==true&&i==0){
                            if(PosMov[i][1]==(PosMov[i][1]-Py)&&PosMov[i][0]==(PosMov[i][0]-Px)||PosMov[i][0]==(Px-PosMov[i][0])&&PosMov[i][1]==(Py-PosMov[i][1])||PosMov[i][0]==(Px-PosMov[i][0])&&PosMov[i][1]==(PosMov[i][1]-Py)||PosMov[i][0]==(PosMov[i][0]-Px)&&PosMov[i][1]==(Py-PosMov[i][1])){ 
                                t1=1;
                            }
                                Px=PosMov[i][0];
                                Py=PosMov[i][1];
                                MDC[Px][Py]=Z;
                                t=t+1;
                        }
                        if(cm==true&&i>0&&t1==0){
                            if(PosMov[i][1]==Py+(PosMov[i][1]-Py)&&PosMov[i][0]==Px+(PosMov[i][0]-Px)||PosMov[i][0]==Px-(Px-PosMov[i][0])&&PosMov[i][1]==Py-(Py-PosMov[i][1])||PosMov[i][0]==Px-(Px-PosMov[i][0])&&PosMov[i][1]==Py+(PosMov[i][1]-Py)||PosMov[i][0]==Px+(PosMov[i][0]-Px)&&PosMov[i][1]==Py-(Py-PosMov[i][1])){                               
                                Px=PosMov[i][0];
                                Py=PosMov[i][1];
                                MDC[Px][Py]=Z;
                                t=t+1;
                            }                          
                        }
                        if (cm==false){
                            break;
                        }
                }
                
                if((MDC[Px][Py]).equals(P)){
                    MovF=D;
                    cm=CM.CM(Px,Py,PosMov[i][0],PosMov[i][1],MDC,Q,P,X,Y);
                        if(cm==true&&i==0){
                            if(PosMov[i][1]==(PosMov[i][1]-Py)&&PosMov[i][0]==(PosMov[i][0]-Px)||PosMov[i][0]==(Px-PosMov[i][0])&&PosMov[i][1]==(Py-PosMov[i][1])||PosMov[i][0]==(Px-PosMov[i][0])&&PosMov[i][1]==(PosMov[i][1]-Py)||PosMov[i][0]==(PosMov[i][0]-Px)&&PosMov[i][1]==(Py-PosMov[i][1])){ 
                                t1=1;
                            }
                                Px=PosMov[i][0];
                                Py=PosMov[i][1];
                                MDC[Px][Py]=D;
                                t=t+1;
                        }
                        if(cm==true&&i>0&&t1==0){
                            if(PosMov[i][1]==Py+(PosMov[i][1]-Py)&&PosMov[i][0]==Px+(PosMov[i][0]-Px)||PosMov[i][0]==Px-(Px-PosMov[i][0])&&PosMov[i][1]==Py-(Py-PosMov[i][1])||PosMov[i][0]==Px-(Px-PosMov[i][0])&&PosMov[i][1]==Py+(PosMov[i][1]-Py)||PosMov[i][0]==Px+(PosMov[i][0]-Px)&&PosMov[i][1]==Py-(Py-PosMov[i][1])){                               
                                Px=PosMov[i][0];
                                Py=PosMov[i][1];
                                MDC[Px][Py]=D;
                                t=t+1;
                            }                          
                        }
                        if (cm==false){
                            break;
                        }
                }               
            }
            if(t==li){
                MDC[tx][ty]=" ";
                for(int w=0;w<li;w=w+1){
                    MDC[PosMov[w][0]][PosMov[w][1]]=" ";
                }
                MDC[PosMov[li-1][0]][PosMov[li-1][1]]=MovF;
                i2=false;
            }
        }
        return J;
    }
    public static String IdentificarGP(String [][]MDC,String J1,String J2){ //Funcion para verificar el jugador ganador y perdedor
        int t1=0,t2=0,t3=0,t4=0;
        String JG="";
        boolean i1=true,i2=true;
        for(int c=0;c<8;c=c+1){
            for(int c1=0;c1<8;c1=c1+1){
                if(MDC[c][c1].equals(X)||MDC[c][c1].equals(Y)){
                    i1=IP.IdentificarPosicion(MDC,c,c1,i1,X,Y,W,Z,O,D,Q,P);
                    if(i1==true){
                        t3=t3+1;
                    }
                        t1=t1+1;
                }
                if(MDC[c][c1].equals(O)||MDC[c][c1].equals(D)){
                    i2=IP.IdentificarPosicion(MDC,c,c1,i2,X,Y,W,Z,O,D,Q,P);
                    if(i2==true){
                        t4=t4+1;
                    }
                        t2=t2+1;
                }
            }
        }
        if(t1==t3){
            JG=J2;
        }
        if(t2==t4){
            JG=J1;
        }
        return JG;
    }
    public static boolean Empata(String [][]MDC){ //Funcion para determinar si se empato
        boolean cm=true;
        int t1=0,t2=0,t3=0,t4=0;
        for(int c=0;c<MDC.length;c=c+1){
            for(int c1=0;c1<MDC.length;c1=c1+1){
                if(MDC[c][c1].equals(X)){t1=t1+1;}
                if(MDC[c][c1].equals(Y)){t2=t2+1;}
                if(MDC[c][c1].equals(D)){t3=t3+1;}
                if(MDC[c][c1].equals(O)){t4=t4+1;}
            }
        }
        if((t1==1&&t4==1&&t2==0&&t3==0)||(t2==1&&t3==1&&t1==0&&t4==0)){
            cm=false;
        }
        return cm;
    }
    public static void PartidasGravadas(){
        if(dir.exists()){
        int nL=m.NumeroLineas(dir);
        String []JT=m.AlmacenarVectorLinea(dir, nL);
        String PS;
        String [][]MDC=new String [8][8];
        String []VT;
        int ip=0;
        boolean i=true;
        m.vl("********************************************");
        m.vl("**          ---Partidas Gravadas---       **");
        m.vl("********************************************");
        for(int h=0;h<JT.length;h=h+1){
            VT=JT[h].split("&");
            if(VT[VT.length-2].equals("X")||VT[VT.length-2].equals("Y")||VT[VT.length-2].equals("O")||VT[VT.length-2].equals("D")){
                m.vl("Nombre de la partida: "+VT[0]+" Fecha de inicio: "+VT[1]+" Nombres de jugadores: "+VT[2]+" "+VT[3]);
            }
        }
        while(i){
            m.v("Escriba el nombre de la partida: ");
            PS=LecturaDatos.LeerCadenaC();
            for(int r=0;r<JT.length;r=r+1){
                i=m.BusquedaSecuencial(i, JT[r].split("&"), PS);
                if(i==false){
                    ip=r;
                    break;
                }
            }
            if(i==true){
                m.vl("Nombre de la partida incorrecto!");
            }
        }
        VT=JT[ip].split("&");
        int q=4;
        for(int w=0;w<(VT.length-5)/3;w=w+1){
                MDC[Integer.parseInt(VT[q])][Integer.parseInt(VT[q+1])]=VT[q+2];
                q=q+3;
        }
        for(int c0=0;c0<8;c0=c0+1){
            for(int c1=0;c1<8;c1=c1+1){
                if(MDC[c0][c1]==null){
                    MDC[c0][c1]=" ";
                }
            }
        }
        CambioJugadores(MDC,VT[0],VT[2],VT[3],VT[VT.length-1],VT[1]);        
    }
        else{
            m.vl("No existen partidas gravadas!");
        }
    }
    public static void main(String[] args){ //Cuepo del programa
        String MDC[][]=new String[8][8]; //Matriz para llenar las posiciones
        int op=0;
        do{
            m.vl("********************************************");
            m.vl("**   Juego de Damas por Guido Peñaloza!   **");
            m.vl("********************************************");
            m.vl("**  1. Jugar nueva partida!               **");
            m.vl("**  2. Partidas gravadas!                 **");
            m.vl("**  3. Registro!                          **");
            m.vl("**  4. Instrucciones!                     **");
            m.vl("**  5. Creditos!                          **");
            m.vl("**  6. Salir!                             **");
            m.vl("********************************************");
            m.v("Digite una opcion: ");
            op=LecturaDatos.LeerI();
            if((op<1)||(op>6)){
                m.vl("Opcion Incorrecta!!");
            }
            switch(op){
                case 1:
                    NuevaPartida(MDC);
                    break;
                case 2:
                    PartidasGravadas();
                    break;
                case 3:
                    Registro REG=new Registro();
                    REG.REGISTROS_JUEGO();
                    break;
                case 4:
                    try{
                        File t=new File("Instrucciones.txt");
                        m.LeerArchivoTexto(t);
                        Thread.sleep(20000);
                    }catch(Exception e){m.vl(""+e);}
                        break;
                case 5:
                    try{
                        File t=new File("Creditos.txt");
                        m.LeerArchivoTexto(t);
                        Thread.sleep(4000);
                    }catch(Exception e){m.vl(""+e);}
                    break;
            }
        }while(op!=6);
        m.vl("Espero que lo haya disfrutado! :D");
    }
}
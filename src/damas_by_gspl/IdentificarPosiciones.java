package damas_by_gspl;
/*
 * @author GPHack20
 */
public class IdentificarPosiciones { 
    boolean IdentificarPosicion(String MDC[][], int Px, int Py, boolean i1, String Peon, String Dama, String Peon1, String Dama1, String PeonE, String DamaE, String Peon1E, String Dama1E){ //Funcion para identificar la posicion correcta  
        int t1=0,t2=0,t3=0,t4=0;
        if(((MDC[Px][Py].equals(Peon))||(MDC[Px][Py].equals(Dama))||(MDC[Px][Py].equals(PeonE))||(MDC[Px][Py].equals(DamaE)))){   
            if((Px+1)<=7&&(Px+1)>=0&&(Py+1)<=7&&(Py+1)>=0){
                    if(MDC[Px+1][Py+1].equals(" ")){
                        t1=t1+1;
                    }
                    if(MDC[Px+1][Py+1].equals(Peon)||MDC[Px+1][Py+1].equals(Dama)){
                        t1=t1-1;
                    }
                    if(MDC[Px+1][Py+1].equals(PeonE)||MDC[Px+1][Py+1].equals(DamaE)){
                        t1=t1-1;
                        if((Px+2)<=7&&(Px+2)>=0&&(Py+2)<=7&&(Py+2)>=0){
                        if(MDC[Px+2][Py+2].equals(" ")){
                            t1=t1+2;
                        }
                    }
                    }
                }
            if((Px-1)<=7&&(Px-1)>=0&&(Py-1)<=7&&(Py-1)>=0){
                if(MDC[Px-1][Py-1].equals(" ")){
                        t2=t2+1;
                    }
                    if(MDC[Px-1][Py-1].equals(PeonE)||MDC[Px-1][Py-1].equals(DamaE)){
                        t2=t2-1;
                    }
                    if(MDC[Px-1][Py-1].equals(Peon)||MDC[Px-1][Py-1].equals(Dama)){
                        t2=t2-1;
                        if((Px-2)<=7&&(Px-2)>=0&&(Py-2)<=7&&(Py-2)>=0){
                        if(MDC[Px-2][Py-2].equals(" ")){
                            t2=t2+2;
                        }
                    }
                }
            }
            if((Px+1)<=7&&(Px+1)>=0&&(Py-1)<=7&&(Py-1)>=0){
                if(MDC[Px+1][Py-1].equals(" ")){
                        t3=t3+1;
                    }
                    if(MDC[Px+1][Py-1].equals(Peon)||MDC[Px+1][Py-1].equals(Dama)){
                        t3=t3-1;
                    }
                    if(MDC[Px+1][Py-1].equals(PeonE)||MDC[Px+1][Py-1].equals(DamaE)){
                        t3=t3-1;
                        if((Px+2)<=7&&(Px+2)>=0&&(Py-2)<=7&&(Py-2)>=0){
                        if(MDC[Px+2][Py-2].equals(" ")){
                            t3=t3+2;
                        }
                    }
                }
            }
            if((Px-1)<=7&&(Px-1)>=0&&(Py+1)<=7&&(Py+1)>=0){
                if(MDC[Px-1][Py+1].equals(" ")){
                        t4=t4+1;
                    }
                    if(MDC[Px-1][Py+1].equals(PeonE)||MDC[Px-1][Py+1].equals(DamaE)){
                        t4=t4-1;
                    }
                    if(MDC[Px-1][Py+1].equals(Peon)||MDC[Px-1][Py+1].equals(Dama)){
                        t4=t4-1;
                        if((Px-2)<=7&&(Px-2)>=0&&(Py+2)<=7&&(Py+2)>=0){
                        if(MDC[Px-2][Py+2].equals(" ")){
                            t4=t4+2;
                        }
                    }
                }
            }
            if(t1>0||t2>0||t3>0||t4>0){
                if(MDC[Px][Py].equals(Dama)){
                        MDC[Px][Py]=Dama1;
                        i1=false;
                }
                if(MDC[Px][Py].equals(DamaE)){
                        MDC[Px][Py]=Dama1E;
                        i1=false;
                }
                if(MDC[Px][Py].equals(Peon)){
                    if(t1>0||t3>0){
                        MDC[Px][Py]=Peon1;
                        i1=false;
                    }
                }   
                if(MDC[Px][Py].equals(PeonE)){
                    if(t2>0||t4>0){
                        MDC[Px][Py]=Peon1E;
                        i1=false;
                    }
                }
            }
        }
        return i1;
    }    
}
package damas_by_gspl;
/*
 * @author GPHack20
 */
public class ComprobarMovimientos {
    boolean CM(int Px, int Py, int Pxf, int Pyf, String [][]MDC,String Peon, String Dama, String PeonE, String DamaE){ //Funcion para comprobar si el moviento de una ficha es correcto
        boolean cm=false;
        int t1=0,t2=0,t3=0,t4=0;
        if(Pxf>=0&&Pxf<=7&&Pyf<=7&&Pyf>=0){
            for(int c=1;c<=7;c++){
                if((Px+c)<=Pxf&&(Py+c)<=Pyf){
                    if(MDC[Px+c][Py+c].equals(" ")){
                        t1=t1+1;
                    }
                }
                if((Px-c)>=Pxf&&(Py-c)>=Pyf){
                    if(MDC[Px-c][Py-c].equals(" ")){
                        t2=t2+1;
                    }
                }
                if((Px-c)>=Pxf&&(Py+c)<=Pyf){
                    if(MDC[Px-c][Py+c].equals(" ")){
                        t3=t3+1;
                    }
                }
                if((Px+c)<=Pxf&&(Py-c)>=Pyf){
                    if(MDC[Px+c][Py-c].equals(" ")){
                        t4=t4+1;
                    }
                }
            }
                if(t1==1&&MDC[Px][Py].equals(Peon)){
                    if(1==(Pxf-Px)&&1==(Pyf-Py)){
                        cm=true;
                    }
                    if(2==(Pxf-Px)&&2==(Pyf-Py)&&MDC[Px+1][Py+1].equals(PeonE)||MDC[Px+1][Py+1].equals(DamaE)){
                        MDC[Px+1][Py+1]=" ";
                        cm=true;
                    }
                }
                if(t4==1&&MDC[Px][Py].equals(Peon)){
                    if(1==(Pxf-Px)&&1==(Py-Pyf)){
                        cm=true;
                    }
                    if(2==(Pxf-Px)&&2==(Py-Pyf)&&MDC[Px+1][Py-1].equals(PeonE)||MDC[Px+1][Py-1].equals(DamaE)){
                        MDC[Px+1][Py-1]=" ";
                        cm=true;
                    }
                }
                if(t3==1&&MDC[Px][Py].equals(PeonE)){
                    if(1==(Px-Pxf)&&1==(Pyf-Py)){
                        cm=true;
                    }
                    if(2==(Px-Pxf)&&2==(Pyf-Py)&&MDC[Px-1][Py+1].equals(Peon)||MDC[Px-1][Py+1].equals(Dama)){
                        MDC[Px-1][Py+1]=" ";
                        cm=true;
                    }
                }
                if(t2==1&&MDC[Px][Py].equals(PeonE)){
                    if(1==(Px-Pxf)&&1==(Py-Pyf)){
                        cm=true;
                    }
                    if(2==(Px-Pxf)&&2==(Py-Pyf)&&MDC[Px-1][Py-1].equals(Peon)||MDC[Px-1][Py-1].equals(Dama)){
                        MDC[Px-1][Py-1]=" ";
                        cm=true;
                    }
                }
                if((MDC[Px][Py].equals(Dama))||(MDC[Px][Py].equals(DamaE))){
                    if(t1==(Pxf-Px)&&t1==(Pyf-Py)){
                        cm=true;
                    }
                    if(t2==(Px-Pxf)&&t2==(Py-Pyf)){
                        cm=true;
                    }
                    if(t3==(Px-Pxf)&&t3==(Pyf-Py)){
                        cm=true;
                    }
                    if(t4==(Pxf-Px)&&t4==(Py-Pyf)){
                        cm=true;
                    }
                }
                    if(t1==(Pxf-Px)-1&&t1==(Pyf-Py)-1){    
                        if(MDC[Pxf-1][Pyf-1].equals(PeonE)||MDC[Pxf-1][Pyf-1].equals(DamaE)&&MDC[Px][Py].equals(Dama)){
                            MDC[Pxf-1][Pyf-1]=" ";
                            cm=true;
                        }
                        if(MDC[Pxf-1][Pyf-1].equals(Peon)||MDC[Pxf-1][Pyf-1].equals(Dama)&&MDC[Px][Py].equals(DamaE)){
                            MDC[Pxf-1][Pyf-1]=" ";
                            cm=true;
                        }
                    }
                    if(t2==(Px-Pxf)-1&&t2==(Py-Pyf)-1){   
                        if(MDC[Pxf+1][Pyf+1].equals(PeonE)||MDC[Pxf+1][Pyf+1].equals(DamaE)&&MDC[Px][Py].equals(Dama)){
                            MDC[Pxf+1][Pyf+1]=" ";
                            cm=true;
                        }
                        if(MDC[Pxf+1][Pyf+1].equals(Peon)||MDC[Pxf+1][Pyf+1].equals(Dama)&&MDC[Px][Py].equals(DamaE)){
                            MDC[Pxf+1][Pyf+1]=" ";
                            cm=true;
                        }
                    }
                    if(t3==(Px-Pxf)-1&&t3==(Pyf-Py)-1){   
                        if(MDC[Pxf+1][Pyf-1].equals(PeonE)||MDC[Pxf+1][Pyf-1].equals(DamaE)&&MDC[Px][Py].equals(Dama)){
                            MDC[Pxf+1][Pyf-1]=" ";
                            cm=true;
                        }
                        if(MDC[Pxf+1][Pyf-1].equals(Peon)||MDC[Pxf+1][Pyf-1].equals(Dama)&&MDC[Px][Py].equals(DamaE)){
                            MDC[Pxf+1][Pyf-1]=" ";
                            cm=true;
                        }
                    }
                    if(t4==(Pxf-Px)-1&&t4==(Py-Pyf)-1){   
                        if(MDC[Pxf-1][Pyf+1].equals(PeonE)||MDC[Pxf-1][Pyf+1].equals(DamaE)&&MDC[Px][Py].equals(Dama)){
                            MDC[Pxf-1][Pyf+1]=" ";
                            cm=true;
                        }
                        if(MDC[Pxf-1][Pyf+1].equals(Peon)||MDC[Pxf-1][Pyf+1].equals(Dama)&&MDC[Px][Py].equals(DamaE)){
                            MDC[Pxf-1][Pyf+1]=" ";
                            cm=true;
                        }
                }
        }
        return cm;
    }    
}
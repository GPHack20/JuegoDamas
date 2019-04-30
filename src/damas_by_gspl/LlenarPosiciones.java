package damas_by_gspl;
/*
 * @author GPHack20
 */
public class LlenarPosiciones {
        public static String X="X",W="W",Z="Z",Y="Y"; //Variables para representar las fichas del jugador uno (NEGRAS)
        public static String O="O",Q="Q",P="P",D="D"; //Variables para representar las fichas del jugador dos (BLANCAS)
        void LlenarPosiciones(String MDC[][]){ //Funcion para llenar la tabla con X y O
        int t=0;
        for(int c0=0;c0<8;c0=c0+1){
            if(t==0){
                MDC[t][c0]=X;
                t=2;
            }
            if(t==2){
                MDC[t][c0]=X;
                t=1;
                c0=c0+1;
            }
            if(t==1){
                MDC[t][c0]=X;
                t=0;
            }            
        }
        t=7;
        for(int c0=7;c0>=0;c0=c0-1){
            if(t==7){
                MDC[t][c0]=O;
                t=5;
            }
            if(t==5){
                MDC[t][c0]=O;
                t=6;
                c0=c0-1;
            }
            if(t==6){
                MDC[t][c0]=O;
                t=7;
            }
        }
        for(int c0=0;c0<8;c0=c0+1){
            for(int c1=0;c1<8;c1=c1+1){
                if(MDC[c0][c1]==null){
                    MDC[c0][c1]=" ";
                }
            }
        }
        System.out.println("");
    }
}
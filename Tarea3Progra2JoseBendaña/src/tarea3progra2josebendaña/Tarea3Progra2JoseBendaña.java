package tarea3progra2josebendaña;

import java.util.ArrayList;
import java.util.Scanner;

public class Tarea3Progra2JoseBendaña {

    public static void main(String[] args) {
        Scanner inicio = new Scanner(System.in);
        String[][] matriz = new String[12][12];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j]="[ ]";
            }
        }
        matriz[2][2]="[X]"; matriz[5][3]="[C]"; matriz[1][0]="|"; matriz[10][0]="|"; matriz[0][9]="---"; matriz[0][0]="|"; matriz[11][6]="---";
        matriz[3][2]="[X]"; matriz[5][4]="[C]"; matriz[2][0]="|"; matriz[0][1]="---"; matriz[0][11]="|"; matriz[11][11]="|"; matriz[11][7]="---";
        matriz[4][2]="[X]"; matriz[5][5]="[C]"; matriz[3][0]="|"; matriz[0][2]="---"; matriz[1][11]="|"; matriz[0][11]="|"; matriz[11][8]="---";
        matriz[5][2]="[X]"; matriz[9][6]="[O]"; matriz[4][0]="|"; matriz[0][3]="---"; matriz[2][11]="|"; matriz[11][0]="|"; matriz[11][9]="---";
        matriz[6][2]="[X]"; matriz[6][5]="[X]"; matriz[5][0]="|"; matriz[0][4]="---"; matriz[3][11]="|"; matriz[0][10]="---"; matriz[11][10]="---";
        matriz[6][3]="[X]"; matriz[2][6]="[X]"; matriz[6][0]="|"; matriz[0][5]="---"; matriz[4][11]="|"; matriz[11][1]="---";
        matriz[6][4]="[X]"; matriz[3][6]="[X]"; matriz[7][0]="|"; matriz[0][6]="---"; matriz[5][11]="|";matriz[11][2]="---";
        matriz[6][6]="[X]"; matriz[4][6]="[X]"; matriz[8][0]="|"; matriz[0][7]="---"; matriz[6][11]="|";matriz[11][3]="---";
        matriz[6][5]="[X]"; matriz[5][6]="[X]"; matriz[9][0]="|"; matriz[0][8]="---"; matriz[7][11]="|"; matriz[11][4]="---";
        matriz[8][8]="[R]"; matriz[1][11]="|"; matriz[10][11]="|";matriz[9][11]="|";matriz[8][11]="|"; matriz[11][5]="---";
        
        /*
        todas esas aplicaciones en las matrices son las normales como las X C R O pero las demas son las espinas por asi decirlo para cuando se pase del tablero
        el robot muera por espinas por asi decirlo
        */
        
        ArrayList <Integer> cajasrecibidas = new ArrayList();
        cajasrecibidas.add(0, 1);
        cajasrecibidas.add(1,2);
        cajasrecibidas.add(2,3);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }
        matriz[8][8]="[ ]";
        System.out.print("""
                           Ingrese el recorrido que usted quiere que el robor haga automaticamente
                           por medio de una cadena de letras, divididas por (,), en caso de querer
                           que el robot se mueva para arriba, insertar [u], para abajo inserte [d]
                           para la derecha inserte [r], para la izquierda inserte [L], para recoger
                           caja inserte [g] y para poner caja inserte [s] en caso de querer entrar
                           en un obstaculo[X] el juego automaticamente seguira con la siguiente letra,
                           si se sale del tablero el robot morira automaticamente por espinas.
                           
                          [Si usted quiere probar el codigo de manera rapida solo copie y pegue esto
                           en la cadena "u,u,u,u,u,u,u,l,l,l,d,d,d,d,g,L,G,L,G,U,U,U,U,R,R,R,R,R,d,d,d,d,d,d,d,d,l,l,s,s,s"]
                           no copie las comillas.
                         
                           Inserte la cadena:  """);
        String cadena = inicio.nextLine();
        ArrayList<String> letras = new ArrayList();
        letras = separarcadena(cadena, letras);
        juego(matriz, letras, 8, 8, 0, cajasrecibidas); 
    }
    
    public static ArrayList<String> separarcadena(String cadena, ArrayList<String> letras){
        Scanner dividir = new Scanner(cadena);
        dividir.useDelimiter(",");
        while (dividir.hasNext()) {
            letras.add(dividir.next());   
        }
        return letras;
    }
    
    public static void juego(String[][] matriz, ArrayList<String> letras, int filarobot, int columrobot, int cajasobtenidas, ArrayList cajasrecibidas){
        matriz[filarobot][columrobot] = "[R]";
        
        if (cajasrecibidas.isEmpty()) {
            System.out.println("Felicidades usted ha entregado todas las cajas, por ende usted ha GANADOOOOOOOOOOOOOOOO");
            return;
            
            
        }else if(letras.isEmpty()){
            System.out.println("Usted ha perdido, se ha quedado sin movimientos en base a su cadena");
            return;
            
        }else{
            if (letras.get(0).equals("u")||letras.get(0).equals("U")) {
                if (matriz[filarobot-1][columrobot].equals("[X]")) {
                    letras.remove(0);
                    juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
                }else if(matriz[filarobot-1][columrobot].equals("[C]")&&((letras.get(0).equals("g"))||(letras.get(0).equals("G")))) {
                    letras.remove(0);
                    matriz[filarobot-1][columrobot]="[C]";
                    juego(matriz, letras, filarobot-1, columrobot, cajasobtenidas, cajasrecibidas);
                }else{
                    matriz[filarobot][columrobot] = "[ ]";
                    if(filarobot-1==0){
                        matriz[filarobot][columrobot] = "[ ]";
                        mostrar(matriz);
                        System.out.println("NAHHHHH, Has perdido, tu robot se ha caido al infierno, se ha salido del tablero");
                        return;   
                    }else{
                        matriz[filarobot-1][columrobot] = "[R]";
                        mostrar(matriz);
                        letras.remove(0);
                        juego(matriz, letras, filarobot-1, columrobot, cajasobtenidas, cajasrecibidas);
                    }
                }
            }else if(letras.get(0).equals("d")||letras.get(0).equals("D")){
                if (matriz[filarobot+1][columrobot].equals("[X]")) {
                    letras.remove(0);
                    juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
                }else if(matriz[filarobot+1][columrobot].equals("[C]")&&((letras.get(0).equals("g"))||(letras.get(0).equals("G")))) {
                    letras.remove(0);
                    matriz[filarobot+1][columrobot]="[C]";
                    juego(matriz, letras, filarobot+1, columrobot, cajasobtenidas, cajasrecibidas);
                }else{
                    matriz[filarobot][columrobot] = "[ ]";
                    if (filarobot + 1 == 11) {
                        matriz[filarobot][columrobot] = "[ ]";
                        mostrar(matriz);
                        System.out.println("NAHHHHH, Has perdido, tu robot se ha caido al infierno, se ha salido del tablero");
                        return;
                    } else {
                        matriz[filarobot + 1][columrobot] = "[R]";
                        mostrar(matriz);
                        letras.remove(0);
                        juego(matriz, letras, filarobot + 1, columrobot, cajasobtenidas, cajasrecibidas);
                    }

                }
            }else if(letras.get(0).equals("l")||letras.get(0).equals("L")){
                if (matriz[filarobot][columrobot-1].equals("[X]")) {
                    letras.remove(0);
                    juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
                }else if(matriz[filarobot][columrobot-1].equals("[C]")&&((letras.get(0).equals("g"))||(letras.get(0).equals("G")))) {
                    letras.remove(0);
                    matriz[filarobot][columrobot-1]="[C]";
                    juego(matriz, letras, filarobot, columrobot-1, cajasobtenidas, cajasrecibidas);
                }else{
                    matriz[filarobot][columrobot] = "[ ]";
                    if (columrobot - 1 == 0) {
                        matriz[filarobot][columrobot] = "[ ]";
                        mostrar(matriz);
                        System.out.println("NAHHHHH, Has perdido, tu robot se ha caido al infierno, se ha salido del tablero");
                        return;
                    } else {
                        matriz[filarobot][columrobot-1] = "[R]";
                        mostrar(matriz);
                        letras.remove(0);
                        juego(matriz, letras, filarobot, columrobot-1, cajasobtenidas, cajasrecibidas);
                    }

                }
            }else if(letras.get(0).equals("r")||letras.get(0).equals("R")){
                if (matriz[filarobot][columrobot+1].equals("[X]")) {
                    letras.remove(0);
                    juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
                }else if(matriz[filarobot][columrobot+1].equals("[C]")&&((letras.get(0).equals("g"))||(letras.get(0).equals("G")))) {
                    letras.remove(0);
                    matriz[filarobot][columrobot+1]="[C]";
                    juego(matriz, letras, filarobot, columrobot+1, cajasobtenidas, cajasrecibidas);
                }else{
                    matriz[filarobot][columrobot] = "[ ]";
                    if (columrobot + 1 == 11) {
                        matriz[filarobot][columrobot] = "[ ]";
                        mostrar(matriz);
                        System.out.println("NAHHHHH, Has perdido, tu robot se ha caido al infierno, se ha salido del tablero");
                        return;
                    } else {
                        matriz[filarobot][columrobot+1] = "[R]";
                        mostrar(matriz);
                        letras.remove(0);
                        juego(matriz, letras, filarobot, columrobot+1, cajasobtenidas, cajasrecibidas);
                    }

                }
            }else if(letras.get(0).equals("g")||letras.get(0).equals("G")){
                if ((((filarobot==5) && (columrobot == 3))||(((filarobot==5) && (columrobot == 4)))||(((filarobot==5) && (columrobot == 5))))){
                    matriz[filarobot][columrobot] = "[R]";
                    cajasobtenidas++;
                    mostrar(matriz);
                    letras.remove(0);
                    juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
                }else{
                    mostrar(matriz);
                    letras.remove(0);
                    juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
                }
            }else if(letras.get(0).equals("s")||letras.get(0).equals("S")){
                if ((filarobot == 9)&&(columrobot==6)) {
                    if(cajasobtenidas>0){
                        cajasrecibidas.remove(0);
                    }
                    mostrar(matriz);
                    letras.remove(0);
                    juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
                }else{
                    mostrar(matriz);
                    letras.remove(0);
                    juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
                }
            }else{
                mostrar(matriz);
                letras.remove(0);
                juego(matriz, letras, filarobot, columrobot, cajasobtenidas, cajasrecibidas);
            }
        } 
    }
    
    public static void mostrar(String[][] matriz){
        System.out.println("------------MATRIZ------------");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
}

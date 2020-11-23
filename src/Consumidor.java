
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin Manjarrez
 */
public class Consumidor extends Thread{
    private boolean pausado = false;
    private boolean ejecutar = true;
    
    NewJFrame puntero;
    private int contador=0;
    
    
    private int primos[] = new int[20];
    private int contadorPrimos=0;
    private int[] generados=null;
    public int semaforo=0;

    public Consumidor (NewJFrame n){
        puntero=n; 
    }
    
    public void pausar(){
        pausado = !pausado;
    }
    
    void detener() {
        ejecutar = false;
    }
    
    public int[] primosObtenidos(){
        return primos;
    }
    
    public int primosContador(){
        return contador;
    }
    
    public boolean estaDespausado(){
        return !pausado;
    }
    
    public void asignarValores(int[] vgenerados){
        generados = vgenerados;
    }
    
    /*    public String llenarArr(){
    for (int j = 1; j <= 100; j++) {
    if(k<20)
    arr2[k]=g.arr[j];
    cad+=k+"- "+arr2[k];
    k++;
    }
    return cad;
    }//llenarArr.
    */
    
    private static boolean esPrimo(int num){
		boolean primo = (num==1)?false:true;
		int divisor=2;
		while(primo && divisor<=Math.sqrt(num)){
			if(num%divisor==0){
				primo = false;
			}
			divisor++;
		}
		return primo;
    }

    @Override
    public void run(){
          while(ejecutar){
              try {
                  if(semaforo == -1){
                      contador = 0;
                      semaforo = 0;
                      pausar(); //Se despausa.
                  }
                  
                  if(estaDespausado()){
                      contador++;
                      puntero.jLabel1.setText("Analizando valores: "+contador+"Primos"+contadorPrimos+" "+semaforo);
                      if(contador<100){
                        if(esPrimo(generados[contador])){
                            primos[contadorPrimos]=generados[contador];
                            contadorPrimos++;
                            if(contadorPrimos==20){
                                puntero.jLabel1.setText("Se encontraron ya 20 primos.");
                                contadorPrimos=101;
                            }
                       }
                    }
                  }
                  if(contador>100){
                      semaforo=1;
                      puntero.jLabel1.setText(contador+ "Encontré "+contadorPrimos);
                      pausar();
                  }
                    
                  
                  sleep(100);
              } catch (InterruptedException ex) {
                  Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
    }

   
}


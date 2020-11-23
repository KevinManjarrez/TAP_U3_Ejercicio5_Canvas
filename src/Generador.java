
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
public class Generador extends Thread {
    
    private boolean pausado = false;
    private boolean ejecutar = true;
    NewJFrame puntero;
    
    
    //ajustes locales
    private int numeros[] = new int[100];
    private int contador=0;
    private int veces =0;
    public int semaforo=0; //0 = iniciando, 1 = ya genero, 2 = ejecuto consumidor, 3=limbo.
    
    
    
    public Generador (NewJFrame p){
        puntero=p;
    }
    
    public void pausar(){
        pausado = !pausado;
    }
    
    public void detener() {
        ejecutar = false;
    }
    
    public int[] valoresGenerados(){
        return numeros;
    }
    
    public int vecesQueGenero(){
        return veces;
    }
    
    public boolean estaDespausado(){
        return !pausado;
    }
    
    
    
    
    
    @Override
    public void run(){
          while(ejecutar){
              try {
                  if(estaDespausado()){
                    if(contador<100){
                        numeros[contador] = (int) (Math.floor(Math.random() *(500000 - 1000 + 1)));
                        contador++;
                        if(contador==100){
                           veces++;
                           contador=0;
                           semaforo=1;
                           pausar();
                        }
                    }
                  }
                  sleep(100);
              } catch (InterruptedException ex) {
                  Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
              }  
        }
    }

    
}

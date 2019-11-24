/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Modelo;
import java.text.DecimalFormat;
/**
 *
 * @author master
 */
public class Calculos {
    Modelo modelo = Modelo.getModelo();
    
    public void pago(){ //calcula el monto de cada pago
        
        double pago=modelo.getMonto()*modelo.getI()/(1- Math.pow((1+modelo.getI()),(modelo.getN()*-1)));
        System.out.println(pago);
        modelo.setPago(Math.round(pago*1000000d)/1000000d);
    }
    
    public double amortizacion(double actual, int periodo, double sumaInteres){ //Funcion Recursiva
        periodo++;//aumenta el numero del periodo
        
        double interes = Math.round(actual*modelo.getI()*1000000d)/1000000d;
        double amortizacion = Math.round((modelo.getPago()-interes)*1000000d)/1000000d;
        
        
        
        actual = Math.round((actual-amortizacion)*1000000d)/1000000d;
        
        DecimalFormat df = new DecimalFormat("$ ###,###,###.00");
 
        String[] rowModel = new String[5];
        
        
        
        rowModel[0]=periodo+"";
        rowModel[1]=df.format(modelo.getPago());
        rowModel[2]=df.format(interes);
        rowModel[3]=df.format(amortizacion);
        rowModel[4]=df.format(actual);
        
        modelo.addRow(rowModel);
        
        if (periodo<modelo.getN()) {
            
            sumaInteres=sumaInteres+interes;
            
            amortizacion(actual, periodo, sumaInteres); 
        }
        
        else{
            
            rowModel[0]="Total";
            rowModel[1]=df.format(modelo.getPago()*periodo);
            rowModel[2]=df.format(sumaInteres+interes);
            rowModel[3]=df.format(modelo.getMonto());
            rowModel[4]=df.format(0);
            modelo.addRow(rowModel);
        }
        
        return actual;
    }
}

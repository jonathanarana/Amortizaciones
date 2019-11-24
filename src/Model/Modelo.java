/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author master
 */
public class Modelo {
    private static Modelo modelo=null;
    private static double Monto,Pago,i;   //c=monto inicial r=pago i=interes
    private static int n;          //n=periodos
    DefaultTableModel modeloTabla;
    
    private Modelo(){}
    
    public static Modelo getModelo(){
        if (modelo==null) {
            modelo=new Modelo();
        }
        return modelo;
    }
    
    public void inicializar(double c, double i, int n){
        Modelo.Monto=c;
        Modelo.i=i;
        Modelo.n=n;
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Periodo");
        modeloTabla.addColumn("Pago");
        modeloTabla.addColumn("Interes");
        modeloTabla.addColumn("Amortización");
        modeloTabla.addColumn("Saldo");
        
        DecimalFormat df = new DecimalFormat("$ ###,###,###.00");
        String[] rowModel = new String[5];
        rowModel[0]="0";
        rowModel[1]="-";
        rowModel[2]="-";
        rowModel[3]="-";
        rowModel[4]=df.format(c);
        modeloTabla.addRow(rowModel);

    }
    
    public double getMonto(){
        return Monto;
    }
      
    public double getPago(){
        return Pago;
    }
    
    public double getI(){
        return i;
    }
    
    public int getN(){
        return n;
    }
    
    public DefaultTableModel getModelTabla(){
        return modeloTabla;
    }
    
    public void setPago(double pago){
        Modelo.Pago=pago;
    }
    
    public void addRow(String[] row){
        modeloTabla.addRow(row);
    }
    
    public void Logger(){
        System.out.println("Monto: "+Monto);
        System.out.println("Pago: "+Pago);
        System.out.println("Interes: "+i);
        System.out.println("Periodos: "+n);
    }
}

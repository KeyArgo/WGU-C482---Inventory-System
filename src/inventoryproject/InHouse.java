/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryproject;

/**
 *
 * @author Argo
 */
public class InHouse extends Part{
    //private variable
    private int machineID;

    
    //constructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID){
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }
    
    //setter
    public void setMachineID(int machineID){
        this.machineID = machineID;
    }
    
    //getter
    public int getMachineID(){
        return this.machineID;
    }
  
}

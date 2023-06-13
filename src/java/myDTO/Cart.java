/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myDTO;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Cart implements Serializable{
    private Plant plant;
    private int quantity;

    public Cart() {
    }

    public Cart(Plant plant, int quantity) {
        this.plant = plant;
        this.quantity = quantity;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}

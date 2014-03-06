/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hivemind;

/**
 *
 * @author Gia
 */
public class Individual {
    private double[] coefficients;
    private double fitness;

    public Individual() {
        
        
        
    }
    


    public double getFitness() {
        return fitness;
    }
    
    public double setFitness(double fitness){
        
        this.fitness = fitness;
        return fitness;
    }
    
    public double[] setCoefficients(double[] coefficients){
        this.coefficients = coefficients;
        return coefficients;
    }
    
    public double[] getCoefficients(){
        return coefficients;
    }
    
    public void mutate(){
        //here be mutants
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hivemind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Gia
 */
public class Individual {
    private double[] coefficients;
    private double fitness;
    private int rangeMax, rangeMin;
    private Random rand = new Random();
    
    /**
     * Constructor for when creating a fresh Individual
     * @param rangeMin the minimum range
     * @param rangeMax the maximum range
     */
    public Individual(int rangeMin, int rangeMax) {
        this.rangeMax=rangeMax;
        this.rangeMin=rangeMin;
        coefficients = new double[6];
        for(int i=0;i<coefficients.length ;i++){
            coefficients[i]=rangeMin+rand.nextDouble()*((rangeMax-rangeMin));
        }
    }
    
    public Individual(double[] coefficients, int rangeMin, int rangeMax){
        this.rangeMax=rangeMax;
        this.rangeMin=rangeMin;
        this.coefficients=coefficients;
    }
      
    
     /**
     * set the fitness of this individual
     * @param fitness pre calculated for this individual
     * @return 
     */
    public double setFitness(double fitness){       
        this.fitness = fitness;
        return fitness;
    }

	public int getMax() {
		return rangeMax;
	}
	public int getMin() {
		return rangeMin;
	}
    
    /**
     * returns the fitness of this individual
     * @return fitness
     */
    public double getFitness() {
        return fitness;
    }   
    
    /**
     * returns this individual
     * @return the coefficients of this individual
     */
    public double[] getIndividual(){
        return coefficients;
    }
    
    /**
     * sets the coefficients of this individual to a new set
     * @param coefficients new set of coefficients
     * @return the new coefficients;
     */
    public double[] setCoefficients(double[] coefficients){
        this.coefficients = coefficients;
        return coefficients;
    }
    
    /**
     * returns this individuals coefficients
     * @return coefficients
     */
    public double[] getCoefficients(){
        return coefficients;
    }
    
    /**
     * picks random number in coefficient array and mutates it 
     * @param coefficients array
     */
    public void mutate(){
        int i = rand.nextInt(coefficients.length);
        //not sure if this is 0-1 or 0-0.999999... Might need to +1
        coefficients[i]=rangeMin+rand.nextDouble()*((rangeMax-rangeMin));
        System.out.println(coefficients[i]);
    }
    
     /**
     * picks random number in coefficient array and mutates it 
     * @param coefficients array
     */
    public void multiMutate(){
        List<Integer> record = new ArrayList<>();
        int i;
        int mutateQuantity = rand.nextInt(coefficients.length);
        for(int j=0; j<=mutateQuantity;){
            i = rand.nextInt(coefficients.length);
            if(!record.contains(i)){
                coefficients[i]=rangeMin+rand.nextDouble()*((rangeMax-rangeMin));
                record.add(i);
                j++;
            }
        }
    }
}

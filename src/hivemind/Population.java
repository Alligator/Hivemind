/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hivemind;

import java.util.Map;
import java.util.Random;

/**
 *
 * @author Gia
 */
public class Population {

    private Individual[] allIndividuals;
    private double[] allIndividualsFitness;
    private double aggregateFitness;
    private Fitness fitness;

    public Population() {
        
        fitness = new Fitness();
    }

    // Method : add up all the fitnesses for the given population // Gia
    // needs testing
    private double setAggregateFitness(){
        
        // Reset, then recalculate
        aggregateFitness = 0;
        
        // Iterate through allIndividual Fitnesses and add them up to set the aggregate
        for (int i = 0; i < allIndividuals.length; i++) {

            aggregateFitness += allIndividualsFitness[i];

        }
        
        return this.aggregateFitness;
    }
    
    //Method : Set the each fitness of the population, for roulette wheel // Gia
    // needs testing
    private double[] setallIndividualsFitness(boolean print){
        
        //Method to iterate through any generation of curve coeff sets and output a fitness for each
   
        if(print){
             
            System.out.println("-------Calculating aggregate Fitness-----setAggregateFitness()");
            
        }
        int genSize = allIndividuals.length;
        this.allIndividualsFitness = new double[genSize];
        int i = 0;

        for (Individual indiv : allIndividuals) {

            double[] indivCoefficients = indiv.getCoefficients();
            double a = indivCoefficients[0];
            double b = indivCoefficients[1];
            double c = indivCoefficients[2];
            double d = indivCoefficients[3];
            double e = indivCoefficients[4];
            double f = indivCoefficients[5];
            double currentFitness = fitness.calculateCurveFitness(a, b, c, d, e, f, false);
            this.allIndividualsFitness[i] = currentFitness;
            i++;

            if (print) {
                System.out.println("---------------CurrentFitness  is.." + currentFitness + "-----------------------\n");
            }
        }

        setAggregateFitness();
        return this.allIndividualsFitness;

    }

    // Method to set roulette Wheel Selection //Gia // needs finishing
    public Individual rouletteWheelSelection() {
        return null;
    }

    public Individual tournamentSelection(int tournamentSize) {
        Random rnd = new Random();
        double best = 0.0;
        Individual winner = null;
        for (int i = 0; i < tournamentSize; i++) {
            Individual ind = allIndividuals[rnd.nextInt(allIndividuals.length)];
            if (ind.getFitness() > best) {
                best = ind.getFitness();
                winner = ind;
            }
        }
        return winner;
    }
}

package hivemind;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Gia
 */
public class Population {

	// Made this an arraylist to make life easier
	private ArrayList<Individual> allIndividuals;

	// the fitness values live in the individuals themselves
    // private double[] allIndividualsFitness;
    private double aggregateFitness;

	// fitness can be static as it won't change
    private static final Fitness fitness = new Fitness();
	
    /**
     * Construct a new, random population
     * @param size The size of the population to generate
	 * @param min  The minimum value for the coefficients
	 * @param max  The maximum value for the coefficients
     */
    public Population(int size, int min, int max) {
		for (int i = 0; i < size; i++) {
			allIndividuals.add(new Individual(min, max));
		}
    }

	/**
	 * Construct a new, empty population
	 */
	public Population() {
		// nothing to do yet
	}

	public void add(Individual ind) {
		allIndividuals.add(ind);
	}

	public int getSize() {
		return allIndividuals.size();
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
    
    // Method : Set the each fitness of the population, for roulette wheel // Gia
    // needs testing
    private void setallIndividualsFitness(){
		// NOTE FROM REECE: I'm going to refactor the print stuff so there doesn't need to be conditionals everywhere
        // Method to iterate through any generation of curve coeff sets and output a fitness for each
   
		System.out.println("-------Calculating aggregate Fitness-----setAggregateFitness()");
        for (Individual indiv : allIndividuals) {

            double[] indivCoefficients = indiv.getCoefficients();
            double a = indivCoefficients[0];
            double b = indivCoefficients[1];
            double c = indivCoefficients[2];
            double d = indivCoefficients[3];
            double e = indivCoefficients[4];
            double f = indivCoefficients[5];
            double currentFitness = fitness.calculateCurveFitness(a, b, c, d, e, f, false);
			indiv.setFitness(currentFitness);

			System.out.println("---------------CurrentFitness  is.." + currentFitness + "-----------------------\n");
        }
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
            Individual ind = allIndividuals.get(rnd.nextInt(allIndividuals.size()));
            if (ind.getFitness() > best) {
                best = ind.getFitness();
                winner = ind;
            }
        }
        return winner;
    }
}

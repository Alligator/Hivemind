package hivemind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Gia
 */
public class Population {

    private ArrayList<Individual> allIndividuals;
    private double aggregateFitness;
	private double minimumFitness;
	private double maximumFitness;
    private int populationSize;

	// the fitness values live in the individuals themselves
    // private double[] allIndividualsFitness;

	// fitness can be static as it won't change
    private static final Fitness fitness = new Fitness();

	// TODO: make private and add accessor. im lazy
	public Individual bestIndividual;
	public Individual worstIndividual;
	
    /**
     * Construct a new, random population
     * @param size The size of the population to generate
	 * @param min  The minimum value for the coefficients
	 * @param max  The maximum value for the coefficients
     */
    public Population(int size, int min, int max) {
		allIndividuals = new ArrayList<Individual>();
		for (int i = 0; i < size; i++) {
			allIndividuals.add(new Individual(min, max));
		}
    }

	public void print() {
		for (Individual ind : allIndividuals) {
			System.out.print(Arrays.toString(ind.getCoefficients()));
			System.out.print(" ");
			System.out.println(ind.getFitness());
		}
	}

	/**
	 * Construct a new, empty population
	 */
	public Population() {
		allIndividuals = new ArrayList<Individual>();
	}

	public void add(Individual ind) {
		allIndividuals.add(ind);
	}

	public int getSize() {
		return allIndividuals.size();
	}

	public double getMinimumFitness() {
		return minimumFitness;
	}

	public double getMaxnimmFitness() {
		return maximumFitness;
	}
    
    /**
     * Method to return an individual by  roulette wheel Selection
     * @return Individual
     */
	   public Individual rouletteWheelSelection() {

        // System.out.println("Roulette Wheel selection now...");
        //System.out.println("aggregate Fitness is  " + this.aggregateFitness);
        //Individual[] individuals = allIndividuals; // get population as array
        //double[] allIndividualsFitness; // get population fitness array
        //double popAggregateFitness = aggregateFitness;
        double[] rouletteWheel = new double[allIndividuals.size()];
        double lotteryNumber = Math.random();
        double totalPercentCheck = 0.0;
        double invertedTotal = 0.0;
        double finalTotalPercentCheck = 0.0;
        double fitnessPercentOfEach[] = new double[allIndividuals.size()];
        double invertedFitnessOfEach[] = new double[allIndividuals.size()];
        Individual selectedIndividual = new Individual(0, 1);

        Individual allIndividualsTmp[] = new Individual[allIndividuals.size()];

        for (int i = 0; i < allIndividuals.size(); i++) {

            allIndividualsTmp[i] = allIndividuals.get(i);
        }

        for (int i = 0; i < allIndividuals.size(); i++) {

            double currentFitness = allIndividualsTmp[i].getFitness();
            double fitnessRatio = currentFitness / aggregateFitness;
            fitnessPercentOfEach[i] = fitnessRatio;
            // System.out.println("FitnessRatio is " + fitnessRatio);
            totalPercentCheck += fitnessPercentOfEach[i];

        }

        // Invert the fitness ratio, because the fittest are closest to zero
        // This is stored in an array
        // This can't be done in the previous for loop, because it needs the total percent check
        for (int i = 0; i < allIndividuals.size(); i++) {

            invertedFitnessOfEach[i] = totalPercentCheck - fitnessPercentOfEach[i];
            invertedTotal += invertedFitnessOfEach[i];
        }

        // Set up the roulette Wheel
        // This can't be done in the previous for loop, because it needs the inverted total
        for (int i = 0; i < allIndividuals.size(); i++) {

            finalTotalPercentCheck += invertedFitnessOfEach[i] / invertedTotal;
            rouletteWheel[i] = finalTotalPercentCheck;
            // System.out.println(rouletteWheel[i]);

        }

         // Now spin the roulette wheel
        for (int i = 0; i < rouletteWheel.length; i++) {
            double roulNum = rouletteWheel[i];
            // System.out.println("Comparing " + roulNum + " with " + lotteryNumber);

            if (lotteryNumber < roulNum) {
                // System.out.print("-------^^^----- at index " + i + " ");
                selectedIndividual = allIndividualsTmp[i];
                break;
            }
        }
        //System.out.println("Total percent check is..." + totalPercentCheck);

        // System.out.println("\n\n\n\n\n\n\n\n\n\n");

        return selectedIndividual;
    }

    public Individual tournamentSelection(int tournamentSize) {
        Random rnd = new Random();
        double best = Double.MAX_VALUE;
        Individual winner = null;
        for (int i = 0; i < tournamentSize; i++) {
            Individual ind = allIndividuals.get(rnd.nextInt(allIndividuals.size()));
            if (ind.getFitness() < best) {
                best = ind.getFitness();
                winner = ind;
            }
        }
        return winner;
    }

    /**
     * Get the total fitness for the whole population (aggregate fitness)
     *
     * @return double value, aggregate fitness of whole population
     */
    public double getAggregateFitness() {
        return this.aggregateFitness;
    }

    /**
     * Method : Set the each Individual's fitness for the whole population Calls
     * the calcCurveFitness method
     *
     * @return
     */
    public void setAllIndividualsFitness() {
        int genSize = getSize();
        int i = 0;
		double sum = 0.0;
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		Individual best = null;
		Individual worst = null;

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
            i++;
			sum += currentFitness;
			if (currentFitness < min) {
				min = currentFitness;
				best = indiv;
			}
			if (currentFitness > max) {
				max = currentFitness;
				worst = indiv;
			}
        }
		aggregateFitness = sum;

		minimumFitness = min;
		maximumFitness = max;

		bestIndividual = best;
		worstIndividual = worst;
    }

    /**
     * Get all the fitness values from the stored array of population fitness
     * values
     *
     * @return double[], fitness for each member of the population
     */
    /*
     public double[] getPopulationFitness() {
     setAllIndividualsFitness();
     return this.allIndividualsFitness;
     }
     */
    /**
     * Get the population
     *
     * @return Array of Individuals
     */
    /*
     public Individual[] getPopulation(){
     return allIndividuals;
     }
     */
    /**
     * Generate a population
     *
     * @param min // min value of coefficients
     * @param max // max value of coefficients
     */
    /*
     public void makePopulation(int min, int max){
     for(int i = 0; i<populationSize; i++){
     Individual individual = makeIndividual(min, max);
     this.allIndividuals[i] = individual;
     }
     }
     */

    /**
     * Makes one individual
     * @param min //value range min for coefficients to generate
     * @param max //value range max for coefficients to generate
     * @return 
     */
    public Individual makeIndividual(int min, int max){
        Individual individual = new Individual(min,max);
        double[] indivCoefficients = individual.getCoefficients();
        return individual;
    }
}

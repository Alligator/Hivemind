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

	private static final Fitness fitness = new Fitness();

	private Individual bestIndividual;
	private Individual worstIndividual;

	/**
	 * Construct a new, random population
	 *
	 * @param size The size of the population to generate
	 * @param min The minimum value for the coefficients
	 * @param max The maximum value for the coefficients
	 */
	public Population(int size, int min, int max) {
		allIndividuals = new ArrayList<Individual>();
		for (int i = 0; i < size; i++) {
			allIndividuals.add(new Individual(min, max));
		}
	}

	/**
	 * Convenience method to print the entire populations coefficients and
	 * fitness
	 */
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

	/**
	 * Add an individual to the population
	 *
	 * @param ind The individual to be added
	 */
	public void add(Individual ind) {
		allIndividuals.add(ind);
	}

	/**
	 * Get the populations size (number of individuals)
	 *
	 * @return The size of the population
	 */
	public int getSize() {
		return allIndividuals.size();
	}

	/**
	 * Get the lowest fitness value in the population
	 *
	 * @return the fitness value
	 */
	public double getMinimumFitness() {
		return minimumFitness;
	}

	/**
	 * Get the highest fitness value in the population
	 *
	 * @return the fitness value
	 */
	public double getMaxnimmFitness() {
		return maximumFitness;
	}

	/**
	 * Method to return an individual by roulette wheel Selection
	 *
	 * @return Individual
	 */
	public Individual rouletteWheelSelection() {
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
		}

		// Now spin the roulette wheel
		for (int i = 0; i < rouletteWheel.length; i++) {
			double roulNum = rouletteWheel[i];
			if (lotteryNumber < roulNum) {
				selectedIndividual = allIndividualsTmp[i];
				break;
			}
		}

		return selectedIndividual;
	}

	/**
	 * Selects an individual from the population using tournament selection
	 *
	 * @param tournamentSize The size of the tournament to run
	 * @return The selected individual
	 */
	public Individual tournamentSelection(int tournamentSize) {
		Random rnd = new Random();

		Individual winner = null;
		double best = Double.MAX_VALUE;

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
	 * Set the each Individual's fitness for the whole population Calls the
	 * calcCurveFitness method
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
			double currentFitness = fitness.calculateCurveFitness(indiv, false);
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
	 * @return the best (fittest) individual in the population
	 */
	public Individual getBestIndividual() {
		return bestIndividual;
	}

	/**
	 * @return the worst (least fit) individual in the population
	 */
	public Individual getWorstIndividual() {
		return worstIndividual;
	}
}

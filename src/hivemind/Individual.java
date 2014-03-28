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
	private final int rangeMax, rangeMin;
	private final Random rand = new Random();

	/**
	 * Constructor for when creating a fresh Individual
	 *
	 * @param rangeMin the minimum range
	 * @param rangeMax the maximum range
	 */
	public Individual(int rangeMin, int rangeMax) {
		this.rangeMax = rangeMax;
		this.rangeMin = rangeMin;
		coefficients = new double[6];
		for (int i = 0; i < coefficients.length; i++) {
			coefficients[i] = rangeMin + rand.nextDouble() * ((rangeMax - rangeMin));
		}
	}

	/**
	 * Constructor for creating an individual from a set array of coefficients
	 *
	 * @param coefficients	The coefficients to use
	 * @param rangeMin	The minimum range
	 * @param rangeMax	The maximum range
	 */
	public Individual(double[] coefficients, int rangeMin, int rangeMax) {
		this.rangeMax = rangeMax;
		this.rangeMin = rangeMin;
		this.coefficients = coefficients;
	}

	/**
	 * set the fitness of this individual
	 *
	 * @param	fitness pre calculated for this individual
	 * @return
	 */
	public double setFitness(double fitness) {
		this.fitness = fitness;
		return fitness;
	}

	/**
	 * @return The max range for the coefficients
	 */
	public int getMax() {
		return rangeMax;
	}

	/**
	 * @return The min range for the coefficients
	 */
	public int getMin() {
		return rangeMin;
	}

	/**
	 * returns the fitness of this individual
	 *
	 * @return fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * returns this individual
	 *
	 * @return the coefficients of this individual
	 */
	public double[] getIndividual() {
		return coefficients;
	}

	/**
	 * sets the coefficients of this individual to a new set
	 *
	 * @param coefficients new set of coefficients
	 * @return the new coefficients;
	 */
	public double[] setCoefficients(double[] coefficients) {
		this.coefficients = coefficients;
		return coefficients;
	}

	/**
	 * returns this individuals coefficients
	 *
	 * @return the coefficients
	 */
	public double[] getCoefficients() {
		return coefficients;
	}

	/**
	 * picks random number in coefficient array and mutates it
	 */
	public void mutate() {
		int i = rand.nextInt(coefficients.length);
		coefficients[i] += (coefficients[i] / 100.0) * ((rand.nextDouble() * 2) - 1);
		if (coefficients[i] > rangeMax) {
			coefficients[i] = rangeMax;
		}
		if (coefficients[i] < rangeMin) {
			coefficients[i] = rangeMin;
		}
	}

	/**
	 * picks random number in coefficient array and mutates it
	 */
	public void multiMutate() {
		List<Integer> record = new ArrayList<>();
		int i;
		int mutateQuantity = rand.nextInt(coefficients.length);
		for (int j = 0; j <= mutateQuantity;) {
			i = rand.nextInt(coefficients.length);
			if (!record.contains(i)) {
				coefficients[i] += (coefficients[i] / 100.0) * ((rand.nextDouble() * 2) - 1);
				if (coefficients[i] > rangeMax) {
					coefficients[i] = rangeMax;
				}
				if (coefficients[i] < rangeMin) {
					coefficients[i] = rangeMin;
				}
				record.add(i);
				j++;
			}
		}
	}
}

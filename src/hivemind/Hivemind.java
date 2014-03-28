package hivemind;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reece
 */
public class Hivemind {

	private static final int ITERATIONS = 10000;

	private static final int POPULATION_SIZE = 100;
	private static final int MIN_RANGE = -10000;
	private static final int MAX_RANGE = 10000;

	private static final double CROSSOVER_RATE = 0.9;
	private static final double MUTATION_RATE = 0.45;

	private static final int TOURNAMENT_SIZE = 4;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		run(ITERATIONS);
	}

	/**
	 * Runs the main GA loop.
	 *
	 * @param iterations The number of iterations (generations) to run for.
	 */
	public static void run(int iterations) {
		String dat = new String();
		Random rnd = new Random();

		// initial population
		Population population = new Population(POPULATION_SIZE, MIN_RANGE, MAX_RANGE);

		for (int i = 0; i < iterations; i++) {
			population.setAllIndividualsFitness();
			if (i % 100 == 0) {
				System.out.println(i);
				System.out.println(population.getAggregateFitness() / population.getSize());
				System.out.println(population.getMinimumFitness());
				System.out.println("------------------");
			}
			Population newPopulation = new Population();

			// elitism
			newPopulation.add(population.getBestIndividual());

			for (int j = 0; j < population.getSize() - 1; j++) {
				if (rnd.nextDouble() < CROSSOVER_RATE) {
					Individual ind1 = population.tournamentSelection(TOURNAMENT_SIZE);
					Individual ind2 = population.tournamentSelection(TOURNAMENT_SIZE);
					// Individual ind1 = population.rouletteWheelSelection();
					// Individual ind2 = population.rouletteWheelSelection();

					Individual result = crossover(ind1, ind2);

					if (rnd.nextDouble() < MUTATION_RATE) {
						result.multiMutate();
					}

					newPopulation.add(result);
				} else {
					newPopulation.add(population.tournamentSelection(TOURNAMENT_SIZE));
				}
			}

			// tsv output, for later graphing
			dat += i + "\t" + population.getMinimumFitness()
				+ "\t" + (population.getAggregateFitness() / population.getSize())
				+ "\t" + population.getMaxnimmFitness() + "\n";
			population = newPopulation;
		}

		population.setAllIndividualsFitness();
		Fitness f = new Fitness();
		System.out.println(f.printPlotGNUCommands(population.getBestIndividual()));

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.dat"));
			bw.write(dat);
			bw.close();
		} catch (IOException ex) {
			Logger.getLogger(Hivemind.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Fixed point crossover
	 *
	 * @param ind1 The individual to take the first three coefficients from
	 * @param ind2 The individual to take the second three coefficients from
	 * @return The new individual
	 */
	private static Individual crossover(Individual ind1, Individual ind2) {
		double[] c1 = ind1.getCoefficients();
		double[] c2 = ind2.getCoefficients();
		Individual result = new Individual(new double[]{c1[0], c1[1], c1[2], c2[3], c2[4], c2[5]}, ind1.getMin(), ind1.getMax());
		return result;
	}
}

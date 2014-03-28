package hivemind;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gia
 */
public class Fitness {

	private final HashMap<Double, Double> sampleDataPoints;

	public Fitness() {
		sampleDataPoints = new HashMap<>();
		readSampleDataPoints();
	}

	/**
	 * Return the gnuplot command to plot the curve for a given individual
	 *
	 * @param ind The individual to be plotted
	 * @return
	 */
	public String printPlotGNUCommands(Individual ind) {
		double[] c = ind.getCoefficients();
		return String.format("plot %f + (%f * x) + (%f * (x**2)) + (%f * (x**3)) + (%f * (x**4)) + (%f * (x**5))", c[0], c[1], c[2], c[3], c[4], c[5]);
	}

	/**
	 * Calculate the fitness of an individual
	 *
	 * @param ind The individual to be processed
	 * @param print If set to true this method will print debugging output
	 * @return
	 */
	public double calculateCurveFitness(Individual ind, boolean print) {
		double fitness = 0;

		for (Map.Entry entry : sampleDataPoints.entrySet()) {
			double xSample = (Double) entry.getKey();
			double yValue = (Double) entry.getValue();

			double yValFromCurrentX = getYValFromFunctionX(ind.getCoefficients(), xSample, print);

			if (print) {
				System.out.println("X is " + xSample + " , \nY is : " + yValFromCurrentX + "\nY from Data is..." + yValue);
			}
			Double yDifference = (yValFromCurrentX - yValue);
			yDifference = Math.abs(yDifference);
			if (print) {
				System.out.println("Difference is... " + yDifference + "\n");
			}
			fitness += yDifference;

		}
		if (print) {
			// System.out.println("Final curve fitness for a:" + a + " b: " + b + " c: " + c + " d: " + d + " e: " + e + " f: " + f + " ");
			System.out.println("Fitness : " + fitness);
		}
		return fitness; // use getYValFromFunctionX // and hash map of data samples

	}

	/**
	 * Work out the Value Y for any given value X, and any given curve with the
	 * given coefficients.
	 *
	 * @param coefficients
	 * @param x	The x value to calculate.
	 * @param print If set to true this method will print debugging output
	 * @return
	 */
	public double getYValFromFunctionX(double[] coefficients, double x, boolean print) {
		double yVal = 0;
		for (int i = 0; i <= 5; i++) {
			yVal += coefficients[i] * Math.pow(x, i);
		}

		if (print) {
			System.out.println("Yval is..." + yVal);
		}
		return yVal;
	}

	private void readSampleDataPoints() {
		Scanner sc;
		try {
			sc = new Scanner(new BufferedReader(new FileReader("build/classes/hivemind/DatPoints.dat")));
			while (sc.hasNext()) {
				double x = sc.nextDouble();
				double y = sc.nextDouble();
				sampleDataPoints.put(x, y);
			}
			sc.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Fitness.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

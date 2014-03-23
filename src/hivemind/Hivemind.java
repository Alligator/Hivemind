/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hivemind;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reece
 */
public class Hivemind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
 //-------------------------------    Fitness Class     ------------------------------
        
        // Variables you need to initialize before you use any of the methods in the fitness class
        
        String filePath = "C:/Users/Gia/Desktop/IntelliSysAss2/datfile.dat";
        // Whether or not you want to print details in any method
        //boolean print = false;
        // coefficients
        //double a = 3.0;
        //double b = 0.5432;
        //double c = 0.44234;
        //double d = 0.64756;
        //double e = 0.76574;
        //double f = 0.767;
        //double testX = 450.0; // any value X on a graph
        
        //Fitness fitness = new Fitness();
        
        // Method : get the value Y from any given curve coefficients, and any given X
        //double YfromX = fitness.getYValFromFunctionX(a,b,c,d,e,f,testX,print);
        //System.out.println("Y value from X in function is   " + YfromX);
        
        //Method : this just prints out all the stuff in the dat file
        //fitness.printHashMap(578, print); // sample data points as static?????
        
        // Method : Get the fitness of any given curve, from its coefficients
        //double fitnessVal = fitness.calculateCurveFitness(a,b,c,d,e,f,print);
        //System.out.println(fitnessVal);
        
        // Method : Print out the function to plot the graphs in GnuPlot
        //String gnuCmd = fitness.printPlotGNUCommands(filePath, 0,4.3,3,2.3,0.5,0);
        //System.out.println(gnuCmd);
        
        //-------------------------------- Individual Class -------------------------------------//
        
        //Individual individual = new Individual(0,1);
        //double[] indivCoefficients = individual.getCoefficients();
        //System.out.println("Indiv coefficients are " + Arrays.toString(indivCoefficients));
        
        
        
//---------------------------------- Population Class -------------------------------------//
        
		/*
        Population pop = new Population(5); // 100 is population size
        pop.makePopulation(0,1); // min max of coefficients length
        Individual[] individuals = pop.getPopulation(); // get population as array
        double[] popFitness = pop.getPopulationFitness(); // get population fitness array
        double popAggregateFitness = pop.getAggregateFitness();
        Individual fittestIndividual = pop.rouletteWheelSelection();
        System.out.println("Selected Individual is.....\n" + Arrays.toString(fittestIndividual.getCoefficients()));
		*/
        
      
        
        // Print everything to do with the population
//        System.out.println("-----Coefficients of Population are-------");
//        for(int i=0; i<individuals.length; i++){
//            double[] iCoefficients = individuals[i].getCoefficients();
//            System.out.println("Individual number ..." + (i + 1));
//            System.out.println(Arrays.toString(iCoefficients));
//            
//        }
        
        // Print everything to do with the population
//        for(int i=0; i<popFitness.length; i++){
//            
//            System.out.println("Fitness for individual " + (i+1) + " is...");
//            System.out.println(popFitness[i]);
//        }
        
//        // Print everything to do with the population
//        System.out.println("Population Fitness as aggregate is.." + popAggregateFitness);
//       
		run(5000);
    }

    public static void run(int iterations) {
		// not intended to work, just hammering out details
		String dat = new String();

		// these will be some kind of probability function in the final version
		double crossoverRate = 0.8;
		double mutationRate = 0.25;

		Random rnd = new Random();

		int tournamentSize = 2;

		// initial population
		Population population = new Population(100, -1000, 1000);

		for (int i = 0; i < iterations; i++) {
			population.setAllIndividualsFitness();
			if (i % 100 == 0) {
				// population.print();
				System.out.println(i);
				System.out.println(population.getAggregateFitness()/population.getSize());
				System.out.println(population.getMinimumFitness());
				System.out.println("------------------");
			}
			Population newPopulation = new Population();

			// newPopulation.add(population.bestIndividual);

			for (int j = 0; j < population.getSize(); j++) {
				// for the size of the population (so it doesn't change) ...
				if (rnd.nextDouble() < crossoverRate) {
					// select two individuals for crossover
					Individual ind1 = population.tournamentSelection(tournamentSize);
					Individual ind2 = population.tournamentSelection(tournamentSize);
					// Individual ind1 = population.rouletteWheelSelection();
					// Individual ind2 = population.rouletteWheelSelection();

					// not sure where crossover will live yet
					Individual result = crossover(ind1, ind2);

					if (rnd.nextDouble() < mutationRate) {
						result.mutate();
					}

					// add the result to the new population
					newPopulation.add(result);
				} else {
					newPopulation.add(population.tournamentSelection(tournamentSize));
				}
			}
			dat += i + "\t" + population.getMinimumFitness()
				+ "\t" + (population.getAggregateFitness()/population.getSize())
				+ "\t" + population.getMaxnimmFitness() + "\n";
			population = newPopulation;
		}

		population.setAllIndividualsFitness();
		Fitness f = new Fitness();
		double[] b = population.bestIndividual.getCoefficients();
		System.out.println(f.printPlotGNUCommands("", b[0], b[1], b[2], b[3], b[4], b[5]));

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.dat"));
			bw.write(dat);
			bw.close();
		} catch (IOException ex) {
			Logger.getLogger(Hivemind.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

	/**
	 * Brain dead fixed point crossover
	 * @param ind1  The individual to take the first three coefficients from
	 * @param ind2  The individual to take the second three coefficients from
	 * @return      The new individual
	 */
	private static Individual crossover(Individual ind1, Individual ind2) {
		double[] c1 = ind1.getCoefficients();
		double[] c2 = ind2.getCoefficients();
		Individual result = new Individual(new double[]{c1[0], c2[1], c1[2], c2[3], c1[4], c2[5]}, ind1.getMin(), ind1.getMax());
		return result;
	}
}
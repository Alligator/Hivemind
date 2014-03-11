/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hivemind;

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
        boolean print = false;
        // coefficients
        double a = 3.0;
        double b = 0.5432;
        double c = 0.44234;
        double d = 0.64756;
        double e = 0.76574;
        double f = 0.767;
        double testX = 450.0; // any value X on a graph
        
        //----------------------------------------------------------------------------
        
        Fitness fitness = new Fitness();
        
        // Method : get the value Y from any given curve coefficients, and any given X
        double YfromX = fitness.getYValFromFunctionX(a,b,c,d,e,f,testX,print);
        System.out.println("Y value from X in function is   " + YfromX);
        
        //Method : this just prints out all the stuff in the dat file
        fitness.printHashMap(578, print); // sample data points as static?????
        
        // Method : Get the fitness of any given curve, from its coefficients
        double fitnessVal = fitness.calculateCurveFitness(a,b,c,d,e,f,print);
        System.out.println(fitnessVal);
        
        // Method : Print out the function to plot the graphs in GnuPlot
        String gnuCmd = fitness.printPlotGNUCommands(filePath, 0,4.3,3,2.3,0.5,0);
        System.out.println(gnuCmd);
        
        
//---------------------------------- Population Class -------------------------------------//
        
        print = true;
        //double[][] currentPopulation = pop.getPopulation(print);
		run(1);
    }

    public static void run(int iterations) {
		// not intended to work, just hammering out details

		// these will be some kind of probability function in the final version
		boolean crossover = true;
		boolean mutation = true;

		int tournamentSize = 3;

		// initial population
		Population population = new Population(100, -1000, 1000);

		for (int i = 0; i < iterations; i++) {
			for (int j = 0; j < population.getSize(); j++) {
				// for the size of the population (so it doesn't change) ...

				// make a blank population
				Population newPopulation = new Population();
				if (crossover) {
					// select two individuals for crossover
					Individual ind1 = population.tournamentSelection(tournamentSize);
					Individual ind2 = population.tournamentSelection(tournamentSize);

					// not sure where crossover will live yet
					Individual result = crossover(ind1, ind2);
					if (mutation) {
						result.mutate();
					}
					// add the result to the new population
					newPopulation.add(result);
				}
			}
		}
    }
}

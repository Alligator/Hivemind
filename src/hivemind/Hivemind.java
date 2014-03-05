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
        
        //-------------    Fitness Class     ----------------------
        
        String filePath = "C:/Users/Gia/Desktop/IntelliSysAss2/datfile.dat";
        boolean print = false;
        double a = 3.0;
        double b = 0.5432;
        double c = 0.44234;
        double d = 0.64756;
        double e = 0.76574;
        double f = 0.767;
        double testX = 450.0;
        
        Fitness fitness = new Fitness();
        
        double YfromX = fitness.getYValFromFunctionX(a,b,c,d,e,f,testX,print);
        System.out.println("Y value from X in function is   " + YfromX);
        
        fitness.printHashMap(578, print); // sample data points as static?????
        
        double fitnessVal = fitness.calculateCurveFitness(a,b,c,d,e,f,print);
        System.out.println(fitnessVal);
        
        
        String gnuCmd = fitness.printPlotGNUCommands(filePath, 0,4.3,3,2.3,0.5,0);
        System.out.println(gnuCmd);
        
        
        //------------------ Population Class -----------------//
        
        Population pop = new Population();
        
        //print = true;
        //double[][] currentPopulation = pop.getPopulation(print);
    }
    
}

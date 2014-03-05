/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hivemind;

import java.util.Random;

/**
 *
 * @author Gia
 */
public class Population {
	private Individual[] individuals;

    public Population() {
    }

	public Individual rouletteWheelSelection() {
		return null;
	}

	public Individual tournamentSelection(int tournamentSize) {
		Random rnd = new Random();
        double best = 0.0;
        Individual winner = null;
        for (int i = 0; i < tournamentSize; i++) {
			Individual ind = individuals[rnd.nextInt(individuals.length)];
            if (ind.getFitness() > best) {
                best = ind.getFitness();
                winner = ind;
            }
        }
		return winner;
	}
}

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
        Individual ind = individuals[rnd.nextInt(individuals.length)];
		return null;
	}
}

package com.elvehicle;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.elvehicle.model.Cell;
import com.elvehicle.model.Generation;
import com.elvehicle.model.Lights;
import com.elvehicle.model.Rule;

public class GameOfLifeApp {
	
	private Set<Rule> setOfRulesToApply = new HashSet<>();
	
	public GameOfLifeApp() {
		// init the set of rules on startup
		Rule rule1 = new Rule() {
			public boolean precondition(int lits, boolean isAlive) {
				return lits < 2 && isAlive;
			}
			public boolean populated() {
				return false;
			}
		};
		Rule rule2 = new Rule() {
			public boolean precondition(int lits, boolean isAlive) {
				return (lits == 2 || lits == 3) && isAlive;
			}
			public boolean populated() {
				return true;
			}
		};
		Rule rule3 = new Rule() {
			public boolean precondition(int lits, boolean isAlive) {
				return lits > 3 && isAlive;
			}
			public boolean populated() {
				return false;
			}
		};
		Rule rule4 = new Rule() {
			public boolean precondition(int lits, boolean isAlive) {
				return lits == 3 && !isAlive;
			}
			public boolean populated() {
				return true;
			}
		};
		setOfRulesToApply.add(rule1);
		setOfRulesToApply.add(rule2);
		setOfRulesToApply.add(rule3);
		setOfRulesToApply.add(rule4);
	}
	
	/**
	 * The pure function that makes the time tick, transforming the generation
	 * from one state to another;
	 * 
	 * @param generation
	 * @return
	 */
	public Generation tick(Generation generation) {
		Generation gen = new Generation();
		Lights lights = new Lights();
		lights.enlight(generation);
		for (Cell cell : lights.getLitCells()) {
			boolean isAlive = generation.contains(cell);
			int brightness = lights.getCellBrightness(cell);
			Rule rule = getRuleToApply(brightness, isAlive);
			if (rule != null && rule.populated()) {
				gen.addCell(cell);
			}
		}
		if (gen != null) {
			gen.calculateDim();
		} 
		return gen;
	}

	private Rule getRuleToApply(int brightness, boolean isAlive) {
		Rule rule = null;
		for (Rule ruleToApply : setOfRulesToApply) {
			if (ruleToApply.precondition(brightness, isAlive)) {
				return ruleToApply;
			}
		}
		return rule;
	}
	
	public static void main(String args[]) {
		GameOfLifeApp gol = new GameOfLifeApp();
		Generation generation = new Generation("3,2;4,3;2,4;3,4;4,4");
		Scanner input = new Scanner(System.in);
		// c - is the halting signal		
		while (! "c".equals(input.nextLine())) {
			generation.display();
			System.out.println();
			System.out.println();
			System.out.println();
			Lights lights = new Lights();
			lights.enlight(generation);
			lights.display();
			System.out.println();
			System.out.println();
			System.out.println();
			generation = gol.tick(generation);
			generation.display();
			System.out.println();
			System.out.println();
			System.out.println();
			lights = new Lights();
			lights.enlight(generation);
			lights.display();
		}
		input.close();
	}
}
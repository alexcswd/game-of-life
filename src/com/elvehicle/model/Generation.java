package com.elvehicle.model;

import java.util.HashSet;
import java.util.Set;

import com.elvehicle.utils.Utils;

/**
 * Class encompassing the whole generation.
 * It contains cells (their coordinates) characterizing the generation.
 *
 */
public class Generation {
	
	private Set<Cell> cells = new HashSet<>();
	private int dim = 0;
	
	public Generation() {
	}
	
	public Generation(String liveCells) {
		initLiveCells(liveCells);
	}
	
	public Set<Cell> getCells() {
		return cells;
	}
	
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	public boolean contains(Cell cell) {
		return cells.contains(cell);
	}
	
	// liveCells contains semicolon delimited live cells' coordinates
	// i.e. like this: "3,5;4,6;5,3;5,4;5,5"
	private void initLiveCells(String liveCellsStr) {
		if (Utils.isEmptyString(liveCellsStr)) {
			return;
		}
		for (String coords : liveCellsStr.split(";")) {
			String[] xy = coords.split(",");
			Cell cell = new Cell(xy[0], xy[1]);
			cells.add(cell);
		}
		calculateDim();
	}
	
	public void calculateDim() {
		dim = 0;
		for (Cell cell : cells) {
			dim = getDim(dim, cell);
		}
	}
	
	private int getDim(int currDim, Cell cell) {
		return Math.max(currDim, Math.max(cell.getX(), cell.getY()));
	}
	
	/**
	 * The utility function that allows to display the generation into console.
	 */
	public void display() {
		// .---.---.---.---.---.
		// |   |   | x |   |   |
		// |---|---|---|---|---|
		// |   |   |   | x |   |
		// |---|---|---|---|---|
		// |   | x | x |   |   |
		// '---'---'---'---'---'
		StringBuffer sb = new StringBuffer(".");
		for (int i = 1; i <= dim+1; i++) {
			sb.append("---.");
		}
		sb.append("\n");
		for (int i = 1; i <= dim+1; i++) {
			sb.append("|");
			for (int j = 1; j <= dim+1; j++) {
				String cell = cells.contains(new Cell(i, j)) ? " x |" : "   |";
				sb.append(cell);
			}
			sb.append("\n");
			if (i == dim+1) {
				sb.append("'");
				for (int d = 1; d <= dim+1; d++) {
					sb.append("---'");
				}
				sb.append("\n");
				
			} else {
				sb.append("|");
				for (int d = 1; d <= dim+1; d++) {
					sb.append("---|");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}

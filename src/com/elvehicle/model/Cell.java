package com.elvehicle.model;

public class Cell {

	private final int x;
	private final int y;
	
	public Cell(String x, String y) {
		this(Integer.valueOf(x), Integer.valueOf(y));
	}
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return String.format("(%d,%d)", this.x, this.y);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other.getClass() != Cell.class) {
			return false;
		}
		Cell otherCell = (Cell) other;
		return this.x == otherCell.x && this.y == otherCell.y;
	}
	
	@Override
	public int hashCode() {
		return this.x * 11 + this.y * 17;
	}
}

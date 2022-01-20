package com.elvehicle.model;

public interface Rule {
	boolean precondition(int lits, boolean isAlive);
	boolean populated();
}

package fr.tarot.counting.model;

public enum DoneType {
	PETITE(1), GARDE(2), GARDE_SANS(4), GARDE_CONTRE(6);
	
	int coefficient;
	DoneType(int coeff){
		coefficient = coeff;
	}
	
	public int getCoefficient() {
		return coefficient;
	}
}

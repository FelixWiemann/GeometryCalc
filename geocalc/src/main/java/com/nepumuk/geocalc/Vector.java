package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 26.06.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1 Interface containing all the functions for the different VectorClasses
 */
public interface Vector {
	// cross-product TODO
	// skalar-product TODO
	// is greater TODO
	// is smaller TODO
	// is greater or equals TODO
	// TODO who is bigger/smaller
	double EqualityTolerance = Double.MIN_VALUE;
	String DIMENSION_ERROR = "The dimensions of both vectors are not the same, but need to be";
	String ROTATE_ERROR_TOO_MUCH_DIMENSIONS = "can't rotate because to much dimensions";
	String IllegalArgumentExceptionNumValZero = "you cannot divide by zero";
	Exception exWithOutMessage = new Exception();
	Exception exROTATE_ERROR_TOO_MUCH_DIMENSIONS = new Exception(
			ROTATE_ERROR_TOO_MUCH_DIMENSIONS);
	IllegalArgumentException exDIMENSION_ERROR = new IllegalArgumentException(
			DIMENSION_ERROR);
	IllegalArgumentException exNumValZero = new IllegalArgumentException(
			IllegalArgumentExceptionNumValZero);

	double[] getComponents();

	void setComponents(double[] value);

	int getDimension();

	void setDimension(int value);

	double getLength();

	void setLength(double value) throws Exception;

	Vector getUnitVector();

	@Override
	boolean equals(Object o);

	@Override
	String toString();

	// is smaller or equals TODO
	Boolean IsUnitVector(Vector v);

	boolean compareIdentical(Vector v1, Vector v2);

	boolean compareIdentical(Vector v1, Vector v2, double tolerance);

	double getDistance(Vector v1, Vector v2) throws Exception;

	double getLength(Vector vector);

	double getValue(int index);

	double SumComponents();

	double SumSquaredComponents();

	double SumSquaredComponents(Vector vector);

	double SumComponents(Vector vector);

	String toString(String form, String separator);

	Vector increment(Vector v) throws Exception;

	Vector getUnitVector(Vector vector);

	Vector multiply(Vector v, double d);

	Vector Normalize(Vector vector) throws Exception;

	Vector subtract(Vector v1, Vector v2) throws Exception;

	Vector decrement(Vector v) throws Exception;

	Vector divide(Vector v, double d);

	Vector add(Vector v1, Vector v2) throws Exception;

	Vector multiply(double d, Vector v);

	Vector negate(Vector v) throws Exception;

	Vector stringToVector(String String, String form, String separator);

	void setValue(int index, double newValue);

	void Normalize() throws Exception;

	void setComponents(double x, double y, double z);

}

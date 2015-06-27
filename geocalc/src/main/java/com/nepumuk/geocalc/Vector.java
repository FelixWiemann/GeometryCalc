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
	Boolean IsUnitVector(VectorN v);

	boolean compareIdentical(VectorN v1, VectorN v2);

	boolean compareIdentical(VectorN v1, VectorN v2, double tolerance);

	double getDistance(VectorN v1, VectorN v2) throws Exception;

	double getLength(VectorN vector);

	double getValue(int index);

	double SumComponents();

	double SumSquaredComponents();

	double SumSquaredComponents(VectorN vector);

	double SumComponents(VectorN vector);

	String toString(String form, String separator);

	Vector rotateAroundVector(Vector VectorToRotate,
							  Vector AxisVector, double degrees) throws Exception;

	Vector increment(VectorN v) throws Exception;

	Vector rotateAroundX(Vector vector, double degrees_around_x)
			throws Exception;

	Vector rotateAroundY(Vector vector, double degrees_around_y)
			throws Exception;

	Vector rotateAroundZ(Vector vector, double degrees_around_z)
			throws Exception;

	Vector getUnitVector(VectorN vector);

	Vector rotateAroundZ(VectorN vector, double degrees) throws Exception;

	Vector multiply(VectorN v, double d);

	Vector Normalize(VectorN vector) throws Exception;

	Vector subtract(VectorN v1, VectorN v2) throws Exception;

	Vector decrement(VectorN v) throws Exception;

	Vector divide(VectorN v, double d);

	Vector add(VectorN v1, VectorN v2) throws Exception;

	Vector multiply(double d, VectorN v);

	Vector negate(VectorN v) throws Exception;

	Vector[] rotateAroundX(VectorN[] vs, double degrees) throws Exception;

	Vector[] rotateAroundY(VectorN[] vs, double degrees) throws Exception;

	Vector[] rotateAroundZ(VectorN[] vs, double degrees) throws Exception;

	Vector stringToVector(String String, String form, String separator);

	void rotateAroundVector(VectorN AxisVector, double degrees)
			throws Exception;

	void setValue(int index, double newValue);

	void Normalize() throws Exception;

	void rotateAroundVector(Vector AxisVector, double degrees)
			throws Exception;

	void rotateAroundX(double degrees_around_x) throws Exception;

	void rotateAroundY(double degrees_around_y) throws Exception;

	void rotateAroundZ(double degrees) throws Exception;

	void setComponents(double x, double y, double z);

}

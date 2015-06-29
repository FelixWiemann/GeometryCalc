package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 26.06.2015.
 * Interface containing all the functions for the different VectorClasses
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 */

public interface Vector {
	// cross-product TODO https://en.wikipedia.org/wiki/Euclidean_vector#Cross_product
	// TODO punktprodukt https://en.wikipedia.org/wiki/Euclidean_vector#Dot_product
	// skalar-product TODO https://en.wikipedia.org/wiki/Euclidean_vector#Scalar_multiplication
	// scalar tripple product https://en.wikipedia.org/wiki/Euclidean_vector#Scalar_triple_product
	// is greater TODO
	// is smaller TODO
	// is greater or equals TODO
	// TODO who is bigger/smaller
	// is smaller or equals TODO https://en.wikipedia.org/wiki/Euclidean_vector#Equality
	// TODO calc this: https://en.wikipedia.org/wiki/Euclidean_vector#Opposite.2C_parallel.2C_and_antiparallel_vectors


	/**
	 * <p>Tolerance used when comparing vectors length to another one
	 */
	double EqualityTolerance = Double.MIN_VALUE;
	/**
	 * <p>string message telling the user that the vectors have not the same dimensions
	 */

	String DIMENSION_ERROR = "The dimensions of both vectors are not the same, but need to be";
	/**
	 * <p>String message telling the user that an invalid number of dimension was given so he can't
	 * <p>rotate these vectors. He needs to have exactly 3 dimensions to rotate
	 * <p>around the x and y axis. To rotate around z axis, you may have 2 or 3 dimensions
	 */
	String ROTATE_ERROR_TOO_MUCH_DIMENSIONS = "can't rotate because to much dimensions";
	/**
	 * <p>telling the user he can't divide by zero.
	 * <p>But why don't use the normal exception?
	 * <p>- 42, because reasons.
	 */
	String IllegalArgumentExceptionNumValZero = "you cannot divide by zero";
	/**
	 * <p>Exception without a message.
	 */
	Exception exWithOutMessage = new Exception();
	/**
	 * <p>Exception using the ROTATE_ERROR_TOO_MUCH_DIMENSIONS-string to tell the user whats happening
	 */
	Exception exROTATE_ERROR_TOO_MUCH_DIMENSIONS = new Exception(
			ROTATE_ERROR_TOO_MUCH_DIMENSIONS);
	/**
	 * <p>Exception using the DIMENSION_ERROR-string to tell the user whats happening
	 */
	IllegalArgumentException exDIMENSION_ERROR = new IllegalArgumentException(
			DIMENSION_ERROR);
	/**
	 * <p>Exception using the IllegalArgumentExceptionNumValZero-string to tell the user whats happening
	 */
	IllegalArgumentException exNumValZero = new IllegalArgumentException(
			IllegalArgumentExceptionNumValZero);

	/**
	 * <p>this function should return the components of the vector as an array of double-values.
	 * @return components of the vector as an array of double-values
	 */
	double[] getComponents();


	/**
	 * <p>this function should set the components of the vector
	 * @param value components of the vector
	 */
	void setComponents(double[] value);

	/**
	 * <p>this function should return the dimension of the vector. e.g. the vector (3,2,1,3)T should return 4
	 * @return dimension of the vector
	 */
	int getDimension();

	/**
	 * <p>creating an empty vector this could be useful for setting the storage size of the vector
	 * @param value dimension of the vector
	 */
	void setDimension(int value);


	/**
	 * <p>returns the length of the vector. see https://en.wikipedia.org/wiki/Euclidean_vector#Length
	 * @return length of the vector
	 */
	double getLength();

	/**
	 * <p>Don't change the direction of the vector, but the size. To do this you could normalize the vector and then multiply it with the length
	 * <p>see normalize() and multiply
	 * @param value length of the vector
	 * @throws Exception if something bad happens
	 */
	void setLength(double value) throws Exception;

	/**
	 * <p>This function returns the unit vector of the vector. unit vector has length=1
	 * <p>see https://en.wikipedia.org/wiki/Unit_vector
	 * @return unit vector
	 */
	Vector getUnitVector();

	@Override
	/**
	 * <p>is the current object of the same object as the given object?
	 */
	boolean equals(Object o);

	@Override
	/**
	 * <p>gives a string representation of the string
	 */
	String toString();


	/**
	 * <p>check whether the given vector has the length 1
	 * @param v Vector to evaluate
	 * @return should be true if v is a unit vector
	 */
	Boolean IsUnitVector(Vector v);

	/**
	 * <p>see whether the vectors are identical (length and direction) with the standard tolerance (Double.MIN_VALUE
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return true if vectors are the same
	 */
	boolean compareIdentical(Vector v1, Vector v2);

	/**
	 * <p>see whether the vectors are identical (length and direction) with the given tolerance
	 * @param v1 first vector
	 * @param v2 second vector
	 * @param tolerance used for evaluating
	 * @return true if vectors are the same
	 */
	boolean compareIdentical(Vector v1, Vector v2, double tolerance);

	/**
	 * @deprecated this will be moved to the Point class TODO
	 * returns the distance between two  ends of the vector
	 * @param v1 v1
	 * @param v2 v2
	 * @return distance
	 * @throws Exception sth. went wrong
	 */
	double getDistance(Vector v1, Vector v2) throws Exception;

	/**
	 * <p>returns the length of the vector. see https://en.wikipedia.org/wiki/Euclidean_vector#Length
	 * @param vector vector to calculate the length
	 * @return length of the vector
	 */
	double getLength(Vector vector);

	/**
	 * returns a value at the given index
	 * e.g.: call on vector v=(1,4,3) with getValue(2) returns 4
	 * @param index pointer to value
	 * @return value of the pointer
	 */
	double getValue(int index);

	/**
	 * sums up all components of the vector
	 * e.g.: call on vector v=(1,4,3) returns 1+4+3=8
	 * @return sum of all components of the vector
	 */
	double SumComponents();

	/**
	 * sums up all components squared
	 * e.g.: call on vector v=(1,4,3) returns 1^2+4^2+3^2=26
	 * @return sum of all components squared
	 */
	double SumSquaredComponents();

	/**
	 * sums up all components squared of the given vector
	 * e.g.: call on vector v=(1,4,3) returns 1^2+4^2+3^2=26
	 * @param vector
	 * @return
	 */
	double SumSquaredComponents(Vector vector);

	/**
	 *
	 * @param vector
	 * @return
	 */
	double SumComponents(Vector vector);

	/**
	 *
	 * @param form
	 * @param separator
	 * @return
	 */
	String toString(String form, String separator);

	/**
	 *
	 * @param v
	 * @return
	 * @throws Exception
	 */
	Vector increment(Vector v) throws Exception;

	/**
	 *
	 * @param vector
	 * @return
	 */
	Vector getUnitVector(Vector vector);

	/**
	 *
	 * @param v
	 * @param d
	 * @return
	 */
	Vector multiply(Vector v, double d);

	/**
	 *
	 * @param vector
	 * @return
	 * @throws Exception
	 */
	Vector Normalize(Vector vector) throws Exception;

	/**
	 * <p>https://en.wikipedia.org/wiki/Euclidean_vector#Addition_and_subtraction
	 * @param v1
	 * @param v2
	 * @return
	 * @throws Exception
	 */
	Vector subtract(Vector v1, Vector v2) throws Exception;


	/**
	 *
	 * @param v
	 * @return
	 * @throws Exception
	 */
	Vector decrement(Vector v) throws Exception;

	/**
	 *
	 * @param v
	 * @param d
	 * @return
	 */
	Vector divide(Vector v, double d);

	/**
	 * <p>https://en.wikipedia.org/wiki/Euclidean_vector#Addition_and_subtraction
	 * @param v1
	 * @param v2
	 * @return
	 * @throws Exception
	 */
	Vector add(Vector v1, Vector v2) throws Exception;

	/**
	 *
	 * @param d
	 * @param v
	 * @return
	 */
	Vector multiply(double d, Vector v);

	/**
	 *
	 * @param v
	 * @return
	 * @throws Exception
	 */
	Vector negate(Vector v) throws Exception;

	/**
	 *
	 * @param String
	 * @param form
	 * @param separator
	 * @return
	 */
	Vector stringToVector(String String, String form, String separator);

	/**
	 *
	 * @param index
	 * @param newValue
	 */
	void setValue(int index, double newValue);

	/**
	 *
	 * @throws Exception
	 */
	void Normalize() throws Exception;

	/**
	 *
	 * @param x
	 * @param y
	 * @param z
	 */
	void setComponents(double x, double y, double z);

}

package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 26.06.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1 Interface containing all the functions for the different VectorClasses
 */
public interface Vector {
	@Override
	boolean equals(Object o);

	@Override
	String toString();

	double[] getComponents();

	void setComponents(double[] value);

	int getDimension();

	void setDimension(int value);

	double getLength();

	void setLength(double value) throws Exception;

	VectorN getUnitVector();

	double getValue(int index);

	void Normalize() throws Exception;

	void rotateAroundVector(VectorN AxisVector, double degrees)
			throws Exception;

	void rotateAroundX(double degrees_around_x) throws Exception;

	void rotateAroundY(double degrees_around_y) throws Exception;

	void rotateAroundZ(double degrees) throws Exception;

	void setComponents(double x, double y, double z);

	void setValue(int index, double newValue);

	double SumComponents();

	double SumSquaredComponents();

	String toString(String form, String separator);
}

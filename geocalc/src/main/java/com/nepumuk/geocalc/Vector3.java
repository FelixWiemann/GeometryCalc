package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 26.06.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 */
public class Vector3 implements Vector {
	private double x_Value;
	private double y_Value;
	private double z_Value;

	@Override
	public double[] getComponents() {
		return new double[0];
	}

	@Override
	public void setComponents(double[] value) {

	}

	@Override
	public int getDimension() {
		return 0;
	}

	@Override
	public void setDimension(int value) {

	}

	@Override
	public double getLength() {
		return 0;
	}

	@Override
	public void setLength(double value) throws Exception {

	}

	@Override
	public VectorN getUnitVector() {
		return null;
	}

	@Override
	public double getValue(int index) {
		return 0;
	}

	@Override
	public void Normalize() throws Exception {

	}

	@Override
	public void rotateAroundVector(VectorN AxisVector, double degrees) throws Exception {

	}

	@Override
	public void rotateAroundX(double degrees_around_x) throws Exception {

	}

	@Override
	public void rotateAroundY(double degrees_around_y) throws Exception {

	}

	@Override
	public void rotateAroundZ(double degrees) throws Exception {

	}

	@Override
	public void setComponents(double x, double y, double z) {

	}

	@Override
	public void setValue(int index, double newValue) {

	}

	@Override
	public double SumComponents() {
		return 0;
	}

	@Override
	public double SumSquaredComponents() {
		return 0;
	}

	@Override
	public String toString(String form, String separator) {
		return null;
	}
}

package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 26.06.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 */
public class Vector2 implements Vector {
	/**
	 * Vector of length 1 describing the x-axis
	 */
	public final static Vector2 xAxis = new Vector2(1, 0);
	/**
	 * Vector of length 1 describing the y-axis
	 */
	public final static Vector2 yAxis = new Vector2(0, 1);
	public final static Vector2 origin = new Vector2(0, 0);
	private static final int dimension = 2;
	private double x_Value;
	private double y_Value;

	public Vector2(double x, double y) {
		x_Value = x;
		y_Value = y;
	}

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
	public Boolean IsUnitVector(VectorN v) {
		return null;
	}

	@Override
	public boolean compareIdentical(VectorN v1, VectorN v2) {
		return false;
	}

	@Override
	public boolean compareIdentical(VectorN v1, VectorN v2, double tolerance) {
		return false;
	}

	@Override
	public double getDistance(VectorN v1, VectorN v2) throws Exception {
		return 0;
	}

	@Override
	public double getLength(VectorN vector) {
		return 0;
	}

	@Override
	public double getValue(int index) {
		switch (index) {
			case 0:
				return x_Value;
			case 1:
				return y_Value;
			default:
				throw new IllegalArgumentException("");
		}
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
	public double SumSquaredComponents(VectorN vector) {
		return 0;
	}

	@Override
	public double SumComponents(VectorN vector) {
		return 0;
	}

	@Override
	public String toString(String form, String separator) {
		return null;
	}

	@Override
	public Vector rotateAroundVector(Vector VectorToRotate, Vector AxisVector, double degrees) throws Exception {
		return null;
	}

	@Override
	public Vector increment(VectorN v) throws Exception {
		return null;
	}

	@Override
	public Vector rotateAroundX(Vector vector, double degrees_around_x) throws Exception {
		return null;
	}

	@Override
	public Vector rotateAroundY(Vector vector, double degrees_around_y) throws Exception {
		return null;
	}

	@Override
	public Vector rotateAroundZ(Vector vector, double degrees_around_z) throws Exception {
		return null;
	}

	@Override
	public Vector getUnitVector(VectorN vector) {
		return null;
	}

	@Override
	public Vector rotateAroundZ(VectorN vector, double degrees) throws Exception {
		return null;
	}

	@Override
	public Vector multiply(VectorN v, double d) {
		return null;
	}

	@Override
	public Vector Normalize(VectorN vector) throws Exception {
		return null;
	}

	@Override
	public Vector subtract(VectorN v1, VectorN v2) throws Exception {
		return null;
	}

	@Override
	public Vector decrement(VectorN v) throws Exception {
		return null;
	}

	@Override
	public Vector divide(VectorN v, double d) {
		return null;
	}

	@Override
	public Vector add(VectorN v1, VectorN v2) throws Exception {
		return null;
	}

	@Override
	public Vector multiply(double d, VectorN v) {
		return null;
	}

	@Override
	public Vector negate(VectorN v) throws Exception {
		return null;
	}

	@Override
	public Vector[] rotateAroundX(VectorN[] vs, double degrees) throws Exception {
		return new Vector[0];
	}

	@Override
	public Vector[] rotateAroundY(VectorN[] vs, double degrees) throws Exception {
		return new Vector[0];
	}

	@Override
	public Vector[] rotateAroundZ(VectorN[] vs, double degrees) throws Exception {
		return new Vector[0];
	}

	@Override
	public Vector stringToVector(String String, String form, String separator) {
		return null;
	}

	@Override
	public void rotateAroundVector(VectorN AxisVector, double degrees) throws Exception {

	}

	@Override
	public void setValue(int index, double newValue) {

	}

	@Override
	public void Normalize() throws Exception {

	}

	@Override
	public void rotateAroundVector(Vector AxisVector, double degrees) throws Exception {

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
}

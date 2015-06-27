package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 26.06.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 */
public class Vector3 implements Vector {
	/**
	 * Vector of length 1 describing the x-axis
	 */
	public final static Vector3 xAxis = new Vector3(1, 0, 0);
	/**
	 * Vector of length 1 describing the y-axis
	 */
	public final static Vector3 yAxis = new Vector3(0, 1, 0);
	/**
	 * Vector of length 1 describing the z-axis
	 */
	public final static Vector3 zAxis = new Vector3(0, 0, 1);
	/**
	 * Vector describing the origin (0,0,0)
	 */
	public final static Vector3 origin = new Vector3(0, 0, 0);
	private static final int dimension = 3;
	private double x_Value;
	private double y_Value;
	private double z_Value;
	private double length;

	/**
	 * constructor of a vector with three dimensions x,y,z
	 *
	 * @param x x-value
	 * @param y y-value
	 * @param z z-value
	 */
	public Vector3(double x, double y, double z) {
		x_Value = x;
		y_Value = y;
		z_Value = z;
		length=this.getLength();
	}

	@Override
	public double[] getComponents() {
		return new double[]{x_Value, y_Value, z_Value};
	}

	@Override
	public void setComponents(double[] value) {
		x_Value = value[0];
		y_Value = value[1];
		z_Value = value[2];
		calculateLength();
	}

	private void calculateLength() {
		this.length = Math.sqrt(this.SumSquaredComponents());
	}

	@Override
	public int getDimension() {
		return 3;
	}

	@Override
	public void setDimension(int value) {
		throw new DimensionalError();
	}

	@Override
	public double getLength() {
		return length;
	}

	@Override
	public void setLength(double value) throws Exception {
	//TODO
	}

	@Override
	public Vector getUnitVector() {
		Vector3 res = new Vector3(x_Value / length, y_Value / length, z_Value / length);
		return res;
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
			case 2:
				return z_Value;
			default:
				throw new IllegalArgumentException("");
		}

	}

	@Override
	public double SumComponents() {
		return x_Value + y_Value + z_Value;
	}

	@Override
	public double SumSquaredComponents() {
		return x_Value * x_Value + y_Value * y_Value + z_Value * z_Value;
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

	/**
	 * rotates a Vector around an axis described by another vector with a number
	 * of degrees
	 *
	 * @param VectorToRotate
	 *            Vector to rotate
	 * @param AxisVector
	 *            vector describing the axis to rotate around
	 * @param degrees
	 *            amount of degrees that shall be rotated
	 * @return rotated vector
	 * @throws Exception
	 */
	@Override
	public Vector rotateAroundVector(Vector VectorToRotate,
									 Vector AxisVector, double degrees) throws Exception {
		/*
		 * TODO optimize, evaluate whether rotation matrix is better
		 * goal: axis
		 * vector has to match one of the axis. we choose y-axis because reasons
		 * first: rotate around y - axis, so that x component becomes zero
		 * second: rotate around x - axis, so that z component becomes zero ->
		 * the vector we rotate around becomes y
		 */
		double Degrees_around_y;
		double Degrees_around_x;
		/* how much to rotate around y that x becomes 0 */
		Degrees_around_y = Math.atan(AxisVector.getComponents()[0]
				/ (AxisVector.getComponents()[2] + EqualityTolerance));
		/* catch a non-valid result and set to 0 */
		if (Double.isNaN(Degrees_around_y)) {
			Degrees_around_y = 0;
		}
		/* to rotate in the right direction, multiply with -1 */
		Degrees_around_y *= -1;
		/* finally rotate around y */
		AxisVector.rotateAroundY(Degrees_around_y);
		/* same procedure with around xAxis */
		Degrees_around_x = Math.atan(AxisVector.getComponents()[2]
				/ AxisVector.getComponents()[1] + EqualityTolerance);
		if (Double.isNaN(Degrees_around_x)) {
			Degrees_around_x = 0;
		}
		Degrees_around_x *= -1;
		/* finally rotate */
		AxisVector.rotateAroundX(Degrees_around_x);
		/*
		 * now that the system has a new layout, we have to match our vector to
		 * that new layout. it should stay the same vector
		 */
		VectorToRotate.rotateAroundY(Degrees_around_y);
		VectorToRotate.rotateAroundX(Degrees_around_x);
		/*
		 * now we can rotate it around the vector that has now the same
		 * direction as the y axis
		 */
		VectorToRotate.rotateAroundY(degrees);
		/*
		 * we have to calculate the system back to the original one so rotate
		 * backwards axis vector is not needed anymore, don't need to calculate
		 * it again
		 */

		VectorToRotate.rotateAroundX(-Degrees_around_x);
		VectorToRotate.rotateAroundY(-Degrees_around_y);
		/* last but not least we can return this shit */
		return VectorToRotate;
	}

	@Override
	public Vector increment(VectorN v) throws Exception {
		return null;
	}

	/**
	 * rotate a 3D vector around the x axis
	 *
	 * @param vector
	 *            vector to rotate
	 * @param degrees_around_x
	 *            amount of degrees that shall be rotated
	 * @return rotated vector
	 * @throws Exception
	 */
	@Override
	public Vector rotateAroundX(Vector vector, double degrees_around_x)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better
		/* check whether it is a 3 dimensional vector */
		if (vector.getDimension() == 3) {
			/* create solution vector, to be sure everything is fine */
			double[] resvals, vecvals;
			resvals = CalcResultDim3.getComponents();
			vecvals = vector.getComponents();
			/* x value stays the same */
			resvals[0] = vecvals[0];
			/* y value is calculated with cos*y_value+sin*z_value */
			resvals[1] = Math.cos(degrees_around_x) * vecvals[1]
					- Math.sin(degrees_around_x) * vecvals[2];
			/* y value is calculated with sin*y_value+cos*z_value */
			resvals[2] = Math.cos(degrees_around_x) * vecvals[2]
					+ Math.sin(degrees_around_x) * vecvals[1];
			CalcResultDim3.setComponents(resvals);
			/* return result */
			return CalcResultDim3;
		} else {
			/* if not, BAM */
			throw exROTATE_ERROR_TOO_MUCH_DIMENSIONS;
		}
	}
	/**
	 * rotate a 3D vector around the y axis
	 *
	 * @param vector
	 *            vector to rotate
	 * @param degrees_around_y
	 *            amount of degrees that shall be rotated
	 * @return rotated vector
	 * @throws Exception
	 */
	@Override
	public VectorN rotateAroundY(Vector vector, double degrees_around_y)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		/*
		 * check whether it is a 3 dimensional vector further comments see:
		 * rotateAroundX
		 */
		if (vector.getDimension() == 3) {
			double[] resvals, vecvals;
			resvals = CalcResultDim3.getComponents();
			vecvals = vector.getComponents();
			resvals[0] = Math.sin(degrees_around_y) * vecvals[2]
					+ Math.cos(degrees_around_y) * vecvals[0];
			resvals[1] = vecvals[1];
			resvals[2] = Math.cos(degrees_around_y) * vecvals[2]
					- Math.sin(degrees_around_y) * vecvals[0];
			CalcResultDim3.setComponents(resvals);
			return CalcResultDim3;
		} else {
			// if not, BAM
			throw exROTATE_ERROR_TOO_MUCH_DIMENSIONS;
		}
	}


	@Override
	public Vector rotateAroundZ(Vector vector, double degrees)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		/* comments see rotateAroundX */
		if (vector.getDimension() == 3) {
			double[] resvals, vecvals;
			resvals = CalcResultDim3.getComponents();
			vecvals = vector.getComponents();
			resvals[0] = Math.cos(degrees) * vecvals[0] - Math.sin(degrees)
					* vecvals[1];
			resvals[1] = Math.sin(degrees) * vecvals[0] + Math.cos(degrees)
					* vecvals[1];
			resvals[2] = vecvals[2];
			CalcResultDim3.setComponents(resvals);
			return CalcResultDim3;
		} else {
			/* able to rotate around z if it has only two dimensions */
			if (vector.getDimension() == 2) {
				double[] resvals, vecvals;
				resvals = CalcResultDim3.getComponents();
				vecvals = vector.getComponents();
				resvals[0] = Math.cos(degrees) * vecvals[0] - Math.sin(degrees)
						* vecvals[1];
				resvals[1] = Math.sin(degrees) * vecvals[0] + Math.cos(degrees)
						* vecvals[1];
				CalcResultDim3.setComponents(resvals);
				return CalcResultDim3;
			} else {
				throw exROTATE_ERROR_TOO_MUCH_DIMENSIONS;
			}
		}
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

}

	@Override
	public void setValue(int index, double newValue) {

	}

	@Override
	public void Normalize() throws Exception {
		x_Value /= length;
		y_Value /= length;
		z_Value /= length;
		length = 1;
	}

	@Override
	public void rotateAroundVector(Vector AxisVector, double degrees) throws Exception {
		this.values = rotateAroundVector(this, AxisVector, degrees).values;
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

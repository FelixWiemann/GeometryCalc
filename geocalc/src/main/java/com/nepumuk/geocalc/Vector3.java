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
	private static Vector3 CalcResult = new Vector3(0, 0, 0);
	protected double x_Value;
	protected double y_Value;
	protected double z_Value;
	protected double length;

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

	public Vector3(Vector3 v) {
		this.x_Value = v.getValue(0);
		this.y_Value = v.getValue(1);
		this.z_Value = v.getValue(2);
		this.calculateLength();
	}

	private void calculateLength() {
		this.length = Math.sqrt(this.SumSquaredComponents());
	}

	public static Vector3 add(Vector3 v1, Vector3 v2) throws Exception {
		return null;
	}

	public static Vector3 multiply(double d, Vector3 v) {
		return null;
	}

	public static Vector3 multiply(Vector3 v, double d) {
		return null;
	}

	public Vector3 rotateAroundZ(Vector3 vector, double degrees)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		/* comments see rotateAroundX */
		if (vector.getDimension() == 3) {
			double[] resvals, vecvals;
			resvals = CalcResult.getComponents();
			vecvals = vector.getComponents();
			resvals[0] = Math.cos(degrees) * vecvals[0] - Math.sin(degrees)
					* vecvals[1];
			resvals[1] = Math.sin(degrees) * vecvals[0] + Math.cos(degrees)
					* vecvals[1];
			resvals[2] = vecvals[2];
			CalcResult.setComponents(resvals);
			return CalcResult;
		} else {
			/* able to rotate around z if it has only two dimensions */
			if (vector.getDimension() == 2) {
				double[] resvals, vecvals;
				resvals = CalcResult.getComponents();
				vecvals = vector.getComponents();
				resvals[0] = Math.cos(degrees) * vecvals[0] - Math.sin(degrees)
						* vecvals[1];
				resvals[1] = Math.sin(degrees) * vecvals[0] + Math.cos(degrees)
						* vecvals[1];
				CalcResult.setComponents(resvals);
				return CalcResult;
			} else {
				throw exROTATE_ERROR_TOO_MUCH_DIMENSIONS;
			}
		}
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
	public Boolean IsUnitVector(Vector v) {
		return null;
	}

	@Override
	public boolean compareIdentical(Vector v1, Vector v2) {
		return false;
	}

	@Override
	public boolean compareIdentical(Vector v1, Vector v2, double tolerance) {
		return false;
	}

	@Override
	public double getDistance(Vector v1, Vector v2) throws Exception {
		return 0;
	}

	@Override
	public double getLength(Vector vector) {
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
	public double SumSquaredComponents(Vector vector) {
		return 0;
	}

	@Override
	public double SumComponents(Vector vector) {
		return 0;
	}

	@Override
	public String toString(String form, String separator) {
		return null;
	}

	@Override
	public Vector increment(Vector v) throws Exception {
		return null;
	}

	@Override
	public Vector getUnitVector(Vector vector) {
		return null;
	}

	@Override
	public Vector3 multiply(Vector v, double d) {
		return null;
	}

	@Override
	public Vector Normalize(Vector vector) throws Exception {
		return null;
	}

	@Override
	public Vector subtract(Vector v1, Vector v2) throws Exception {
		return null;
	}

	@Override
	public Vector decrement(Vector v) throws Exception {
		return null;
	}

	@Override
	public Vector divide(Vector v, double d) {
		return null;
	}

	@Override
	public Vector add(Vector v1, Vector v2) throws Exception {
		return null;
	}

	@Override
	public Vector multiply(double d, Vector v) {
		return null;
	}

	@Override
	public Vector negate(Vector v) throws Exception {
		return null;
	}

	@Override
	public Vector3 stringToVector(String String, String form, String separator) {
		return null;
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
	public void setComponents(double x, double y, double z) {

	}

	public Vector3[] rotateAroundZ(Vector3[] vs, double degrees) throws Exception {
		return new Vector3[0];
	}

	public void rotateAroundVector(Vector3 AxisVector, double degrees) throws Exception {
		double[] values = rotateAroundVector(this, AxisVector, degrees).getComponents();
		x_Value = values[0];
		y_Value = values[1];
		z_Value = values[2];
	}

	/**
	 * rotates a Vector around an axis described by another vector with a number of degrees
	 *
	 * @param VectorToRotate Vector to rotate
	 * @param AxisVector     vector describing the axis to rotate around
	 * @param degrees        amount of degrees that shall be rotated
	 * @return rotated vector
	 * @throws Exception
	 */
	public Vector3 rotateAroundVector(Vector3 VectorToRotate,
									  Vector3 AxisVector, double degrees) throws Exception {
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

	public void rotateAroundY(double degrees_around_y) throws Exception {

	}

	public void rotateAroundX(double degrees_around_x) throws Exception {

	}

	public void rotateAroundZ(double degrees) throws Exception {

	}

	/**
	 * rotate all 3D vectors around the x axis
	 *
	 * @param vs      vectors to rotate
	 * @param degrees amount of degrees that shall be rotated
	 * @return rotated vectors
	 * @throws Exception
	 */
	public Vector3[] rotateAroundX(Vector3[] vs, double degrees)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		for (int i = 0; i < vs.length; ) {
			/* rotate each vector separately */
			vs[i] = rotateAroundX(vs[i], degrees);
			i++;
		}
		return vs;
	}

	/**
	 * rotate a 3D vector around the x axis
	 *
	 * @param vector           vector to rotate
	 * @param degrees_around_x amount of degrees that shall be rotated
	 * @return rotated vector
	 * @throws Exception
	 */

	public Vector3 rotateAroundX(Vector3 vector, double degrees_around_x)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better
		/* check whether it is a 3 dimensional vector */
		if (vector.getDimension() == 3) {
			/* create solution vector, to be sure everything is fine */
			double[] resvals, vecvals;
			resvals = CalcResult.getComponents();
			vecvals = vector.getComponents();
			/* x value stays the same */
			resvals[0] = vecvals[0];
			/* y value is calculated with cos*y_value+sin*z_value */
			resvals[1] = Math.cos(degrees_around_x) * vecvals[1]
					- Math.sin(degrees_around_x) * vecvals[2];
			/* y value is calculated with sin*y_value+cos*z_value */
			resvals[2] = Math.cos(degrees_around_x) * vecvals[2]
					+ Math.sin(degrees_around_x) * vecvals[1];
			CalcResult.setComponents(resvals);
			/* return result */
			return CalcResult;
		} else {
			/* if not, BAM */
			throw exROTATE_ERROR_TOO_MUCH_DIMENSIONS;
		}
	}

	/**
	 * rotate all 3D vector around the y axis
	 *
	 * @param vs
	 *            vectors to rotate
	 * @param degrees
	 *            amount of degrees that shall be rotated
	 * @return rotated vectors
	 * @throws Exception
	 */
	public Vector3[] rotateAroundY(Vector3[] vs, double degrees)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		for (int i = 0; i < vs.length; ) {
			/* rotate each vector individually */
			vs[i] = rotateAroundY(vs[i], degrees);
			i++;
		}
		return vs;
	}

	/**
	 * rotate a 3D vector around the y axis
	 *
	 * @param vector           vector to rotate
	 * @param degrees_around_y amount of degrees that shall be rotated
	 * @return rotated vector
	 * @throws Exception
	 */

	public Vector3 rotateAroundY(Vector3 vector, double degrees_around_y)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		/*
		 * check whether it is a 3 dimensional vector further comments see:
		 * rotateAroundX
		 */
		if (vector.getDimension() == 3) {
			double[] resvals, vecvals;
			resvals = CalcResult.getComponents();
			vecvals = vector.getComponents();
			resvals[0] = Math.sin(degrees_around_y) * vecvals[2]
					+ Math.cos(degrees_around_y) * vecvals[0];
			resvals[1] = vecvals[1];
			resvals[2] = Math.cos(degrees_around_y) * vecvals[2]
					- Math.sin(degrees_around_y) * vecvals[0];
			CalcResult.setComponents(resvals);
			return CalcResult;
		} else {
			// if not, BAM
			throw exROTATE_ERROR_TOO_MUCH_DIMENSIONS;
		}
	}

	/**
	 * TODO
	 * rotate all 3D vectors around the z axis
	 *
	 * @param vs
	 *            vectors to rotate
	 * @param degrees
	 *            amount of degrees that shall be rotated
	 * @return rotated vectors
	 * @throws Exception
	 */
	/*public VectorN[] rotateAroundZ(VectorN[] vs, double degrees)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		/* rotate each vector individually
		for (int i = 0; i < vs.length; ) {
			vs[i] = VectorN.rotateAroundZ(vs[i], degrees);
			i++;
		}
		return vs;
	}*/


}

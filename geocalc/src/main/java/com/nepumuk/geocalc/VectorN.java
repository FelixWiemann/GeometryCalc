package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 16.04.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 *
 */
@SuppressWarnings("unused")
public class VectorN {

	private double[] values; // 0: x, 1:y, 2:z
	private int dimension; // dimension of the vector
	private static VectorN CalcResultDim3 = new VectorN(3);

	private final static double EqualityTolerance = Double.MIN_VALUE;
	/**
	 * Vector of length 1 describing the x-axis
	 */

	public final static VectorN xAxis = new VectorN(1, 0, 0);
	/**
	 * Vector of length 1 describing the y-axis
	 */
	public final static VectorN yAxis = new VectorN(0, 1, 0);
	/**
	 * Vector of length 1 describing the z-axis
	 */
	public final static VectorN zAxis = new VectorN(0, 0, 1);
	/**
	 * Vector describing the origin (0,0,0)
	 */
	public final static VectorN origin = new VectorN(0, 0, 0);
	protected final static String DIMENSION_ERROR = "The dimensions of both vectors are not the same, but need to be";
	protected final static String ROTATE_ERROR_TOO_MUCH_DIMENSIONS = "can't rotate because to much dimensions";
	protected final static String IllegalArgumentExceptionNumValZero = "you cannot divide by zero";
	protected final static Exception exWithOutMessage = new Exception();
	protected final static Exception exROTATE_ERROR_TOO_MUCH_DIMENSIONS = new Exception(
			ROTATE_ERROR_TOO_MUCH_DIMENSIONS);
	protected final static IllegalArgumentException exDIMENSION_ERROR = new IllegalArgumentException(
			DIMENSION_ERROR);
	protected final static IllegalArgumentException exNumValZero = new IllegalArgumentException(
			IllegalArgumentExceptionNumValZero);

	/**
	 * constructor of a vector with two dimensions x,y
	 *
	 * @param x
	 *            x-value
	 * @param y
	 *            y-value
	 */
	public VectorN(double x, double y) {
		values = new double[2];
		values[0] = x;
		values[1] = y;
		this.dimension = 2;

	}

	/**
	 * constructor of a vector with three dimensions x,y,z
	 *
	 * @param x
	 *            x-value
	 * @param y
	 *            y-value
	 * @param z
	 *            z-value
	 */
	public VectorN(double x, double y, double z) {
		this.values = new double[3];
		values[0] = x;
		values[1] = y;
		values[2] = z;
		this.dimension = 3;

	}

	/**
	 * constructor of a vector with the values of the array and array.length dimensions
	 *
	 * @param values
	 *            array of values
	 */
	public VectorN(double[] values) {
		this.values = values;
		this.dimension = values.length;

	}

	/**
	 * constructor of a vector based on a dimension, values are empty
	 *
	 * @param dim
	 *            dimension the new vector should have
	 *
	 */
	public VectorN(int dim) {
		values = new double[dim];
		dimension = dim;

	}

	/**
	 * constructor of a vector based on another one
	 *
	 * @param v
	 *            vector the new vector should be based on
	 */
	public VectorN(VectorN v) {
		values = v.getComponents();
		this.dimension = v.getDimension();

	}

	/**
	 * Add two Vectors
	 *
	 * @param v1
	 *            Vector 1
	 * @param v2
	 *            Vector 2
	 * @return Sum of vectors
	 * @throws Exception
	 */
	public static VectorN add(VectorN v1, VectorN v2) throws Exception {

		int dim1 = v1.getDimension();
		// both vectors need to have the same dimension!
		if (dim1 == v2.getDimension()) {
			double[] valsv1, valsv2;
			valsv1 = v1.getComponents();
			valsv2 = v2.getComponents();
			// for my use is dim = 3 most often used, i don't have to go through that for-loop
			if (dim1 == 3) {
				// add components with same index
				CalcResultDim3.setComponents(valsv1[0] + valsv2[0], valsv1[1]
						+ valsv2[1], valsv1[2] + valsv2[2]);
				return CalcResultDim3;
			} else {
				// for loop for every other use
				VectorN result = new VectorN(dim1);
				double[]resvals;
				resvals = result.getComponents();
				for (int i = 0; i < dim1; i++) {
					resvals[i] = valsv1[i] + valsv2[i];
				}
				return result;
			}
		} else {
			// otherwise you cannot add them
			throw exDIMENSION_ERROR;
		}
	}

	/**
	 * determine whether two vectors are able to bee seen as identical (tolerance = {@link Double}
	 *
	 * @param v1
	 *            Vector No 1 to compare
	 * @param v2
	 *            Vector No 2 to compare
	 * @return true, if identical, false if not
	 */
	public static boolean compareIdentical(VectorN v1, VectorN v2) {
		return compareIdentical(v1, v2, EqualityTolerance);
	}

	/**
	 * determine whether two vectors are able to bee seen as identical with the
	 * given tolerance
	 *
	 * @param v1
	 *            Vector No 1 to compare
	 * @param v2
	 *            Vector No 2 to compare
	 * @param tolerance
	 *            tolerance used in comparison
	 * @return true, if identical, false if not
	 */
	public static boolean compareIdentical(VectorN v1, VectorN v2,
										   double tolerance) {
		int dim1 = v1.getDimension();
		// if not the same dimension -> can't be identical
		if (dim1 == v2.getDimension()) {
			double[] v1comps = v1.getComponents();
			double[] v2comps = v2.getComponents();
			for (int x = 0; x < dim1; x++) {
				if (Math.abs(v1comps[x] - v2comps[x]) >= tolerance) {
					// if the difference between the value is greather than the tolerance, return false -> not the same
					return false;
				}
			}
			// same, if all differences are smaller than the tolerance
			return true;
		} else {
			return false;
		}

	}

	/**
	 * decrements the vector with length 1
	 * (subtract from the vector the normalized vector)
	 *
	 * @param v
	 *            Vector to be decremented
	 * @return returns Vector with length -1
	 * @throws Exception
	 */
	public static VectorN decrement(VectorN v) throws Exception {
		return VectorN.subtract(v, VectorN.Normalize(v));
	}

	/**
	 * divide a Vector with a double
	 *
	 * @param v
	 *            Vector to be divided
	 * @param d
	 *            used to divide the Vector
	 * @return Vector divided by double
	 */
	public static VectorN divide(VectorN v, double d) {
		if (d == 0) {
			/* can't divide by zero */
			throw exNumValZero;
		}
		int dim = v.getDimension();
		if (dim == 3) {
			double[] dims = v.getComponents();
			CalcResultDim3.setComponents(dims[0] / d, dims[1] / d, dims[2] / d);
			return CalcResultDim3;
		} else {
			/*
			 * create dividedVector, the result vector, to be sure it is empty
			 * need to make sure it has enough dimensions
			 */
			VectorN dividedVector = new VectorN(dim);
			double[] ds = v.getComponents();
			/*
			 * now divide each dimension by the given double and set the result
			 * in dividedVector
			 */
			for (int i = v.getDimension() - 1; i == 0; i--) {
				ds[i] /= d;
			}
			dividedVector.setComponents(ds);
			return dividedVector;
		}
	}

	/**
	 * returns the distance between two ends of two Vectors
	 *
	 * @param v1
	 *            Vector 1
	 * @param v2
	 *            Vector 2
	 * @return distance between ends of the given vectors
	 * @throws Exception
	 */
	public static double getDistance(VectorN v1, VectorN v2) throws Exception {
		v1 = VectorN.subtract(v1, v2);
		return v1.getLength();
	}

	/**
	 * returns the length of a vector
	 *
	 * @param vector
	 *            Vector to be measured
	 * @return length of the given vector
	 */
	public static double getLength(VectorN vector) {
		return Math.sqrt(SumSquaredComponents(vector));
	}

	/**
	 * returns the unit vector of an vector, with length 1
	 *
	 * @param vector
	 *            Vector to unify
	 * @return unit vector of the given vector
	 */
	public static VectorN getUnitVector(VectorN vector) {
		// TODO optimize
		double[] vecvals;
		double length = vector.getLength();
		vecvals = vector.getComponents();
		int vdim = vector.dimension;
		if (vdim==3)
		{
			CalcResultDim3.setComponents(vecvals[0]/length,vecvals[1]/length,vecvals[2]/length);
			return CalcResultDim3;
		}
		else{
			for (int x = 0; x < vdim; x++) {
				vecvals[x] = vecvals[x] / length;

			}
			vector.setComponents(vecvals);
			return vector;
		}
	}

	/**
	 * increments the vector with length 1
	 *
	 * @param v
	 *            vector to increment
	 * @return incremented vector
	 * @throws Exception
	 */
	public static VectorN increment(VectorN v) throws Exception {

		return add(v, VectorN.Normalize(v));

	}

	/**
	 * determines whether the vector is unit vector or not
	 *
	 * @param v
	 *            vector to check
	 * @return bool: true -> unit vector; false -> no unit vector
	 */
	public static Boolean IsUnitVector(VectorN v) {
		return (Math.abs(v.getLength() - 1) <= EqualityTolerance);
	}

	/**
	 * multiply a vector with a double
	 *
	 * @param d
	 *            double used to multiply with vector
	 * @param v
	 *            Vector to be multiplied
	 * @return vector multiplied with double
	 */
	public static VectorN multiply(double d, VectorN v) {
		/* multiply by using other method. */
		return multiply(v, d);
	}

	/**
	 * multiply a vector with a double
	 *
	 * @param v
	 *            Vector to be multiplied
	 * @param d
	 *            double used to multiply with vector
	 * @return vector multiplied with double
	 */
	public static VectorN multiply(VectorN v, double d) {
		int dim = v.getDimension();
		if (dim == 3) {
			double[] dims = v.getComponents();
			CalcResultDim3.setComponents(dims[0] * d, dims[1] * d, dims[2] * d);
			return CalcResultDim3;

		}
		/*
		 * create multiplicatedVector, the result vector, to be sure it is empty
		 * need to make sure it has enough dimensions
		 */
		VectorN multiplicatedVector = new VectorN(dim);
		/*
		 * now multiply each dimension by the given double and set the result in
		 * dividedVector
		 */

		double[] ds = v.getComponents();

		for (int i = v.getDimension() - 1; i == 0; i--) {
			ds[i] *= d;
		}
		multiplicatedVector.setComponents(ds);
		return multiplicatedVector;

	}

	/**
	 * negate one Vector
	 *
	 * @param v
	 *            Vector to be negated
	 * @return negated vector
	 * @throws Exception
	 */
	public static VectorN negate(VectorN v) throws Exception {
		return VectorN.subtract(VectorN.subtract(v, v), v);
	}

	/**
	 * returns the unit vector of an vector (length=1)
	 * @deprecated
	 * @param vector
	 *            Vector to be unified
	 * @return unified vector
	 * @throws Exception
	 *
	 */
	public static VectorN Normalize(VectorN vector) throws Exception {
		double length = vector.getLength();
		if (length == 0) {
			throw exWithOutMessage;
		} else {

			int vecdim = vector.getDimension();
			double[] vars = vector.getComponents();
			for (int i = 0; i < vecdim; i++) {
				vars[i] /= length;
			}
			return new VectorN(vars);
		}
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
	public static VectorN rotateAroundVector(VectorN VectorToRotate,
											 VectorN AxisVector, double degrees) throws Exception {
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
	public static VectorN rotateAroundX(VectorN vector, double degrees_around_x)
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
	 * rotate all 3D vectors around the x axis
	 *
	 * @param vs
	 *            vectors to rotate
	 * @param degrees
	 *            amount of degrees that shall be rotated
	 * @return rotated vectors
	 * @throws Exception
	 */
	public static VectorN[] rotateAroundX(VectorN[] vs, double degrees)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		for (int i = 0; i < vs.length;) {
			/* rotate each vector separately */
			vs[i] = VectorN.rotateAroundX(vs[i], degrees);
			i++;
		}
		return vs;
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
	public static VectorN rotateAroundY(VectorN vector, double degrees_around_y)
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
	public static VectorN[] rotateAroundY(VectorN[] vs, double degrees)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		for (int i = 0; i < vs.length;) {
			/* rotate each vector individually */
			vs[i] = VectorN.rotateAroundY(vs[i], degrees);
			i++;
		}
		return vs;
	}

	// is greater TODO

	// is smaller TODO

	// is greater or equals TODO

	// is smaller or equals TODO

	/**
	 * rotate a 3D vector around the z axis
	 *
	 * @param vector
	 *            vector to rotate
	 * @param degrees
	 *            amount of degrees that shall be rotated
	 * @return rotated vector
	 * @throws Exception
	 */
	public static VectorN rotateAroundZ(VectorN vector, double degrees)
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
			}

			else {
				throw exROTATE_ERROR_TOO_MUCH_DIMENSIONS;
			}
		}
	}

	/**
	 * rotate all 3D vectors around the z axis
	 *
	 * @param vs
	 *            vectors to rotate
	 * @param degrees
	 *            amount of degrees that shall be rotated
	 * @return rotated vectors
	 * @throws Exception
	 */
	public static VectorN[] rotateAroundZ(VectorN[] vs, double degrees)
			throws Exception {
		//TODO optimize, evaluate whether rotation matrix is better

		/* rotate each vector individually */
		for (int i = 0; i < vs.length;) {
			vs[i] = VectorN.rotateAroundZ(vs[i], degrees);
			i++;
		}
		return vs;
	}

	// TODO write this
	/**
	 * @deprecated
	 * @param String
	 * @param form
	 * @param separator
	 * @return
	 */
	public static VectorN stringToVector(String String, String form,
										 String separator) {

		String form_back;
		switch (form) {
			case "(":
				form_back = ")";
				break;
			case "[":
				form_back = "]";
				break;
			case "{":
				form_back = "}";
				break;
			case "/":
				form_back = "\\";
				break;
			case "\\":
				form_back = "/";
				break;
			case "\"":
				form_back = "\"";
				break;
			default:
				form = "(";
				form_back = ")";
				break;
		}
		if (separator == null) {
			separator = ";";
		}
		String x_val = String.substring(String.indexOf(form));
		String y_val = String.substring(String.indexOf(form));
		String z_val = String.substring(String.indexOf(form));

		return new VectorN(Double.parseDouble(x_val), Double.parseDouble(y_val),
				Double.parseDouble(z_val));
	}

	/**
	 * subtract two Vectors
	 *
	 * @param v1
	 *            minuend
	 * @param v2
	 *            subtrahend
	 * @return difference
	 * @throws Exception
	 */
	public static VectorN subtract(VectorN v1, VectorN v2) throws Exception {
		// vectors need to have the same dimension
		//TODO optimize, evaluate whether rotation matrix is better

		if (v1.getDimension() == v2.getDimension()) {
			VectorN result = new VectorN(v1.getDimension());

			for (int i = 0; i < v1.getDimension(); i++) {
				result.getComponents()[i] = v1.getComponents()[i]
						- v2.getComponents()[i];
			}

			return result;
		} else {
			throw exDIMENSION_ERROR;
		}
	}

	// cross-product TODO

	// skalar-product TODO

	// unitVector (is(static bool[vector]); is(), normalize(static
	// vector[vector];normalize()

	/**
	 * sum up all components in the vector
	 *
	 * @param vector
	 *            vector to be summed up
	 * @return sum of all components in the vector
	 */
	public static double SumComponents(VectorN vector) {
		double sum = 0;
		double[] copms = vector.getComponents();
		int dim = vector.getDimension();
		for (int x = 0; x < dim; x++) {
			sum += copms[x];
		}
		return sum;

	}

	/**
	 * squares all components of the vector and sums them up
	 *
	 * @param vector
	 *            vector to be summed up
	 * @return squared sum of all components
	 */
	public static double SumSquaredComponents(VectorN vector) {
		double squared_sum = 0;
		double[] copms = vector.getComponents();
		int dim = vector.getDimension();
		for (int x = 0; x < dim; x++) {
			squared_sum += copms[x] * copms[x];
		}
		return squared_sum;
	}

	@Override
	public boolean equals(Object o) {
		// Return true if the objects are identical.
		// (This is just an optimization, not required for correctness.)
		if (this == o) {
			return true;
		}

		// Return false if the other object has the wrong type.
		// This type may be an interface depending on the interface's specification.
		if (!(o instanceof VectorN)) {
			return false;
		}

		// Cast to the appropriate type.
		// This will succeed because of the instanceof, and lets us access private fields.
		//  VectorN lhs = (VectorN) o;
		return false;

		// Check each field. Primitive fields, reference fields, and nullable reference
		// fields are all treated differently.
		//TODO
	}
	/**
	 * @return get all values of the vector
	 */
	public double[] getComponents() {
		return values;
	}

	// TODO who is bigger/smaller

	/**
	 * @return get the dimension of a vector
	 */
	public int getDimension() {
		return this.dimension;
	}

	/**
	 * returns the length of the current vector
	 *
	 * @return length of the current vector
	 */
	public double getLength() {
		return VectorN.getLength(this);

	}

	/**
	 * returns an unit vector of the current vector
	 *
	 * @return unit Vector of the current vector
	 */
	public VectorN getUnitVector() {
		return VectorN.getUnitVector(this);
	}

	/**
	 * get the value of the vector at the given index
	 *
	 * @param index
	 *            index of the value to get
	 * @return value at the given index
	 */
	public double getValue(int index) {
		return values[index];
	}

	/**
	 * @deprecated
	 * length of the calling vector will be made 1
	 *
	 * @throws Exception
	 *
	 */
	public void Normalize() throws Exception {
		this.values = VectorN.Normalize(this).values;
	}

	/**
	 * rotates the vector around the axis vector
	 *
	 * @param AxisVector
	 *            Vector to rotate around
	 * @param degrees
	 *            amount of degrees that shall be rotated
	 * @throws Exception
	 */
	public void rotateAroundVector(VectorN AxisVector, double degrees)
			throws Exception {
		this.values = rotateAroundVector(this, AxisVector, degrees).values;
	}

	/**
	 * rotate the 3D vector around the x axis
	 *
	 * @param degrees_around_x
	 *            amount of degrees that shall be rotated
	 * @throws Exception
	 */
	public void rotateAroundX(double degrees_around_x) throws Exception {
		this.values = VectorN.rotateAroundX(this, degrees_around_x).values;
	}

	/**
	 * rotate the 3D vector around the y axis
	 *
	 * @param degrees_around_y
	 *            amount of degrees that shall be rotated
	 * @throws Exception
	 */
	public void rotateAroundY(double degrees_around_y) throws Exception {
		this.values = VectorN.rotateAroundY(this, degrees_around_y).values;
	}

	/**
	 * rotate the 3D vector around the z axis
	 *
	 * @param degrees
	 *            amount of degrees that shall be rotated
	 * @throws Exception
	 */
	public void rotateAroundZ(double degrees) throws Exception {
		this.values = VectorN.rotateAroundZ(this, degrees).values;
	}

	/**
	 * sets new values of the current vector
	 *
	 * @param x
	 *            x-value
	 * @param y
	 *            y-value
	 * @param z
	 *            z-value
	 */
	public void setComponents(double x, double y, double z) {
		this.values[0] = x;
		this.values[1] = y;
		this.values[2] = z;

	}

	/**
	 * @param value
	 *            set all values of the vector
	 */
	public void setComponents(double[] value) {
		values = value;
	}

	/**
	 * @param value
	 *            set the dimension of a vector
	 */
	public void setDimension(int value) {
		this.dimension = value;
	}

	/**
	 * TODO
	 * changes the length of the vector without changing its direction
	 *
	 * @param value
	 *            length the vector should have
	 * @throws Exception
	 */
	public void setLength(double value) throws Exception {
		this.values = new VectorN(VectorN.multiply(VectorN.Normalize(this), value)).values;
	}

	/**
	 * set the value at a given index
	 *
	 * @param index
	 *            index of the value to change
	 * @param newValue
	 *            new value
	 */
	public void setValue(int index, double newValue) {
		values[index] = newValue;
	}

	/**
	 * sum up all components in the vector
	 *
	 * @return sums of all components of the vector
	 */
	public double SumComponents() {
		return VectorN.SumComponents(this);
	}

	/**
	 * squares all components of the vector and sums them up
	 *
	 * @return squared sum of all components
	 */
	public double SumSquaredComponents() {
		return VectorN.SumSquaredComponents(this);
	}

	@Override
	public String toString() {
		return toString(null, null);
	}

	/**
	 * returns a string-value of the vector
	 *
	 * @param form
	 *            opening value, e.g. "(", "{",...
	 * @param separator
	 *            separator value, e.g. ";"
	 * @return string describing the vector
	 */
	public String toString(String form, String separator) {
		String form_back;
		switch (form) {
			case "(":
				form_back = ")";
				break;
			case "[":
				form_back = "]";
				break;
			case "{":
				form_back = "}";
				break;
			case "/":
				form_back = "\\";
				break;
			case "\\":
				form_back = "/";
				break;
			case "\"":
				form_back = "\"";
				break;
			default:
				form = "(";
				form_back = ")";
				break;
		}

		if (separator == null) {
			separator = ";";
		}

		String tostring = form;
		for (int i = 0; i < this.getDimension(); i++) {
			tostring += Double.toString(this.getComponents()[i]) + separator;
		}

		tostring = tostring
				.substring(0, tostring.length() - separator.length());
		tostring += form_back;
		return tostring;
	}


}


package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 16.04.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 * This class is capable of representing a vector with N dimensions.
 *
 */
@SuppressWarnings("unused")
public class VectorN implements Vector {


	private static VectorN CalcResultDim3 = new VectorN(3);
	private double[] values; // 0: x, 1:y, 2:z
	private int dimension; // dimension of the vector

	/**
	 * constructor of a vector with the values of the array and array.length dimensions
	 * use Vector2 for a two dimensional vector instead.
	 * use Vector3 for a three dimensional vector instead.
	 * @param values
	 *            array of values
	 */
	public VectorN(double[] values) {
		this.values = values;
		this.dimension = values.length;

	}

	/**
	 * constructor of a vector based on a dimension, values are empty
	 * use Vector2 for a two dimensional vector instead.
	 * use Vector3 for a three dimensional vector instead.
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
	public VectorN(Vector v) {
		values = v.getComponents();
		this.dimension = v.getDimension();

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
		//  Vector lhs = (Vector) o;
		return false;

		// Check each field. Primitive fields, reference fields, and nullable reference
		// fields are all treated differently.
		//TODO
	}

	@Override
	public String toString() {
		return toString(null, null);
	}

	/**
	 * @return get all values of the vector
	 */
	@Override
	public double[] getComponents() {
		return values;
	}

	/**
	 * @param value set all values of the vector
	 */
	@Override
	public void setComponents(double[] value) {
		values = value;
	}

	/**
	 * @return get the dimension of a vector
	 */
	@Override
	public int getDimension() {
		return this.dimension;
	}

	/**
	 * @param value set the dimension of a vector
	 */
	@Override
	public void setDimension(int value) {
		this.dimension = value;
	}

	/**
	 * returns the length of the current vector
	 *
	 * @return length of the current vector
	 */
	@Override
	public double getLength() {
		return getLength(this);

	}

	/**
	 * TODO changes the length of the vector without changing its direction
	 *
	 * @param value length the vector should have
	 * @throws Exception
	 */
	@Override
	public void setLength(double value) throws Exception {
		this.values = new VectorN(multiply(Normalize(this), value)).values;
	}

	/**
	 * returns an unit vector of the current vector
	 *
	 * @return unit Vector of the current vector
	 */
	@Override
	public Vector getUnitVector() {
		return getUnitVector(this);
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

	/**
	 * get the value of the vector at the given index
	 *
	 * @param index index of the value to get
	 * @return value at the given index
	 */
	@Override
	public double getValue(int index) {
		return values[index];
	}

	/**
	 * sum up all components in the vector
	 *
	 * @return sums of all components of the vector
	 */
	@Override
	public double SumComponents() {
		return SumComponents(this);
	}

	/**
	 * sum up all components in the vector
	 *
	 * @param vector
	 *            vector to be summed up
	 * @return sum of all components in the vector
	 */
	public double SumComponents(VectorN vector) {
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
	 * @return squared sum of all components
	 */
	@Override
	public double SumSquaredComponents() {
		return SumSquaredComponents(this);
	}

	@Override
	public double SumSquaredComponents(Vector vector) {
		return 0;
	}

	@Override
	public double SumComponents(Vector vector) {
		return 0;
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
	@Override
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

	@Override
	public Vector increment(Vector v) throws Exception {
		return null;
	}

	/**
	 * returns the unit vector of an vector, with length 1
	 *
	 * @param vector
	 *            Vector to unify
	 * @return unit vector of the given vector
	 */
	@Override
	public  Vector getUnitVector(Vector vector) {
		// TODO optimize
		double[] vecvals;
		double length = vector.getLength();
		vecvals = vector.getComponents();
		int vdim = dimension;
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

	@Override
	public Vector multiply(Vector v, double d) {
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

	// is greater TODO

	// is smaller TODO

	// is greater or equals TODO

	// is smaller or equals TODO

	@Override
	public Vector add(Vector v1, Vector v2) throws Exception {
		return null;
	}

	@Override
	public Vector multiply(double d, Vector v) {
		return null;
	}

	// TODO write this

	@Override
	public Vector negate(Vector v) throws Exception {
		return null;
	}

	@Override
	public Vector stringToVector(String String, String form, String separator) {
		return null;
	}

	/**
	 * set the value at a given index
	 *
	 * @param index
	 *            index of the value to change
	 * @param newValue
	 *            new value
	 */
	@Override
	public void setValue(int index, double newValue) {
		values[index] = newValue;
	}

	/**
	 * @throws Exception
	 * @deprecated length of the calling vector will be made 1
	 */
	@Override
	public void Normalize() throws Exception {
		this.values = Normalize(this).values;
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
	@Override
	public void setComponents(double x, double y, double z) {
		this.values[0] = x;
		this.values[1] = y;
		this.values[2] = z;

	}


	// cross-product TODO

	// skalar-product TODO

	// unitVector (is(static bool[vector]); is(), normalize(static
	// vector[vector];normalize()

	/**
	 * multiply a vector with a double
	 *
	 * @param v
	 *            Vector to be multiplied
	 * @param d
	 *            double used to multiply with vector
	 * @return vector multiplied with double
	 */
	public VectorN multiply(VectorN v, double d) {
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
	 * returns the unit vector of an vector (length=1)
	 * @deprecated
	 * @param vector
	 *            Vector to be unified
	 * @return unified vector
	 * @throws Exception
	 *
	 */
	public VectorN Normalize(VectorN vector) throws Exception {
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
	 * returns the length of a vector
	 *
	 * @param vector
	 *            Vector to be measured
	 * @return length of the given vector
	 */
	public double getLength(VectorN vector) {
		return Math.sqrt(SumSquaredComponents(vector));
	}

	/**
	 * squares all components of the vector and sums them up
	 *
	 * @param vector
	 *            vector to be summed up
	 * @return squared sum of all components
	 */
	public double SumSquaredComponents(VectorN vector) {
		double squared_sum = 0;
		double[] copms = vector.getComponents();
		int dim = vector.getDimension();
		for (int x = 0; x < dim; x++) {
			squared_sum += copms[x] * copms[x];
		}
		return squared_sum;
	}

	// TODO who is bigger/smaller

	/**
	 * rotate a 3D vector around the z axis
	 *
	 * @param vector  vector to rotate
	 * @param degrees amount of degrees that shall be rotated
	 * @return rotated vector
	 * @throws Exception
	 */
	public VectorN rotateAroundZ(VectorN vector, double degrees)
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

	/**
	 * determine whether two vectors are able to bee seen as identical (tolerance = {@link Double}
	 *
	 * @param v1 Vector No 1 to compare
	 * @param v2 Vector No 2 to compare
	 * @return true, if identical, false if not
	 */
	public boolean compareIdentical(VectorN v1, VectorN v2) {
		return compareIdentical(v1, v2, EqualityTolerance);
	}

	/**
	 * determine whether two vectors are able to bee seen as identical with the given tolerance
	 *
	 * @param v1        Vector No 1 to compare
	 * @param v2        Vector No 2 to compare
	 * @param tolerance tolerance used in comparison
	 * @return true, if identical, false if not
	 */
	public boolean compareIdentical(VectorN v1, VectorN v2,
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
	 * decrements the vector with length 1 (subtract from the vector the normalized vector)
	 *
	 * @param v Vector to be decremented
	 * @return returns Vector with length -1
	 * @throws Exception
	 */
	public VectorN decrement(VectorN v) throws Exception {
		return subtract(v, Normalize(v));
	}

	/**
	 * subtract two Vectors
	 *
	 * @param v1 minuend
	 * @param v2 subtrahend
	 * @return difference
	 * @throws Exception
	 */
	public VectorN subtract(VectorN v1, VectorN v2) throws Exception {
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

	/**
	 * divide a Vector with a double
	 *
	 * @param v Vector to be divided
	 * @param d used to divide the Vector
	 * @return Vector divided by double
	 */
	public VectorN divide(VectorN v, double d) {
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
	public double getDistance(VectorN v1, VectorN v2) throws Exception {
		v1 = subtract(v1, v2);
		return v1.getLength();
	}

	/**
	 * increments the vector with length 1
	 *
	 * @param v
	 *            vector to increment
	 * @return incremented vector
	 * @throws Exception
	 */
	public VectorN increment(VectorN v) throws Exception {

		return add(v, Normalize(v));

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
	public VectorN add(VectorN v1, VectorN v2) throws Exception {

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
				double[] resvals;
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
	 * determines whether the vector is unit vector or not
	 *
	 * @param v
	 *            vector to check
	 * @return bool: true -> unit vector; false -> no unit vector
	 */
	public Boolean IsUnitVector(VectorN v) {
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
	public VectorN multiply(double d, VectorN v) {
		/* multiply by using other method. */
		return multiply(v, d);
	}

	/**
	 * negate one Vector
	 *
	 * @param v Vector to be negated
	 * @return negated vector
	 * @throws Exception
	 */
	public VectorN negate(VectorN v) throws Exception {
		return subtract(subtract(v, v), v);
	}


/*
	/** TODO write
	 * @deprecated
	 * @param String
	 * @param form
	 * @param separator
	 * @return
	 *
	public VectorN stringToVector(String String, String form,
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
	}*/


}


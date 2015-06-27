package com.nepumuk.geocalc;

/**
 * Created by Felix "nepumuk" Wiemann on 27.06.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 */
public class DimensionalError extends Error {
	private String ErrorMessage = "CANT_CHANGE_DIMENSION_USE_OTHER_VECTOR_INSTEAD";

	public DimensionalError() {

	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public Throwable getCause() {
		return super.getCause();
	}
}

package com.nepumuk.vectordraw;


import android.graphics.Color;
import android.graphics.Paint;

import com.nepumuk.geocalc.VectorN;


/**
 * Created by Felix "nepumuk" Wiemann on 16.04.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 */
public class cVector extends VectorN {
	// nobody needs colored shit if he doesn't want to draw them
	// therefore I introduce a class that extends the VectorN class and is able to hold the colors.
	public static int ColorStandard = Color.BLACK;
	public static Paint.Style PaintStyleStandard = Paint.Style.STROKE;
	public static Paint.Join PaintJoinStandard = Paint.Join.ROUND;
	public static Paint.Cap PaintCapStandard = Paint.Cap.ROUND;
	public static Paint.Align PaintAlignStandard = Paint.Align.CENTER;
	public static int PaintStrokeWidthStandard = 2;
	public static Boolean PaintAntiAliasStandard = true;
	public static Boolean PaintDitherStandard = true;
	public static Paint PaintStandard = new Paint() {
		{
			this.setAntiAlias(cVector.PaintAntiAliasStandard);
			this.setDither(cVector.PaintDitherStandard);
			this.setColor(cVector.ColorStandard);
			this.setStyle(cVector.PaintStyleStandard);
			this.setStrokeJoin(cVector.PaintJoinStandard);
			this.setStrokeCap(cVector.PaintCapStandard);
			this.setStrokeWidth(cVector.PaintStrokeWidthStandard);
		}
	};

	private int vPaintColor;
	private Paint.Style vPaintStyle;
	private Paint.Join vPaintStrokeJoin;
	private Paint.Cap vPaintStrokeCap;
	private Paint.Align vPaintAlign;
	private int vPaintStrokeWidth;
	private Boolean vPaintAntiAlias;
	private Boolean vPaintDither;
	private Paint vPaint;

	@SuppressWarnings("unused")
	public cVector(cVector v) {
		super(v);
		this.vPaint = v.getvPaint();
	}

	@SuppressWarnings("unused")
	public cVector(cVector v, int Color) {
		super(v);
		this.changePaintColor(Color);
	}

	@SuppressWarnings("unused")
	public cVector(double x, double y) {
		super(x, y);
		initPaint();

	}

	/**
	 * constructor of an vector with three dimensions x,y,z
	 *
	 * @param x
	 *            x-value
	 * @param y
	 *            y-value
	 * @param z
	 *            z-value
	 */
	@SuppressWarnings("unused")
	public cVector(double x, double y, double z) {
		super(x, y, z);
		initPaint();

	}

	/**
	 * constructor of an vector with three dimensions x,y,z
	 *
	 * @param x
	 *            x-value
	 * @param y
	 *            y-value
	 * @param z
	 *            z-value
	 */
	/*public cVector(double x, double y, double z) {
		super(x, y, z);
		initPaint();
		this.changePaintColor(Color);

	}*/
/*
	public cVector(double x, double y, int Color) {
		super(x, y);
		initPaint();
		this.changePaintColor(Color);
	}*/

	/**
	 * constructor with an array of values
	 *
	 * @param values
	 *            array of values
	 */
	@SuppressWarnings("unused")
	public cVector(double[] values) {
		super(values);
		initPaint();
	}

	/**
	 * constructor with an array of values
	 *
	 * @param values
	 *            array of values
	 */
	public cVector(double[] values, int Color) {
		super(values);
		initPaint();
		this.changePaintColor(Color);
	}

	/**
	 * constructor of an vector based on a dimension
	 *
	 * @param dim
	 *            dimension the new vector should have
	 */
	@SuppressWarnings("unused")
	public cVector(int dim) {
		super(dim);
		initPaint();

	}


	public cVector(int dim, int Color) {
		super(dim);
		initPaint();
		this.changePaintColor(Color);

	}

	public cVector(VectorN v) {
		super(v);
		initPaint();
	}

	public cVector(VectorN v, int Color) {
		super(v);
		initPaint();
		this.changePaintColor(Color);
	}

	/**
	 * @param Color Color to change to
	 */
	public void changePaintColor(int Color) {

		vPaint.setColor(Color);

	}

	/**
	 * @return the vColor
	 */
	public Paint getvPaint() {
		return vPaint;
	}

	/**
	 * initialize the Paint the vector can be drawn with
	 */
	private void initPaint() {
		vPaint = new Paint();
		vPaint.setAntiAlias(cVector.PaintAntiAliasStandard);
		vPaint.setDither(cVector.PaintDitherStandard);
		vPaint.setColor(cVector.ColorStandard);
		vPaint.setStyle(cVector.PaintStyleStandard);
		vPaint.setStrokeJoin(cVector.PaintJoinStandard);
		vPaint.setStrokeCap(cVector.PaintCapStandard);
		vPaint.setStrokeWidth(cVector.PaintStrokeWidthStandard);
	}

	/**
	 * @param vColor
	 *            the vColor to set
	 */
	@SuppressWarnings("unused")
	public void setvColor(Paint vColor) {
		this.vPaint = vColor;
	}

	/**
	 * @return the vColor
	 */
	public int getvPaintColor() {
		return vPaintColor;
	}

	/**
	 * @param vColor
	 *            the vColor to set
	 */
	@SuppressWarnings("unused")
	public void setvPaintColor(int vColor) {
		this.vPaintColor = vColor;
	}

	/**
	 * @return the vPaintStyle
	 */
	@SuppressWarnings("unused")
	public Paint.Style getvPaintStyle() {
		return vPaintStyle;
	}

	/**
	 * @param vPaintStyle
	 *            the vPaintStyle to set
	 */
	@SuppressWarnings("unused")
	public void setvPaintStyle(Paint.Style vPaintStyle) {
		this.vPaintStyle = vPaintStyle;
	}

	/**
	 * @return the vPaintJoin
	 */
	@SuppressWarnings("unused")
	public Paint.Join getvPaintStrokeJoin() {
		return vPaintStrokeJoin;
	}

	/**
	 * @param vPaintJoin
	 *            the vPaintJoin to set
	 */
	@SuppressWarnings("unused")
	public void setvPaintStrokeJoin(Paint.Join vPaintJoin) {
		this.vPaintStrokeJoin = vPaintJoin;
	}

	/**
	 * @return the vPaintCap
	 */
	@SuppressWarnings("unused")
	public Paint.Cap getvPaintStrokeCap() {
		return vPaintStrokeCap;
	}

	/**
	 * @param vPaintCap the vPaintCap to set
	 */
	@SuppressWarnings("unused")
	public void setvPaintStrokeCap(Paint.Cap vPaintCap) {
		this.vPaintStrokeCap = vPaintCap;
	}

	/**
	 * @return the vPaintAlign
	 */
	@SuppressWarnings("unused")
	public Paint.Align getvPaintAlign() {
		return vPaintAlign;
	}

	/**
	 * @param vPaintAlign
	 *            the vPaintAlign to set
	 */
	@SuppressWarnings("unused")
	public void setvPaintAlign(Paint.Align vPaintAlign) {
		this.vPaintAlign = vPaintAlign;
	}

	/**
	 * @return the vPaintStrokeWidth
	 */
	@SuppressWarnings("unused")
	public int getvPaintStrokeWidth() {
		return vPaintStrokeWidth;
	}

	/**
	 * @param vPaintStrokeWidth
	 *            the vPaintStrokeWidth to set
	 */
	@SuppressWarnings("unused")
	public void setvPaintStrokeWidth(int vPaintStrokeWidth) {
		this.vPaintStrokeWidth = vPaintStrokeWidth;
	}

	/**
	 * @return the vPaintDither
	 */
	@SuppressWarnings("unused")
	public Boolean getvPaintDither() {
		return vPaintDither;
	}

	/**
	 * @param vPaintDither
	 *            the vPaintDither to set
	 */
	@SuppressWarnings("unused")
	public void setvPaintDither(Boolean vPaintDither) {
		this.vPaintDither = vPaintDither;
	}

	/**
	 * @return the vPaintAntiAlias
	 */
	@SuppressWarnings("unused")
	public Boolean getvPaintAntiAlias() {
		return vPaintAntiAlias;
	}

	/**
	 * @param vPaintAntiAlias
	 *            the vPaintAntiAlias to set
	 */
	@SuppressWarnings("unused")
	public void setvPaintAntiAlias(Boolean vPaintAntiAlias) {
		this.vPaintAntiAlias = vPaintAntiAlias;
	}
	@SuppressWarnings("unused")
	public void restoreDefaultPaintValues() {
		initPaint();
	}

	/**
	 * Reset the paint of the vector to the standard Values
	 */
	@SuppressWarnings("unused")
	public void PaintRefresh() {
		vPaint = new Paint();
		vPaint.setAntiAlias(this.vPaintAntiAlias);
		vPaint.setDither(vPaintDither);
		vPaint.setColor(vPaintColor);
		vPaint.setStyle(vPaintStyle);
		vPaint.setStrokeJoin(vPaintStrokeJoin);
		vPaint.setStrokeCap(vPaintStrokeCap);
		vPaint.setStrokeWidth(vPaintStrokeWidth);
		vPaint.setTextAlign(PaintAlignStandard);
	}

}


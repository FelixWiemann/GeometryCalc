package com.nepumuk.vectordraw;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nepumuk.geocalc.VectorN;

import java.util.ArrayList;


/**
 *
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class VectorDrawView extends ImageView implements GestureDetector.OnGestureListener, View.OnTouchListener,
		GestureDetector.OnDoubleTapListener {
	private ScaleGestureDetector mScaleDetector;
	private static float mScaleFactor = 1.f;
	private Context ct;
	private static class ScaleListener
			extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor();
			tm = touchMode.Null;
			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));


			return true;
		}
	}
	private static cVector cVector2 = new cVector(0, 0);
	private static touchMode tm = touchMode.Null;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		mScaleDetector = new ScaleGestureDetector(ct, new ScaleListener());
		mScaleDetector.onTouchEvent(event);

		switch (event.getActionIndex()) {
			case 0:
				HandleUserInteractionSingleFinger(event);
				break;
			case 1:
				//HandleUserInteractionDoubleFinger(event);
				break;
			case 3:
				makeToast("3");
				break;
			case 4:
				makeToast("4");
				break;
			default:
				break;
		}
		reDraw();
		return true;

	}
/*
	private void HandleUserInteractionDoubleFinger(MotionEvent event) {
		multituch=true;
		try{
		float x1 = event.getX(0);
		float x2 = event.getX(1);
		float prevx1 = event.getHistoricalX(0,1);
		float prevx2 = event.getHistoricalX(1,1);
		float prevy1 = event.getHistoricalX(0,1);
		float prevy2 = event.getHistoricalX(1,1);
		float y1 = event.getX(0);
		float y2 = event.getX(1);
		float zoomDif=1;

		if (y1-y2<50)
		{
			zoomDif = (prevx1-x1)-(prevx2-x2);
			zoomFactor = zoomDif/100;
		}}
		catch (Exception e){}
		if (event.getActionIndex()==event.ACTION_UP){multituch=false;}
	}*/

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		return false;
	}


	private void makeToast(String message) {
		Toast.makeText(ct, message, Toast.LENGTH_SHORT).show();
	}

	public void context(Context c) {
		ct = c;
		makeToast("context set");
	}


	private void HandleUserInteractionSingleFinger(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		// define action
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (x > BitMapSize[0] - 50) {
					tm = touchMode.RotateX;
				} else if (y > BitMapSize[1] - 50) {
					tm = touchMode.RotateY;
				} else {
					tm = touchMode.Move;
				}
				mPreviousX = event.getX();
				mPreviousY = event.getY();

				break;

			case MotionEvent.ACTION_MOVE:
				float dx = x - mPreviousX;
				float dy = y - mPreviousY;
				// act in defined action
				switch (tm) {
					case Move:
						bitMapGraphics.eraseColor(backgroundColor);
						try {
							moveCenter((int) dx, (int) dy);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					case Null:
						break;
					case RotateX:
						for (VectorN[] vs : VectorAsQuader) {
							try {
								vs[0].rotateAroundX(rotationFactor * dy);
								vs[1].rotateAroundX(rotationFactor * dy);
								vs[2].rotateAroundX(rotationFactor * dy);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						for (VectorN[] vs : VectorsAsTriangles) {
							try {
								vs[0].rotateAroundX(rotationFactor * dy);
								vs[1].rotateAroundX(rotationFactor * dy);
								vs[2].rotateAroundX(rotationFactor * dy);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						for (VectorN vs : VectorAsLine) {
							try {
								vs.rotateAroundX(rotationFactor * dy);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						for (VectorN vs : VectorAsPoint) {
							try {
								vs.rotateAroundX(rotationFactor * dy);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						break;
					case RotateY:
						for (VectorN[] vs : VectorAsQuader) {
							try {
								vs[0].rotateAroundY(rotationFactor * dx);
								vs[1].rotateAroundY(rotationFactor * dx);
								vs[2].rotateAroundY(rotationFactor * dx);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						for (VectorN[] vs : VectorsAsTriangles) {
							try {
								vs[0].rotateAroundY(rotationFactor * dx);
								vs[1].rotateAroundY(rotationFactor * dx);
								vs[2].rotateAroundY(rotationFactor * dx);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						for (VectorN vs : VectorAsLine) {
							try {
								vs.rotateAroundY(rotationFactor * dx);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						for (VectorN vs : VectorAsPoint) {
							try {
								vs.rotateAroundY(rotationFactor * dx);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						break;
					case Zoom:
						break;
					default:
						break;

				}
				break;
			case MotionEvent.ACTION_UP:
				// reset action
				tm = touchMode.Null;
				break;

		}

		mPreviousX = x;
		mPreviousY = y;
		try {
			ClearDrawing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reDraw();
	}

	public VectorDrawView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	@SuppressWarnings("all")
	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		final TypedArray a = getContext().obtainStyledAttributes(
				attrs, R.styleable.VectorDrawView, defStyle, 0);

		/*mExampleString = a.getString(
				R.styleable.VectorDrawView_exampleString);
		mExampleColor = a.getColor(
				R.styleable.VectorDrawView_exampleColor,
				mExampleColor);
		// Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
		// values that should fall on pixel boundaries.
		mExampleDimension = a.getDimension(
				R.styleable.VectorDrawView_exampleDimension,
				mExampleDimension);

		if (a.hasValue(R.styleable.VectorDrawView_exampleDrawable)) {
			mExampleDrawable = a.getDrawable(
					R.styleable.VectorDrawView_exampleDrawable);
			mExampleDrawable.setCallback(this);
		}

		a.recycle();

		// Set up a default TextPaint object
		mTextPaint = new TextPaint();
		mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextAlign(Paint.Align.LEFT);

		// Update TextPaint and text measurements from attributes
		invalidateTextPaintAndMeasurements();
	}

	private void invalidateTextPaintAndMeasurements() {
		mTextPaint.setTextSize(mExampleDimension);
		mTextPaint.setColor(mExampleColor);
		mTextWidth = mTextPaint.measureText(mExampleString);

		Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
		mTextHeight = fontMetrics.bottom;*/
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}


	// TODO onSizeChanged, Zoom, create error logs& save them for each try&catch,
	// make sure, every function has its own description and is fully commented
	//create different bitmaps, draw them parallel, display them at once

	private enum touchMode {
		RotateX, Move, Zoom, RotateY, Null
	}
	private int mActivePointerId;

	/**
	 * initialize VectorDraw, automatically done after creating it
	 */
	private void Initialize() {
		// init center of drawing TODO
		zero = new double[2];
		BitMapSize[0] = 500;
		BitMapSize[1] = 500;
		zero[0] = BitMapSize[0] / 2;
		zero[1] = BitMapSize[1] / 2;


		// init other vars
		backgroundColor = Color.WHITE;
		plainZFactor = Math.PI / 4;
		mScaleFactor = 1;

		// init bitmap&graphics
		bitMapGraphics = Bitmap.createBitmap((int) BitMapSize[0],
				(int) BitMapSize[1], Bitmap.Config.ARGB_8888);
		GraphicsVectorDraw = new Canvas(bitMapGraphics);
		GraphicsVectorDraw.drawColor(backgroundColor);
		// draw user interface
		this.DrawLine(cVector.PaintStandard, new double[]{BitMapSize[0] - 50,
				0}, new double[]{BitMapSize[0] - 50, BitMapSize[1]});
		this.DrawLine(cVector.PaintStandard, new double[]{0,
				BitMapSize[1] - 50}, new double[]{BitMapSize[0],
				BitMapSize[1] - 50});
		// load bitmap
		this.setImageBitmap(bitMapGraphics);
		this.draw(GraphicsVectorDraw);
		this.addVectorAsLineToDrawing(new cVector(100, 100, 100));
		reDraw();
		//mScaleDetector = new ScaleGestureDetector(ct, new ScaleListener());


	}

	/**
	 * origin of the drawing
	 */
	public double[] zero;

	// todo: after zoom/move center drawVectorAsLine new

	private double[] BitMapSize = new double[2];
	// definition of the axis
	private cVector xAxis = new cVector(new double[]{100, 0, 0}, Color.BLACK);
	private cVector yAxis = new cVector(new double[]{0, -100, 0}, Color.BLACK);
	private cVector zAxis = new cVector(new double[]{0, 0, 100}, Color.BLACK);
	// colors
	private int backgroundColor = Color.WHITE;
	// some factors
	private double plainZFactor;


	private float AxisSize = 10f;
	float mPreviousX;
	float mPreviousY;

	float rotationFactor = (float) (Math.PI / 100);

	private ArrayList<cVector> VectorAsPoint = new ArrayList<>();
	private ArrayList<cVector> VectorAsLine = new ArrayList<>();
	private ArrayList<cVector[]> VectorsAsTriangles = new ArrayList<>();
	private ArrayList<cVector[]> VectorAsQuader = new ArrayList<>();

	Canvas GraphicsVectorDraw;
	Bitmap bitMapGraphics;

	private static final String ERROR_CONVERTING_VECTOR_TO_POINT = String.valueOf(R.string.ERROR_CONVERTING_VECTOR_TO_POINT);

	// TODO implement zooming private String ERROR_ZOOM_FACTOR_VALUE =
	// "The value of the zoom factor can't be zero or less.";

	private static final String ERROR_DRAW_TRIANGLE_INVALID_ARGUMENTS = String.valueOf(R.string.ERROR_DRAW_TRIANGLE_INVALID_ARGUMENTS);


	/**
	 * @param context context
	 */
	public VectorDrawView(Context context) {
		super(context);
		init(null, 0);
		Initialize();

	}

	/**
	 * @param context context
	 * @param attrs   attributes
	 */
	public VectorDrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
		Initialize();
	}

	/**
	 * Adds a point to the vectordraw that automatically gets redrawn when zoom and the central
	 * point is changed
	 *
	 * @param v Vector that defines the point
	 */
	public void addPointToDrawing(cVector v) {
		VectorAsPoint.add(v);
	}

	/**
	 * Adds a line to the vectordraw that automatically gets redrawn when zoom and the central point
	 * is changed
	 *
	 * @param v Vector that defines the Line of the Vector
	 */
	public void addVectorAsLineToDrawing(cVector v) {
		VectorAsLine.add(v);
	}

	/**
	 * @param vs vectors describing the cube
	 */
	@SuppressWarnings("unused")
	public void addVectorAsQuader(cVector[] vs) {
		VectorAsQuader.add(vs);
	}

	/**
	 * Adds a array of vectors to the vectordraw that automatically gets redrawn when zoom and the
	 * central point is changed
	 *
	 * @param vs vectors describing the triangle
	 */
	@SuppressWarnings("unused")
	public void addVectorAsTriangle(cVector[] vs) {
		VectorsAsTriangles.add(vs);
	}


	/**
	 * @throws Exception
	 */
	public void Clear() throws Exception {
		// TODO optimize
		// clears everything, and draws the x, y, z axis again
		try {
			GraphicsVectorDraw.drawColor(backgroundColor);
			GraphicsVectorDraw.drawLine((float) zero[0], (float) zero[1],
					(float) VectorToPoint(plainVectorOnZ(xAxis))[0],
					(float) VectorToPoint(plainVectorOnZ(xAxis))[1],
					cVector.PaintStandard);
			GraphicsVectorDraw.drawLine((float) zero[0], (float) zero[1],
					(float) VectorToPoint(plainVectorOnZ(yAxis))[0],
					(float) VectorToPoint(plainVectorOnZ(yAxis))[1],
					cVector.PaintStandard);
			GraphicsVectorDraw.drawLine((float) zero[0], (float) zero[1],
					(float) VectorToPoint(plainVectorOnZ(zAxis))[0],
					(float) VectorToPoint(plainVectorOnZ(zAxis))[1],
					cVector.PaintStandard);
			this.setImageBitmap(bitMapGraphics);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			// reset everything
			VectorAsPoint = null;
			VectorAsLine = null;
			VectorsAsTriangles = null;
			VectorAsQuader = null;
		}

	}

	/**
	 * clears everything and then draws the vector
	 *
	 * @param vector to draw after cleaning everything
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void clearDraw(cVector vector) throws Exception {
		try {
			// use the drawVectorAsLine[array of vectors] => don't need to
			// program it again
			cVector[] v = {vector};
			clearDraw(v);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * clears the VectorForm and draws the given vectors
	 *
	 * @param vectors to draw after cleaning everything
	 * @throws Exception
	 */
	public void clearDraw(cVector[] vectors) throws Exception {
		try {
			// clear that damn thing
			Clear();
			// drawVectorAsLine all the vectors!
			drawVectorAsLine(vectors);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * Clear everything on the VectorDraw and the x, y and z axis
	 *
	 * @throws Exception
	 */
	public void ClearDrawing() throws Exception {
		//  TODO optimize
		// GraphicsVectorDraw=new Canvas(bitMapGraphics);
		GraphicsVectorDraw.drawColor(backgroundColor);
		GraphicsVectorDraw.drawLine((float) zero[0], (float) zero[1],
				(float) VectorToPoint(plainVectorOnZ(xAxis))[0],
				(float) VectorToPoint(plainVectorOnZ(xAxis))[1],
				cVector.PaintStandard);
		GraphicsVectorDraw.drawLine((float) zero[0], (float) zero[1],
				(float) VectorToPoint(plainVectorOnZ(yAxis))[0],
				(float) VectorToPoint(plainVectorOnZ(yAxis))[1],
				cVector.PaintStandard);
		GraphicsVectorDraw.drawLine((float) zero[0], (float) zero[1],
				(float) VectorToPoint(plainVectorOnZ(zAxis))[0],
				(float) VectorToPoint(plainVectorOnZ(zAxis))[1],
				cVector.PaintStandard);
		this.DrawLine(new Paint(), new double[]{BitMapSize[0] - 50, 0},
				new double[]{BitMapSize[0] - 50, BitMapSize[1]});
		this.DrawLine(new Paint(), new double[]{0, BitMapSize[1] - 50},
				new double[]{BitMapSize[0], BitMapSize[1] - 50});
		this.setImageBitmap(bitMapGraphics);
	}

	/**
	 * TODO Implement
	 *
	 * @param v1 defines the radius of the circle
	 * @param v2 orthogonal to this vector the circle is drawn
	 * @deprecated not yet implemented draws a circle around zero with radius v1 orthogonal to v2
	 */
	@SuppressWarnings("unused")
	public void drawCircle(VectorN v1, VectorN v2) {
		drawCircle(VectorN.origin, v1, v2);
	}

	/**
	 * TODO implement
	 *
	 * @param v1 v1
	 * @param v2 v2
	 * @param v3 v3
	 */
	@SuppressWarnings("unused")
	public void drawCircle(VectorN v1, VectorN v2, VectorN v3) {
		// GraphicsVectorDraw


	}

	/**
	 * draw a Line between the two points p1 and p2
	 *
	 * @param Pen defines the drawing pen
	 * @param p1  starting point
	 * @param p2  ending point
	 */
	private void DrawLine(Paint Pen, double[] p1, double[] p2) {
		GraphicsVectorDraw.drawLine((float) p1[0], (float) p1[1],
				(float) p2[0], (float) p2[1], Pen);
		this.setImageBitmap(bitMapGraphics);
	}

	/**
	 * draws a line between the ends of the given vectors
	 *
	 * @param v1 start point
	 * @param v2 end point
	 * @throws Exception
	 */
	public void drawLineBetweenVectors(cVector v1, cVector v2) throws Exception {
		this.DrawLine(v1.getvPaint(), VectorToPoint(plainVectorOnZ(v1)),
				VectorToPoint(plainVectorOnZ(v2)));
	}

	/**
	 * Draws a line between the ends of two vectors
	 *
	 * @param v1 vector 1
	 * @param v2 vector 2
	 * @param p  paint
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void drawLineBetweenVectors(cVector v1, cVector v2, Paint p)
			throws Exception {
		// TODO implement paint
		this.DrawLine(p, VectorToPoint(plainVectorOnZ(v1)),
				VectorToPoint(plainVectorOnZ(v2)));
	}

	/**
	 * Draws a single point at the end of the given vector with size 1
	 *
	 * @param v vector that describe the point
	 */
	public void drawPoint(cVector v) {
		drawPoint(v, 1);
	}

	/**
	 * Draws a single point at the end of the given vector
	 *
	 * @param v    vector that describe the point
	 * @param size Size of the cross defining the point
	 */
	public void drawPoint(cVector v, int size) {
		try {
			cVector temp = new cVector(plainVectorOnZ(v));
			temp.multiply(temp, mScaleFactor);
			double[] tempcomps = temp.getComponents();
			this.DrawLine(v.getvPaint(),
					new double[]{tempcomps[0] + size + zero[0],
							tempcomps[1] + size + zero[1]},
					new double[]{tempcomps[0] - size + zero[0],
							tempcomps[1] - size + zero[1]});
			this.DrawLine(v.getvPaint(),
					new double[]{tempcomps[0] + size + zero[0],
							tempcomps[1] - size + zero[1]},
					new double[]{tempcomps[0] - size + zero[0],
							tempcomps[1] + size + zero[1]});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * draws a dice on the base of 3 Vectors; it works, but you shouldn't use it!
	 *
	 * @param vectors vectors that describe the quader
	 * @throws Exception
	 */
	public void drawQuader(cVector[] vectors) throws Exception {

		cVector v1, v2, v3, v4, v5, v6, v7;
		v1 = new cVector(plainVectorOnZ(VectorN.multiply(vectors[0], mScaleFactor)));
		v2 = new cVector(plainVectorOnZ(VectorN.multiply(vectors[1], mScaleFactor)));
		v3 = new cVector(plainVectorOnZ(VectorN.multiply(vectors[2], mScaleFactor)));
		v5 = new cVector(plainVectorOnZ(VectorN.add(
				VectorN.multiply(vectors[0], mScaleFactor),
				VectorN.multiply(vectors[1], mScaleFactor))));
		v6 = new cVector(plainVectorOnZ(VectorN.add(
				VectorN.multiply(vectors[0], mScaleFactor),
				VectorN.multiply(vectors[2], mScaleFactor))));
		v7 = new cVector(plainVectorOnZ(VectorN.add(
				VectorN.add(VectorN.multiply(vectors[0], mScaleFactor),
						VectorN.multiply(vectors[1], mScaleFactor)),
				VectorN.multiply(vectors[2], mScaleFactor))));
		v4 = new cVector(plainVectorOnZ(VectorN.add(
				VectorN.multiply(vectors[2], mScaleFactor),
				VectorN.multiply(vectors[1], mScaleFactor))));

		// clearDraw(dada);
		double[] zero = VectorToPoint(new VectorN(new double[]{0, 0}));
		this.DrawLine(vectors[0].getvPaint(), zero, VectorToPoint(v1));
		this.DrawLine(vectors[0].getvPaint(), zero, VectorToPoint(v2));
		this.DrawLine(vectors[0].getvPaint(), zero, VectorToPoint(v3));
		this.DrawLine(vectors[0].getvPaint(), VectorToPoint(v1), VectorToPoint(v5));
		this.DrawLine(vectors[1].getvPaint(), VectorToPoint(v1), VectorToPoint(v6));
		this.DrawLine(vectors[1].getvPaint(), VectorToPoint(v2), VectorToPoint(v5));
		this.DrawLine(vectors[1].getvPaint(), VectorToPoint(v2), VectorToPoint(v4));
		this.DrawLine(vectors[1].getvPaint(), VectorToPoint(v3), VectorToPoint(v4));
		this.DrawLine(vectors[2].getvPaint(), VectorToPoint(v3), VectorToPoint(v6));
		this.DrawLine(vectors[2].getvPaint(), VectorToPoint(v5), VectorToPoint(v7));
		this.DrawLine(vectors[2].getvPaint(), VectorToPoint(v6), VectorToPoint(v7));
		this.DrawLine(vectors[2].getvPaint(), VectorToPoint(v4), VectorToPoint(v7));

	}

	/**
	 * draws a triangle with the given vectors. make sure the array contains only 3 vectors
	 *
	 * @param vectors vectors that describe the triangle
	 * @throws Exception
	 */
	public void drawTriangle(cVector[] vectors) throws Exception {
		try {
			if (vectors.length != 3) {
				throw new Exception(ERROR_DRAW_TRIANGLE_INVALID_ARGUMENTS);
			} else {
				drawLineBetweenVectors(vectors[0], vectors[1]);
				drawLineBetweenVectors(vectors[1], vectors[2]);
				drawLineBetweenVectors(vectors[0], vectors[2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * drawVectorAsLine a single vector
	 *
	 * @param vector vector that has to be drawn
	 * @throws Exception
	 */
	public void drawVectorAsLine(cVector vector) throws Exception {
		try {
			// use the drawVectorAsLine[array of vectors] => don't need to
			// program it again
			cVector[] v = {vector};
			drawVectorAsLine(v);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * draws the vector as a line from center to end of vector in the given color
	 *
	 * @param vector      draw a single vector as a line
	 * @param vectorColor color the vector has to be drawn in
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void drawVectorAsLine(cVector vector, Color vectorColor)
			throws Exception {
		// use the drawVectorAsLine[array of vectors] => don't need to program
		// it again

		cVector[] v = {vector};
		drawVectorAsLine(v);
	}

	/**
	 * draws all vectors in the given array from the middle of the drawing
	 *
	 * @param vectors Arrays of vectors to be drawn
	 * @throws Exception
	 */
	public void drawVectorAsLine(cVector[] vectors) throws Exception {
		// make sure, every vector is drawn
		try {
			for (cVector vector : vectors) {
				cVector v = new cVector(plainVectorOnZ(VectorN.multiply(vector, mScaleFactor)), vector.getvPaintColor());

				// finally drawVectorAsLine it
				this.DrawLine(v.getvPaint(), zero, VectorToPoint(v));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/*
	 * draws all vectors in the given array from the middle of the drawing in
	 * the specific color. color switches back after drawing
	 *
	 * @param vectors Vectors to drawVectorAsLine
	 *
	 * @param vectorColor color the vectors should have
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void drawVectorAsLine(cVector[] vectors, int vectorColor)
			throws Exception {
		// uses public void drawVectorAsLine(Vectors[] vectors)

		drawVectorAsLine(vectors);

	}


	@SuppressWarnings("unused")
	public void getAxisPen() {
		// TODO
		// return AxisPen;
	}

	@SuppressWarnings("unused")
	public float getAxisSize() {
		return AxisSize;
	}

	/**
	 * @return the bitMapGraphics
	 */
	@SuppressWarnings("unused")
	public Bitmap getBitMapGraphics() {
		return bitMapGraphics;
	}

	@SuppressWarnings("unused")
	public Canvas getGraphicsVectorDraw() {
		return GraphicsVectorDraw;
	}


	/**
	 * Add points to drawing. They get redrawn everytime zoom, rotation and/or position of zero
	 * changed
	 *
	 * @param vs vectors to add
	 */
	@SuppressWarnings("unused")
	public void addPointToDrawing(cVector[] vs) {
		for (cVector v : vs) {
			addPointToDrawing(v);
		}
	}

	/**
	 * reset the VectorDraw to it's default values
	 */
	@SuppressWarnings("unused")
	public void Refresh() {
		Initialize();
	}


	/**
	 * Adds a lines to the VectorDrawView that automatically gets redrawn when zoom and the central
	 * point is changed
	 *
	 * @param vs Vectors to add
	 */
	@SuppressWarnings("unused")
	public void addVectorAsLineToDrawing(cVector[] vs) {
		for (cVector v : vs) {
			addVectorAsLineToDrawing(v);
		}
	}

	/**
	 * moves the center of the drawing with the defined parameters
	 *
	 * @param x x offset
	 * @param y y offset
	 * @throws Exception
	 */
	public void moveCenter(int x, int y) throws Exception {
		try {
			// to move it with the given numbers => "+="
			zero[0] += x;
			zero[1] += y;
			// Clear everything on the screen
			ClearDrawing();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * projects a 3D vector on a 2D vector, that can be drawn in VectorDraw
	 *
	 * @param v Vector to be plained
	 * @return plained vector
	 */
	private VectorN plainVectorOnZ(VectorN v) {
		double[] ds = v.getComponents();
		double dz = ds[2];
		// plain
		double d[];
		d = plainZ(dz);
		double dx = ds[0] - d[0] / 2;
		double dy = ds[1] + d[1] / 2;
		d[0] = dx;
		d[1] = dy;
		return new VectorN(dx, dy);
		//cVector2.setComponents(d);
		//return cVector2;
	}

	/**
	 * converts z value to x and y value
	 *
	 * @param z
	 * @return
	 */

	@SuppressWarnings("all")
	private double[] plainZ(double z) {
		double x, y;
		x = Math.sin(plainZFactor) * z;
		y = Math.cos(plainZFactor) * z;
		double[] d = {x, y};
		return d;
	}

	/**
	 * redraws everything stored in the VectorDraw
	 */
	public void reDraw() {
		try {
			// all Points
			int x = VectorAsPoint.size();
			for (int i = 0; i < x; i++) {
				drawPoint(VectorAsPoint.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// all Lines
			int x = VectorAsLine.size();
			for (int i = 0; i < x; i++) {
				drawVectorAsLine(VectorAsLine.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// all triangles
			int x = VectorsAsTriangles.size();
			for (int i = 0; i < x; i++) {

				drawTriangle(VectorsAsTriangles.get(i));

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			// all quaders
			int x = VectorAsQuader.size();
			for (int i = 0; i < x; i++) {
				drawQuader(VectorAsQuader.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param axisPen the axisPen to set
	 */
	@SuppressWarnings("unused")
	public void setAxisPen(Paint axisPen) {
		// AxisPen = axisPen;
	}

	/**
	 * @param axisSize the axisSize to set
	 */
	@SuppressWarnings("unused")
	public void setAxisSize(float axisSize) {
		AxisSize = axisSize;
	}

	/**
	 * @param bitMapGraphics the bitMapGraphics to set
	 */
	@SuppressWarnings("unused")
	public void setBitMapGraphics(Bitmap bitMapGraphics) {
		this.bitMapGraphics = bitMapGraphics;
	}

	/**
	 * @param graphicsVectorDraw the graphicsVectorDraw to set
	 */
	@SuppressWarnings("unused")
	public void setGraphicsVectorDraw(Canvas graphicsVectorDraw) {
		GraphicsVectorDraw = graphicsVectorDraw;
	}

	/**
	 *
	 */
	public void test() {
		GraphicsVectorDraw.drawLine((float) zero[0], (float) zero[1],
				(float) zero[0] + 200, (float) zero[1] + 200, new Paint(
						Color.BLACK));
		this.setImageBitmap(bitMapGraphics);
		this.draw(GraphicsVectorDraw);
	}

	/**
	 * @param v
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("all")
	public double[] VectorToPoint(VectorN v) throws Exception {
		if (v.getDimension() == 2) {
			double[] da = {(v.getComponents()[0] + zero[0]),
					(v.getComponents()[1] + zero[1])};
			return da;
		} else {
			throw new Exception(ERROR_CONVERTING_VECTOR_TO_POINT);
		}
	}


}

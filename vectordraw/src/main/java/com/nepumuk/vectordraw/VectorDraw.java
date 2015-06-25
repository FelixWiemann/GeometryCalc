package com.nepumuk.vectordraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.nepumuk.geocalc.VectorN;

import java.util.ArrayList;
/**
 * Created by Felix "nepumuk" Wiemann on 16.04.2015.
 *
 * @author Felix "nepumuk" Wiemann
 * @version 0.1
 */

@SuppressWarnings("unused")
	public class VectorDraw extends ImageView implements  GestureDetector.OnGestureListener,
			GestureDetector.OnDoubleTapListener {
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

		// TODO onSizeChanged, Zoom, create error logs& save them for each try&catch,
		// make sure, every function has its own description and is fully commented
		//create different bitmaps, draw them parallel, display them at once

		private enum touchMode {
			RotateX, Move, Zoom, RotateY, Null
		}
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
			zoomFactor = 1;
			try {
				Clear();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// init bitmap&graphics
			bitMapGraphics = Bitmap.createBitmap((int) BitMapSize[0],
					(int) BitMapSize[1], Bitmap.Config.ARGB_8888);
			GraphicsVectorDraw = new Canvas(bitMapGraphics);
			GraphicsVectorDraw.drawColor(backgroundColor);
			// draw user interface
			this.DrawLine(cVector.PaintStandard, new double[] { BitMapSize[0] - 50,
					0 }, new double[] { BitMapSize[0] - 50, BitMapSize[1] });
			this.DrawLine(cVector.PaintStandard, new double[] { 0,
					BitMapSize[1] - 50 }, new double[] { BitMapSize[0],
					BitMapSize[1] - 50 });
			// load bitmap
			this.setImageBitmap(bitMapGraphics);
			this.draw(GraphicsVectorDraw);
			// eventhandling
			this.setOnTouchListener(new OnTouchListener() {

				touchMode tm = touchMode.Null;

				/*
				 * (non-Javadoc)
				 *
				 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
				 * android.view.MotionEvent)
				 */
				public boolean onTouch(View view, MotionEvent event) {

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
									for (int i = 0; i < VectorAsQuaders.size(); i++) {
										try {
											VectorAsQuaders.get(i)[0]
													.rotateAroundX(rotationFactor * dy);
											VectorAsQuaders.get(i)[1]
													.rotateAroundX(rotationFactor * dy);
											VectorAsQuaders.get(i)[2]
													.rotateAroundX(rotationFactor * dy);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
									for (int i = 0; i < VectorsAsTriangles.size(); i++) {
										try {
											VectorsAsTriangles.get(i)[0]
													.rotateAroundX(rotationFactor * dy);
											VectorsAsTriangles.get(i)[1]
													.rotateAroundX(rotationFactor * dy);
											VectorsAsTriangles.get(i)[2]
													.rotateAroundX(rotationFactor * dy);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
									for (int i = 0; i < VectorAsLine.size(); i++) {
										try {
											VectorAsLine.get(i).rotateAroundX(
													rotationFactor * dy);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
									for (int i = 0; i < VectorAsPoint.size(); i++) {
										try {
											VectorAsPoint.get(i).rotateAroundX(
													rotationFactor * dy);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}

									break;
								case RotateY:
									for (int i = 0; i < VectorAsQuaders.size(); i++) {
										try {
											VectorAsQuaders.get(i)[0]
													.rotateAroundZ(rotationFactor * dx);
											VectorAsQuaders.get(i)[1]
													.rotateAroundZ(rotationFactor * dx);
											VectorAsQuaders.get(i)[2]
													.rotateAroundZ(rotationFactor * dx);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
									for (int i = 0; i < VectorAsLine.size(); i++) {
										try {
											VectorAsLine.get(i).rotateAroundZ(
													rotationFactor * dx);
										} catch (Exception e) {
											e.printStackTrace();
										}

									}
									for (int i = 0; i < VectorAsPoint.size(); i++) {
										try {
											VectorAsPoint.get(i).rotateAroundZ(
													rotationFactor * dx);
										} catch (Exception e) {
											e.printStackTrace();
										}

									}
									for (int i = 0; i < VectorsAsTriangles.size(); i++) {
										try {
											VectorsAsTriangles.get(i)[0]
													.rotateAroundZ(rotationFactor * dx);
											VectorsAsTriangles.get(i)[1]
													.rotateAroundZ(rotationFactor * dx);
											VectorsAsTriangles.get(i)[2]
													.rotateAroundZ(rotationFactor * dx);
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

					return true;
				}
			});
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
		private double zoomFactor;

		private float AxisSize = 10f;
		float mPreviousX;
		float mPreviousY;

		float rotationFactor = (float) (Math.PI / 100);

		private ArrayList<cVector> VectorAsPoint = new ArrayList<>();
		private ArrayList<cVector> VectorAsLine = new ArrayList<>();
		private ArrayList<cVector[]> VectorsAsTriangles = new ArrayList<>();
		private ArrayList<cVector[]> VectorAsQuaders = new ArrayList<>();

		Canvas GraphicsVectorDraw;
		Bitmap bitMapGraphics;

		private static final String ERROR_CONVERTING_VECTOR_TO_POINT = "The dimension of the vector has to be 2";

		// TODO implement zooming private String ERROR_ZOOM_FACTOR_VALUE =
		// "The value of the zoom factor can't be zero or less.";

		private static final String ERROR_DRAW_TRIANGLE_INVALID_ARGUMENTS = "The amount of vectors is not correct";



		/**
		 * @param context
		 */
		public VectorDraw(Context context) {
			super(context);
			Initialize();

		}

		/**
		 * @param context
		 * @param attrs
		 */
		public VectorDraw(Context context, AttributeSet attrs) {
			super(context, attrs);
			Initialize();
		}

		/**
		 * Adds a point to the vectordraw that automatically gets redrawn when zoom
		 * and the central point is changed
		 *
		 * @param v
		 *            Vector that defines the point
		 */
		public void addPointToDrawing(cVector v) {
			VectorAsPoint.add(v);
		}

		/**
		 * Adds a line to the vectordraw that automatically gets redrawn when zoom
		 * and the central point is changed
		 *
		 * @param v
		 *            Vector that defines the Line of the Vector
		 */
		public void addVectorAsLineToDrawing(cVector v) {
			VectorAsLine.add(v);
		}

		/**
		 * @param vs
		 */
		public void addVectorAsQuader(cVector[] vs) {
			VectorAsQuaders.add(vs);
		}

		/**
		 * Adds a array of vectors to the vectordraw that automatically gets redrawn
		 * when zoom and the central point is changed
		 *
		 * @param vs
		 */
		public void addVectorAsTriangle(cVector[] vs) {
			VectorsAsTriangles.add(vs);
		}


		/**
		 * @throws Exception
		 */
		public void Clear() throws Exception {
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
				VectorAsQuaders = null;
			}

		}

		/**
		 * clears everything and then draws the vector
		 *
		 * @param vector
		 * @throws Exception
		 */
		public void clearDraw(cVector vector) throws Exception {
			try {
				// use the drawVectorAsLine[array of vectors] => don't need to
				// program it again
				cVector[] v = { vector };
				clearDraw(v);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}

		/**
		 * clears the VectorForm and draws the given vectors
		 *
		 * @param vectors
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
			this.DrawLine(new Paint(), new double[] { BitMapSize[0] - 50, 0 },
					new double[] { BitMapSize[0] - 50, BitMapSize[1] });
			this.DrawLine(new Paint(), new double[] { 0, BitMapSize[1] - 50 },
					new double[] { BitMapSize[0], BitMapSize[1] - 50 });
			this.setImageBitmap(bitMapGraphics);
		}

		/**
		 * TODO Implement
		 *
		 * @deprecated not yet implemented draws a circle around zero with radius v1
		 *             orthogonal to v2
		 * @param v1
		 *            defines the radius of the circle
		 * @param v2
		 *            orthogonal to this vector the circle is drawn
		 */
		public void drawCircle(VectorN v1, VectorN v2) {
			drawCircle(VectorN.origin, v1, v2);
		}

		/**
		 * TODO implement
		 *
		 * @param v1
		 * @param v2
		 * @param v3
		 */
		public void drawCircle(VectorN v1, VectorN v2, VectorN v3) {
			// GraphicsVectorDraw

		}

		/**
		 * draw a Line between the two points p1 and p2
		 *
		 * @param Pen
		 *            defines the drawing pen
		 * @param p1
		 *            starting point
		 * @param p2
		 *            ending point
		 */
		private void DrawLine(Paint Pen, double[] p1, double[] p2) {
			GraphicsVectorDraw.drawLine((float) p1[0], (float) p1[1],
					(float) p2[0], (float) p2[1], Pen);
			this.setImageBitmap(bitMapGraphics);
		}

		/**
		 * draws a line between the ends of the given vectors
		 *
		 * @param v1
		 *            start point
		 * @param v2
		 *            end point
		 * @throws Exception
		 */
		public void drawLineBetweenVectors(cVector v1, cVector v2) throws Exception {
			this.DrawLine(v1.getvPaint(), VectorToPoint(plainVectorOnZ(v1)),
					VectorToPoint(plainVectorOnZ(v2)));
		}

		/**
		 * @param v1
		 * @param v2
		 * @param p
		 * @throws Exception
		 */
		public void drawLineBetweenVectors(cVector v1, cVector v2, Paint p)
				throws Exception {
			this.DrawLine(p, VectorToPoint(plainVectorOnZ(v1)),
					VectorToPoint(plainVectorOnZ(v2)));
		}

		/**
		 * Draws a single point at the end of the given vector
		 *
		 * @param v
		 */
		public void drawPoint(cVector v) {
			drawPoint(v, 1);
		}

		/**
		 * Draws a single point at the end of the given vector
		 *
		 * @param v
		 * @param size
		 *            Size of the cross defining the point
		 */
		public void drawPoint(cVector v, int size) {
			try {
				cVector temp = new cVector(plainVectorOnZ(v));
				this.DrawLine(v.getvPaint(),
						new double[] { temp.getComponents()[0] + size + zero[0],
								temp.getComponents()[1] + size + zero[1] },
						new double[] { temp.getComponents()[0] - size + zero[0],
								temp.getComponents()[1] - size + zero[1] });
				this.DrawLine(v.getvPaint(),
						new double[] { temp.getComponents()[0] + size + zero[0],
								temp.getComponents()[1] - size + zero[1] },
						new double[] { temp.getComponents()[0] - size + zero[0],
								temp.getComponents()[1] + size + zero[1] });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 *
		 * draws a dice on the base of 3 Vectors; it works, but you shouldn't use
		 * it!
		 *
		 * @param vectors
		 * @throws Exception
		 */
		public void drawQuader(cVector[] vectors) throws Exception {

			cVector v1, v2, v3, v4, v5, v6, v7;
			v1 = new cVector(plainVectorOnZ(VectorN.multiply(vectors[0], zoomFactor)));
			v2 = new cVector(plainVectorOnZ(VectorN.multiply(vectors[1], zoomFactor)));
			v3 = new cVector(plainVectorOnZ(VectorN.multiply(vectors[2], zoomFactor)));
			v5 = new cVector(plainVectorOnZ(VectorN.add(
					VectorN.multiply(vectors[0], zoomFactor),
					VectorN.multiply(vectors[1], zoomFactor))));
			v6 = new cVector(plainVectorOnZ(VectorN.add(
					VectorN.multiply(vectors[0], zoomFactor),
					VectorN.multiply(vectors[2], zoomFactor))));
			v7 = new cVector(plainVectorOnZ(VectorN.add(
					VectorN.add(VectorN.multiply(vectors[0], zoomFactor),
							VectorN.multiply(vectors[1], zoomFactor)),
					VectorN.multiply(vectors[2], zoomFactor))));
			v4 = new cVector(plainVectorOnZ(VectorN.add(
					VectorN.multiply(vectors[2], zoomFactor),
					VectorN.multiply(vectors[1], zoomFactor))));

			// clearDraw(dada);
			double[] zero = VectorToPoint(new VectorN(new double[] { 0, 0 }));
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
		 * draws a triangle with the given vectors. make sure the array contains
		 * only 3 vectors
		 *
		 * @param vectors
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
		 * @param vector
		 *            vector that has to be drawn
		 * @throws Exception
		 */
		public void drawVectorAsLine(cVector vector) throws Exception {
			try {
				// use the drawVectorAsLine[array of vectors] => don't need to
				// program it again
				cVector[] v = { vector };
				drawVectorAsLine(v);

			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}

		/**
		 * draws the vector as a line from center to end of vector in the given
		 * color
		 *
		 * @param vector
		 * @param vectorColor
		 * @throws Exception
		 */
		public void drawVectorAsLine(cVector vector, Color vectorColor)
				throws Exception {
			// use the drawVectorAsLine[array of vectors] => don't need to program
			// it again
			cVector[] v = { vector };
			drawVectorAsLine(v);
		}

		/**
		 * draws all vectors in the given array from the middle of the drawing
		 *
		 * @param vectors
		 *            Arrays of vectors to be drawn
		 * @throws Exception
		 */
		public void drawVectorAsLine(cVector[] vectors) throws Exception {
			// make sure, every vector is drawn
			try {
				for (cVector vector : vectors) {
					// Vector v = new Vector(Vector.multiplikate(vectors[i] ,
					// zoomFactor));
					cVector v = new cVector(plainVectorOnZ(vector), vector.getvPaintColor());

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
		public void drawVectorAsLine(cVector[] vectors, int vectorColor)
				throws Exception {
			// uses public void drawVectorAsLine(Vectors[] vectors)

			drawVectorAsLine(vectors);

		}


		public void getAxisPen() {
			// TODO
			// return AxisPen;
		}

		public float getAxisSize() {
			return AxisSize;
		}

		/**
		 * @return the bitMapGraphics
		 */
		public Bitmap getBitMapGraphics() {
			return bitMapGraphics;
		}

		public Canvas getGraphicsVectorDraw() {
			return GraphicsVectorDraw;
		}

		// / <summary>
		// / Adds a number of points to the vectordraw that automatically gets
		// redrawn when zoom and the central point is changed
		// / </summary>
		// / <param name="vs">Vectors that defines the point</param>
		/**
		 * Add points to drawing. They get redrawn everytime zoom, rotation and/or
		 * position of zero changed
		 *
		 * @param vs
		 */
		public void addPointToDrawing(cVector[] vs) {
			for (int i = 0; i < vs.length; i++) {
				addPointToDrawing(vs[i]);
			}
		}

		/**
		 * reset the VectorDraw to it's default values
		 */
		public void Refresh() {
			Initialize();
		}



		/**
		 * Adds a lines to the vectordraw that automatically gets redrawn when zoom
		 * and the central point is changed
		 *
		 * @param vs
		 */
		public void addVectorAsLineToDrawing(cVector[] vs) {
			for (cVector v : vs) {
				addVectorAsLineToDrawing(v);
			}
		}

		/**
		 * moves the center of the drawing with the defined parameters
		 *
		 * @param x
		 *            x offset
		 * @param y
		 *            y offset
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
		 * @param v
		 *            Vector to be plained
		 * @return plained vector
		 */
		private VectorN plainVectorOnZ(VectorN v) {
			double dz = v.getComponents()[2];
			// plain
			double d[];
			d = plainZ(dz);
			double dx = v.getComponents()[0] - d[0]/2 ;
			double dy = v.getComponents()[1] + d[1]/2 ;
			return new VectorN(dx, dy);
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
			double[] d = { x, y };
			return d;
		}

		/**
		 * redraws everything stored in the VectorDraw
		 */
		public void reDraw() {
			try {
				// all Points
				for (int i = 0; i < VectorAsPoint.size(); i++) {
					drawPoint( VectorAsPoint.get(i));
				}

			}

			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// all Lines
				for (int i = 0; i < VectorAsLine.size(); i++) {
					drawVectorAsLine(VectorAsLine.get(i));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// all triangles
				for (int i = 0; i < VectorsAsTriangles.size(); i++) {

					drawTriangle(VectorsAsTriangles.get(i));

				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			try {
				// all quaders
				for (int i = 0; i < VectorAsQuaders.size(); i++) {
					drawQuader( VectorAsQuaders.get(i));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * @param axisPen
		 *            the axisPen to set
		 */
		public void setAxisPen(Paint axisPen) {
			// AxisPen = axisPen;
		}

		/**
		 * @param axisSize
		 *            the axisSize to set
		 */
		public void setAxisSize(float axisSize) {
			AxisSize = axisSize;
		}

		/**
		 * @param bitMapGraphics
		 *            the bitMapGraphics to set
		 */
		public void setBitMapGraphics(Bitmap bitMapGraphics) {
			this.bitMapGraphics = bitMapGraphics;
		}

		/**
		 * @param graphicsVectorDraw
		 *            the graphicsVectorDraw to set
		 */
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
				double[] da = { (v.getComponents()[0] + zero[0]),
						(v.getComponents()[1] + zero[1]) };
				return da;
			} else {
				throw new Exception(ERROR_CONVERTING_VECTOR_TO_POINT);
			}
		}

	}


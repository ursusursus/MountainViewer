package sk.ursus.mountainviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyCompassView extends View {

	private float direction = 0;
	//antialiasing zevraj vykon onaci ?
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private boolean firstDraw;
	private Bitmap bitmap;
	private Matrix matrix;

	public MyCompassView(Context context) {
		super(context);
				
		init(context);
	}

	public MyCompassView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public MyCompassView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {

		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);
		paint.setColor(Color.RED);
		paint.setTextSize(30);

		firstDraw = true;
		
		bitmap = (Bitmap) BitmapFactory.decodeResource(context.getResources(), R.drawable.krizna_hi_res);
		if(bitmap == null) {
			Log.i("IS","NULL");
		} else {
			Log.i("IS","NOT NULL");
		}
		
		matrix = new Matrix();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		int cxCompass = getMeasuredWidth() / 2;
		//int cyCompass = getMeasuredHeight() / 2;
		int cyCompass = getMeasuredHeight();
		float radiusCompass;
		
		matrix.reset();
		matrix.setTranslate((getMeasuredWidth() - bitmap.getWidth()) / 2, getMeasuredHeight() - (bitmap.getHeight() / 2));
		//matrix.postRotate(direction, cxCompass, cyCompass);
		matrix.postRotate(-direction, cxCompass, cyCompass);
		canvas.drawBitmap(bitmap, matrix, null);
		
		canvas.drawPoint(cxCompass, cyCompass, paint);
		
/*		if (cxCompass > cyCompass) {
			radiusCompass = (float) (cyCompass * 0.9);
		} else {
			radiusCompass = (float) (cxCompass * 0.9);
		}*/
		//canvas.drawCircle(cxCompass, cyCompass, radiusCompass, paint);
		//canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

	/*	if (!firstDraw) {

			canvas.drawLine(cxCompass, cyCompass,
					(float) (cxCompass + radiusCompass * Math.sin((double) (-direction) * 3.14 / 180)),
					(float) (cyCompass - radiusCompass * Math.cos((double) (-direction) * 3.14 / 180)), paint);*/

			//canvas.drawText(String.valueOf(direction), cxCompass, cyCompass, paint);
	//	} 
		
		

	}

	public void updateDirection(float dir) {
		firstDraw = false;
		direction = dir;
		invalidate();
	}

}

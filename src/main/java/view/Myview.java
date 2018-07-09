package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.zhoukao.R;

public class Myview extends View{
    Paint paint;
    Paint paint2;
    Bitmap b1,b2;
    Canvas canvas;
    Path path;
    String text="刮刮看";
    public Myview(Context context) {
        super(context);
        init(context);
    }

    public Myview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Myview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setAlpha(0);
        paint.setStrokeWidth(50);
        paint.setStyle(Paint.Style.STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        b1= BitmapFactory.decodeResource(getResources(), R.drawable.zhoukao);
        b2 = Bitmap.createBitmap(b1.getWidth(),b1.getHeight(), Bitmap.Config.ARGB_8888);
        paint2=new Paint();
        paint.setAntiAlias(true);
        paint2.setTextSize(25);
        paint.setStrokeWidth(10);
        path=new Path();
        canvas=new Canvas(b2);
        canvas.drawColor(Color.GRAY);
        canvas.drawText(text,canvas.getWidth()/4,canvas.getHeight()/2,paint2);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
        }
        canvas.drawPath(path, paint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(b1,0,0,null);
        canvas.drawBitmap(b2,0,0,null);
    }
}

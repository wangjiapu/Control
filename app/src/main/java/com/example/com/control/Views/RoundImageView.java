package com.example.com.control.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;


import com.example.com.control.R;

/**
 * Created by xiyou3g on 2017/5/21.
 * 圆形imageView
 */

public class RoundImageView extends ImageView {

    private static final int DEFAULT_BORDER=10;
    private static final int TYPE_CIRCLE=0;
    private static final int TYPE_ROUND=1;
    private int type;//类型
    private Paint mPaint;         //设置笔画
    private int mBorderRadius;    //设置圆角的度数；

    private int mwith;    //图片直径
    private int mradius;//半径
    private Matrix mMatrix;//缩放矩阵
    private RectF mRectF;              //圆角范围;
    private BitmapShader bitmapShader;//使用图片填充形状


     public RoundImageView(Context context, @Nullable AttributeSet attrs) {
         this(context, attrs,0);
     }

     public RoundImageView(Context context) {
         this(context,null);
     }

      public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
          super(context, attrs, defStyleAttr);
          mMatrix=new Matrix();
          mPaint=new Paint();
          mPaint.setAntiAlias(true);
          //获取自定义属性值
          TypedArray typedArray=context.getTheme()
                  .obtainStyledAttributes(attrs, R.styleable.RoundImageView,defStyleAttr,0);
          int count=typedArray.getIndexCount();
          for(int i=0;i<count;i++){
              int attr=typedArray.getIndex(i);
              switch (attr){
                  case R.styleable.RoundImageView_borderRadius:
                      //获取圆角的大小
                      mBorderRadius=typedArray.getDimensionPixelSize(
                              R.styleable.RoundImageView_borderRadius,
                              (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                      DEFAULT_BORDER,getResources().getDisplayMetrics()));
                      break;
                  case R.styleable.RoundImageView_imageType:
                      type=typedArray.getInt(R.styleable.RoundImageView_imageType,TYPE_CIRCLE);
                      break;

              }
          }
          typedArray.recycle();
      }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mwith=Math.min(getMeasuredWidth(),getMeasuredHeight());
        mradius=mwith/2;
        setMeasuredDimension(mwith,mwith);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       if (getDrawable()==null){
           return;
       }
       setShader();
        if(type == TYPE_ROUND){
            canvas.drawRoundRect(mRectF,mBorderRadius,mBorderRadius,mPaint);
        }else{
            canvas.drawCircle(mradius,mradius,mradius,mPaint);
        }
    }

    private void setShader() {
        float scale = 1.0f;
        Drawable dw=getDrawable();
        Bitmap bitmap=drawableTobitmap(dw);
        bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        if (type==TYPE_ROUND){
            scale=Math.max(getWidth()*1.0f/bitmap.getWidth(),getHeight()*1.0f/bitmap.getHeight());

        }else if (type==TYPE_CIRCLE){
            int bw=Math.min(bitmap.getWidth(),getHeight());
            scale=mwith*1.0f/bw;
        }
        Log.e("qqqqqqqqqqqqqq",mMatrix+"");
        mMatrix.setScale(scale,scale);
        bitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(bitmapShader);

    }

    private Bitmap drawableTobitmap(Drawable dw) {
        if(dw instanceof BitmapDrawable){
            BitmapDrawable bd=(BitmapDrawable)dw;
            return  bd.getBitmap();
        }
        int w=dw.getIntrinsicWidth();
        int h=dw.getIntrinsicHeight();//返回值是以dp为单位
        Bitmap bt=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bt);
        dw.setBounds(0,0,w,h);
        dw.draw(canvas);
       return bt;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF  = new RectF(0,0,getWidth(),getHeight());
    }

    //对外公布设置borderRadius 的方法;
    public void setBorderRadius(int borderRadius){
        int pxValue = dpTodp(borderRadius);
        if(this.mBorderRadius!= pxValue){
            this.mBorderRadius = pxValue;
            //这时候不需要父布局的onLayout，只需调用onDraw即可;
            invalidate();
        }
    }

    //对外公布设置形状的方法;
    public void setType(int type){
        if(this.type!=type){
            this.type = type;
            if(this.type != TYPE_CIRCLE && this.type != TYPE_ROUND){
                this.type = TYPE_CIRCLE;
            }
            //这个时候改变形状了，就需要调用父布局的onLayout ,那么此 view 的onMeasure方法也会被调用;
            requestLayout();
        }
    }

    public int dpTodp(int val){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,val,
                getResources().getDisplayMetrics());
    }
}

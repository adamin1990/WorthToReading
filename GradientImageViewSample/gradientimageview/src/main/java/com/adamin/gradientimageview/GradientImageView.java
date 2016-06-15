package com.adamin.gradientimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by adamlee on 2016/6/15.
 */
public class GradientImageView extends ImageView {



    private float startX = 0f;
    private float startY = 0f;
    private float widthRatio = 1.0f;
    private float heightRatio = 1.0f;
    private float rotate = 0.0f;
    private int startColor = Color.parseColor("#00000000");
    private int endColor = Color.parseColor("#FF000000");
    private int middleColor = -1;

    private float startOffset = 0.0f;
    private float endOffset = 1.0f;
    private float middleOffset = 0.5f;

    int [] colors = null;
    float [] offsets = null;
    Shader gradient = null;
    Matrix rotateMatrix = null;
    Paint gradientPaint = null;
    public GradientImageView(Context context) {
        this(context,null);
    }

    public GradientImageView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public GradientImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GradientImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }



    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        if(attrs==null){
            return;
        }
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.GradientImageViewAttrs);
        startX=array.getFloat(R.styleable.GradientImageViewAttrs_giv_x,startX);
        startY=array.getFloat(R.styleable.GradientImageViewAttrs_giv_y,startY);
        widthRatio=array.getFloat(R.styleable.GradientImageViewAttrs_giv_width,widthRatio);
        heightRatio=array.getFloat(R.styleable.GradientImageViewAttrs_giv_height,heightRatio);
        rotate=array.getFloat(R.styleable.GradientImageViewAttrs_giv_rotate,rotate);

        startColor=array.getColor(R.styleable.GradientImageViewAttrs_giv_startColor,startColor);
        endColor = array.getColor(R.styleable.GradientImageViewAttrs_giv_endColor, endColor);
        middleColor = array.getColor(R.styleable.GradientImageViewAttrs_giv_middleColor, middleColor);

        startOffset = array.getFloat(R.styleable.GradientImageViewAttrs_giv_startOffset, startOffset);
        endOffset = array.getFloat(R.styleable.GradientImageViewAttrs_giv_endOffset, endOffset);
        middleOffset = array.getFloat(R.styleable.GradientImageViewAttrs_giv_middleOffset, middleOffset);

        array.recycle();

        if(middleColor==-1){
            colors=new int[]{startColor,endColor};
            offsets=new float[]{startOffset,endOffset};
        }else {
            colors=new int[]{startColor,middleColor,endColor};
            offsets=new float[]{startOffset,middleOffset,endOffset};
        }
        gradientPaint=new Paint();
        rotateMatrix=new Matrix();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float left=startX*getWidth();
        float top=startY*getHeight();
        float right=left+widthRatio*getWidth();
        float bottom=top +heightRatio*getHeight();
        if(gradient==null){
            gradient=new LinearGradient(left,top,right,bottom,colors,offsets
            , Shader.TileMode.CLAMP);

        }

        rotateMatrix.setRotate(rotate,getWidth()/2,getHeight()/2);  //旋转角度设置
        gradient.setLocalMatrix(rotateMatrix);
        gradientPaint.setShader(gradient);
        canvas.drawRect(left,top,right,bottom,gradientPaint);
    }


    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
        gradient = null;
        postInvalidate();
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
        gradient = null;
        postInvalidate();
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
        gradient = null;
        postInvalidate();
    }

    public float getWidthRatio() {
        return widthRatio;
    }

    public void setWidthRatio(float widthRatio) {
        this.widthRatio = widthRatio;
        gradient = null;
        postInvalidate();
    }

    public float getHeightRatio() {
        return heightRatio;
    }

    public void setHeightRatio(float heightRatio) {
        this.heightRatio = heightRatio;
        gradient = null;
        postInvalidate();
    }
}

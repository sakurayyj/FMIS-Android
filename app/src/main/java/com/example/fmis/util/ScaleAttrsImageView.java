package com.example.fmis.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.fmis.R;

public class ScaleAttrsImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener {

    private Matrix mMatrix;
    public ScaleAttrsImageView(Context context) {
        this(context, null);
    }
    public ScaleAttrsImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    private  boolean isScale = false;
    public ScaleAttrsImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取所有的自定义属性和样式
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScaleImageView, defStyleAttr, 0);
        //获取自定义属性的个数
        final int indexCount = typedArray.getIndexCount();
        //获取相关设定的值
        for (int i = 0; i < indexCount; i++) {
            final int attr = typedArray.getIndex(i);
            switch (attr){
                case  R.styleable.ScaleImageView_isScaleImage:
                    isScale = typedArray.getBoolean(attr,false);
            }
        }
        mMatrix = new Matrix();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    private boolean mOnce = false;

    @Override
    public void onGlobalLayout() {
        if (!mOnce) {
            final int width = getWidth();
            final int height = getHeight();
            final Drawable drawable = getDrawable();
            if (drawable==null){
                return;
            }
            final int intrinsicWidth = drawable.getIntrinsicWidth();
            final int intrinsicHeight = drawable.getIntrinsicHeight();
            float  scale = 1.0f;
            //如果图片宽度大于控件宽度，图片高度小于控件高度  图片缩小
            if (intrinsicWidth > width && intrinsicHeight < height) {
                scale = intrinsicWidth * 1.0f / width;
            }
            //如果图片的高度大于控件的高度，图片的宽度小于控件的宽度  图片缩小
            if (intrinsicHeight > height && intrinsicWidth < width) {
                scale = intrinsicHeight * 1.0f / height;
            }
            //如果图片的宽与高都大于控件的宽与高 或者 图片的宽与高都小于控件的宽与高
            if ((intrinsicHeight > height && intrinsicWidth > width) ) {
                scale = Math.min(width * 1.0f / intrinsicWidth, height * 1.0f / intrinsicHeight);
            }
            if (isScale&&(intrinsicHeight < height && intrinsicWidth < width)) {
                scale = Math.min(width * 1.0f / intrinsicWidth, height * 1.0f / intrinsicHeight);
            }
            //得到初始化时图片需要进行缩放的值
            final int moveX = getWidth() / 2 - intrinsicWidth / 2;
            final int moveY = getHeight() / 2 - intrinsicHeight / 2;

            mMatrix.postTranslate(moveX, moveY);
            mMatrix.postScale(scale,scale,getWidth()/2,getHeight()/2);
            setImageMatrix(mMatrix);
            mOnce=true;
        }
    }
}

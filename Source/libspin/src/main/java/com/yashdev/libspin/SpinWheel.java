package com.yashdev.libspin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Random;

import com.yashdev.libspin.model.SpinItem;
import com.yashdev.libspin.utils.Utils;
import com.yashdev.libspin.views.Spin;

public class SpinWheel extends RelativeLayout implements Spin.PieRotateListener {
    private int mBackgroundColor;
    private int mTextColor;
    private int mTopTextSize;
    private int mSecondaryTextSize;
    private int mBorderColor;
    private int mTopTextPadding;
    private int mEdgeWidth;
    private Drawable mCenterImage;
    private Drawable mCursorImage;

    private Spin spin;
    private ImageView ivCursorView;

    private SpinWheelRoundItemSelectedListener spinWheelRoundItemSelectedListener;

    @Override
    public void rotateDone(int index) {
        if (spinWheelRoundItemSelectedListener != null) {
            spinWheelRoundItemSelectedListener.SpinWheelRoundItemSelected(index);
        }
    }

    public interface SpinWheelRoundItemSelectedListener {
        void SpinWheelRoundItemSelected(int index);
    }

    public void setSpinWheelRoundItemSelectedListener(SpinWheelRoundItemSelectedListener listener) {
        this.spinWheelRoundItemSelectedListener = listener;
    }

    public SpinWheel(Context context) {
        super(context);
        init(context, null);
    }

    public SpinWheel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context ctx, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.SpinWheelView);
            mBackgroundColor = typedArray.getColor(R.styleable.SpinWheelView_spnwBackgroundColor, 0xffcc0000);
            mTopTextSize = typedArray.getDimensionPixelSize(R.styleable.SpinWheelView_spnwTopTextSize, (int) Utils.convertDpToPixel(15f, getContext()));
            mSecondaryTextSize = typedArray.getDimensionPixelSize(R.styleable.SpinWheelView_spnwSecondaryTextSize, (int) Utils.convertDpToPixel(20f, getContext()));
            mTextColor = typedArray.getColor(R.styleable.SpinWheelView_spnwTopTextColor, 0);
            mTopTextPadding = typedArray.getDimensionPixelSize(R.styleable.SpinWheelView_spnwTopTextPadding, (int) Utils.convertDpToPixel(25f, getContext())) + (int) Utils.convertDpToPixel(10f, getContext());
            mCursorImage = typedArray.getDrawable(R.styleable.SpinWheelView_spnwCursor);
            mCenterImage = typedArray.getDrawable(R.styleable.SpinWheelView_spnwCenterImage);
            mEdgeWidth = typedArray.getInt(R.styleable.SpinWheelView_spnwEdgeWidth, 10);
            mBorderColor = typedArray.getColor(R.styleable.SpinWheelView_spnwEdgeColor, 0);
            typedArray.recycle();
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.layout_spinwheel, this, false);

        spin = frameLayout.findViewById(R.id.spinView);
        ivCursorView = frameLayout.findViewById(R.id.cursorView);

        spin.setPieRotateListener(this);
        spin.setPieBackgroundColor(mBackgroundColor);
        spin.setTopTextPadding(mTopTextPadding);
        spin.setTopTextSize(mTopTextSize);
        spin.setSecondaryTextSizeSize(mSecondaryTextSize);
        spin.setPieCenterImage(mCenterImage);
        spin.setBorderColor(mBorderColor);
        spin.setBorderWidth(mEdgeWidth);


        if (mTextColor != 0)
            spin.setPieTextColor(mTextColor);

        ivCursorView.setImageDrawable(mCursorImage);

        addView(frameLayout);
    }

    
    public boolean isTouchEnabled() {
        return spin.isTouchEnabled();
    }

    public void setTouchEnabled(boolean touchEnabled) {
        spin.setTouchEnabled(touchEnabled);
    }

    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //This is to control that the touch events triggered are only going to the PieView
        for (int i = 0; i < getChildCount(); i++) {
            if (isPielView(getChildAt(i))) {
                return super.dispatchTouchEvent(ev);
            }
        }
        return false;
    }

    private boolean isPielView(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < getChildCount(); i++) {
                if (isPielView(((ViewGroup) view).getChildAt(i))) {
                    return true;
                }
            }
        }
        return view instanceof Spin;
    }

    public void setSpinWheelBackgrouldColor(int color) {
        spin.setPieBackgroundColor(color);
    }

    public void setSpinWheelCursorImage(int drawable) {
        ivCursorView.setBackgroundResource(drawable);
    }

    public void setSpinWheelCenterImage(Drawable drawable) {
        spin.setPieCenterImage(drawable);
    }

    public void setBorderColor(int color) {
        spin.setBorderColor(color);
    }

    public void setSpinWheelTextColor(int color) {
        spin.setPieTextColor(color);
    }

    public void setData(List<SpinItem> data) {
        spin.setData(data);
    }

    public void setRound(int numberOfRound) {
        spin.setRound(numberOfRound);
    }

    public void setPredeterminedNumber(int fixedNumber) {
        spin.setPredeterminedNumber(fixedNumber);
    }

    public void startSpinWheelWithTargetIndex(int index) {
        spin.rotateTo(index);
    }
    
    public void startSpinWheelWithRandomTarget() {
        Random r = new Random();
        spin.rotateTo(r.nextInt(spin.getSpinItemListSize() - 1));
    }
}

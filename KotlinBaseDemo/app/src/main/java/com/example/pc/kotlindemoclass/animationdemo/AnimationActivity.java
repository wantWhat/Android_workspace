package com.example.pc.kotlindemoclass.animationdemo;

import android.animation.FloatEvaluator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.example.pc.kotlindemoclass.R;

import java.util.Arrays;

public class AnimationActivity extends AppCompatActivity {
    private static final String TAG = "AnimationActivity";
    private Button mBtn;
    private ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mBtn = findViewById(R.id.animation_btn);
        layout = findViewById(R.id.content);
        layoutTranslater();
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tweenAnimation();
                //ValueAnimation();
                //objectAnimation();
                //valueProperty();
                TextView tv = new TextView(AnimationActivity.this);
                tv.setWidth(100);
                tv.setHeight(100);
                tv.setText("add view");
                tv.setTextSize(20);
                tv.setTextColor(Color.GREEN);
                tv.setBackground(getDrawable(R.drawable.ic_home_black_24dp));
                layout.addView(tv);
            }
        });

    }

    private void tweenAnimation() {
        TranslateAnimation animation = new TranslateAnimation(0,0,180,180);
        animation.setDuration(1000);
        animation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                Log.i(TAG,"v==" +v);
                return 0;
            }
        });
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(TAG, "onAnimationRepeat" + animation.getRepeatCount());
            }
        });
        animation.setRepeatCount(10);
        mBtn.setAnimation(animation);
        mBtn.startAnimation(animation);
    }

    /**
     * ValueAnimation 不能直接修改对象的属性，需要手动调用set/get
     * */
    private void ValueAnimation() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setObjectValues(new float[2]);
        valueAnimator.setTarget(mBtn);
        valueAnimator.setEvaluator(new TypeEvaluator<float[]>() {
            @Override
            public float[] evaluate(float v, float[] o, float[] t1) {
                float[] temp = new float[2];
                temp[0] = v * 2;
                temp[1] = v /2;
                return temp;
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float[] temp = (float[]) valueAnimator.getAnimatedValue();
                Log.i(TAG, "valueAnimator value==" + Arrays.toString(temp));
                mBtn.setWidth((int) temp[0] );
                mBtn.setHeight((int)temp[1]);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    private void objectAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBtn, "translationX",500f,100f);
        animator.setDuration(2000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.i(TAG,"valueAnimator==" + valueAnimator.getAnimatedValue());
            }
        });

    }

    //ViewPropertyAnimator
    private void valueProperty() {
        //移动到（100，100） 位置,绝对位置
        mBtn.animate().x(100).y(100).start();
        //by 相对位置
        mBtn.animate().translationXBy(200);
    }

    //add /remove view 时的动画
    private void layoutTranslater() {
        LayoutTransition lt = new LayoutTransition();
        ObjectAnimator anim = ObjectAnimator.ofFloat(layout, "scaleX", 0f, 1f);
        lt.setAnimator(LayoutTransition.APPEARING, anim);
        layout.setLayoutTransition(lt);
    }
}

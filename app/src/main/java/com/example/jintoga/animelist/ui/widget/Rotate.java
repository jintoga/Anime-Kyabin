package com.example.jintoga.animelist.ui.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Keep;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * A {@link Transition} which animates the rotation of a {@link View}.
 */
@SuppressLint("NewApi")
public class Rotate extends Transition {

    private static final String PROP_ROTATION = "iosched:rotate:rotation";
    private static final String[] TRANSITION_PROPERTIES = { PROP_ROTATION };

    @Keep
    public Rotate() {
        super();
    }

    @Keep
    public Rotate(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public String[] getTransitionProperties() {
        return TRANSITION_PROPERTIES;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
                                   TransitionValues endValues) {
        if (startValues == null || endValues == null)  return null;

        float startRotation = (float) startValues.values.get(PROP_ROTATION);
        float endRotation = (float) endValues.values.get(PROP_ROTATION);
        if (startRotation == endRotation) return null;

        View view = endValues.view;
        // ensure the pivot is set
        view.setPivotX(view.getWidth() / 2f);
        view.setPivotY(view.getHeight() / 2f);
        return ObjectAnimator.ofFloat(endValues.view, View.ROTATION, startRotation, endRotation);
    }

    private void captureValues(TransitionValues transitionValues) {
        final View view = transitionValues.view;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) return;
        transitionValues.values.put(PROP_ROTATION, view.getRotation());
    }
}
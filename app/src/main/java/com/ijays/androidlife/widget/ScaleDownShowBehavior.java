package com.ijays.androidlife.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.ijays.androidlife.utils.AnimationUtil;

/**
 * Created by ijaysdev on 16/7/21.
 */
public class ScaleDownShowBehavior extends FloatingActionButton.Behavior {

    private boolean isTakingAnimation = false;//记录退出动画是否正在执行

    private OnStateChangedListener mOnStateChangedListener;

    public interface OnStateChangedListener {
        void onChanged(boolean isShow);
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        this.mOnStateChangedListener = listener;
    }

    public ScaleDownShowBehavior(Context context, AttributeSet attrs) {
        super();
    }

    private ViewPropertyAnimatorListener mViewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view) {
            isTakingAnimation = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isTakingAnimation = false;
            view.setVisibility(View.INVISIBLE);

        }

        @Override
        public void onAnimationCancel(View view) {
            isTakingAnimation = false;
        }
    };

    /**
     * Called when a descendant of the CoordinatorLayout attempts to initiate a nested scroll.
     * <p>
     * <p>Any Behavior associated with any direct child of the CoordinatorLayout may respond
     * to this event and return true to indicate that the CoordinatorLayout should act as
     * a nested scrolling parent for this scroll. Only Behaviors that return true from
     * this method will receive subsequent nested scroll events.</p>
     *
     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
     *                          associated with
     * @param child             the child view of the CoordinatorLayout this Behavior is associated with
     * @param directTargetChild the child view of the CoordinatorLayout that either is or
     *                          contains the target of the nested scroll operation
     * @param target            the descendant view of the CoordinatorLayout initiating the nested scroll
     * @param nestedScrollAxes  the axes that this nested scroll applies to. See
     *                          {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
     *                          {@link ViewCompat#SCROLL_AXIS_VERTICAL}
     * @return true if the Behavior wishes to accept this nested scroll
     * @see NestedScrollingParent#onStartNestedScroll(View, View, int)
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * Called when a nested scroll in progress has updated and the target has scrolled or
     * attempted to scroll.
     * <p>
     * <p>Any Behavior associated with the direct child of the CoordinatorLayout may elect
     * to accept the nested scroll as part of {@link #onStartNestedScroll}. Each Behavior
     * that returned true will receive subsequent nested scroll events for that nested scroll.
     * </p>
     * <p>
     * <p><code>onNestedScroll</code> is called each time the nested scroll is updated by the
     * nested scrolling child, with both consumed and unconsumed components of the scroll
     * supplied in pixels. <em>Each Behavior responding to the nested scroll will receive the
     * same values.</em>
     * </p>
     *
     * @param coordinatorLayout the CoordinatorLayout parent of the view this Behavior is
     *                          associated with
     * @param child             the child view of the CoordinatorLayout this Behavior is associated with
     * @param target            the descendant view of the CoordinatorLayout performing the nested scroll
     * @param dxConsumed        horizontal pixels consumed by the target's own scrolling operation
     * @param dyConsumed        vertical pixels consumed by the target's own scrolling operation
     * @param dxUnconsumed      horizontal pixels not consumed by the target's own scrolling
     *                          operation, but requested by the user
     * @param dyUnconsumed      vertical pixels not consumed by the target's own scrolling operation,
     *                          but requested by the user
     * @see NestedScrollingParent#onNestedScroll(View, int, int, int, int)
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        if ((dyConsumed > 0 || dyUnconsumed > 0) && child.getVisibility() == View.VISIBLE && !isTakingAnimation) {
            //手指往上滑动
            //TODO design包升级到25.1.0后必须设置成INVISIBLE，否则无法再次调用此方法
            AnimationUtil.scaleHide(child, mViewPropertyAnimatorListener);
            if (mOnStateChangedListener != null) {
                mOnStateChangedListener.onChanged(false);
            }


        } else if ((dyConsumed < 0 || dyUnconsumed < 0) && child.getVisibility() != View.VISIBLE) {
            //手指往下滑动
            AnimationUtil.scaleShow(child, null);
            if (mOnStateChangedListener != null) {
                mOnStateChangedListener.onChanged(true);
            }

        }
    }

    public static <V extends View> ScaleDownShowBehavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The View is not the child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (!(behavior instanceof ScaleDownShowBehavior)) {
            throw new IllegalArgumentException("The View is not associated with ScaleDownBevior");
        }
        return (ScaleDownShowBehavior) behavior;
    }
}


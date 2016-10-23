package uk.co.jatra.multistateanimatablebutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ReadyableImageButton extends ImageButton {

    private static final int[] READYING_STATE_SET = {
            R.attr.state_readying
    };

    private static final int[] OPERATING_STATE_SET = {
            R.attr.state_operating
    };

    private static final int[] COMPLETING_STATE_SET = {
            R.attr.state_completing
    };

    private static final int[] READY_STATE_SET = {
            R.attr.state_ready
    };
    private boolean readying;
    private boolean operating;
    private boolean completing;
    private boolean ready;
    private Animatable animatable;

    public ReadyableImageButton(Context context) {
        super(context);
    }

    public ReadyableImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReadyableImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReadyableImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 4);
        if (isReadying()) {
            mergeDrawableStates(drawableState, READYING_STATE_SET);
        }
        if (isOperating()) {
            mergeDrawableStates(drawableState, OPERATING_STATE_SET);
        }
        if (isCompleting()) {
            mergeDrawableStates(drawableState, COMPLETING_STATE_SET);
        }
        if (isReady()) {
            mergeDrawableStates(drawableState, READY_STATE_SET);
        }
        return drawableState;
    }

    public boolean isReadying() {
        return readying;
    }

    public void setReadying(boolean readying) {
        this.readying = readying;
        refreshDrawableState();
    }

    public boolean isOperating() {
        return operating;
    }

    public void setOperating(boolean operating) {
        this.operating = operating;
        refreshDrawableState();
    }

    public boolean isCompleting() {
        return completing;
    }

    public void setCompleting(boolean completing) {
        this.completing = completing;
        refreshDrawableState();
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
        refreshDrawableState();
    }

    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();
        startAnimation();
    }

    private void startAnimation() {
        if (animatable != null) {
            animatable.stop();
            animatable = null;
        }
        Drawable drawable = getDrawable();
        if (drawable instanceof StateListDrawable) {
            drawable = drawable.getCurrent();
        }
        if (drawable instanceof Animatable) {
            animatable = (Animatable)drawable;
            animatable.start();
        }
    }
}

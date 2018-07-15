package linkersoft.blackpanther.twirky;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import linkersoft.blackpanther.trump.TrumpyRecycler;


public class TwerkyListView extends TrumpyRecycler {

    public TwerkyListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public TwerkyListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if(!(adapter instanceof TwerkAdapter))throw new IllegalTwerkingException();
    }
    @Override
    protected void in(final Context context, AttributeSet attrs) {
        super.in(context, attrs);
        final float stretchScale;
        final float shrinkScale;

        TypedArray trump_attrs = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TwerkyListView, 0, 0);
        try {
            stretchScale = trump_attrs.getFloat(R.styleable.TwerkyListView_stretchScale, 0.0F);
            shrinkScale = trump_attrs.getFloat(R.styleable.TwerkyListView_shrinkScale, 0.0F);
            ShrinkStretchSpan = trump_attrs.getInt(R.styleable.TwerkyListView_ShrinkStretchSpan, 0);
            int twerkStyle =  trump_attrs.getInt(R.styleable.TwerkyListView_twerkStyle, 0);
            switch (twerkStyle){
                case 0:
                    upTwerk=true;
                    style=new TwerkStyle() {
                        @Override
                        public void OnTwerk(MotionEvent event) {
                            switch (event.getAction() & event.getActionMasked()){
                                case MotionEvent.ACTION_DOWN:
                                    iYy=(int)event.getY();
                                    ufYy =iYy-maxYgap;
                                    center = getCentrallyVisibleItemPosition();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    byMotion=true;
                                    cYy=(int)event.getY();
                                    if(!downTwerk && cYy<=iYy && cYy>= ufYy){
                                        upTwerk=true;
                                        float frac=(iYy-cYy)/(float)maxYgap;
                                        currTIME =Math.round(frac*DURATION);
                                        twerkUp.start();
                                        twerkUp.setCurrentPlayTime(currTIME);
                                        twerkUp.cancel();
                                    }break;
                                case MotionEvent.ACTION_UP:
                                    byMotion=false;
                                    if(upTwerk){
                                        twerkUpRev.setFloatValues(lastNimFrak,0F);
                                        twerkUpRev.start();
                                        twerkUpRev.setCurrentPlayTime(DURATION-currTIME);
                                    }break;
                                case MotionEvent.ACTION_CANCEL:
                                    byMotion=false;
                                    if(upTwerk){
                                        twerkUpRev.setFloatValues(lastNimFrak,0F);
                                        twerkUpRev.start();
                                        twerkUpRev.setCurrentPlayTime(DURATION-currTIME);
                                    }break;
                            }
                        }
                    };
                    break;
                case 1:
                    downTwerk=true;
                    style=new TwerkStyle() {
                        @Override
                        public void OnTwerk(MotionEvent event) {
                            switch (event.getAction() & event.getActionMasked()){
                                case MotionEvent.ACTION_DOWN:
                                    iYy=(int)event.getY();
                                    dfYy=iYy+maxYgap;
                                    center = getCentrallyVisibleItemPosition();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    byMotion=true;
                                    cYy=(int)event.getY();
                                    if(cYy>=iYy && cYy<= dfYy){
                                        downTwerk=true;
                                        float frac=(cYy-iYy)/(float)maxYgap;
                                        currTIME =Math.round(frac*DURATION);
                                        twerkDown.start();
                                        twerkDown.setCurrentPlayTime(currTIME);
                                        twerkDown.cancel();
                                    }break;
                                case MotionEvent.ACTION_UP:
                                    byMotion=false;
                                    if(downTwerk){
                                        twerkDownRev.setFloatValues(lastNimFrak,0F);
                                        twerkDownRev.start();
                                        twerkDownRev.setCurrentPlayTime(DURATION-currTIME);
                                    }break;
                                case MotionEvent.ACTION_CANCEL:
                                    byMotion=false;
                                    if(downTwerk){
                                        twerkDownRev.setFloatValues(lastNimFrak,0F);
                                        twerkDownRev.start();
                                        twerkDownRev.setCurrentPlayTime(DURATION-currTIME);
                                    }break;
                            }
                        }
                    };
                    break;
                case 2:
                    upTwerk=true;
                    downTwerk=true;
                    style=new TwerkStyle() {
                        @Override
                        public void OnTwerk(MotionEvent event) {
                            switch (event.getAction() & event.getActionMasked()){
                                case MotionEvent.ACTION_DOWN:
                                    iYy=(int)event.getY();
                                    ufYy =iYy-maxYgap;
                                    dfYy=iYy+maxYgap;
                                    center = getCentrallyVisibleItemPosition();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    byMotion=true;
                                    cYy=(int)event.getY();
                                    if(!downTwerk && cYy<=iYy && cYy>= ufYy){
                                        upTwerk=true;
                                        float frac=(iYy-cYy)/(float)maxYgap;
                                        currTIME =Math.round(frac*DURATION);
                                        twerkUp.start();
                                        twerkUp.setCurrentPlayTime(currTIME);
                                        twerkUp.cancel();
                                    }else if(!upTwerk && cYy>=iYy && cYy<= dfYy){
                                        downTwerk=true;
                                        float frac=(cYy-iYy)/(float)maxYgap;
                                        currTIME =Math.round(frac*DURATION);
                                        twerkDown.start();
                                        twerkDown.setCurrentPlayTime(currTIME);
                                        twerkDown.cancel();
                                    }break;
                                case MotionEvent.ACTION_UP:
                                    byMotion=false;
                                    if(upTwerk){
                                        twerkUpRev.setFloatValues(lastNimFrak,0F);
                                        twerkUpRev.start();
                                        twerkUpRev.setCurrentPlayTime(DURATION-currTIME);
                                    }else if(downTwerk){
                                        twerkDownRev.setFloatValues(lastNimFrak,0F);
                                        twerkDownRev.start();
                                        twerkDownRev.setCurrentPlayTime(DURATION-currTIME);
                                    }break;
                                case MotionEvent.ACTION_CANCEL:
                                    byMotion=false;
                                    if(upTwerk){
                                        twerkUpRev.setFloatValues(lastNimFrak,0F);
                                        twerkUpRev.start();
                                        twerkUpRev.setCurrentPlayTime(DURATION-currTIME);
                                    }else if(downTwerk){
                                        twerkDownRev.setFloatValues(lastNimFrak,0F);
                                        twerkDownRev.start();
                                        twerkDownRev.setCurrentPlayTime(DURATION-currTIME);
                                    }break;
                            }
                        }
                    };
                    break;
            }
        } finally {
            trump_attrs.recycle();
        }
        twerkpoleStart = new FastOutSlowInInterpolator();
        twerkpoleEnd = new OvershootInterpolator();

        post(new Runnable() {
            @Override
            public void run() {
                maxYgap=dp2px(250,context);
                rowHeight=getView(0).getLayoutParams().height;
                stretchGap=Math.round(stretchScale*rowHeight);
                shrinkGap=Math.round(shrinkScale*rowHeight);
                setTwerking(true);
            }
        });
    }

    private int dp2px(float Xdp, Context context) {
        return Math.round(Xdp * context.getResources().getDisplayMetrics().density);
    }
    private void preTwerk(){
        /*ShrinkStretchSpan => rows above/below the center affected by the stretch/contraction*/
        if(upTwerk){
            twerkUp =ValueAnimator.ofFloat(0F,1F);
            twerkUp.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                @Override
                public void onAnimationUpdate(ValueAnimator animation){
                    lastNimFrak= TwerkUpAnimae(animation);
                    requestLayout();
                }
            });
            twerkUp.setDuration(DURATION);
            twerkUp.setInterpolator(twerkpoleStart);

            twerkUpRev =ValueAnimator.ofFloat(1F,0F);
            twerkUpRev.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                @Override
                public void onAnimationUpdate(ValueAnimator animation){
                    TwerkUpAnimae(animation);
                    requestLayout();
                }
            });
            twerkUpRev.setDuration(DURATION);
            twerkUpRev.setInterpolator(twerkpoleEnd);
            twerkUpRev.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if(!byMotion)upTwerk=false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            upTwerk=false;
        }
        //============================================================
        if(downTwerk){
            twerkDown =ValueAnimator.ofFloat(0F,1F);
            twerkDown.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                @Override
                public void onAnimationUpdate(ValueAnimator animation){
                    lastNimFrak= TwerkDownAnimae(animation);
                    requestLayout();
                }
            });
            twerkDown.setDuration(DURATION);
            twerkDown.setInterpolator(twerkpoleStart);

            twerkDownRev =ValueAnimator.ofFloat(1F,0F);
            twerkDownRev.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                @Override
                public void onAnimationUpdate(ValueAnimator animation){
                    TwerkDownAnimae(animation);
                    requestLayout();
                }
            });
            twerkDownRev.setDuration(DURATION);
            twerkDownRev.setInterpolator(twerkpoleEnd);
            twerkDownRev.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if(!byMotion)downTwerk=false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            downTwerk=false;
        }
    }
    public void setTwerkInterpolator(Interpolator twerkpoleStart,Interpolator twerkpoleEnd){
        this.twerkpoleStart =twerkpoleStart;
        this.twerkpoleEnd =twerkpoleEnd;
    }
    public void setTwerking(boolean twerking){
        if(!twerking){
            setOnTouchListener(null);
            return;
        }preTwerk();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Twerk(event);
                return false;
            }
        });
    }
    public void setTwerkingOffTime(int twerkOfftime){
        DURATION=twerkOfftime;
    }
    private float TwerkUpAnimae(ValueAnimator animation){
        float NimFrack=(float)animation.getAnimatedValue();
        float unitGap=stretchGap/(float)ShrinkStretchSpan;
            for (int expandIndx = center,stretchGapped=stretchGap; expandIndx < (center+ShrinkStretchSpan+1); expandIndx++){//center-down [expansion]
                View row=getView(expandIndx);
                if (row!=null){
                    ViewGroup.LayoutParams vLp=row.getLayoutParams();
                    float start=rowHeight;
                    float end= rowHeight+stretchGapped;
                    vLp.height=(int)((1-NimFrack)*start+ NimFrack*end);
                    stretchGapped-=unitGap;
                }
            }for (int contractIndx = center-1,shrinkGapped=shrinkGap; contractIndx >(center-ShrinkStretchSpan-2) ; contractIndx--) {//center-up [contraction]
                View row=getView(contractIndx);
                if (row!=null){
                    ViewGroup.LayoutParams vLp=row.getLayoutParams();
                    float start=rowHeight;
                    float end= rowHeight-shrinkGapped;
                    vLp.height=(int)((1-NimFrack)*start+ NimFrack*end);
                    shrinkGapped+=unitGap;
                }
            }return NimFrack;
    }
    private float TwerkDownAnimae(ValueAnimator animation){
        float NimFrack=(float)animation.getAnimatedValue();
        float unitGap=stretchGap/(float)ShrinkStretchSpan;
            for (int expandIndx = center,shrinkGapped=shrinkGap; expandIndx < (center+ShrinkStretchSpan+1); expandIndx++){//center-down [expansion]
                View row=getView(expandIndx);
                if (row!=null){
                    ViewGroup.LayoutParams vLp=row.getLayoutParams();
                    float start=rowHeight;
                    float end= rowHeight-shrinkGapped;
                    vLp.height=(int)((1-NimFrack)*start+ NimFrack*end);
                    shrinkGapped+=unitGap;
                }
            }for (int contractIndx = center-1,stretchGapped=stretchGap; contractIndx >(center-ShrinkStretchSpan-2) ; contractIndx--) {//center-up [contraction]
                View row=getView(contractIndx);
                if (row!=null){
                    ViewGroup.LayoutParams vLp=row.getLayoutParams();
                    float start=rowHeight;
                    float end= rowHeight+stretchGapped;
                    vLp.height=(int)((1-NimFrack)*start+ NimFrack*end);
                    stretchGapped-=unitGap;
                }
            }return NimFrack;
    }
    private void Twerk(MotionEvent event){
        style.OnTwerk(event);
    }

    private ValueAnimator twerkUpRev;
    private ValueAnimator twerkUp;
    private ValueAnimator twerkDown;
    private ValueAnimator twerkDownRev;
    private Interpolator twerkpoleStart;
    private Interpolator twerkpoleEnd;
    private static int rowHeight;
    private int DURATION=500;
    private int maxYgap;
    private int center;
    private int iYy;
    private int ufYy;
    private int dfYy;
    private int currTIME;
    private int cYy;
    private int ShrinkStretchSpan;
    private int stretchGap;
    private int shrinkGap;
    private float lastNimFrak;
    private boolean upTwerk;
    private boolean downTwerk;
    private boolean byMotion;
    private TwerkStyle style;

    interface TwerkStyle{
        public void OnTwerk(MotionEvent event);
    }

    private class IllegalTwerkingException extends IllegalStateException{
        public IllegalTwerkingException(){
            super("Illegal Twerking!!! Adapter must extend TwerkAdapter! let's make Twerking gr8 again(*_*)");
        }
    }
    public static class TwerkAdapter extends RecyclerView.Adapter<TwerkAdapter.TwerkHolder>{
        @Override
        public int getItemCount(){
            return 0;
        }
        @Override
        public TwerkHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
           return null;
        }
        @Override
        public void onBindViewHolder(TwerkHolder twerkReceiver, int position){

        }

//        @Override
//        public void onViewAttachedToWindow(TwerkHolder holder) {
//            super.onViewAttachedToWindow(twervae);
//
//        }

        @Override
        public void onViewDetachedFromWindow(TwerkHolder holder) {
            super.onViewDetachedFromWindow(holder);
            if(rowHeight!=0)holder.v.getLayoutParams().height=rowHeight;
        }

        public class TwerkHolder extends  RecyclerView.ViewHolder {
            View v;
            public TwerkHolder(View v){
                super(v);
                this.v =v;
            }
        }
    }

    @Override
    public String toString() {
        return "@LiNKeR(>_<)~"+super.toString();
    }
}

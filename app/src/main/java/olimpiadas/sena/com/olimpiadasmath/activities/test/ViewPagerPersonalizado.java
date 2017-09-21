package olimpiadas.sena.com.olimpiadasmath.activities.test;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;

/**
 * Created by rich4 on 28/06/2017.
 */

public class ViewPagerPersonalizado extends ViewPager {


    private CardPagerAdapter mCardAdapter;
    private GestureDetectorCompat detector;
    private boolean enabled;

    public ViewPagerPersonalizado(Context context) {
        super(context);

    }

    public ViewPagerPersonalizado(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;

        detector = new GestureDetectorCompat(context,new OnSwipeListener(){

            @Override
            public boolean onSwipe(Direction direction) {

                // Possible implementation
                int pos = getCurrentItem();

                //if(direction == Direction.right) {
                    //mCardAdapter.getMoveTestListener().moveClick(pos==0?0:pos-1);
                    //   return true;
                //}
                //else
                //if(direction == Direction.left) {
                //    int sizeCard = mCardAdapter.getCount();
                //    mCardAdapter.getMoveTestListener().moveClick(pos== sizeCard-1? sizeCard-1:pos+1);
                //    return true;
                //}
                return super.onSwipe(direction);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        if(AppControl.getInstance().onPractice && this.enabled){
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        if(AppControl.getInstance().onPractice && enabled){
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setCardAdapter(CardPagerAdapter mCardAdapter) {
        this.mCardAdapter = mCardAdapter;
    }
}

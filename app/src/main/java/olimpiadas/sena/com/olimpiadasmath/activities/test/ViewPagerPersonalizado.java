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

    public ViewPagerPersonalizado(Context context) {
        super(context);
    }

    public ViewPagerPersonalizado(Context context, AttributeSet attrs) {
        super(context, attrs);
        detector = new GestureDetectorCompat(context,new OnSwipeListener(){
            @Override
            public boolean onSwipe(Direction direction) {

                // Possible implementation
                if(direction == Direction.right) {
                    mCardAdapter.getMoveTestListener().moveClick(getCurrentItem()==0?0:getCurrentItem()-1);
                    return true;
                }
                else if(direction == Direction.left) {
                    mCardAdapter.getMoveTestListener().moveClick(getCurrentItem()==mCardAdapter.getCount()-1?mCardAdapter.getCount()-1:getCurrentItem()+1);
                    return true;
                }

                return super.onSwipe(direction);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        if(AppControl.getInstance().onPractice){
            return super.onTouchEvent(ev);

        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        if(AppControl.getInstance().onPractice){
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }


    public void setCardAdapter(CardPagerAdapter mCardAdapter) {
        this.mCardAdapter = mCardAdapter;
    }
}

package olimpiadas.sena.com.olimpiadasmath.librerias;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class CircleMenu extends View {
    private static final int STATUS_MENU_OPEN = 1;
    private static final int STATUS_MENU_OPENED = 2;
    private static final int STATUS_MENU_CLOSE = 4;
    private static final int STATUS_MENU_CLOSE_CLEAR = 8;
    private static final int STATUS_MENU_CLOSED = 16;
    private static final int STATUS_MENU_CANCEL = 32;
    private static final int MAX_SUBMENU_NUM = 8;
    private final int shadowRadius;
    private int partSize;
    private int iconSize;
    private float circleMenuRadius;
    private int itemNum;
    private float itemMenuRadius;
    private float fraction;
    private float rFraction;
    private float pathLength;
    private int mainMenuColor;
    private Drawable openMenuIcon;
    private Drawable closeMenuIcon;
    private List<Integer> subMenuColorList;
    private List<Drawable> subMenuDrawableList;
    private List<RectF> menuRectFList;
    private int centerX;
    private int centerY;
    private int clickIndex;
    private int rotateAngle;
    private int itemIconSize;
    private int pressedColor;
    private int status;
    private boolean pressed;
    private Paint oPaint;
    private Paint cPaint;
    private Paint sPaint;
    private PathMeasure pathMeasure;
    private Path path;
    private Path dstPath;
    private OnMenuSelectedListener onMenuSelectedListener;
    private OnMenuStatusChangeListener onMenuStatusChangeListener;
    private GifImageView gifMenu;
    private CircleMenu circleMenuito;

    public CircleMenu(Context context) {
        this(context, (AttributeSet)null);
    }

    public CircleMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.shadowRadius = 5;
        this.status = 16;
        this.init();
    }

    private void init() {
        this.initTool();
        this.mainMenuColor = Color.parseColor("#CDCDCD");
        this.subMenuColorList = new ArrayList();
        this.subMenuDrawableList = new ArrayList();
        this.menuRectFList = new ArrayList();
    }

    private void initTool() {
        this.oPaint = new Paint(1);
        this.oPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.cPaint = new Paint(1);
        this.cPaint.setStyle(Paint.Style.STROKE);
        this.cPaint.setStrokeCap(Paint.Cap.ROUND);
        this.sPaint = new Paint(1);
        this.sPaint.setStyle(Paint.Style.FILL);
        this.path = new Path();
        this.dstPath = new Path();
        this.pathMeasure = new PathMeasure();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthSize = width;
        int measureHeightSize = height;
        if(widthMode == -2147483648) {
            measureWidthSize = this.dip2px(20.0F) * 10;
        }

        if(heightMode == -2147483648) {
            measureHeightSize = this.dip2px(20.0F) * 10;
        }

        this.setMeasuredDimension(measureWidthSize, measureHeightSize);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int minSize = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        this.partSize = minSize / 10;
        this.iconSize = this.partSize;
        this.circleMenuRadius = (float)(this.partSize * 3);
        this.centerX = this.getMeasuredWidth() / 2;
        this.centerY = this.getMeasuredHeight() / 2;
        this.resetMainDrawableBounds();
        this.path.addCircle((float)this.centerX, (float)this.centerY, this.circleMenuRadius, Path.Direction.CW);
        this.pathMeasure.setPath(this.path, true);
        this.pathLength = this.pathMeasure.getLength();
        RectF mainMenuRectF = new RectF((float)(this.centerX - this.partSize), (float)(this.centerY - this.partSize), (float)(this.centerX + this.partSize), (float)(this.centerY + this.partSize));
        this.menuRectFList.add(mainMenuRectF);
    }

    protected void onDraw(Canvas canvas) {
        switch(this.status) {
            case 1:
                this.drawMainMenu(canvas);
                this.drawSubMenu(canvas);
                break;
            case 2:
                this.drawMainMenu(canvas);
                this.drawSubMenu(canvas);
                break;
            case 4:
                this.drawMainMenu(canvas);
                this.drawSubMenu(canvas);
                this.drawCircleMenu(canvas);
                break;
            case 8:
                this.drawMainMenu(canvas);
                this.drawCircleMenu(canvas);
                break;
            case 16:
                this.drawMainMenu(canvas);
                break;
            case 32:
                this.drawMainMenu(canvas);
                this.drawSubMenu(canvas);
        }

    }

    private void drawCircleMenu(Canvas canvas) {
        if(this.status == 4) {
            this.drawCirclePath(canvas);
            this.drawCircleIcon(canvas);
        } else {
            this.cPaint.setStrokeWidth((float)(this.partSize * 2) + (float)this.partSize * 0.5F * this.fraction);
            this.cPaint.setColor(this.calcAlphaColor(this.getClickMenuColor(), true));
            canvas.drawCircle((float)this.centerX, (float)this.centerY, this.circleMenuRadius + (float)this.partSize * 0.5F * this.fraction, this.cPaint);
        }

    }

    private int getClickMenuColor() {
        return this.clickIndex == 0?this.mainMenuColor:((Integer)this.subMenuColorList.get(this.clickIndex - 1)).intValue();
    }

    private void drawCircleIcon(Canvas canvas) {
        canvas.save();
        Drawable selDrawable = (Drawable)this.subMenuDrawableList.get(this.clickIndex - 1);
        if(selDrawable != null) {
            int startAngle = (this.clickIndex - 1) * (360 / this.itemNum);
            int endAngle = 360 + startAngle;
            int itemX = (int)((double)this.centerX + Math.sin(Math.toRadians((double)((float)(endAngle - startAngle) * this.fraction + (float)startAngle))) * (double)this.circleMenuRadius);
            int itemY = (int)((double)this.centerY - Math.cos(Math.toRadians((double)((float)(endAngle - startAngle) * this.fraction + (float)startAngle))) * (double)this.circleMenuRadius);
            canvas.rotate(360.0F * this.fraction, (float)itemX, (float)itemY);
            selDrawable.setBounds(itemX - this.iconSize / 2, itemY - this.iconSize / 2, itemX + this.iconSize / 2, itemY + this.iconSize / 2);
            selDrawable.draw(canvas);
            canvas.restore();
        }
    }

    private void drawCirclePath(Canvas canvas) {
        canvas.save();
        canvas.rotate((float)this.rotateAngle, (float)this.centerX, (float)this.centerY);
        this.dstPath.reset();
        this.dstPath.lineTo(0.0F, 0.0F);
        this.pathMeasure.getSegment(0.0F, this.pathLength * this.fraction, this.dstPath, true);
        this.cPaint.setStrokeWidth((float)(this.partSize * 2));
        this.cPaint.setColor(this.getClickMenuColor());
        canvas.drawPath(this.dstPath, this.cPaint);
        canvas.restore();
    }

    private void drawSubMenu(Canvas canvas) {
        float offsetRadius = 1.5F;

        for(int i = 0; i < this.itemNum; ++i) {
            int angle = i * (360 / this.itemNum);
            int itemX;
            int itemY;
            if(this.status == 1) {
                itemX = (int)((double)this.centerX + Math.sin(Math.toRadians((double)angle)) * (double)(this.circleMenuRadius - (1.0F - this.fraction) * (float)this.partSize * 1.5F));
                itemY = (int)((double)this.centerY - Math.cos(Math.toRadians((double)angle)) * (double)(this.circleMenuRadius - (1.0F - this.fraction) * (float)this.partSize * 1.5F));
                this.oPaint.setColor(this.calcAlphaColor(((Integer)this.subMenuColorList.get(i)).intValue(), false));
                this.sPaint.setColor(this.calcAlphaColor(((Integer)this.subMenuColorList.get(i)).intValue(), false));
            } else if(this.status == 32) {
                itemX = (int)((double)this.centerX + Math.sin(Math.toRadians((double)angle)) * (double)(this.circleMenuRadius - this.fraction * (float)this.partSize * 1.5F));
                itemY = (int)((double)this.centerY - Math.cos(Math.toRadians((double)angle)) * (double)(this.circleMenuRadius - this.fraction * (float)this.partSize * 1.5F));
                this.oPaint.setColor(this.calcAlphaColor(((Integer)this.subMenuColorList.get(i)).intValue(), true));
                this.sPaint.setColor(this.calcAlphaColor(((Integer)this.subMenuColorList.get(i)).intValue(), true));
            } else {
                itemX = (int)((double)this.centerX + Math.sin(Math.toRadians((double)angle)) * (double)this.circleMenuRadius);
                itemY = (int)((double)this.centerY - Math.cos(Math.toRadians((double)angle)) * (double)this.circleMenuRadius);
                this.oPaint.setColor(((Integer)this.subMenuColorList.get(i)).intValue());
                this.sPaint.setColor(((Integer)this.subMenuColorList.get(i)).intValue());
            }

            if(this.pressed && this.clickIndex - 1 == i) {
                this.oPaint.setColor(this.pressedColor);
            }

            this.drawMenuShadow(canvas, itemX, itemY, this.itemMenuRadius);
            canvas.drawCircle((float)itemX, (float)itemY, this.itemMenuRadius, this.oPaint);
            this.drawSubMenuIcon(canvas, itemX, itemY, i);
            RectF menuRectF = new RectF((float)(itemX - this.partSize), (float)(itemY - this.partSize), (float)(itemX + this.partSize), (float)(itemY + this.partSize));
            if(this.menuRectFList.size() - 1 > i) {
                this.menuRectFList.remove(i + 1);
            }

            this.menuRectFList.add(i + 1, menuRectF);
        }

    }

    private void drawSubMenuIcon(Canvas canvas, int centerX, int centerY, int index) {
        int diff;
        if(this.status != 1 && this.status != 32) {
            diff = this.iconSize;
        } else {
            diff = this.itemIconSize;
        }

        this.resetBoundsAndDrawIcon(canvas, (Drawable)this.subMenuDrawableList.get(index), centerX , centerY, diff);
    }

    private void resetBoundsAndDrawIcon(Canvas canvas, Drawable drawable, int centerX, int centerY, int diff) {
        if(drawable != null) {
            drawable.setBounds(centerX - diff, centerY - diff, centerX + diff, centerY + diff);
            drawable.draw(canvas);
        }
    }

    private void drawMainMenu(Canvas canvas) {
        float centerMenuRadius;
        float realFraction;
        if(this.status == 4) {
            realFraction = 1.0F - this.fraction * 2.0F == 0.0F?0.0F:1.0F - this.fraction * 2.0F;
            centerMenuRadius = (float)this.partSize * realFraction;
        } else if(this.status == 8) {
            realFraction = this.fraction * 4.0F >= 1.0F?1.0F:this.fraction * 4.0F;
            centerMenuRadius = (float)this.partSize * realFraction;
        } else if(this.status != 16 && this.status != 32) {
            centerMenuRadius = (float)this.partSize;
        } else {
            centerMenuRadius = (float)this.partSize;
        }

        if(this.status != 1 && this.status != 2 && this.status != 4) {
            if(this.pressed && this.clickIndex == 0) {
                this.oPaint.setColor(this.pressedColor);
            } else {
                this.oPaint.setColor(this.mainMenuColor);
                this.sPaint.setColor(this.mainMenuColor);
            }
        } else {
            this.oPaint.setColor(this.calcPressedEffectColor(0, 0.5F));
        }

        this.drawMenuShadow(canvas, this.centerX, this.centerY, centerMenuRadius);
        canvas.drawCircle((float)this.centerX, (float)this.centerY, centerMenuRadius, this.oPaint);
        this.drawMainMenuIcon(canvas);
    }

    private void drawMainMenuIcon(Canvas canvas) {
        canvas.save();
        switch(this.status) {
            case 1:
                canvas.rotate(45.0F * (this.fraction - 1.0F), (float)this.centerX, (float)this.centerY);
                this.resetBoundsAndDrawIcon(canvas, this.closeMenuIcon, this.centerX, this.centerY, this.iconSize );
                break;
            case 2:
                this.resetBoundsAndDrawIcon(canvas, this.closeMenuIcon, this.centerX, this.centerY, this.iconSize );
                break;
            case 4:
                this.resetBoundsAndDrawIcon(canvas, this.closeMenuIcon, this.centerX, this.centerY, this.itemIconSize );
                break;
            case 8:
                canvas.rotate(90.0F * (this.rFraction - 1.0F), (float)this.centerX, (float)this.centerY);
                this.resetBoundsAndDrawIcon(canvas, this.openMenuIcon, this.centerX, this.centerY, this.itemIconSize );
                break;
            case 16:
                if(this.openMenuIcon != null) {
                    this.openMenuIcon.draw(canvas);
                }
                break;
            case 32:
                canvas.rotate(-45.0F * this.fraction, (float)this.centerX, (float)this.centerY);
                if(this.closeMenuIcon != null) {
                    this.closeMenuIcon.draw(canvas);
                }
        }

        canvas.restore();
    }

    private void drawMenuShadow(Canvas canvas, int centerX, int centerY, float radius) {
        if(radius + 5.0F > 0.0F) {
            this.sPaint.setShader(new RadialGradient((float)centerX, (float)centerY, radius + 5.0F, -16777216, 0, Shader.TileMode.CLAMP));
            canvas.drawCircle((float)centerX, (float)centerY, radius + 5.0F, this.sPaint);
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        if(this.status != 4 && this.status != 8) {
            int index = this.clickWhichRectF(event.getX(), event.getY());
            switch(event.getAction()) {
                case 0:
                    this.pressed = true;
                    if(index != -1) {
                        this.clickIndex = index;
                        this.updatePressEffect(index, this.pressed);
                    }
                    break;
                case 1:
                    this.pressed = false;
                    if(index != -1) {
                        this.clickIndex = index;
                        this.updatePressEffect(index, this.pressed);
                    }

                    if(index == 0) {
                        if(this.status == 16) {
                            this.status = 1;
                            this.startOpenMenuAnima();
                        } else if(this.status == 2) {
                            this.status = 32;
                            this.startCancelMenuAnima();
                        }
                    } else if(this.status == 2 && index != -1) {
                        this.status = 4;
                        if(this.onMenuSelectedListener != null) {
                            this.onMenuSelectedListener.onMenuSelected(index - 1);
                        }

                        this.rotateAngle = this.clickIndex * (360 / this.itemNum) - 360 / this.itemNum - 90;
                        this.startCloseMeunAnima();
                    }
                    break;
                case 2:
                    if(index == -1) {
                        this.pressed = false;
                        this.invalidate();
                    }
            }

            return true;
        } else {
            return true;
        }
    }

    private void updatePressEffect(int menuIndex, boolean press) {
        if(press) {
            this.pressedColor = this.calcPressedEffectColor(menuIndex, 0.15F);
        }

        this.invalidate();
    }

    private int calcPressedEffectColor(int menuIndex, float depth) {
        int color = menuIndex == 0?this.mainMenuColor:((Integer)this.subMenuColorList.get(menuIndex - 1)).intValue();
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 1.0F - depth;
        return Color.HSVToColor(hsv);
    }

    private int calcAlphaColor(int color, boolean reverse) {
        int alpha;
        if(reverse) {
            alpha = (int)(255.0F * (1.0F - this.fraction));
        } else {
            alpha = (int)(255.0F * this.fraction);
        }

        if(alpha >= 255) {
            alpha = 255;
        }

        if(alpha <= 0) {
            alpha = 0;
        }

        return ColorUtils.setAlphaComponent(color, alpha);
    }

    private void startOpenMenuAnima() {
        ValueAnimator openAnima = ValueAnimator.ofFloat(new float[]{1.0F, 100.0F});
        openAnima.setDuration(500L);
        openAnima.setInterpolator(new OvershootInterpolator());
        openAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleMenu.this.fraction = valueAnimator.getAnimatedFraction();
                CircleMenu.this.itemMenuRadius = CircleMenu.this.fraction * (float) CircleMenu.this.partSize;
                CircleMenu.this.itemIconSize = (int)(CircleMenu.this.fraction * (float)CircleMenu.this.iconSize);
                CircleMenu.this.invalidate();
            }
        });
        openAnima.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                CircleMenu.this.status = 2;
                if(CircleMenu.this.onMenuStatusChangeListener != null) {
                    CircleMenu.this.onMenuStatusChangeListener.onMenuOpened();
                }

            }
        });
        openAnima.start();
    }

    private void startCancelMenuAnima() {
        ValueAnimator cancelAnima = ValueAnimator.ofFloat(new float[]{1.0F, 100.0F});
        cancelAnima.setDuration(500L);
        cancelAnima.setInterpolator(new AnticipateInterpolator());
        cancelAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleMenu.this.fraction = valueAnimator.getAnimatedFraction();
                CircleMenu.this.itemMenuRadius = (1.0F - CircleMenu.this.fraction) * (float) CircleMenu.this.partSize;
                CircleMenu.this.itemIconSize = (int)((1.0F - CircleMenu.this.fraction) * (float) CircleMenu.this.iconSize);
                CircleMenu.this.invalidate();
            }
        });
        cancelAnima.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                CircleMenu.this.status = 16;
                if(CircleMenu.this.onMenuStatusChangeListener != null) {
                    CircleMenu.this.onMenuStatusChangeListener.onMenuClosed();
                }
                circleMenuito.setVisibility(View.GONE);
                gifMenu.setVisibility(View.VISIBLE);

            }
        });
        cancelAnima.start();
    }

    private void startCloseMeunAnima() {
        ValueAnimator aroundAnima = ValueAnimator.ofFloat(new float[]{1.0F, 100.0F});
        aroundAnima.setDuration(600L);
        aroundAnima.setInterpolator(new AccelerateDecelerateInterpolator());
        aroundAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleMenu.this.fraction = valueAnimator.getAnimatedFraction();
                float animaFraction = CircleMenu.this.fraction * 2.0F >= 1.0F?1.0F: CircleMenu.this.fraction * 2.0F;
                CircleMenu.this.itemIconSize = (int)((1.0F - animaFraction) * (float) CircleMenu.this.iconSize);
               CircleMenu.this.invalidate();
            }
        });
        aroundAnima.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                CircleMenu.this.status = 8;
            }
        });
        ValueAnimator spreadAnima = ValueAnimator.ofFloat(new float[]{1.0F, 100.0F});
        spreadAnima.setInterpolator(new LinearInterpolator());
        spreadAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleMenu.this.fraction = valueAnimator.getAnimatedFraction();
            }
        });
        ValueAnimator rotateAnima = ValueAnimator.ofFloat(new float[]{1.0F, 100.0F});
        rotateAnima.setInterpolator(new OvershootInterpolator());
        rotateAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleMenu.this.rFraction = valueAnimator.getAnimatedFraction();
                CircleMenu.this.itemIconSize = (int)(CircleMenu.this.rFraction * (float) CircleMenu.this.iconSize);
                CircleMenu.this.invalidate();
            }
        });
        AnimatorSet closeAnimaSet = new AnimatorSet();
        closeAnimaSet.setDuration(500L);
        closeAnimaSet.play(spreadAnima).with(rotateAnima);
        closeAnimaSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                CircleMenu.this.status = 16;
                if(CircleMenu.this.onMenuStatusChangeListener != null) {
                    CircleMenu.this.onMenuStatusChangeListener.onMenuClosed();
                }

            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(aroundAnima).before(closeAnimaSet);
        animatorSet.start();
    }

    private int clickWhichRectF(float x, float y) {
        int which = -1;
        Iterator var4 = this.menuRectFList.iterator();

        while(var4.hasNext()) {
            RectF rectF = (RectF)var4.next();
            if(rectF.contains(x, y)) {
                which = this.menuRectFList.indexOf(rectF);
                break;
            }
        }

        return which;
    }

    private Drawable convertDrawable(int iconRes) {
        return this.getResources().getDrawable(iconRes);
    }

    private Drawable convertBitmap(Bitmap bitmap) {
        return new BitmapDrawable(this.getResources(), bitmap);
    }

    private void resetMainDrawableBounds() {
        this.openMenuIcon.setBounds(this.centerX - this.iconSize *3, this.centerY - this.iconSize *3, this.centerX + this.iconSize*3, this.centerY + this.iconSize*3 );
        this.closeMenuIcon.setBounds(this.centerX - this.iconSize *2, this.centerY - this.iconSize*2 , this.centerX + this.iconSize *2, this.centerY + this.iconSize*2 );
    }

    public CircleMenu setMainMenu(int mainMenuColor, int openMenuRes, int closeMenuRes) {
        this.openMenuIcon = this.convertDrawable(openMenuRes);
        this.closeMenuIcon = this.convertDrawable(closeMenuRes);
        this.mainMenuColor = mainMenuColor;
        return this;
    }

    public CircleMenu setMainMenu(int mainMenuColor, Bitmap openMenuBitmap, Bitmap closeMenuBitmap) {
        this.openMenuIcon = this.convertBitmap(openMenuBitmap);
        this.closeMenuIcon = this.convertBitmap(closeMenuBitmap);
        this.mainMenuColor = mainMenuColor;
        return this;
    }

    public CircleMenu setMainMenu(int mainMenuColor, Drawable openMenuDrawable, Drawable closeMenuDrawable) {
        this.openMenuIcon = openMenuDrawable;
        this.closeMenuIcon = closeMenuDrawable;
        this.mainMenuColor = mainMenuColor;
        return this;
    }

    public CircleMenu addSubMenu(int menuColor, int menuRes) {
        if(this.subMenuColorList.size() < 8 && this.subMenuDrawableList.size() < 8) {
            this.subMenuColorList.add(Integer.valueOf(menuColor));
            this.subMenuDrawableList.add(this.convertDrawable(menuRes));
            this.itemNum = Math.min(this.subMenuColorList.size(), this.subMenuDrawableList.size());
        }

        return this;
    }

    public CircleMenu addSubMenu(int menuColor, Bitmap menuBitmap) {
        if(this.subMenuColorList.size() < 8 && this.subMenuDrawableList.size() < 8) {
            this.subMenuColorList.add(Integer.valueOf(menuColor));
            this.subMenuDrawableList.add(this.convertBitmap(menuBitmap));
            this.itemNum = Math.min(this.subMenuColorList.size(), this.subMenuDrawableList.size());
        }

        return this;
    }

    public CircleMenu addSubMenu(int menuColor, Drawable menuDrawable) {
        if(this.subMenuColorList.size() < 8 && this.subMenuDrawableList.size() < 8) {
            this.subMenuColorList.add(Integer.valueOf(menuColor));
            this.subMenuDrawableList.add(menuDrawable);
            this.itemNum = Math.min(this.subMenuColorList.size(), this.subMenuDrawableList.size());
        }

        return this;
    }

    public void openMenu() {
        if(this.status == 16) {
            this.status = 1;
            this.startOpenMenuAnima();
        }

    }

    public void closeMenu() {
        if(this.status == 2) {
            this.status = 32;
            this.startCancelMenuAnima();
        }

    }

    public boolean isOpened() {
        return this.status == 2;
    }

    public CircleMenu setOnMenuSelectedListener(OnMenuSelectedListener listener) {
        this.onMenuSelectedListener = listener;
        return this;
    }

    public CircleMenu setOnMenuStatusChangeListener(OnMenuStatusChangeListener listener) {
        this.onMenuStatusChangeListener = listener;
        return this;
    }

    private int dip2px(float dpValue) {
        float scale = this.getContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public void setCloseAction(GifImageView gifMenu, CircleMenu circleMenu) {
        this.gifMenu = gifMenu;
        this.circleMenuito = circleMenu;
    }
}

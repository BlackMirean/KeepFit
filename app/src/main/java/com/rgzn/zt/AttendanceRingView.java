package com.rgzn.zt;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class AttendanceRingView extends View {

    private Paint backgroundPaint; // 圆环底色
    private Paint progressPaint;   // 圆环进度颜色
    private Paint textPaint;       // 文字画笔

    private int maxProgress = 100;
    private int progress = 50; // 默认进度为 50%

    public AttendanceRingView(Context context) {
        super(context);
        init();
    }

    public AttendanceRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AttendanceRingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(Color.parseColor("#FFFFFF"));
        backgroundPaint.setStyle(Paint.Style.STROKE);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(Color.parseColor("#000000"));
        progressPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.parseColor("#000000"));
        textPaint.setTextSize(40);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void setProgress(int progress) {
        if (progress >= 0 && progress <= maxProgress) {
            this.progress = progress;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height);
        int backgroundStrokeWidth = 10; // 背景圆环的宽度
        int progressStrokeWidth = 25;   // 进度圆环的宽度

        float centerX = width / 2f;
        float centerY = height / 2f;
        float backgroundRadius = (diameter - backgroundStrokeWidth) / 2f;
        float progressRadius = (diameter - progressStrokeWidth) / 2f;

        // 绘制底色圆环
        backgroundPaint.setStrokeWidth(backgroundStrokeWidth);
        canvas.drawCircle(centerX, centerY, backgroundRadius, backgroundPaint);

        // 绘制进度圆弧
        RectF rectF = new RectF(centerX - progressRadius, centerY - progressRadius, centerX + progressRadius, centerY + progressRadius);
        float sweepAngle = 360f * progress / maxProgress;
        progressPaint.setStrokeWidth(progressStrokeWidth);
        canvas.drawArc(rectF, -90, sweepAngle, false, progressPaint);

        // 绘制百分比文字
        String progressText = progress + "%";
        float textWidth = textPaint.measureText(progressText);
        float textX = centerX - textWidth / 2;
        float textY = centerY + textPaint.getTextSize() / 2;

        canvas.drawText(progressText, textX, textY, textPaint);


    }
}

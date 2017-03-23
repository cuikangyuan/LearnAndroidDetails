package com.cky.learnandroiddetails.SpannableStringLearn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.cky.learnandroiddetails.R;

import java.util.List;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
import static android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE;

/**
 * Created by cuikangyuan
 */

public class TagUtil {
    public static void setTag(String tag, TextView textView) {
        if (TextUtils.isEmpty(tag) || "无".equals(tag)) {
            return;
        }
        TextView tv_tag = (TextView) LayoutInflater.from(textView.getContext()).inflate(R.layout.view_tag, null, true);
        tv_tag.setText(tag);
        Bitmap drawingCache = convertViewToBitmap(tv_tag);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(drawingCache);
        bitmapDrawable.setBounds(0, 0, drawingCache.getWidth(), drawingCache.getHeight());
        SpannableString spannableString = new SpannableString(tag + textView.getText());
        MyIm imageSpan = new MyIm(bitmapDrawable);
        spannableString.setSpan(imageSpan, 0, tag.length(), SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    public static void setTags(List<String> tags, TextView textView) {

        TextView tv_tag = null;
        Bitmap drawingCache = null;
        BitmapDrawable bitmapDrawable = null;
        MyIm imageSpan = null;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String str = null;
        int lastIndex = 0;
        for (int i = 0; i < tags.size(); i++) {
            str = tags.get(i);
            if (!TextUtils.isEmpty(str)) {
                tv_tag = (TextView) LayoutInflater.from(textView.getContext()).inflate(R.layout.view_tag, null, true);
                tv_tag.setText(str);
                drawingCache = convertViewToBitmap(tv_tag);
                bitmapDrawable = new BitmapDrawable(drawingCache);
                bitmapDrawable.setBounds(0, 0, drawingCache.getWidth(), drawingCache.getHeight());
                imageSpan = new MyIm(bitmapDrawable);
                spannableStringBuilder.append(str);
                spannableStringBuilder.setSpan(imageSpan, lastIndex, lastIndex + str.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                lastIndex = str.length();
            }
        }
        spannableStringBuilder.append(textView.getText());
        textView.setText(spannableStringBuilder);
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 可以居中对齐的ImageSPan
     */
    public static class MyIm extends ImageSpan {
        public MyIm(Context arg0, int arg1) {
            super(arg0, arg1);
        }

        public MyIm(Drawable drawable) {
            super(drawable);
        }

        public int getSize(Paint paint, CharSequence text, int start, int end,
                           Paint.FontMetricsInt fm) {
            Drawable d = getDrawable();
            Rect rect = d.getBounds();
            if (fm != null) {
                Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
                int fontHeight = fmPaint.bottom - fmPaint.top;
                int drHeight = rect.bottom - rect.top;

                int top = drHeight / 2 - fontHeight / 4;
                int bottom = drHeight / 2 + fontHeight / 4;

                fm.ascent = -bottom;
                fm.top = -bottom;
                fm.bottom = top;
                fm.descent = top;
            }
            //15为padding
            return rect.right + 15;
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end,
                         float x, int top, int y, int bottom, Paint paint) {
            Drawable b = getDrawable();
            canvas.save();
            int transY = 0;
            transY = ((bottom - top) - b.getBounds().bottom) / 2 + top;
            canvas.translate(x, transY);
            b.draw(canvas);
            canvas.restore();
        }
    }
}

package com.example.simplegameengineforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PlayingField extends View {

    //количество ячеек
    static int sellsHoriz = 8;
    static int sellsVerti = 14;

//    размер игрового поля в пикселях
    private int xSize;
    private int ySize;

    Paint paintFrame = new Paint();  // рамка
    Paint paintFilling = new Paint();



    public PlayingField(Context context, @Nullable AttributeSet attrs) {
        super(context);
        paintFrame.setColor(Color.BLACK); // установим черным цвет
        paintFrame.setStyle(Paint.Style.FILL);
        paintFrame.setStrokeWidth(20);

        paintFilling.setColor(Color.RED); // установим черным цвет
        paintFilling.setStyle(Paint.Style.FILL);
        paintFilling.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        xSize = w;
        ySize = h;
        System.out.println(" ==== Minefield Wigth ====" + xSize );
        System.out.println(" ====  Minefield Higth ====" + ySize );

    }

    public  void onDraw(Canvas canvas){

        // рорисовываю внутреннии линии
        canvas.drawLine(xSize/3, 0, xSize/3, xSize, paintFilling);
        canvas.drawLine(xSize*2/3, 0, xSize*2/3, xSize, paintFilling);
        canvas.drawLine(0, xSize/3, xSize, xSize/3, paintFilling);
        canvas.drawLine(0, xSize*2/3, xSize, xSize*2/3, paintFilling);

        // рорисовываю оконтовку
        canvas.drawLine(0, 0, xSize, 0, paintFrame);
        canvas.drawLine(0, 0, 0, xSize, paintFrame);
        canvas.drawLine(xSize, xSize, xSize, 0, paintFrame);
        canvas.drawLine(xSize, xSize, 0, xSize, paintFrame);

        invalidate();
    }
}

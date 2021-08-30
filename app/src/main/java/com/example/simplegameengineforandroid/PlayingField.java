package com.example.simplegameengineforandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PlayingField extends View {

    //количество ячеек
    static int sellsHoriz = 8;
    static int sellsVerti = 8;
    static Cell[][] playingField = new Cell[sellsHoriz][sellsVerti];
    static int k;
    {
        // заполняем массив пустыми ячейками

        for (int  y = 0; y < sellsVerti; y++){
            for (int x = 0; x< sellsHoriz; x++){
                playingField[x][y] = new Cell(x,y);
            }
        }

        // для примера
        playingField[3][2].collor = 5;

        playingField[0][0].text = "x";


        playingField[1][2].picture = "b";

    };



//    размер игрового поля в пикселях
    private int xSize;
    private int ySize;


    private  int sellSize; // размер ячейки расчетный в пикселях.

    Paint paintFrame = new Paint();  // рамка
    Paint paintFilling = new Paint();


    // Для отрисовки картинки
    Bitmap bitmapX;
    Matrix matrix;
    Paint paintX;


    public PlayingField(Context context, @Nullable AttributeSet attrs) {
        super(context);
        paintFrame.setColor(Color.BLACK); // установим черным цвет
        paintFrame.setStyle(Paint.Style.FILL);
        paintFrame.setStrokeWidth(4);

        paintFilling.setColor(Color.RED); // установим красный цвет
        paintFilling.setStyle(Paint.Style.FILL);
        paintFilling.setStrokeWidth(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        xSize = w;
        ySize = h;
        System.out.println(" ==== Minefield Wigth ====" + xSize );
        System.out.println(" ====  Minefield Higth ====" + ySize );

    }

    public  void onDraw(Canvas canvas){

        // определяем размер ячейки
        int sizeX = xSize/sellsHoriz;  // sizeX* sellsVerti не должен превышать sizeY иначе не впишется  в поле
        int sizeY = ySize/sellsVerti;

        if (sizeX*sellsVerti > ySize){
            sellSize =sizeY;
        }else {
            sellSize = sizeX;
        }
        int corX = (xSize-sellSize*sellsHoriz)/2;
        System.out.println("------------sellSize-----------" + sellSize);


        //отрисовываем сетку в зависимости от  количества и размера ячеек (corX для смещение по центру).
        for (int i = 0; i<= sellsHoriz; i++){
            //вертикальные линии
            canvas.drawLine(sellSize*i+corX, 0, sellSize*i+corX, sellSize*sellsVerti, paintFilling);
        }
        for (int j = 0; j<= sellsVerti; j++){
            //горизонтальные линии
            canvas.drawLine(0+corX,sellSize*j,  sellSize*sellsHoriz+corX, sellSize*j, paintFilling);
        }



        //отрисовываем  двумерный массив обьектов.

        for (int  y = 0; y < sellsVerti; y++){
            for (int x = 0; x< sellsHoriz; x++){

                if (playingField[x][y].collor == 5){
                    Paint paintOr = new Paint();
                    paintOr.setColor(Color.RED); // установим черным цвет
                    paintOr.setStyle(Paint.Style.FILL);
                    paintOr.setStrokeWidth(sellSize-10);
                    canvas.drawPoint((x*sellSize)+(sellSize/2)+corX, (y*sellSize)+(sellSize/2)+corX, paintOr);
                }


                if (playingField[x][y].text =="x"){

                    Paint paintB = new Paint();
                    paintB.setColor(Color.BLUE); // установим черным цвет
                    paintB.setStyle(Paint.Style.FILL);

                    paintB.setTextSize(sellSize);
                    canvas.drawText("Х", (x*sellSize)+corX, (y*sellSize)+sellSize+corX, paintB); // так как текст рисуется с нижнего левого угла
                    canvas.drawText("О", ((x+1)*sellSize)+corX, (y*sellSize)+sellSize+corX, paintB); // так как текст рисуется с нижнего левого угла
                }


                if (playingField[x][y].picture =="b"){


                    this.onDrowX(x,y, corX);

                    canvas.drawBitmap(bitmapX, matrix, paintX);



                }




            }
        }


//        // рорисовываю внутреннии линии
//        canvas.drawLine(xSize/3, 0, xSize/3, xSize, paintFilling);
//        canvas.drawLine(xSize*2/3, 0, xSize*2/3, xSize, paintFilling);
//        canvas.drawLine(0, xSize/3, xSize, xSize/3, paintFilling);
//        canvas.drawLine(0, xSize*2/3, xSize, xSize*2/3, paintFilling);
//
//        // рорисовываю оконтовку
//        canvas.drawLine(0, 2, xSize, 2, paintFrame);
//        canvas.drawLine(0, 0, 0, xSize, paintFrame);
//        canvas.drawLine(xSize, xSize, xSize, 0, paintFrame);
//        canvas.drawLine(xSize, xSize, 0, xSize, paintFrame);

        invalidate();
    }

    public void onDrowX(int x, int y, int corX){
        paintX = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapX = BitmapFactory.decodeResource(getResources(), R.drawable.x);
        matrix = new Matrix();
        matrix.reset();
        matrix.setTranslate((x*sellSize)+corX, (y*sellSize)+corX);
        k = bitmapX.getWidth();
        System.out.println("------------K-----------" + k);

        matrix.preScale( (float)sellSize/k,  (float)sellSize/k);
    }
}

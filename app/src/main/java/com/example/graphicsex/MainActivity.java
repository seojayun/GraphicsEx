package com.example.graphicsex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //직접풀어보기 9-1실습

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));
        //4.내가만든 도화지 클래스를 세팅시킨다.(화면전체를 도화지로 쓴다)
    }
    private static class MyCanvas extends View {
          //1. 클래스 생성
        public MyCanvas(Context context) {
            //2.캔버스 생성
            super(context);
        }

        @Override
        //3.onDraw 메소드 생성(도화지는부모로부터 받음)
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint p = new Paint();
            p.setAntiAlias(true);

            p.setStrokeWidth(30);
            canvas.drawLine(30,30,300,30,p);
            p.setStrokeCap(Paint.Cap.ROUND);  //붓의 끝을 둥글게 만든다.
            canvas.drawLine(39,80,300,80,p);
            RectF rectF = new RectF();
            rectF.set(60,120,260,200);
            canvas.drawOval(rectF,p); //타원그리기
            rectF.set(60,170,160,270);
            canvas.drawArc(rectF,40,110,true,p); // 밑변 아크(원뿔형태)
            p.setColor(Color.BLUE);
            rectF.set(60,280,160,380);
            canvas.drawRect(rectF,p);  //사각형
            p.setColor(Color.argb(120,255,0,0));  // .argb 투명도및 색상에 관련된 부분   arpha 가 투명도 설정 부분 최대가 255
            rectF.set(90,310,190,410);//사각형을 겹치게 나둠(색상변경후)
            canvas.drawRect(rectF,p);












            p.setStrokeWidth(5);
            p.setColor(Color.GREEN);

            Path path1 = new Path();  // 변수선언
            path1.moveTo(10,600);
            path1.lineTo(60,650);
            path1.lineTo(110,700);
            path1.lineTo(160,750);
            path1.lineTo(210,800);
            canvas.drawPath(path1,p);



            Path path2 = new Path();
            path2.moveTo(500,600);
            path2.lineTo(650,600);
            path2.lineTo(650,700);
            path2.lineTo(500,700);
            path2.lineTo(500,800);
            path2.lineTo(650,800);
            canvas.drawPath(path2,p);

            Path path3 = new Path();
            path3.moveTo(750,450);
            path3.lineTo(750,900);
            path3.lineTo(750,700);
            path3.lineTo(950,700);
            canvas.drawPath(path3,p);



            p.setStrokeWidth(0);
            p.setTextSize(30);
            canvas.drawText("안드로이드",10,990,p);





        }
    }
}


//---------------------기초 수업내용--------------


//    엑티비티에  xml를 사용하는 것이 아닌 도화지를 배치해서 그림그리는 기능 을 배치
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_main);
//        setContentView(new MyCanvas(this));
//        //4.내가만든 도화지 클래스를 세팅시킨다.(화면전체를 도화지로 쓴다)
//    }
//private static class MyCanvas extends View {
//    //1. 클래스 생성
//    public MyCanvas(Context context) {
//        //2.캔버스 생성
//        super(context);
//    }
//
//    @Override
//    //3.onDraw 메소드 생성(도화지는부모로부터 받음)
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        //5.붓은 생성해야 되기 때문에 붓 생성(변수선언)
//        Paint p = new Paint();
//        //6.AntiAlias  -> 끝 부분을 부드럽게 처리해주는 기능
//        p.setAntiAlias(true);
//        //7.붓에 파랑 물감을 뭍힘
//        p.setColor(Color.BLUE);
//        //8..drawLine(); -> 선을 긋는다.
//
//        //9.   x축에서 10, y축에서 10 시작 -> x축으로 300,y축으로30떨어진 곳까지 선을 긋는다는 의미
//        canvas.drawLine(10,10,300,10,p);
//
//        p.setColor(Color.RED);
//        //10. 붓의 굵기  -> 붓의 굵기는 먼저나와도 되고 나중에 나와도 관계 없다. (0이 기본)
//        p.setStrokeWidth(5);
//
//        canvas.drawLine(10,30,300,30,p);
//
//        ///--------9-1실습
//
//        p.setColor(Color.GREEN);
//        p.setStrokeWidth(0);
//
//        p.setStyle(Paint.Style.FILL);
//        Rect rect1 = new Rect(10,50,110,150);   // 변수를 선언하여 생성
//        canvas.drawRect(rect1,p);
//
//        p.setStyle(Paint.Style.STROKE);
//        Rect rect2 = new Rect(130,50,230,150);// 변수를 선언하여 생성
//        canvas.drawRect(rect2,p);
//
//        RectF rect3 = new RectF(250,50,350,150);
//        canvas.drawRoundRect(rect3,20,20,p);
//
//        p.setStyle(Paint.Style.FILL);
//        canvas.drawRect(10,160,110,260,p);
//
//        p.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(130,160,230,260,p);
//
//
//
//
//        canvas.drawCircle(210,350,50,p);
//
//        p.setStrokeWidth(5);
//
//        Path path1 = new Path();  // 변수선언
//        path1.moveTo(10,600);
//        path1.lineTo(60,650);
//        path1.lineTo(110,700);
//        path1.lineTo(160,750);
//        path1.lineTo(210,800);
//        canvas.drawPath(path1,p);
//
//
//
//        Path path2 = new Path();
//        path2.moveTo(500,600);
//        path2.lineTo(650,600);
//        path2.lineTo(650,700);
//        path2.lineTo(500,700);
//        path2.lineTo(500,800);
//        path2.lineTo(650,800);
//        canvas.drawPath(path2,p);
//
//        Path path3 = new Path();
//        path3.moveTo(750,450);
//        path3.lineTo(750,900);
//        path3.lineTo(750,700);
//        path3.lineTo(950,700);
//        canvas.drawPath(path3,p);
//
//
//
//        p.setStrokeWidth(0);
//        p.setTextSize(30);
//        canvas.drawText("안드로이드",10,990,p);
//
//
//
//
//
//    }
//}
//}
package com.example.paintshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, CIRCLE=2,  ROOD=3,  SUB=4, SUB1=5, SUB2=6;//7. 정적변수 선언
    static  int curShape=LINE;   //8. 변수선언
    static  int colorC = 0;


    //4. onCreateOptionsMenu  와 onOptionsItemSelected 를 생성하여 간단한 옵션메뉴를 자바로 생성 (많을경우 폴더 생성 후 제작)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,LINE,0,"선 그리기");  //5. 옵션메뉴 2개 생성
        menu.add(0,CIRCLE,0,"원 그리기");//5. 옵션메뉴 2개 생성
        menu.add(0,ROOD,0,"사각형 그리기");//5. 옵션메뉴 2개 생성
        SubMenu sMenu = menu.addSubMenu("색상변경 >>>");
        sMenu.add(0,SUB,0,"빨강");
        sMenu.add(0,SUB1,0,"초록");
        sMenu.add(0,SUB2,0,"파랑");
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId()) {  //6.옵션 설정
            case LINE:  //선그리기
                curShape =LINE;    //9. 숫자1적용되게 입력
                break;
            case CIRCLE: //원그리기
                curShape=CIRCLE;//9. 숫자2적용되게 입력
                break;
            case ROOD: //사각형그리기
                curShape = ROOD;  //숫자 3적용되게 입력
                break;


        }switch (item.getItemId()) {
            case SUB:
                colorC=SUB;
                break;
            case SUB1:
                colorC=SUB1;
                break;
            case SUB2:
                colorC=SUB2;
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        setContentView(new MyCanvas(this));  //1.엑티비티 전체를 캔버스로 쓴다.



    }
    private static class MyCanvas extends View {

        int   startX = -1, startY=-1, stopX=-1,stopY=-1;   //13.정수값을 가져오기 위한 변수 선언  (-1 : 좌표값 밖)


        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //10. onTouchEvent 메소드 추가

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: //11.마우스 눌렀을 때,
                    startX=(int)event.getX();  //14.소수점 이하 값은 찍히지 않는다.
                    startY=(int)event.getY();

//                    android.util.Log.i("test","좌표값 x = "+event.getX());   //12.로그캣을 통한 좌표값 알기 위한 설정  로그캣에서 Edit Filter Configuration 에서 생성하면 확인 가능하다.
//                    android.util.Log.i("test","좌표값 y = "+event.getY());//12.로그캣을 통한 좌표값 알기 위한 설정

                    break;
                case MotionEvent.ACTION_MOVE://11.마우스 누른상태에서 이동했을때,

                    stopX=(int)event.getX();
                    stopY=(int)event.getY();
//                     android.util.Log.i("test","좌표값 x = "+event.getX());   //12.로그캣을 통한 좌표값 알기 위한 설정  로그캣에서 Edit Filter Configuration 에서 생성하면 확인 가능하다.
//                    android.util.Log.i("test","좌표값 y = "+event.getY());//12.로그캣을 통한 좌표값 알기 위한 설정
                    this.invalidate();   //15.화면을 무효화 시킴 = onDraw 메소드를 호출
                    break;

                case MotionEvent.ACTION_UP://11.마우스를 떼었을 때,

//                       stopX=(int)event.getX();
//                       stopY=(int)event.getY();
//
////                     android.util.Log.i("test","좌표값 x = "+event.getX());   //12.로그캣을 통한 좌표값 알기 위한 설정  로그캣에서 Edit Filter Configuration 에서 생성하면 확인 가능하다.
////                    android.util.Log.i("test","좌표값 y = "+event.getY());//12.로그캣을 통한 좌표값 알기 위한 설정
//                    this.invalidate();   //15.화면을 무효화 시킴 = onDraw 메소드를 호출
                    break;

            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {  //실제 그림을 그리는 메소드
            super.onDraw(canvas);
            Paint paint = new Paint();  // 2. 붓 변수 생성
            paint.setAntiAlias(true);  //3. 부드럽게 생성
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            switch (colorC) {
                case SUB :
                    paint.setColor(Color.RED);
                    break;
                case SUB1 :
                    paint.setColor(Color.GREEN);
                    break;
                case SUB2 :
                    paint.setColor(Color.BLUE);
                    break;

            }

            switch ( curShape) {

                case LINE :
                    canvas.drawLine(startX,startY,stopX,stopY,paint);
                    break;
                case  CIRCLE:
                    int radius=(int)Math.sqrt(Math.pow(stopX-startX,2))+(int)Math.sqrt(Math.pow(stopY-startY,2));    //17.반지름 구하기 위한 변수 선언  //.sqrt()  루트
                    canvas.drawCircle(startX,startY,radius,paint);                      //17-1 피타고라스 정리에 의해 구해진거임 ㅋㅋ
                    break;
                case ROOD:
                    canvas.drawRect(startX,startY,stopX,stopY,paint);
                    break;

            }






        }
    }
}

//------------------------------------------------------------------------------수업내용

//
//public class MainActivity extends AppCompatActivity {
//
//    final static int LINE = 1, CIRCLE=2;    //7. 정적변수 선언
//    static  int curShape=LINE;   //8. 변수선언
//
//
//    //4. onCreateOptionsMenu  와 onOptionsItemSelected 를 생성하여 간단한 옵션메뉴를 자바로 생성 (많을경우 폴더 생성 후 제작)
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        menu.add(0,LINE,0,"선 그리기");  //5. 옵션메뉴 2개 생성
//        menu.add(0,CIRCLE,0,"원 그리기");//5. 옵션메뉴 2개 생성
//        return  true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch ( item.getItemId()) {  //6.옵션 설정
//            case LINE:  //선그리기
//                curShape =LINE;    //9. 숫자1적용되게 입력
//                break;
//            case CIRCLE: //원그리기
//                curShape=CIRCLE;//9. 숫자2적용되게 입력
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//
//        setContentView(new MyCanvas(this));  //1.엑티비티 전체를 캔버스로 쓴다.
//
//
//
//    }
//    private static class MyCanvas extends View {
//
//        int   startX = -1, startY=-1, stopX=-1,stopY=-1;   //13.정수값을 가져오기 위한 변수 선언  (-1 : 좌표값 밖)
//
//
//        public MyCanvas(Context context) {
//            super(context);
//        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//            //10. onTouchEvent 메소드 추가
//
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN: //11.마우스 눌렀을 때,
//                    startX=(int)event.getX();  //14.소수점 이하 값은 찍히지 않는다.
//                    startY=(int)event.getY();
//
////                    android.util.Log.i("test","좌표값 x = "+event.getX());   //12.로그캣을 통한 좌표값 알기 위한 설정  로그캣에서 Edit Filter Configuration 에서 생성하면 확인 가능하다.
////                    android.util.Log.i("test","좌표값 y = "+event.getY());//12.로그캣을 통한 좌표값 알기 위한 설정
//
//                    break;
//                case MotionEvent.ACTION_MOVE://11.마우스 누른상태에서 이동했을때,
//
//                    stopX=(int)event.getX();
//                    stopY=(int)event.getY();
////                     android.util.Log.i("test","좌표값 x = "+event.getX());   //12.로그캣을 통한 좌표값 알기 위한 설정  로그캣에서 Edit Filter Configuration 에서 생성하면 확인 가능하다.
////                    android.util.Log.i("test","좌표값 y = "+event.getY());//12.로그캣을 통한 좌표값 알기 위한 설정
//                    this.invalidate();   //15.화면을 무효화 시킴 = onDraw 메소드를 호출
//                    break;
//
//                case MotionEvent.ACTION_UP://11.마우스를 떼었을 때,
//
////                       stopX=(int)event.getX();
////                       stopY=(int)event.getY();
////
//////                     android.util.Log.i("test","좌표값 x = "+event.getX());   //12.로그캣을 통한 좌표값 알기 위한 설정  로그캣에서 Edit Filter Configuration 에서 생성하면 확인 가능하다.
//////                    android.util.Log.i("test","좌표값 y = "+event.getY());//12.로그캣을 통한 좌표값 알기 위한 설정
////                    this.invalidate();   //15.화면을 무효화 시킴 = onDraw 메소드를 호출
//                    break;
//
//            }
//            return true;
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {  //실제 그림을 그리는 메소드
//            super.onDraw(canvas);
//            Paint paint = new Paint();  // 2. 붓 변수 생성
//            paint.setAntiAlias(true);  //3. 부드럽게 생성
//
//            paint.setStrokeWidth(5);
//
//            paint.setStyle(Paint.Style.STROKE);  //18.스타일은 기본이 FALL이다.(꽉채움)
//            switch ( curShape) { //16.직선과 원을 그릴때의 경우를 코딩
//                case LINE :
//                    canvas.drawLine(startX,startY,stopX,stopY,paint);
//                    break;
//                case  CIRCLE:
//                    int radius=(int)Math.sqrt(Math.pow(stopX-startX,2))+(int)Math.sqrt(Math.pow(stopY-startY,2));    //17.반지름 구하기 위한 변수 선언  //.sqrt()  루트
//                    canvas.drawCircle(startX,startY,radius,paint);                      //17-1 피타고라스 정리에 의해 구해진거임 ㅋㅋ
//                    break;
//            }
//            canvas.drawLine(startX,startY,stopX,stopY,paint);  //16,해당위치에 선을 긋게 설정한다.
//
//
//
//
//
//
//
//        }
//    }
//}
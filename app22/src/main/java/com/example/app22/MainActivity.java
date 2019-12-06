package com.example.app22;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2, ROOD = 3, SUB = 4, SUB1 = 5, SUB2 = 6, SUB3 = 7;
    static int curShape = LINE;
    static int colorC = 0;

    static List<MyShape> myShapes = new ArrayList<MyShape>();
    static boolean isFinish = false;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, LINE, 0, "선 그리기");
        menu.add(0, CIRCLE, 0, "원 그리기");
        menu.add(0, ROOD, 0, "사각형 그리기");
        SubMenu sMenu = menu.addSubMenu("색상변경 >>>");
        sMenu.add(0, SUB, 0, "빨강");
        sMenu.add(0, SUB1, 0, "초록");
        sMenu.add(0, SUB2, 0, "파랑");
        sMenu.add(0, SUB3, 0, "검정");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case LINE:
                curShape = LINE;
                break;
            case CIRCLE:
                curShape = CIRCLE;
                break;
            case ROOD:
                curShape = ROOD;


        }
        switch (item.getItemId()) {
            case SUB:
                colorC = SUB;
                break;
            case SUB1:
                colorC = SUB1;
                break;
            case SUB2:
                colorC = SUB2;
                break;
            case SUB3:
                colorC = SUB3;
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyCanvas(this));
    }

    private static class MyCanvas extends View {

        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    isFinish = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    isFinish = true;
                    MyShape shape = new MyShape();
                    shape.shapeType = curShape;
                    shape.startX = startX;
                    shape.startY = startY;
                    shape.stopX = stopX;
                    shape.stopY = stopY;
                    shape.color = colorC;
                    myShapes.add(shape);
                    this.invalidate();


                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            for (int i = 0; i < myShapes.size(); i++) {
                MyShape shape = myShapes.get(i);

                switch (shape.shapeType) {
                    case LINE:
                        canvas.drawLine(shape.startX, shape.startY, shape.stopX, shape.stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(shape.stopX - shape.startX, 2)) + (int) Math.sqrt(Math.pow(shape.stopY - shape.startY, 2));
                        canvas.drawCircle(shape.startX, shape.startY, radius, paint);
                        break;
                    case ROOD:
                        canvas.drawRect(shape.startX, shape.startY, shape.stopX, shape.stopY, paint);
                        break;
                }
                switch (shape.color) {
                    case SUB:
                        paint.setColor(Color.RED);
                        break;
                    case SUB1:
                        paint.setColor(Color.GREEN);
                        break;
                    case SUB2:
                        paint.setColor(Color.BLUE);
                        break;
                    case SUB3:
                        paint.setColor(Color.BLACK);
                        break;
                }
            }
            if (isFinish == false) {
                switch (colorC) {
                    case SUB:
                        paint.setColor(Color.RED);
                        break;
                    case SUB1:
                        paint.setColor(Color.GREEN);
                        break;
                    case SUB2:
                        paint.setColor(Color.BLUE);
                        break;
                    case SUB3:
                        paint.setColor(Color.BLACK);
                        break;

                }

                switch (curShape) {

                    case LINE:
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)) + (int) Math.sqrt(Math.pow(stopY - startY, 2));
                        canvas.drawCircle(startX, startY, radius, paint);
                        break;
                    case ROOD:
                        canvas.drawRect(startX, startY, stopX, stopY, paint);
                        break;
                }
            }


        }
    }

    private static class MyShape {
        int shapeType;
        int startX, startY, stopX, stopY;
        int color;


    }


}

//------------------------------------------------------------------------------11/4수업내용

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

//--------------------------------------11/5일 수업내용
//
//public class MainActivity extends AppCompatActivity {
//
//    final static int LINE = 1, CIRCLE = 2, ROOD = 3, SUB = 4, SUB1 = 5, SUB2 = 6, SUB3 = 7;
//    static int curShape = LINE;
//    static int colorC = 0;
//
//    static List<MyShape> myShapes = new ArrayList<MyShape>();   //5. 도형을 그린 후  저장할 리스트 --> 동적 배열을 생성 함.  ★★  객체배열
//    static boolean isFinish=false;  //6. 현재그리는 도형을 완료 했는지 안했는지 확인해주는 변수
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        menu.add(0, LINE, 0, "선 그리기");
//        menu.add(0, CIRCLE, 0, "원 그리기");
//        menu.add(0, ROOD, 0, "사각형 그리기");
//        SubMenu sMenu = menu.addSubMenu("색상변경 >>>");
//        sMenu.add(0, SUB, 0, "빨강");
//        sMenu.add(0, SUB1, 0, "초록");
//        sMenu.add(0, SUB2, 0, "파랑");
//        sMenu.add(0,SUB3,0,"검정");
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case LINE:
//                curShape = LINE;
//                break;
//            case CIRCLE:
//                curShape = CIRCLE;
//                break;
//            case ROOD:
//                curShape = ROOD;
//
//
//        }
//        switch (item.getItemId()) {
//            case SUB:
//                colorC = SUB;
//                break;
//            case SUB1:
//                colorC = SUB1;
//                break;
//            case SUB2:
//                colorC = SUB2;
//                break;
//            case SUB3:
//                colorC = SUB3;
//                break;
//
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(new MyCanvas(this));
//    }
//
//    private static class MyCanvas extends View {
//
//        int startX = -1, startY = -1, stopX = -1, stopY = -1;
//
//        public MyCanvas(Context context) {
//            super(context);
//        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    startX = (int) event.getX();
//                    startY = (int) event.getY();
//                    isFinish=false;  // 7. 시작한 시점이기 때문에 false 적용
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    stopX = (int) event.getX();
//                    stopY = (int) event.getY();
//                    this.invalidate();
//                    break;
//                case MotionEvent.ACTION_UP:
//                    isFinish=true;  // 8. 시작한 시점이기 때문에 true 적용
//                    MyShape shape = new MyShape();
//                    //9. 마우스를 최종적으로뗄 때마다 인스턴스 객체 생성
//                    shape.shapeType=curShape;
//                    //10. 도형의 종류를 담아둠
//                    shape.startX=startX;
//                    shape.startY=startY;
//                    shape.stopX=stopX;
//                    shape.stopY=stopY;
//                    //11.좌표 위치를 담아둠
//                    shape.color=colorC;
//                    //12.칼라 를 담아둠
//                    myShapes.add(shape);
//                    //13.   myShapes 의 0번째 방부터 차례차례 객체 배열의 값이 들어간다.(예를들어서 처음 빨강 사각형을 만들었다면 종류/좌표값(4개)/색상 을 담는다)
//                    this.invalidate();
//                    //14.onDraw 메소드 호출
//
//
//                    break;
//            }
//            return true;
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//            Paint paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setStrokeWidth(5);
//            paint.setStyle(Paint.Style.STROKE);
//
//            for(int i=0; i<myShapes.size(); i++) {  //15. for문 작성 후에 스위치 문 복사 붙여넣기
//                MyShape shape=myShapes.get(i);
//                //16. 같은 값을 받기 위해서 같은 형 사용
//                switch (shape.shapeType) {  //18.shape.shapeType 이제 실제로 되있는 값변경
//                    case LINE:
//                        canvas.drawLine(shape.startX,shape.startY, shape.stopX, shape.stopY, paint);
//                        break;
//                    case CIRCLE:
//                        int radius = (int) Math.sqrt(Math.pow(shape.stopX - shape.startX, 2)) + (int) Math.sqrt(Math.pow(shape.stopY - shape.startY, 2));
//                        canvas.drawCircle(shape.startX, shape.startY, radius, paint);
//                        break;
//                    case ROOD:
//                        canvas.drawRect(shape.startX, shape.startY, shape.stopX, shape.stopY, paint);
//                        break;
//                }
//                switch (shape.color) {  //17.이제 실제로 들어가 있는 값은 shape.color
//                    case SUB:
//                        paint.setColor(Color.RED);
//                        break;
//                    case SUB1:
//                        paint.setColor(Color.GREEN);
//                        break;
//                    case SUB2:
//                        paint.setColor(Color.BLUE);
//                        break;
//                    case SUB3:
//                        paint.setColor(Color.BLACK);
//                        break;
//                }
//            }
//            if(isFinish==false){  //19. isFinish==false  if문안에 적용한다.  ==>  아직 마우스를 떼지 않았기 때문에 그대로를 적용해주고 있는 상태
//                switch (colorC) {
//                    case SUB:
//                        paint.setColor(Color.RED);
//                        break;
//                    case SUB1:
//                        paint.setColor(Color.GREEN);
//                        break;
//                    case SUB2:
//                        paint.setColor(Color.BLUE);
//                        break;
//                    case SUB3:
//                        paint.setColor(Color.BLACK);
//                        break;
//
//                }
//
//                switch (curShape) {
//
//                    case LINE:
//                        canvas.drawLine(startX, startY, stopX, stopY, paint);
//                        break;
//                    case CIRCLE:
//                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)) + (int) Math.sqrt(Math.pow(stopY - startY, 2));
//                        canvas.drawCircle(startX, startY, radius, paint);
//                        break;
//                    case ROOD:
//                        canvas.drawRect(startX, startY, stopX, stopY, paint);
//                        break;
//                }
//            }
//
//
//
//
//        }
//    }
//    //11월 5일 수업내용
//    private static class MyShape {  //1.클래스 생성  MyShape 클래스명  -> 기존 내용을 보관하는 클래스
//        int shapeType;  //2.도형의 종류
//        int startX,startY,stopX,stopY; //3.도형의좌표값
//        int color;  //4.색상보관
//
//
//    }
//
//
//}
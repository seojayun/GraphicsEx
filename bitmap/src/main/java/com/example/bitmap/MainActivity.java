package com.example.bitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
//이번에도 이미지뷰를 이용하지 않고, 변환하는 것이므로, 자바만을 이용한다.



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView( new MyImageView(this));  // 2. MyImageView 를 생성한다.
    }

    private static  class MyImageView extends View {  //  1.   private static  class  생성 및 View 상속 후  MyImageView , onDraw  메소드 생성

        public MyImageView(Context context) {
            super(context);

        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            //12.붓 한개 선언
            Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.pic1);
            //3. Bitmap 클래스의 bm 변수 선언 후  BitmapFactory 메소드 호출 하여 이미지 불러옴
            int x=((this.getWidth()-bm.getWidth())/2);
            int y=((this.getHeight()-bm.getHeight())/2);
            //6.x,y값을 선언하여, 이미지 위치를 잡은 후
            int canX = this.getWidth()/2;
            int canY = this.getHeight()/2;


            // ----------------------------------임시 블록
//            canvas.rotate(45,canX,canY);
//            //8. .rotate 회전  cenX,cenY 변수 선언 한 후에  this (도화지)  의 높이와 길이 /2 를 하여, 캔버스를 중심으로 45도 회전한다.  그림을 회전시키는 것이 아닌 캔버스 자체를 회전시킴
//            //8. 만약 45 옆에 0,0이면 중심축이 0,0으로 가게되므로, 엉뚱한 위치로 회전하게 된다.  즉 변수를 통해 중심축을 만들고 중심축을 설정하였다.
            // ----------------------------------임시 블록


           // ----------------------------------임시 블록
//            canvas.translate(-150,200);
//            //9.  .translate(); 이동  (x,y)축
//            canvas.scale(2,2,canX,canY);
//            //10.  .scale();  배율 크기
//            canvas.skew(0.3f,0.3f);
//            //11. .skew(); 기울기 비틀기
            // ----------------------------------임시 블록

            // ----------------------------------임시 블록
            BlurMaskFilter bMask;
            //13. BlurMaskFilter 클래스 선언
            bMask=new BlurMaskFilter(30,BlurMaskFilter.Blur.NORMAL);
            //14. 인스턴스 객체 생성 후 값 입력, 첫번째 입력값은 블러링값, 두번째 입력값은 종류
            paint.setMaskFilter(bMask);
            //15.붓에 효과를 준다 bMask 만큼...
            // ----------------------------------임시 블록

            // ----------------------------------임시 블록  ---> 사용안되니 원인 나중에 확인
            EmbossMaskFilter eMask;
            //16.Blur효과가 있는 상태에서는 엠보싱효과가 안나오기 때문에 주석 처리 후 작업함.  EmbossMaskFilter 클래스의 eMask 변수 선언
            eMask = new EmbossMaskFilter(new float[]{3,3,10},0.5f,5,10);
            //17.  배열의 숫자에 따른 빛 방향이 틀리다.(371p), 빛 밝기(0.5f),반사(5),오목하게보이는 크기 등을 적용
            paint.setMaskFilter(eMask);
            //18.붓에 eMask 를 세팅시킨다.
            canvas.drawCircle(canX,canY,150,paint);
            // ----------------------------------임시 블록

//            // ----------------------------------임시 블록
//            float [] array = {1.5f,0,0,0,-25,
//                              0,1.5f,0,0,-25,
//                              0,0,1.5f,0,-25,
//                              0,0,0,1.5f,0, };
//            ColorMatrix colorMatrix = new ColorMatrix(array);
//            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//            //19. 배열 생성 후,  ColorMatrix 클래스 생성 후 인스턴스 객체 생성  배열은  r/g/b/알파값(불투명/투명) 순이다.  수치를 높일 수록 밝아진다. (물감하고는 반대이기 때문)  맨 뒤의 값은 밝기의 정도를 확인
//
//            // ----------------------------------임시 블록

            canvas.drawBitmap(bm,x,y,paint);
           //4.   도화지의 위치 (0,0)  , null값은 붓을 이용 안한다   7.레프트 탑에 x,y 값 적용한다.    //16. null 값에 붓(paint)을 적용한다.
            bm.recycle();
            //5.작업 완료 후 해제해야 한다. .recycle(); 명령어
        }


    }
}


package com.example.miniphotoshop;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton ibZoomIn, ibZoomOut, ibZoomRate, ibBright, ibDark, ibBlur, ibEmbos;
    LinearLayout LinearCan;

    MyCanvas myCanvas;

    static float scaleX = 1, scaleY = 1, angle = 0, satur = 1;
    static boolean blur = false, embos = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibZoomIn = (ImageButton) findViewById(R.id.ibZoomIn);
        ibZoomOut = (ImageButton) findViewById(R.id.ibZoomOut);
        ibZoomRate = (ImageButton) findViewById(R.id.ibZoomRate);
        ibBright = (ImageButton) findViewById(R.id.ibBright);
        ibDark = (ImageButton) findViewById(R.id.ibDark);
        ibBlur = (ImageButton) findViewById(R.id.ibBlur);
        ibEmbos = (ImageButton) findViewById(R.id.ibEmbos);
        LinearCan = (LinearLayout) findViewById(R.id.LinearCan);


        myCanvas = new MyCanvas(this);

        LinearCan.addView(myCanvas);

        ibZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scaleX += 0.2f;
                scaleY += 0.2f;

                myCanvas.invalidate();
            }
        });
        ibZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scaleX -= 0.2f;
                scaleY -= 0.2f;

                myCanvas.invalidate();

            }
        });
        ibZoomRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angle += 20;
                myCanvas.invalidate();
            }
        });

        ibBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satur += 0.2f;
                myCanvas.invalidate();
            }
        });

        ibDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satur -= 0.2f;
                myCanvas.invalidate();
            }

        });

        ibBlur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blur == true) {
                    blur = false;
                } else {
                    blur = true;
                }
                myCanvas.invalidate();
            }
        });

        ibEmbos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (embos == true) {
                    embos = false;
                } else {
                    embos = true;
                }
                myCanvas.invalidate();
            }
        });

    }

    private static class MyCanvas extends View {

        public MyCanvas(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint p = new Paint();

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.lll89252841541);

            int canX = this.getWidth() / 2;
            int canY = this.getHeight() / 2;

            int x = (this.getWidth() - bm.getWidth()) / 2;
            int y = (this.getHeight() - bm.getHeight()) / 2;


            canvas.scale(scaleX, scaleY, canX, canY);


            canvas.rotate(angle, canX, canY);


            ColorMatrix cm = new ColorMatrix();

            cm.setSaturation(satur);


            p.setColorFilter(new ColorMatrixColorFilter(cm));

            if (blur == true) {
                BlurMaskFilter bMask;
                bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
                p.setMaskFilter(bMask);
            }
            if (embos == true) {
                EmbossMaskFilter eMask;
                eMask = new EmbossMaskFilter(new float[]{3, 3, 3}, 0.5f, 5, 100);
                p.setMaskFilter(eMask);
            }
            canvas.drawBitmap(bm, x, y, p);
            bm.recycle();
        }

    }
}


//------------------  11월 6일 기본수업
//
//public class MainActivity extends AppCompatActivity {
//
//    ImageButton ibZoomIn,ibZoomOut,ibZoomRate,ibBright,ibDark,ibGray;
//    LinearLayout LinearCan;
//    //6.MyCanvas 변수 선언
//    MyCanvas myCanvas;
//    //11.배율을 정하기 위한 변수 선언  1이 기본 (1배)
//    static float scaleX=1,scaleY=1,     angle = 0, color=1,  satur=1; // 17.angle 회전을 위한 변수 선언   //19.color 색상을 밝게 하기 위한 변수 선언   20.satur 채도를 위한 변수 선언
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ibZoomIn=(ImageButton)findViewById(R.id.ibZoomIn);
//        ibZoomOut=(ImageButton)findViewById(R.id.ibZoomOut);
//        ibZoomRate=(ImageButton)findViewById(R.id.ibZoomRate);
//        ibBright=(ImageButton)findViewById(R.id.ibBright);
//        ibDark=(ImageButton)findViewById(R.id.ibDark);
//        ibGray=(ImageButton)findViewById(R.id.ibGray);
//        LinearCan=(LinearLayout)findViewById(R.id.LinearCan);
//
//        //7.myCanvas 의 인스턴스 객체 생성
//        myCanvas=new MyCanvas(this);
//        //8.리니어 레이아웃에 add 시킨다 캔버스를
//        LinearCan.addView(myCanvas);
//        //10.확대 버튼 누를때마다...
//        ibZoomIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //12. 기본값에 0.2f씩 증가
//                scaleX+=0.2f; // scaleX = scaleX+0.2f
//                scaleY+=0.2f;
//                //13.무효화 시켜준다.
//                myCanvas.invalidate();  //onDraw메소드 호출  = 무효화 시킨다.
//            }
//        });
//        ibZoomOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //15. 기본값에 0.2f씩 감소
//                scaleX-=0.2f; // scaleX = scaleX-0.2f
//                scaleY-=0.2f;
//                //16.무효화 시켜준다.
//                myCanvas.invalidate();  //onDraw메소드 호출  = 무효화 시킨다.
//
//            }
//        });
//        ibZoomRate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                angle+=20; // angle = angle +20;
//                myCanvas.invalidate();  //onDraw메소드 호출  = 무효화 시킨다.
//            }
//        });
//        //19.점점 밝아지게 하는 버튼
//        ibBright.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //21. 0.2식 색상증가 ->점점 밝아짐
//                color+= 0.2f;
//
//
//                myCanvas.invalidate();  //onDraw메소드 호출  = 무효화 시킨다.
//            }
//        });
//
//        ibDark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //25. 점점 어둡게 하는 버튼
//                color-= 0.2f;
//                myCanvas.invalidate();
//            }
//        });
//        //26. 흑백/칼라 이미지변경을 위한 버튼
//        ibGray.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //27. satur 값이 0일때 1 , 1일때 0으로 대입되도록 변경해주는 if문 작성  -> 마치 리모콘 버튼 전원 온오프기능 처럼 하는것.
//                if(satur==0) {
//                    satur=1;
//                }else {
//                    satur=0;
//                }
//                myCanvas.invalidate();
//            }
//        });
//    }
//    //1.클래스 생성 후 생성자, 메소드 생성
//    private  static  class MyCanvas extends View {
//
//        public MyCanvas(Context context) {
//            super(context);
//
//        }
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//            //4. 붓생성
//            Paint p = new Paint();
//            //2. Bitmap 클래스의 변수 bm 선언 후 파일경로를 가져와 그린다.
//            Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.lll89252841541);
//            //9.중심축을 잡기 위한 변수 선언
//            int canX = this.getWidth()/2;
//            int canY = this.getHeight()/2;
//            //3.중앙을 찾기 위한 변수 선언
//            int x = (this.getWidth()-bm.getWidth())/2;
//            int y = (this.getHeight()-bm.getHeight())/2;
//
//            //14.1.2f 된 xy값을 대입,위치 대입
//            canvas.scale(scaleX,scaleY,canX,canY);
//
//            //18. 캔버스의 회전을 위한값, 위치 설정
//            canvas.rotate(angle,canX,canY);
//            //22.배열 생성
//            float array[]= {color,0,0,0,0,  //레드값
//                    0,color,0,0,0,   //그린값
//                    0,0,color,0,0,  //블루값
//                    0,0,0,1,0};   //투명도값
//            //23.ColorMatrix 인스턴스 객체 생성
//            ColorMatrix cm = new ColorMatrix(array);  //기본값이 1로 세팅되어 있음
//
//            //28. .setSaturation  생성해놓은 ColorMatrix cm 의 채도값을 0으로 변경하게 된다. (ibGray 버튼이 클릭될때)
//            if(satur==0) {
//                cm.setSaturation(0);
//            }
//
//            //24.cm에 들어가있는 색상을 적용하여 붓을 세팅시킨다.
//            p.setColorFilter(new ColorMatrixColorFilter(cm));
//            //5.캔버스의 내용, 위치, 붓을 설정
//            canvas.drawBitmap(bm,x,y,p);
//            bm.recycle();  // 리소스를 해제한다는 뜻
//        }
//
//    }
//}
package com.example.spoorthi.cropimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int width;
    int height;
    Button crop;
    PinchImageView mpinchImageView;
    Matrix mMatrix;
    Bitmap bm;
    Bitmap bmp;
    ImageView alteredImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mpinchImageView = (PinchImageView) findViewById(R.id.pinchImgView);
        alteredImageView = (ImageView) this.findViewById(R.id.imgView);

        ViewTreeObserver vto = alteredImageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                height = alteredImageView.getMeasuredHeight();
                width = alteredImageView.getMeasuredWidth();
                Log.d("alterView", "Height: " + height + " Width: " + width);
                return true;
            }
        });

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.lighthouse);
        mpinchImageView.setImageBitmap(bm);

        crop = (Button) findViewById(R.id.crop);
        crop.setOnClickListener(MainActivity.this);

        /*iv2.setImageBitmap(bm);
        iv2.setScaleType(ImageView.ScaleType.FIT_XY);
        iv2.setOnTouchListener(new MultiTouchListener());*/

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.crop:
                mMatrix = mpinchImageView.getCroppedImage();
                Log.e("**********", "" + mMatrix);
                Point size = new Point();

                Display currentDisplay = getWindowManager().getDefaultDisplay();
                currentDisplay.getSize(size);
//        int width = (int)(size.x/1.8);
//        int height = (size.y/2)-240;

//        int width = (int)(size.x/1.2);
//        int height = (size.y/2)-240;

                int mwidth = (int) (size.x / 1.8);
                int mheight = (size.y / 2) - 240;
//
//                Log.d("width", "" + width);
//                Log.d("height", "" + height);

                //int dh = currentDisplay.getHeight() / 2 - 100;

                bmp = BitmapFactory.decodeResource(getResources(), R.drawable.lighthouse);

                Bitmap alteredBitmap = Bitmap.createBitmap(width, height, bmp.getConfig());
                Canvas canvas = new Canvas(alteredBitmap);
                Paint paint = new Paint();

                canvas.drawBitmap(bmp, mMatrix, paint);

                alteredImageView.setImageBitmap(alteredBitmap);
                break;


        }

    }
}

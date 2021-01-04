package com.example.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    int score;
    ImageView ımageView;
    ImageView ımageView1;
    ImageView ımageView2;
    ImageView ımageView3;
    ImageView ımageView4;
    ImageView ımageView5;
    ImageView ımageView6;
    ImageView ımageView7;
    ImageView ımageView8;
    ImageView[] ımageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=findViewById(R.id.timeText);
        timeText.setText("22232");
        scoreText=findViewById(R.id.scoreText);
        ımageView=findViewById(R.id.imageView);
        ımageView1=findViewById(R.id.imageView1);
        ımageView2=findViewById(R.id.imageView2);
        ımageView3=findViewById(R.id.imageView3);
        ımageView4=findViewById(R.id.imageView4);
        ımageView5=findViewById(R.id.imageView5);
        ımageView6=findViewById(R.id.imageView6);
        ımageView7=findViewById(R.id.imageView7);
        ımageView8=findViewById(R.id.imageView8);
        ımageArray= new ImageView[]{ımageView,ımageView1,ımageView2,ımageView3,ımageView4,ımageView5,ımageView6,ımageView7,ımageView8};
        hideİmages();
        score=0;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time:"+l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time's up");
                handler.removeCallbacks(runnable);
                for(ImageView image:ımageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Do you want to play again?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game is over",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();


            }
        }.start();
    }



    public void increaseScore(View view){

        score++;

        scoreText.setText("Score:"+score);


    }
    public void hideİmages(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:ımageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(8);
                ımageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);



    }
}

package com.gameso.sevenbonuses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    Button buttonRollDice;
    private boolean gameRunning = false;

    private ImageView[] diceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button buttonExitGame = findViewById(R.id.buttonExitGame);
        buttonRollDice = findViewById(R.id.buttonRollDice);
        buttonExitGame.setOnClickListener(onClickExitGame);
        buttonRollDice.setOnClickListener(onClickListener);

        ImageView imageDiceFirst = findViewById(R.id.imageDiceFirst);
        ImageView imageDiceSecond = findViewById(R.id.imageDiceSecond);
        diceView = new ImageView[] {imageDiceFirst, imageDiceSecond};
    }

    View.OnClickListener onClickExitGame = view -> finishAffinity();

    View.OnClickListener onClickListener = view -> {
        if(!gameRunning){
            startAnimation();
        } else {
            Toast.makeText(getApplicationContext(), "You already did your spin, idiot!", Toast.LENGTH_SHORT).show();
        }
    };

    public void startAnimation(){
        if(!gameRunning){
            gameRunning = true;
            CountDownTimer countDownTimer = new CountDownTimer(3000, 100) {
                @Override
                public void onTick(long l) {
                    int[] imageResources = {R.drawable.dot1, R.drawable.dots2, R.drawable.dots3, R.drawable.dots4, R.drawable.dots5, R.drawable.dots6};
                    for(ImageView dice : diceView){
                        int random = (int) (Math.random() * diceView.length);
                        dice.setImageResource(imageResources[random]);
                        dice.setTag(imageResources[random]);
                    }
                }

                @Override
                public void onFinish() {
                    Toast.makeText(getApplicationContext(), "Fine! Here is your result", Toast.LENGTH_SHORT).show();
                    gameRunning = false;
                }
            }.start();
        }
    }
}
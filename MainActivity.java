package com.example.tictoctoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //0-x
    //1-0
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //State meanings:
    //0-x
    //1-0
    //2-Null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {2,5,8}, {1,4,7},
                            {0,4,8}, {2,4,6}};
    public void Playertap (View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
           gameReset(view);
        }
        if (gameState[tappedImage] == 2 && gameActive ){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0) {
                img.setImageResource(R.drawable.tic_tac_toe_x);
                activePlayer = 1;
                TextView Status = findViewById(R.id.Status);
                Status.setText("o's turn: tap to play");
            }
            else{
                img.setImageResource(R.drawable.tic_tac_toe_letter_o);
                activePlayer = 0;
                TextView Status = findViewById(R.id.Status);
                Status.setText("x's turn: tap to play");
            }
        img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[0]] == gameState[winPosition[2]]  &&
                    gameState[winPosition[0]]!= 2 ){
               //Somebody has won! - find out who!
                String winnerStr;
               if(gameState[winPosition[0]] == 0 ){
                  winnerStr = "X has won";
               }
               else{
                   winnerStr = "O has won";
               }
               //Update the status bar for winner announcement
                TextView Status = findViewById(R.id.Status);
                Status.setText(winnerStr);
            }
        }
    }

    public void gameReset(View view){
     gameActive = true;
     activePlayer = 0;
     for(int i=0; i<gameState.length; i++){
         gameState[i] = 2;
     }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView Status = findViewById(R.id.Status);
        Status.setText("x's turn - tap to play");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1 = true;
    private int count;
    private int player1pt;
    private int player2pt;
    private int drawpt;

    private TextView textViewp1;
    private TextView textViewp2;
    private TextView textViewDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewp1 = findViewById(R.id.player1);
        textViewp2 = findViewById(R.id.player2);
        textViewDraw = findViewById(R.id.draw);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String buttonId = "button_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }


        Button buttonReset = findViewById(R.id.reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        } else {
            if (player1) {
                ((Button) v).setText("0");
                ((Button) v).setTextColor(Color.RED);
            } else {
                ((Button) v).setText("X");
                ((Button) v).setTextColor(Color.BLACK);
            }
        }
        count++;

        if (Win()) {
            if (player1) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (count == 9) {
            draw();
        } else {
            player1 = !player1;
        }
    }


    private boolean Win(){

        String field[][] = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i =0; i < 3; i++){
           if(field[i][0].equals(field[i][1])
                   && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
               return true;
           }
        }

        for(int i =0; i < 3; i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void player1Wins(){
        player1pt++;
        Toast.makeText(this, "Player1 (0) Wins !!", Toast.LENGTH_LONG).show();
        updatepoints();
        resetBoard();
    }

    private void player2Wins(){
        player2pt++;
        Toast.makeText(this, "Player2 (X) Wins !!", Toast.LENGTH_LONG).show();
        updatepoints();
        resetBoard();
    }

    private void draw(){
        drawpt++;
        Toast.makeText(this, "Match Draw !!", Toast.LENGTH_LONG).show();
        updatepoints();
        resetBoard();
    }

    private void updatepoints(){
        textViewDraw.setText("Draw : " + drawpt);
        textViewp1.setText("Player1 (0): " + player1pt);
        textViewp2.setText("Player2 (X): " + player2pt);

    }

    private void resetBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
                count = 0;
                player1 = true;
            }
        }
    }

    private void resetAll(){
        drawpt = 0;
        player1pt = 0;
        player2pt = 0;
        updatepoints();
        resetBoard();
    }
}

package android.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] btns = new Button[3][3];
    private Boolean player1Turn = true;
    private Boolean resetGame = false;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView tvPlayer1;
    private TextView tvPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayer1 = findViewById(R.id.tv_p1);
        tvPlayer2 = findViewById(R.id.tv_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String btId = "bt_" + i + j;
                int resId = getResources().getIdentifier(btId,"id",getPackageName());
                btns[i][j] = findViewById(resId);
                btns[i][j].setOnClickListener(this);
            }
        }

        Button btReset = findViewById(R.id.bt_reset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame = true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button)view).getText().toString().equals("")) return;

        if (player1Turn)((Button)view).setText("X");

        else ((Button)view).setText("O");

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                // player 1 wins
                player1Points++;
                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
                updatePointsText();
                resetBoard();
            }
            else {
                // player 2 wins
                player2Points++;
                Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
                updatePointsText();
                resetBoard();
            }
        }
        else if (roundCount == 9) {
            // draw
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            resetBoard();
        }
    }

    private void updatePointsText() {
        tvPlayer1.setText("Player 1: " + player1Points);
        tvPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btns[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;

        if (resetGame) {
            player1Points = 0;
            player2Points = 0;
            updatePointsText();
            resetGame = false;
        }
    }

    private Boolean checkForWin() {
        String[][] field = new String[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                field[i][j] = btns[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) return true;
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) return true;
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) return true;

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) return true;

        return false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("Player1Turn", player1Turn);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}

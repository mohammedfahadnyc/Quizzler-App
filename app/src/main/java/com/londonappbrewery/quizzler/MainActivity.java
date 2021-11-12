package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare member variables here:
    Button mTrueButton, mFalseButton;
    TextView mQuestionTextView, mScoreTextView;
    ProgressBar mProgressBar;
    int mIndex, mQuestionId, mScore;
    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int)Math.ceil (100.00/mQuestionBank.length);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton= (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar= (ProgressBar) findViewById(R.id.progress_bar);
        mQuestionId = mQuestionBank[mIndex].getQuestionId();
        mQuestionTextView.setText(mQuestionId);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });


    }

    private void updateQuestion ()
    {
        mIndex = (mIndex+1) % mQuestionBank.length;
        if (mIndex==0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("You scored"+ mScore);
            alert.setCancelable(false);
            alert.setTitle("Game ended!");
            alert.setPositiveButton("Close the app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestionId = mQuestionBank[mIndex].getQuestionId();
        mQuestionTextView.setText(mQuestionId);
        mScoreTextView.setText("Score : "+ mScore+" / "+mQuestionBank.length);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

    }

    private void checkAnswer (boolean userAnswer)
    {
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if (userAnswer == correctAnswer)
        {
            mScore += 1;
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }




}

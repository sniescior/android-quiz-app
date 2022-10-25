package com.awesome.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;

    private Question[] questions = new Question[] {
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_version, false),
    };

    private int currentIndex = 0;
    private static final String KEY_CURRENT_INDEX = "currentIndex";

    public static final String KEY_EXTRA_ANSWER = "com.awesome.correctAnswer";

    private static final int REQUEST_CODE_PROMPT = 0;

    // Check user's answer to the question
    private void validateAnswer(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(answerWasShown) {
                resultMessageId = R.string.answer_was_shown;
        } else {
            if(userAnswer == correctAnswer) {
                resultMessageId = R.string.a_correct;
            } else {
                resultMessageId = R.string.a_incorrect;
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_LONG).show();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    void writeToLog(String Type) {
        final String TAG = "On " + Type + " Method";
        Log.d(TAG, "fired up");
    }

    private static Boolean answerWasShown = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) { return; }
        if(requestCode == REQUEST_CODE_PROMPT) {
            if(data == null) { return; }
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
            System.out.println(answerWasShown);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeToLog("Create");

        if(savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton = findViewById(R.id.button_true);
        falseButton = findViewById(R.id.button_false);
        nextButton = findViewById(R.id.button_next);
        promptButton = findViewById(R.id.promptButton);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(v -> validateAnswer(true));
        falseButton.setOnClickListener(v -> validateAnswer(false));
        nextButton.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1)%questions.length;
            answerWasShown = false;
            setNextQuestion();
        });

        promptButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
//            startActivity(intent);
            startActivityForResult(intent, REQUEST_CODE_PROMPT);
        });

        setNextQuestion();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        writeToLog("SAVE STATE");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        writeToLog("Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        writeToLog("Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeToLog("Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        writeToLog("Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        writeToLog("Destroy");
    }
}
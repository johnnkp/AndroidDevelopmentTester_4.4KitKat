package hkcc.ccn3165.assignment.maths;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.development.R;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static TextToSpeech tts;
    int firstNumber = 0, secondNumber = 0;
    String mOperator;
    TextView answer, countdown, question;
    Button submit;
    EditText input;
    boolean isAnswered = false;
    AlertDialog skip;
    public static byte questionIndex = 0, score = 0;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_question);
        // 建立 TTS
        createLanguageTTS();

        question = findViewById(R.id.question);
        question.setText(question());

        answer = findViewById(R.id.answer);
        input = findViewById(R.id.input);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                int userAnswer, correctAnswer = 0;
                switch (mOperator) {
                    case "+":
                        correctAnswer = firstNumber + secondNumber;
                        break;

                    case "-":
                        correctAnswer = firstNumber - secondNumber;
                        break;

                    case "×":
                        correctAnswer = firstNumber * secondNumber;
                        break;

                    case "÷":
                        correctAnswer = firstNumber / secondNumber;
                        break;
                }

                if (input.getText().toString().equals("")) {
                    String mNotEntered = "You haven't entered anything!", mTry = "Try to answer this question!";
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Try to answer")
                            .setMessage(mNotEntered + "\n" + mTry)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                }
                            })
                            .show();
                    //【英文】發音
                    // tts.speak(mNotEntered + mTry, TextToSpeech.QUEUE_FLUSH, null);
                    // userAnswer = -1;
                } else {
                    userAnswer = Integer.parseInt(input.getText().toString());
                    input.setEnabled(false);
                    isAnswered = true;

                    submit.setEnabled(false);
                    String mCorrectness, mCorrectOutput = "Correct answer is " + correctAnswer, mCheer;
                    if (userAnswer == correctAnswer) {
                        score++;
                        mCorrectness = "You are right!";
                        mCheer = "Great job!";
                        answer.setTextColor(Color.MAGENTA);
                        answer.setText(mCorrectness + "\n" +
                                mCheer);
                        tts.speak(mCorrectness + mCheer, TextToSpeech.QUEUE_FLUSH, null);
                    } else {
                        mCorrectness = "That’s not right.";
                        mCheer = "You can do better next question!";
                        answer.setTextColor(Color.RED);
                        answer.setText(mCorrectness + "\n" +
                                mCorrectOutput + "\n" +
                                mCheer);
                    }
                }

            }
        });

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                if (!isAnswered) {
                    String mNotAnswered = "You haven't answered this question!", mSkipQuestion = "Do you want to skip?";
                    if (skip == null) {
                        AlertDialog.Builder skipQuestionBuilder = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Try to answer")
                                .setMessage(mNotAnswered + "\n" +
                                        mSkipQuestion)
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                    }
                                })
                                .setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        nextQuestion(view);
                                    }
                                });
                        skip = skipQuestionBuilder.create();
                        skip.setOnShowListener(new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface arg0) {
                                skip.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                                    skip.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                                }
                            }
                        });
                    }
                    skip.show();
                    // tts.speak(mNotAnswered + mSkipQuestion, TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    nextQuestion(view);
                }
            }
        });

        countdown = findViewById(R.id.timer);
        if (timer == null) {
            timer = new CountDownTimer(300000 + 500, 1000) { // https://yifeng.studio/2017/09/26/android-countdowntimer-using-attentions/
                public void onTick(long millisUntilFinished) {
                    countdown.setText(millisUntilFinished / 60000 + " : " + millisUntilFinished / 1000 % 60);
                }
                public void onFinish() {
                    countdown.setText("Time's up!");
                    input.setEnabled(false);
                    submit.setEnabled(false);
                    Intent mSummary = new Intent(MainActivity.this, Summary.class);
                    startActivityForResult(mSummary, ++questionIndex);
                }
            }.start();
        }
    }

    private String question() {
        Random myRandom = new Random();
        String mQuestion = "Question: ", operator[] = {"+", "-", "×", "÷"};

        mOperator = operator[myRandom.nextInt(4)];
        switch (mOperator) {
            case "+":
                firstNumber = 1 + myRandom.nextInt(99);
                secondNumber = 1 + myRandom.nextInt(99);
                break;

            case "-":
                firstNumber = 1 + myRandom.nextInt(99);
                do {
                    secondNumber = myRandom.nextInt(100);
                } while (firstNumber < secondNumber);
                break;

            case "×":
                firstNumber = 1 + myRandom.nextInt(20);
                secondNumber = 1 + myRandom.nextInt(20);
                break;

            case "÷":
                firstNumber = 1 + myRandom.nextInt(99);
                do {
                    secondNumber = myRandom.nextInt(100);
                } while (secondNumber == 0 || firstNumber % secondNumber != 0);
                break;
        }
        mQuestion = mQuestion + firstNumber + mOperator + secondNumber + " = ?";
        return mQuestion;
    }

    private void nextQuestion(View view) {
        if (countdown.getText().toString().equals("Time's up!")) {
            input.setEnabled(false);
            submit.setEnabled(false);
            Intent mSummary = new Intent(view.getContext(), Summary.class);
            startActivityForResult(mSummary, ++questionIndex);
        } else if (questionIndex < 9) {
            questionIndex++;
            question.setText(question());
            isAnswered = false;
            answer.setTextColor(Color.BLACK);
            answer.setText("Answer:");
            input.setEnabled(true);
            submit.setEnabled(true);
        } else {
            input.setEnabled(false);
            submit.setEnabled(false);
            Intent mSummary = new Intent(view.getContext(), Summary.class);
            startActivityForResult(mSummary, ++questionIndex);
        }
    }

    protected void onDestroy() {
        if (tts != null) tts.shutdown(); // 釋放 TTS
        if (timer != null) timer.cancel(); // http://yifeng.studio/2017/09/26/android-countdowntimer-using-attentions/
        super.onDestroy();
    }

    // https://tomkuo139.blogspot.com/2016/03/android-tts-api-text-to-speech.html
    private void createLanguageTTS() {
        if (tts == null) {
            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int arg0) {
                    // TTS 初始化成功
                    if (arg0 == TextToSpeech.SUCCESS) {
                        // 目前指定的【語系+國家】TTS, 已下載離線語音檔, 可以離線發音
                        if (tts.isLanguageAvailable(Locale.UK) == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                            tts.setLanguage(Locale.UK); // 不要用 Locale.ENGLISH, 會預設用英文(印度)
                        } else if (tts.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                            tts.setLanguage(Locale.US);
                        }
                    }
                }
            }
            );
        }
    }
}

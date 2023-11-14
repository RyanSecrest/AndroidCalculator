package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView outputTv;
    TextView topTv;
    MaterialButton button_c,button_d,button_percent;
    MaterialButton button_minus,button_plus,button_multiply,button_divide,button_equal;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    MaterialButton button_dot,button_M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputTv = findViewById(R.id.output_tv);
        topTv = findViewById(R.id.top_tv);

        assignId(button_c,R.id.button_c);
        assignId(button_d,R.id.button_D);
        assignId(button_percent,R.id.button_percent);
        assignId(button_minus,R.id.button_minus);
        assignId(button_plus,R.id.button_plus);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_divide,R.id.button_divide);
        assignId(button_equal,R.id.button_equal);
        assignId(button_0,R.id.button_0);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_dot,R.id.button_dot);
        assignId(button_M,R.id.button_M);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = topTv.getText().toString();

        if (buttonText.equals("C")) {
            topTv.setText("");
            outputTv.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            String result = compute(topTv.getText().toString());
            topTv.setText(result);
            outputTv.setText(result);
            return;
        }

        if (buttonText.equals("D")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        topTv.setText(dataToCalculate);

        String result = compute(dataToCalculate);

        if (!result.equals("Err")) {
            outputTv.setText(result);
        } else {
            outputTv.setText("0");
        }
    }



    String compute(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return result;
        } catch (Exception e) {
            Log.e("CalculatorApp", "Error evaluating JavaScript: " + e.getMessage());
            return "Err";
        }
    }

}

package com.example.calcapp;

//import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView calculation_view, solution_view;
    MaterialButton buttonC, buttonOpenBracket, buttonCloseBracket;
    MaterialButton buttonDivide, buttonMultiply, buttonAdd, buttonSubtract, buttonEqualTo;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculation_view = findViewById(R.id.calculation_view);
        solution_view = findViewById(R.id.solution_view);

        assignId(buttonC,R.id.button_c);
        assignId(buttonOpenBracket,R.id.button_open_bracket);
        assignId(buttonCloseBracket,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonSubtract,R.id.button_subtract);
        assignId(buttonAdd,R.id.button_Add);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button0,R.id.button_0);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonEqualTo,R.id.button_equals_to);

    }
    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_view.getText().toString();

        if (buttonText.equals("AC")){
            solution_view.setText("");
            calculation_view.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_view.setText(calculation_view.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }

        solution_view.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")){
            calculation_view.setText(finalResult);
        }
    }
    //final

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;
        }catch(Exception e){
            return "Err";
        }
    }
}


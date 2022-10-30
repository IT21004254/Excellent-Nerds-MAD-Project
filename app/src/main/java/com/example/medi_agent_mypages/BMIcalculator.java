package com.example.medi_agent_mypages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class BMIcalculator extends AppCompatActivity {


    private EditText BMIheight,BMIweight;
    private TextView BMIscore, BMIcategory;
    private Button BMIbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        BMIheight = (EditText) findViewById(R.id.tbBMIheight);
        BMIweight = (EditText) findViewById(R.id.tbBMIweight);
        BMIscore = (TextView) findViewById(R.id.lblBMIscoreprint);
        BMIcategory = (TextView) findViewById(R.id.lblBMIcategoryprint);
        BMIbutton = (Button) findViewById(R.id.btnBMIcalcbutton);


        BMIbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmicalculate();
            }
        });


    }

    private void bmicalculate(){

        double heightInCm = Double.parseDouble(BMIheight.getText().toString());
        double weighInKg = Double.parseDouble(BMIweight.getText().toString());
        double bmiResult;
            if (BMIheight.length() == 0 || BMIweight.length() == 0){

                Toast.makeText(this,"Values for both Height and Weight are needed ", Toast.LENGTH_LONG).show();

            } else {

                bmiResult = (weighInKg / ((heightInCm/100)*(heightInCm/100)));

                BMIscore.setText(String.format("%.2f",bmiResult));

                if (bmiResult < 18.5){
                    BMIcategory.setTextColor(Color.RED);
                    BMIcategory.setText("Underweight");
                }

                else if (bmiResult >= 18.5 && bmiResult < 25){

                    BMIcategory.setTextColor(Color.GREEN);
                    BMIcategory.setText("Healthy Weight");
                }
                else if(bmiResult >= 25 && bmiResult < 30){

                    BMIcategory.setTextColor(Color.YELLOW);
                    BMIcategory.setText("OverWeight");

                }
                else{
                    BMIcategory.setTextColor(Color.RED);
                    BMIcategory.setText("Obese");
                }


            }



    }
}
package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {
TextView input,output;
Button all_clear,clear,equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            input=findViewById(R.id.input);
            output=findViewById(R.id.output);
            all_clear=findViewById(R.id.ac);
            clear=findViewById(R.id.c);
            equal=findViewById(R.id.btneq);
            equal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String data = input.getText().toString();
                    Context context = Context.enter();
                    context.setOptimizationLevel(-1);
                    Scriptable scriptable = context.initStandardObjects();
                    String result = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
                    output.setText(result);
                }
            });
            all_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    input.setText("");
                    output.setText("0");
                }
            });
            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text=input.getText().toString();
                    input.setText(input.getText().toString().substring(0,text.length()-1));
                }
            });
            return insets;
        });
    }

    public void getText(View view) {
        Button btn=(Button) view;

        input.setText(input.getText().toString()+btn.getText().toString());
    }

}
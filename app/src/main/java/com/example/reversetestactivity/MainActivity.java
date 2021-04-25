package com.example.reversetestactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView TvIpHeader, TvResult, TvOpHeader;
    EditText EtInput, EtSkipNumber;
    Button button;
    HashMap<Character, Integer> charCountMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TvIpHeader = findViewById(R.id.TvIpHeader);
        TvOpHeader = findViewById(R.id.TvOpHeader);
        EtInput = findViewById(R.id.EtInput);
        TvResult = findViewById(R.id.TvResult);
        EtSkipNumber = findViewById(R.id.EtSkipNumber);
        button = findViewById(R.id.button);

        charCountMap = new HashMap<>();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = EtInput.getText().toString();
                int idealNumber = 0;
                if (!EtSkipNumber.getText().toString().isEmpty()) {
                    idealNumber = Integer.parseInt(EtSkipNumber.getText().toString());
                }

                if (!EtInput.getText().toString().isEmpty()) {
                    String[] data = EtInput.getText().toString().split("\\.");
//
//                    textview2.setText(reverseWords(data[0]));
                    TvResult.setText(reverseText(data, idealNumber));

                } else {
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String reverseText(String[] data, int idealNumber) {
        String outPut = "";
        for (String datum : data) {
            outPut = outPut + reverseWords(datum, idealNumber) + ". ";
        }

        return outPut;
    }

    static String reverseWords(String str, int idealNumber) {

        Pattern pattern = Pattern.compile("\\s");
        String[] temp = pattern.split(str);
        String result = "";

        final int _length = temp.length;

        String idealEndingSting = "";
        int idealPosition = idealNumber != 0 ? idealNumber - 1 : 0;

        if (idealPosition >= 0 && idealPosition < temp.length) {

            idealPosition = temp.length - 1 - idealPosition; // 5 -2

            for (int i = temp.length - 1; i >= 0; i--) {
                if (i >= idealPosition)
                    idealEndingSting = temp[i] + (idealEndingSting.isEmpty() ? "" : " ") + idealEndingSting;
            }

            String[] _tempTemp = new String[idealPosition];

            for (int i = 0; i < temp.length; i++) {
                if (i < idealPosition)
                    _tempTemp[i] = temp[i];
            }
            temp = _tempTemp;
        }
        if (idealPosition >= _length) {

            for (int i = 0; i < temp.length; i++) {
                result = result + (!result.isEmpty() ? " " : "") + temp[i];
            }

        } else {
            for (int i = 0; i < temp.length; i++) {
//                if (i == temp.length - 1)
//                    result = temp[i] + result;
//                else
                result = temp[i] + (!result.isEmpty() ? " " : "") + result;
            }
        }

        return result + (result.isEmpty() ? "" : " ") + idealEndingSting;
    }
}

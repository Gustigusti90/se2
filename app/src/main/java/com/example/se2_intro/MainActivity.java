package com.example.se2_intro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MainActivity extends AppCompatActivity {

    EditText matrikelInput;
    Button sendButton;
    TextView textOutput;
    Button convertButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelInput=findViewById(R.id.input);
        sendButton=findViewById(R.id.button);
        textOutput=findViewById(R.id.serverReply);
        convertButton=findViewById(R.id.convertButton);


        // Server Connection:
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = matrikelInput.getText().toString();
                TCPClient connection = new TCPClient(input);

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<String> result = executorService.submit(connection);

                try {
                    textOutput.setText(result.get());
                } catch (InterruptedException e) {
                    Log.e("Interrupted Exception",e.getMessage());
                } catch (ExecutionException e) {
                    Log.e("Execution Exception",e.getMessage());
                }
            }
        });


        // ASCII Converter:
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = matrikelInput.getText().toString();

                if (input.isEmpty()) {
                    textOutput.setText("Bitte MatrikelNr eingeben");
                }
                else if (input.length()!=8) {
                    textOutput.setText("MatrikelNr muss 8 stellig sein!");
                }
                else {
                    char[] chr = input.toCharArray();
                    char[] output = new char[8];
                    String res = "";

                    for (int i=0; i<chr.length; i++ ){
                        if (i%2==0)
                            res+=chr[i];
                        else {
                            int temp= chr[i]+48;
                            char ascii = (char)temp;
                            res += ascii;
                        }
                    }
                    textOutput.setText(res);
                }
            }
        });

    }

}
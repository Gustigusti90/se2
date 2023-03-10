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
    TextView serverReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelInput=findViewById(R.id.input);
        sendButton=findViewById(R.id.button);
        serverReply=findViewById(R.id.serverReply);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = matrikelInput.getText().toString();
                TCPClient connection = new TCPClient(input);

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<String> result = executorService.submit(connection);

                try {
                    serverReply.setText(result.get());
                } catch (InterruptedException e) {
                    Log.e("Interrupted Exception",e.getMessage());
                } catch (ExecutionException e) {
                    Log.e("Execution Exception",e.getMessage());
                }
            }
        });




    }
}
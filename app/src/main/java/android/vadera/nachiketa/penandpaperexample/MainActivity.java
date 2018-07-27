package android.vadera.nachiketa.penandpaperexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.vadera.nachiketa.pen_paper.AndroidReadWrite;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView textView = null;
    EditText editText = null;
    AndroidReadWrite androidReadWrite = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tvDisplay);
        editText = (EditText) findViewById(R.id.etInput);

        androidReadWrite = new AndroidReadWrite();
    }

    public void writeFile(View view) {
        androidReadWrite.saveToExternalDir("PenAndPaper", "test.txt", editText.getText().toString());
        Toast.makeText(this, "Written", Toast.LENGTH_SHORT).show();
    }

    public void readFile(View view) {
        textView.setText(androidReadWrite.loadFromExternalDir("PenAndPaper", "test.txt"));
    }

}

package android.vadera.nachiketa.penandpaperexample;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.vadera.nachiketa.pen_paper.AndroidReadWrite;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView = null;
    EditText editText = null;
    Spinner spinner = null;
    AndroidReadWrite androidReadWrite = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tvDisplay);
        editText = (EditText) findViewById(R.id.etInput);
        spinner = (Spinner) findViewById(R.id.spnMode);

        androidReadWrite = new AndroidReadWrite();
    }

    public void writeFile(View view) {
        switch (spinner.getSelectedItem().toString()) {
            case "--Select a mode--":
                Toast.makeText(this, "Select a mode first", Toast.LENGTH_SHORT).show();
                break;

            case "Context":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    androidReadWrite.saveWithContext(this, "testFile.txt", editText.getText().toString(), Context.MODE_NO_LOCALIZED_COLLATORS);
                }
                Toast.makeText(this, "Saved - Check Log(i) for details", Toast.LENGTH_SHORT).show();
                break;

            case "External Directory":
                androidReadWrite.saveToExternalDir("PenAndPaper", "example.txt", editText.getText().toString());
                Toast.makeText(this, "Saved - Check Log(i) for details", Toast.LENGTH_SHORT).show();
                break;

            case "Assets":
                Toast.makeText(this, "You cannot write to assets at runtime", Toast.LENGTH_SHORT).show();
                break;

            case "Raw":
                Toast.makeText(this, "You cannot write to raw at runtime", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void readFile(View view) {
        switch (spinner.getSelectedItem().toString()) {
            case "--Select a mode--":
                Toast.makeText(this, "Select a mode first", Toast.LENGTH_SHORT).show();
                break;

            case "Context":
                textView.setText(androidReadWrite.loadFromContext(this, "testFile.txt"));
                Toast.makeText(this, "Loaded - Check Log(i) for details", Toast.LENGTH_SHORT).show();
                break;

            case "External Directory":
                textView.setText(androidReadWrite.loadFromExternalDir("PenAndPaper", "example.txt"));
                Toast.makeText(this, "Loaded - Check Log(i) for details", Toast.LENGTH_SHORT).show();
                break;

            case "Assets":
                textView.setText(androidReadWrite.loadFromAssets("sample.csv", getAssets()));
                Toast.makeText(this, "Loaded - Check Log(i) for details", Toast.LENGTH_SHORT).show();
                break;

            case "Raw":
                textView.setText(androidReadWrite.loadFromRaw("hello.txt"));
                Toast.makeText(this, "Loaded - Check Log(i) for details", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}

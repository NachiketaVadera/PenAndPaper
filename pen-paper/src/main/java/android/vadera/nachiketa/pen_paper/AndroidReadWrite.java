package android.vadera.nachiketa.pen_paper;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Objects;

public class AndroidReadWrite {

    private static final String TAG = "AndroidReadWrite";

    // Writing file with context -----------------------------------------------------------------------------

    public boolean saveWithContext(Context context, String fileName, String data, int contextMode) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(fileName, contextMode);
            Writer writer = new OutputStreamWriter(Objects.requireNonNull(fileOutputStream));
            writer.write(data);
            writer.close();
            Log.i(TAG, "saveFileWithContext: Saved " + fileName + " successfully");
            return true;
        } catch (IOException e) {
            Log.i(TAG, "saveFileWithContext: Error while saving " + fileName + "\n" + e.getMessage());
            return false;
        }
    }

    // Reading file from context ---------------------------------------------------------------------------

    public String loadFromContext(Context context, String fileName) {
        StringBuilder builder = new StringBuilder();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(fileInputStream)));
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                builder.append(data);
            }
            bufferedReader.close();
            Log.i(TAG, "loadFileFromContext: File " + fileName + " loaded successfully");
            return builder.toString();
        } catch (IOException e) {
            Log.i(TAG, "loadFileFromContext: Error while loading " + fileName + "\n" + e.getMessage());
            return "Error : " + e.getMessage();
        }
    }

    // Reading file to res/raw folder---------------------------------------------------------------------------

    public String loadFromRaw(String fileName) {
        StringBuilder builder = new StringBuilder();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("res/raw/" + fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        String data;
        try {
            while ((data = bufferedReader.readLine()) != null) {
                builder.append(data);
            }
            bufferedReader.close();
            Log.i(TAG, "loadFromRaw: File " + fileName + " loaded successfully");
            return builder.toString();
        }
        catch (IOException e) {
            Log.i(TAG, "loadFromRaw: Error while reading " + fileName + "\n" + e.getMessage());
            return "Error : " + e.getMessage();
        }
    }

    // Reading file from assets folder ---------------------------------------------------------------------------

    public String loadFromAssets(String fileName, AssetManager assets) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assets.open(fileName)));
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                builder.append(data);
            }
            bufferedReader.close();
            Log.i(TAG, "readFromAssets: File " + fileName + " loaded successfully");
            return builder.toString();
        }
        catch (IOException e) {
            Log.i(TAG, "readFromAssets: Error while reading " + fileName + "\n" + e.getMessage());
            return "Error : " + e.getMessage();
        }
    }

    // Write file to external storage ---------------------------------------------------------------------------

    public boolean saveToExternalDir(String directoryName,String fileName, String data) {
        String root = Environment.getExternalStorageDirectory().toString();
        File directory = new File(root + directoryName);
        if (!directory.exists()) {
            Log.i(TAG, "saveToExternalDir: Directory " + directoryName + " does not exist");
            if (directory.mkdirs()) {
                Log.i(TAG, "saveToExternalDir: Directory " + directoryName + " created");
            } else {
                Log.i(TAG, "saveToExternalDir: Error while creating " + directoryName);
            }
        } else {
            Log.i(TAG, "saveToExternalDir: Directory found");
        }
        File file = new File(directory, fileName);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(Objects.requireNonNull(fileOutputStream));
            writer.write(data);
            writer.close();
            Log.i(TAG, "saveToExternalDir: Saved " + fileName + " in " + directoryName + " successfully");
            return true;
        } catch (IOException e) {
            Log.i(TAG, "saveToExternalDir: Error while saving " + fileName + " in " + directoryName + "\n" + e.getMessage());
            return false;
        }
    }

    // read file from external storage ---------------------------------------------------------------------------

    public String loadFromExternalDir(String directoryName, String fileName) {
        String root = Environment.getExternalStorageDirectory().toString();
        File directory = new File(root + directoryName);
        File file = new File(directory, fileName);

        StringBuilder builder = new StringBuilder();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(fileInputStream)));
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                builder.append(data);
            }
            bufferedReader.close();
            Log.i(TAG, "loadFromExternalDir: File " + fileName + " loaded successfully");
            return builder.toString();
        } catch (IOException e) {
            Log.i(TAG, "loadFromExternalDir: Error while loading " + fileName + "\n" + e.getMessage());
            return "Error : " + e.getMessage();
        }
    }
}

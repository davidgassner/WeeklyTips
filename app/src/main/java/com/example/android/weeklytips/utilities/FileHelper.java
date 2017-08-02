package com.example.android.weeklytips.utilities;

import android.content.Context;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHelper {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    private File storageDirectory;
    private Context context;

    public FileHelper(Context context) {
        this.context = context;
        storageDirectory = context.getFilesDir();
    }

    public boolean copyAssetToStorage(String fileName) {

        File file = new File(storageDirectory, fileName);
        if (file.exists()) {
            return true;
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = context.getAssets().open(fileName);
            outputStream = new FileOutputStream(file);
            copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStream(inputStream);
            closeStream(outputStream);
        }
        return true;
    }

    public File getFile(String fileName) {
        return new File(storageDirectory, fileName);
    }

    private void copy(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int n;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
    }

    private static void closeStream(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

}

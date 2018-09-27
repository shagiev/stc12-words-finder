package finder;

import java.io.*;

public class ResultWriterThread {

    private BufferedOutputStream stream;

    public ResultWriterThread(String filename) {
        try {
            this.stream = new BufferedOutputStream(new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void write(String sentence) {
        try {
            this.stream.write(sentence.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (this.stream == null) {
            return;
        }
        try {
            this.stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stream = null;
    }
    // Destructor??
}

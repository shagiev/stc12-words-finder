package finder;

import java.io.*;

public class ResultWriter {

    private BufferedOutputStream stream;

    public ResultWriter(String filename) {
        try {
            this.stream = new BufferedOutputStream(new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void write(String sentence) {
        if (sentence == "") {
            return;
        }
        sentence = sentence.trim() + "\n";
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
            this.stream.flush();
            this.stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stream = null;
    }
    // Destructor??
}

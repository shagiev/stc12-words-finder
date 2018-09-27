package finder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileCrawlerThread extends Thread {

    private String filename;
    private BufferedInputStream stream;

    public FileCrawlerThread(String filename) {
        this.filename = filename;
    }

    public void run() {
        try {
            stream = new BufferedInputStream(new FileInputStream(filename));
//            do {
//
//            } while ();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
//
//    private String readSentence() {
//        char ch;
//        while (ch !== '.' && stream.)
//    }

}

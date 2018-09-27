import finder.Crawler;

public class Main {
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        String res = "result.txt";
        String[] sources = {"tolstoj_lew_nikolaewich-text_0040.fb2", "azazel.htm"};
        String[] words = {"понедельник", "вторник"};
        crawler.getOccurencies(sources, words, res);
    }

}

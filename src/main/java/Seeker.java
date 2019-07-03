import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Seeker {

    static List<String> wordsInfo = new ArrayList<>();
    static List<Verb> verbs = new ArrayList<>();


    public static void readFile() throws IOException {
        FileReader fr = new FileReader("resources/vocabulary.txt");
        Scanner scan = new Scanner(fr);

        while (scan.hasNextLine()) {
            wordsInfo.add(scan.nextLine());
        }

        fr.close();
    }

    public static void parseFileInfo() {

        for (String wordInfo : wordsInfo) {
            String[] splitResult = wordInfo.split(";");
            Verb verb = new Verb(splitResult[0].charAt(0), splitResult[1], splitResult[2], splitResult[3], splitResult[4]);
            verbs.add(verb);
        }
    }

    public static List<Verb> findFormsByVerb(String verb) {
        char letter = verb.charAt(0);
        List<Verb> filteredVerbsByLetter = verbs.stream().filter(v -> v.getLetter() == letter).collect(Collectors.toList());
        return filteredVerbsByLetter.stream().filter(v -> v.getInfinitive().equals(verb)).collect(Collectors.toList());
    }
}

package sample;

import java.util.List;

public class Controller {
    public static String transpose(String string, int interval) {
        List<String> chords = Splitter.split(string);

        for (int i = 0; i < chords.size(); i++) {
            chords.set(i, Transposer.transpose(chords.get(i), interval));
        }

        return Splitter.concat(chords);
    }
}

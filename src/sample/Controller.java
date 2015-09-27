package sample;

public class Controller {
    public static String transpose(String string, int interval) {
        String[] chords = Splitter.split(string);

        for (int i = 0; i < chords.length; i++) {
            if(chords[i].matches("[A-G](.*)")) {
                chords[i] = Transposer.transpose(chords[i], interval);
            }
        }

        return Splitter.concat(chords);
    }
}

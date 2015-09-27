package sample;

/**
 * Created by zscse on 2015. 09. 27..
 */
public class Transposer {
    private static final String[] scale = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    private static int getValue(String pitch) throws IllegalArgumentException {
        for (int i = 0; i < 12; i++) {
            if (scale[i].equals(pitch)) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    private static String getPitch(int value) {
        return scale[value];
    }

    private static int transpose(int value, int interval) {
        value += interval;
        return ((value % 12) + 12) % 12;
    }

    public static String transpose(String pitch, int interval) {
        int value = getValue(pitch);
        value = transpose(value, interval);
        return getPitch(value);
    }
}
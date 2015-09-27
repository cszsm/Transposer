package sample;

/**
 * Created by zscse on 2015. 09. 27..
 */
public class Transposer {
    private static final String[] scale_b = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    private static final String[] scale_h = {"A", "B", "H", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    private static boolean h = true;

    private static int getValue(String pitch) throws IllegalArgumentException {
        for (int i = 0; i < 12; i++) {
            if (!h && scale_b[i].equals(pitch) ||
                    h && scale_h[i].equals(pitch)) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    private static String getPitch(int value) {
        return h ? scale_h[value] : scale_b[value];
    }

    private static int transpose(int value, int interval) {
        value += interval;
        return ((value % 12) + 12) % 12;
    }

    public static String transpose(String chord, int interval) {
        String pitch;
        String end;

        if (!chord.matches("[A-H](.*)")) {
            return chord;
        }

        if (chord.matches(".#(.*)")) {
            pitch = chord.substring(0, 2);
            end = chord.substring(2);
        } else {
            pitch = chord.substring(0, 1);
            end = chord.substring(1);
        }

        int value = getValue(pitch);
        value = transpose(value, interval);
        return getPitch(value) + end;
    }

    public static void setH(boolean h) {
        Transposer.h = h;
    }
}
package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zscse on 2015. 09. 27..
 */
public class Splitter {
    public static List<String> split(String string) {
        String[] lines = string.split("\n");
        List<String> list = new ArrayList<>();

        for (String line : lines) {
            String[] words = line.split(" ");
            list.addAll(Arrays.asList(words));
            list.add("\n");
        }
        return list;
    }

    public static String concat(List<String> strings) {
        String tmp = new String();

        for (String s : strings) {
            if (!s.equals("\n")) {
                s += " ";
            }
            tmp += s;
        }
        return tmp;
    }
}

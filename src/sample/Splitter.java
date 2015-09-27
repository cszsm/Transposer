package sample;

import java.util.List;

/**
 * Created by zscse on 2015. 09. 27..
 */
public class Splitter {
    public static String[] split(String string) {
        return string.split(" ");
    }

    public static String concat(String[] strings) {
        String tmp = new String();

        for (String string : strings) {
            if (!string.equals("\n")) {
                string += " ";
            }

            tmp += string;
        }

        return tmp;
    }
}

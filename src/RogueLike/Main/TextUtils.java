package RogueLike.Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextUtils {
    public static class LineTooLongException extends IllegalArgumentException {
        public LineTooLongException(String line, int maxLength) {
            super(String.format("Line too long (%d > %d): '%s'", line.length(), maxLength, line));
        }
    }

    /**
     * Join a list of strings together into a sequence of lines, not splitting strings across lines, and with each
     * line being at most maxLength in length.
     * @param strings The list of strings to join.
     * @param delimiter The delimiter to insert between each of the strings.
     * @param maxLength The maximum line length.
     * @return A list of strings; each element is one line of joined input strings.
     * @throws LineTooLongException if any string is so long that it can't fit on its own line.
     */
    public static ArrayList<String> joinStringsWithLineBreaks(List<String> strings, CharSequence delimiter, int maxLength)
    {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        // need to use an iterator here so that we can use hasNext() within the loop body
        for (Iterator<String> it = strings.iterator(); it.hasNext();) {
            String nextString = it.next();
            if (it.hasNext()) {
                nextString += delimiter;
            }

            if (currentLine.length() + nextString.length() > maxLength) {
                if (currentLine.length() == 0) {
                    // then there's no way we can fit the string, even on its own line - throw an exception!
                    throw new LineTooLongException(nextString, maxLength);
                }
                //otherwise, we just start a new line
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(nextString);
            }
            else {
                // it fits on the current line, so add it on
                currentLine.append(nextString);
            }
        }

        // add the final line
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    public static String sentenceCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}

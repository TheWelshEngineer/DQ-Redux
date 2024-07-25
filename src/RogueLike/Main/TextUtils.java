package RogueLike.Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextUtils {
    public static class LineTooLongException extends IllegalArgumentException {
        private static final long serialVersionUID = 1L;

        public LineTooLongException(String line, int maxLength) {
            super(String.format("Line too long (%d > %d): '%s'", line.length(), maxLength, line));
        }
    }

    /**
     * Join a list of strings together into a sequence of lines, not splitting strings across lines,
     * and with each line being at most maxLength in length.
     *
     * @param strings The list of strings to join.
     * @param delimiter The delimiter to insert between each of the strings.
     * @param maxLength The maximum line length.
     * @param subsequentIndent The number of spaces by which to indent lines after the first.
     * @return A list of strings; each element is one line of joined input strings.
     * @throws LineTooLongException if any string is so long that it can't fit on its own line.
     */
    public static ArrayList<String> joinStringsWithLineBreaks(
            List<String> strings, CharSequence delimiter, int maxLength, int subsequentIndent) {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        // need to use an iterator here so that we can use hasNext() within the loop body
        for (Iterator<String> it = strings.iterator(); it.hasNext(); ) {
            String nextString = it.next();
            if (it.hasNext()) {
                nextString += delimiter;
            }

            if (currentLine.length() + nextString.length() > maxLength) {
                if (nextString.length() + subsequentIndent > maxLength) {
                    // then there's no way we can fit the string, even on its own line - throw an
                    // exception!
                    throw new LineTooLongException(nextString, maxLength);
                }
                // otherwise, we just start a new line
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(" ".repeat(subsequentIndent) + nextString);
            } else {
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

    public static ArrayList<String> joinStringsWithLineBreaks(
            List<String> strings, CharSequence delimiter, int maxLength) {
        return joinStringsWithLineBreaks(strings, delimiter, maxLength, 0);
    }

    /**
     * Wrap a string to a specified max length, preserving `\n` newlines, and indenting
     * automatically wrapped lines. Note that lines after a line break that was present in the
     * initial string are not indented. Breaks lines at whitespace boundaries.
     *
     * @param string The string to wrap.
     * @param maxLength The max length before wrapping.
     * @param subsequentIndent The number of spaces by which to indent lines after the first.
     * @return A string with lines separated by `\n` newline characters, and with each line at most
     *     `maxLength` in length.
     * @throws LineTooLongException if any word is so long that it can't fit on its own line.
     */
    public static String wordWrap(String string, int maxLength, int subsequentIndent) {
        ArrayList<String> newLines = new ArrayList<>();
        for (String line : string.split("\n")) {
            // Need to specifically handle empty lines here, as joinStringsWithLineBreaks() will
            // just discard them
            if (line.isEmpty()) {
                newLines.add("");
            }
            newLines.addAll(
                    TextUtils.joinStringsWithLineBreaks(
                            List.of(line.split(" ")), " ", maxLength, subsequentIndent));
        }

        return String.join("\n", newLines);
    }

    public static String sentenceCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}

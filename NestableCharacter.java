import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class NestableCharacter extends Nestable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 184829138437L;

    public static char[][] syntaxPairs = {{'(', ')'}, {'[', ']'}, {'{', '}'}};
    public static Map<Character, Character> mSyntaxPairs = new HashMap<>();
    public static Map<Character, NestEffect> charType = new HashMap<>();

    /*
     * populate the lookup tables for pairing and effects
     */
    static {
        for (char[] pair : syntaxPairs) {
            charType.put(pair[0], NestEffect.OPEN);
            charType.put(pair[1], NestEffect.CLOSE);
            mSyntaxPairs.put(pair[0], pair[1]);
            mSyntaxPairs.put(pair[1], pair[0]);
        }
    }

    Character value;

    public NestableCharacter(char c) {
        super(charType.getOrDefault(c, NestEffect.NEUTRAL));
        value = c;
    }

    public NestableCharacter(int i) {
        this((char) i);
    }

    @Override
    public boolean matches(Nestable other) {
        if (other == null || !(other instanceof NestableCharacter) || !this.effect.matches(other.effect))
            return false;

        NestableCharacter ncOther = (NestableCharacter) other;
        if (value == null)
            return ncOther.value == null;
        return value.equals(mSyntaxPairs.getOrDefault(ncOther.value, ncOther.value));
    }

    @Override
    public boolean equals(Object other) {
        if ((other == null) || !(other instanceof NestableCharacter))
            return false;
        NestableCharacter oc = (NestableCharacter) other;
        if (value == null)
            return oc.value == null;
        else
            return value.equals(oc.value);
    }

    public int hashCode() {
        return value.hashCode();
    }

    public String toString() {
        return "'" + value + "':" + effect;
    }

    /*
     * This function converts a String to a Queue of NestableCharacter
     */
    public static Queue<NestableCharacter> getNestableCharactersFromString(String input) {
        return input.chars().mapToObj(NestableCharacter::new)
                .collect(Collectors.toCollection(LinkedList<NestableCharacter>::new));
    }

    /*
     * This function reads a file into a Queue of NestableCharacter
     */
    public static Queue<NestableCharacter> getNestableCharactersFromFile(String filename) throws IOException {
        Queue<NestableCharacter> out = new LinkedList<>();
        FileReader in = new FileReader(filename);

        while (in.ready())
            out.add(new NestableCharacter(in.read()));
        in.close();

        return out;
    }
}

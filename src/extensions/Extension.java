package extensions;

import java.util.LinkedList;
import java.util.List;

public abstract class Extension {
    private static List<Extension> extensions = new LinkedList<>();
    public static List<Extension> getExtensions() {
        return new LinkedList<>(extensions);
    }

    protected String name;
    protected List<Conditioning> conditions = new LinkedList<>();

    public Extension() {
        extensions.add(this);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public boolean checkConditions(String hexFormatChain) {
        for(Conditioning condition: conditions) {
            if(!condition.isFullfiled(hexFormatChain)) {
                return false;
            }
        }
        return true;
    }
}

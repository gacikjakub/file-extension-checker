package extensions;

import java.util.regex.Pattern;

public class GIF extends Extension {

    public GIF() {
        this.name = "gif";
        this.conditions.add(new hexSignature());
    }

    private class hexSignature implements Conditioning {

        @Override
        public boolean isFullfiled(String hexFormatChain) {
            return Pattern.matches("^47 49 46 38 3[7|9] 61 [0-9A-F|\\s]*$", hexFormatChain);
        }
    }
}
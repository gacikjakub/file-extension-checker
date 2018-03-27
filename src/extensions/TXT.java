package extensions;

import java.util.regex.Pattern;

public class TXT extends Extension {

    @Override
    public boolean checkConditions(String hexFormatChain) {
        for(Conditioning condition: conditions) {
            if(condition.isFullfiled(hexFormatChain)) {
                return true;
            }
        }
        return false;
    }

    public TXT() {
        this.name = "txt";
        this.conditions.add(new UTF8());
        this.conditions.add(new UTF16());
        this.conditions.add(new UTF32());
    }

    private class UTF8 implements Conditioning {

        @Override
        public boolean isFullfiled(String hexFormatChain) {
            return Pattern.matches("^EF BB BF [0-9A-F|\\s]*$", hexFormatChain);
        }
    }

    private class UTF16 implements Conditioning {

        @Override
        public boolean isFullfiled(String hexFormatChain) {
            return Pattern.matches("^FF FE [0-9A-F|\\s]*$", hexFormatChain);
        }
    }

    private class UTF32 implements Conditioning {

        @Override
        public boolean isFullfiled(String hexFormatChain) {
            return Pattern.matches("^FF FE 00 00 [0-9A-F|\\s]*$", hexFormatChain);
        }
    }
}

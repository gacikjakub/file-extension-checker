package extensions;

import java.util.regex.Pattern;

public class JPEG extends Extension {

    @Override
    public boolean checkConditions(String hexFormatChain) {
        for(Conditioning condition: conditions) {
            if(condition.isFullfiled(hexFormatChain)) {
                return true;
            }
        }
        return false;
    }

    public JPEG() {
        this.name = "jpg";
        this.conditions.add(new RawFormat());
        this.conditions.add(new JFIFFormat());
        this.conditions.add(new ExifFormat());
    }

    private class RawFormat implements Conditioning {

        @Override
        public boolean isFullfiled(String hexFormatChain) {
            return hexFormatChain.startsWith("FF D8 FF D8");
        }
    }

    private class JFIFFormat implements Conditioning {

        @Override
        public boolean isFullfiled(String hexFormatChain) {
            return Pattern.matches("^FF D8 FF E0 ([0-9A-F]{2} ){2}4A 46 49 46 00 01 [0-9A-F|\\s]*$", hexFormatChain);
        }
    }

    private class ExifFormat implements Conditioning {

        @Override
        public boolean isFullfiled(String hexFormatChain) {
            return Pattern.matches("^FF D8 FF E1 ([0-9A-F]{2} ){2}45 78 69 66 00 00 [0-9A-F|\\s]*$", hexFormatChain);
        }
    }
}
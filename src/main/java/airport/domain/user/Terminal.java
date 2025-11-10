package airport.domain.user;

import java.util.Arrays;

public enum Terminal {
    T1("T1", "제1여객터미널"),
    T2("T2", "제2여객터미널");

    private final String code;
    private final String description;

    Terminal(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Terminal from(String code) {
        return Arrays.stream(values())
                .filter(terminal -> terminal.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 지원하지 않는 터미널입니다"));
    }


    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

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


    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

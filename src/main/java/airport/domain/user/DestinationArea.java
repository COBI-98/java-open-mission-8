package airport.domain.user;

import java.util.Arrays;

public enum DestinationArea {

    MYEONGDONG("명동/시청", "서울 도심 명동/시청 일대"),
    HONGDAE("홍대/마포", "홍대/마포 일대"),
    GANGNAM("강남/잠실", "강남/잠실 일대"),
    INCHEON("인천/부천", "인천/부천 일대");

    private final String label;
    private final String description;

    DestinationArea(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public static DestinationArea fromLabel(final String label) {
        return Arrays.stream(values())
                .filter(area -> area.label.equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "[ERROR] 목적지 구역이 올바르지 않습니다"
                ));
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}

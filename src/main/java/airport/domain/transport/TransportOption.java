package airport.domain.transport;

public class TransportOption {
    private final TransportType type;
    private final int etaMinutes;
    private final int transfers;
    private final int cost;

    public TransportOption(
            final TransportType type,
            final int etaMinutes,
            final int transfers,
            final int cost
    ) {
        this.type = type;
        this.etaMinutes = etaMinutes;
        this.transfers = transfers;
        this.cost = cost;
    }

    public TransportType getType() {
        return type;
    }

    public int getEtaMinutes() {
        return etaMinutes;
    }

    public int getTransfers() {
        return transfers;
    }

    public int getCost() {
        return cost;
    }
    
}

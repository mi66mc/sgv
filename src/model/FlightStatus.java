package model;

public enum FlightStatus {
    AGENDADO("Agendado"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluido"),
    CANCELADO("Cancelado");

    private final String dbValue;

    FlightStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    public String toDbValue() {
        return dbValue;
    }

    public static FlightStatus fromString(String status) {
        if (status == null) {
            return null;
        }
        for (FlightStatus fs : FlightStatus.values()) {
            if (fs.name().equalsIgnoreCase(status) || fs.dbValue.equalsIgnoreCase(status)) {
                return fs;
            }
        }
        // Fallback
        if ("Em andamento".equalsIgnoreCase(status)) {
            return EM_ANDAMENTO;
        }
        throw new IllegalArgumentException("Unknown flight status: " + status);
    }
}

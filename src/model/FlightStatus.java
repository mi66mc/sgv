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
        if (status == null || status.isBlank()) {
            return null;
        }

        String normalized = status.trim().toLowerCase();

        for (FlightStatus fs : values()) {
            if (fs.name().toLowerCase().equals(normalized) || fs.dbValue.toLowerCase().equals(normalized)) {
                return fs;
            }
        }

        throw new IllegalArgumentException("Status de voo desconhecido: " + status);
    }
}

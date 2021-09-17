package no.teknisk.test.model;

public enum Player {
    SAM("sam"),
    DEALER("dealer");

    private final String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

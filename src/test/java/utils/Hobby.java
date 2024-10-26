package utils;

public enum Hobby {
    SPORTS("Sports"), READING("Reading"), MUSIC("Music");
    private final String title;

    Hobby (String title) {
        this.title = title;
    }

    public String getHobby() {
        return title;
    }
}

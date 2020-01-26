class Data {
    String date;
    String title;
    String hour;
    String scene;
    String link;

    Data(String date, String title, String hour, String scene, String link) {
        this.date = date
        this.title = title
        this.hour = hour
        this.scene = scene
        this.link = link
    }

    @Override
    public String toString() {
        return "Data{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", hour='" + hour + '\'' +
                ", scene='" + scene + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}

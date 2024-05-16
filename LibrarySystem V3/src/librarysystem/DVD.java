package librarysystem;

import java.util.Objects;

public class DVD extends LibraryItem {

    private String UPC, subtitle;
    private int releaseYear, runningTime;

    public DVD(String UPC, String subtitle, int releaseYear, int runningTime, String title, String author, String category, Status s) {
        super(title, author, category, s);
        this.UPC = UPC;
        this.subtitle = subtitle;
        this.releaseYear = releaseYear;
        this.runningTime = runningTime;
    }

    public DVD() {

    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    @Override
    public String toString() {
        return super.toString() + "DVD{" + "UPC=" + UPC + ", subtitle=" + subtitle + ", releaseYear=" + releaseYear + ", runningTime=" + runningTime + '}';
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DVD)) {
            return false;
        }
        DVD dvd = (DVD) obj;
        return super.equals(obj)
                && Objects.equals(UPC, dvd.UPC)
                && releaseYear == dvd.releaseYear
                && Objects.equals(subtitle, dvd.subtitle)
                && runningTime == dvd.runningTime;
    }

}

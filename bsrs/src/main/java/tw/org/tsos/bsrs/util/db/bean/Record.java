package tw.org.tsos.bsrs.util.db.bean;

@SuppressWarnings("UnusedDeclaration")
public class Record {

    private long date;
    private int score;
    private int level;

    @Override
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", score=" + score +
                ", level=" + level +
                '}';
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

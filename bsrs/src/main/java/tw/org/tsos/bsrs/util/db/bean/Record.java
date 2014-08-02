package tw.org.tsos.bsrs.util.db.bean;

@SuppressWarnings("UnusedDeclaration")
public class Record {

    private long date;
    private int score;
    private String text;

    @Override
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", score=" + score +
                ", text='" + text + '\'' +
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

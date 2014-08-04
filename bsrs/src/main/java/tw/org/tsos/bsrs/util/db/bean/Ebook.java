package tw.org.tsos.bsrs.util.db.bean;

@SuppressWarnings("UnusedDeclaration")
public class Ebook {

    private String name;
    private String link;
    private String cover;

    @Override
    public String toString() {
        return "Ebook{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

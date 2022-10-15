public class GetSetNewsUsdEur {

private String nameNew;
private String urlNew;
private String dateNew;
private String textNews;
private String textNewsRia;
private String urlNewRia;
private String authorNewRia;
private String dateNewRia;


    public String getNameNew() {
        return nameNew;
    }

    public void setNameNew(String nameNew) {
        this.nameNew = nameNew;
    }

    public String getUrlNew() {
        return urlNew;
    }

    public void setUrlNew(String urlNew) {
        this.urlNew = urlNew;
    }

    public String getDateNew() {
        return dateNew;
    }

    public void setDateNew(String dateNew) {
        this.dateNew = dateNew;
    }

    public String getTextNews() {
        return textNews;
    }

    public void setTextNews(String textNews) {
        this.textNews = textNews;
    }

    public String getTextNewsRia() {
        return textNewsRia;
    }

    public void setTextNewsRia(String textNewsRia) {
        this.textNewsRia = textNewsRia;
    }

    public String getUrlNewRia() {
        return urlNewRia;
    }

    public void setUrlNewRia(String urlNewRia) {
        this.urlNewRia = urlNewRia;
    }

    public String getAuthorNewRia() {
        return authorNewRia;
    }

    public void setAuthorNewRia(String authorNewRia) {
        this.authorNewRia = authorNewRia;
    }

    public String getDateNewRia() {
        return dateNewRia;
    }

    public void setDateNewRia(String dateNewRia) {
        this.dateNewRia = dateNewRia;
    }

    @Override
    public String toString() {
        return "GetSetNewsUsdEur{" +
                "nameNew='" + nameNew + '\'' +
                ", urlNew='" + urlNew + '\'' +
                ", dateNew='" + dateNew + '\'' +
                ", textNews='" + textNews + '\'' +
                ", textNewsRia='" + textNewsRia + '\'' +
                ", urlNewRia='" + urlNewRia + '\'' +
                ", authorNewRia='" + authorNewRia + '\'' +
                ", dateNewRia='" + dateNewRia + '\'' +
                '}';
    }
}

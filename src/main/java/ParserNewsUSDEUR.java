import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ParserNewsUSDEUR {

    private final static String str = "https://mash.ru";
    public static ArrayList<String> newsResult = new ArrayList<>();
    public static GetSetNewsUsdEur getSetNewsUsdEur = new GetSetNewsUsdEur();

    public static void main(String[] args) throws IOException {

        Document newsList = Jsoup.connect("https://ria.ru/world/")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        Elements elementsNewsRia = newsList.getElementsByClass("list-item__title color-font-hover-only");
        for (Element element : elementsNewsRia) {
            getSetNewsUsdEur.setTextNewsRia(element.text());
            getSetNewsUsdEur.setUrlNewRia(element.attr("href"));
            newsResult.add("НОВОСТИ РИА: " + getSetNewsUsdEur.getTextNewsRia()+ ": " + getSetNewsUsdEur.getUrlNewRia());
            //System.out.println(element.getElementsByClass("list-item__info").addClass("list-item__date").text() + element.text() + " : " + element.attr("href"));
            //String newsDetLink = element.attr("href");
            //System.out.println("Получаю автора новости...");
            Document detNewsLink = Jsoup.connect(getSetNewsUsdEur.getUrlNewRia())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements elementsNewsDet = detNewsLink.getElementsByClass("media__copyright-item m-copyright");
            for (Element elementAuthor : elementsNewsDet) {
                String author = elementAuthor.text();
                getSetNewsUsdEur.setAuthorNewRia(author);
                Elements elementsNewsDates = detNewsLink.getElementsByClass("article__header");
                for (Element elementDate : elementsNewsDates) {
                    String dates = elementDate.getElementsByClass("article__info-date").text();
                    //System.out.println(author + ": " + dates);
                    getSetNewsUsdEur.setDateNewRia(dates);
                    newsResult.add(getSetNewsUsdEur.getAuthorNewRia() + ": " + getSetNewsUsdEur.getDateNewRia());
                    Elements textNewsRia = detNewsLink.getElementsByClass("article__text");
                    for (Element textNewRia : textNewsRia) {
                        getSetNewsUsdEur.setTextNewsRia(textNewRia.text());
                        newsResult.add(getSetNewsUsdEur.getTextNewsRia());
                    }
                }
            }
        }
        Document mashNews = Jsoup.connect("https://mash.ru/news")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        Elements elementsNewsMash = mashNews.getElementsByClass("Grid-sc-1xxtj66-0 jDWWyj styles_content__1R2in");
        for (Element elementNew : elementsNewsMash) {
            getSetNewsUsdEur.setNameNew(elementNew.getElementsByClass("styles_title__3cNBJ").text());
            getSetNewsUsdEur.setUrlNew(str + elementNew.getElementsByClass("styles_link__3w0Ov").attr("href"));
            //System.out.println("НОВОСТЬ: " + getSetNewsUsdEur.getNameNew() + ": " + getSetNewsUsdEur.getUrlNew());
            newsResult.add("НОВОСТЬ MASH: " + getSetNewsUsdEur.getNameNew() + ": " + getSetNewsUsdEur.getUrlNew());
            Document mashNewDet = Jsoup.connect(getSetNewsUsdEur.getUrlNew())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements elementsNewDate = mashNewDet.getElementsByClass("Grid-sc-1xxtj66-0 bbUhjM");
            for (Element element : elementsNewDate) {
                getSetNewsUsdEur.setDateNew(element.getElementsByClass("Grid-sc-1xxtj66-0 bbUhjM").text());
                //System.out.println("Дата публикации: " + getSetNewsUsdEur.getDateNew());
                newsResult.add("Дата публикации: " + getSetNewsUsdEur.getDateNew());
                Elements textNew = mashNewDet.getElementsByClass("styles_text__9aT2I");
                for (Element text : textNew) {
                    getSetNewsUsdEur.setTextNews(text.text());
                    //System.out.println(getSetNewsUsdEur.getTextNews());
                    newsResult.add(getSetNewsUsdEur.getTextNews());
                }
}
}
        newsResult.forEach(System.out::println);
}
}

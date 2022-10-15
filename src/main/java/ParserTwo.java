import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserTwo {
    private final static String urlHead = "https://www.gismeteo.ru";
    private final static String urlCityHeadNow = "now";

    private static ArrayList<String> listUrlCity = new ArrayList<>();
    private static ArrayList<String> listUrlOblast = new ArrayList<>();
    private static ArrayList<String> listOblast = new ArrayList<>();
    private static ArrayList<String> listCity = new ArrayList<>();
    private static ArrayList<String> listUrlRayon = new ArrayList<>();
    private static ArrayList<String> listRayon = new ArrayList<>();
    private static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        GetSetParserTwo getSetParserTwo = new GetSetParserTwo();

        Document weatherRussia = (Document) Jsoup.connect("https://www.gismeteo.ru/catalog/russia/")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        Elements catalogRus = weatherRussia.getElementsByClass("catalog-item-link");

        for (Element oblast : catalogRus) {
            String url = oblast.getElementsByClass("link-item").attr("href");

            String urlRegion = urlHead + url;
            listOblast.add(oblast.text());
            listUrlOblast.add(urlRegion);
            System.out.println("Готовлю ссылку на регион, для передачи ее на следующий этап...");
            getSetParserTwo.setUrlOblast(urlRegion);
            Document region = Jsoup.connect(getSetParserTwo.getUrlOblast())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements catalogRegion = region.getElementsByClass("catalog-item-link");
            for (Element rayon : catalogRegion) {
                String urlRayon = rayon.getElementsByClass("link-item").attr("href");
                String urlRayonRes = urlHead + urlRayon;
                listRayon.add(rayon.text());
                listUrlRayon.add(urlRayonRes);
                System.out.println("Готовлю ссылку на район, для передачи ее на следующий этап...");
                getSetParserTwo.setUrlRayon(urlRayonRes);
                Document rayonPars = Jsoup.connect(getSetParserTwo.getUrlRayon())
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Elements catalogRayon = rayonPars.getElementsByClass("catalog-item-link");
                for (Element city : catalogRayon) {
                    String urlCity = city.getElementsByClass("link-item").attr("href");
                    String urlCityRes = urlHead + urlCity + urlCityHeadNow;
                    listCity.add(city.text());
                    listUrlCity.add(urlCityRes);
                    System.out.println("Готовлю ссылку на город, для передачи ее на следующий этап...");
                    getSetParserTwo.setUrlCity(urlCityRes);
                    Document weatherCityRes = Jsoup.connect(getSetParserTwo.getUrlCity())
                            .userAgent("Chrome/4.0.249.0 Safari/532.5")
                            .referrer("http://www.google.com")
                            .get();
                    getSetParserTwo.setInfoTemper(weatherCityRes.getElementsByClass("unit unit_temperature_c").first().text());
                    getSetParserTwo.setWeatherTextNowForObl(weatherCityRes.getElementsByClass("page-title").text());
                    getSetParserTwo.setDateNowForObl(weatherCityRes.getElementsByClass("now-localdate").text());
                    getSetParserTwo.setHourWeatherNowObl(weatherCityRes.getElementsByClass("now-desc").text());
/*                    System.out.println(getSetParserTwo.getWeatherTextNowForObl() + ":");
                    System.out.println(getSetParserTwo.getDateNowForObl() + ": " + getSetParserTwo.getHourWeatherNowObl() + ": " + getSetParserTwo.getInfoTemper());*/
                    System.out.println("Записываю итоговые данные: " + getSetParserTwo.getWeatherTextNowForObl() + ": " + urlCityRes + "...");
                    result.add(getSetParserTwo.getWeatherTextNowForObl() + ":");
                    result.add(getSetParserTwo.getDateNowForObl() + ": " + getSetParserTwo.getHourWeatherNowObl() + ": " + getSetParserTwo.getInfoTemper());
                }
            }
        }
        //result.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Я готов показать результат погоды в любом городе России...");
        System.out.println("Введите название города: ");
        getSetParserTwo.setCityEnter(scanner.nextLine());
        for (String cityWeather : result) {
            if (cityWeather.equalsIgnoreCase(getSetParserTwo.getCityEnter())) {
                System.out.println(cityWeather);
            }
        }
    }
}

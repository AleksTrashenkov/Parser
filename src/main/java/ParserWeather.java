import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ParserWeather {
    private final static String urlHead = "https://www.gismeteo.ru";
    private final static String urlCityHead = "/weather-";
    private final static String urlCityEnd = "-district/";
    private final static String urlCityHeadNow = "now";

    private static ArrayList<String> listUrlCity = new ArrayList<>();
    private static ArrayList<String> listUrlOblast = new ArrayList<>();
    private static ArrayList<String> listOblast = new ArrayList<>();
    private static ArrayList<String> listUrlOblCity = new ArrayList<>();
    private static ArrayList<String> listUrlRayonCity = new ArrayList<>();
    private static ArrayList<String> listCityResult = new ArrayList<>();
    private static ArrayList<String> listResultingRayon = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        GetSetWeather getSetWeather = new GetSetWeather();

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите название города: ");
            getSetWeather.setCity(scanner.nextLine());
            getSetWeather.setCity(getSetWeather.getCity().substring(0, 1).toUpperCase() + getSetWeather.getCity().substring(1));
        } catch (Throwable err) {
            System.out.println("Название города не введено, либо введено не строковое значение...");
        }
        System.out.println("Определяю параметры... ");

        Document weatherRus = Jsoup.connect("https://www.gismeteo.ru/catalog/russia/")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements regions = weatherRus.getElementsByClass("catalog-item-link");

        for (Element region : regions) {
            String urlRegion = region.getElementsByClass("link-item").attr("href");

            if (region.text().startsWith(getSetWeather.getCity()) && urlRegion.startsWith(urlCityHead)) {
                listCityResult.add(region.text());
                listUrlCity.add(urlHead + urlRegion + urlCityHeadNow);
                parserRayonCity(listUrlCity, getSetWeather.getCity());
                //parserCityUrl(listUrlCity, getSetWeather.getCity());
                //System.out.println(region.text());
                //System.out.println(urlHead + urlRegion + urlCityHeadNow);
            } else {
                if (!(urlRegion.startsWith(urlCityHead))) {
                    listOblast.add(region.text());
                    listUrlOblast.add(urlHead + urlRegion);
                    parserOblUrl(listUrlOblast, getSetWeather.getCity());
                    //System.out.println(region.text());
                    //System.out.println(urlHead + urlRegion);
                }
            }
        }
    }

    public static void parserOblUrl(ArrayList<String> urlOblRes, String city) {
        GetSetWeather getSetWeather = new GetSetWeather();

        for (String urlOblast : urlOblRes) {
            getSetWeather.setUrlOblast(urlOblast);
        }

        try {
            Document weatherOblRes = Jsoup.connect(getSetWeather.getUrlOblast())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements infoObl = weatherOblRes.getElementsByClass("catalog-item-link");
            for (Element oblast : infoObl) {
                String urlObl = oblast.getElementsByClass("link-item").attr("href");
                if (oblast.text().startsWith(city) && urlObl.startsWith(urlCityHead) && !(urlObl.endsWith(urlCityEnd))) {

                    getSetWeather.setUrlRayon(urlHead + urlObl + urlCityHeadNow);
                    listUrlOblCity.add(getSetWeather.getUrlRayon());
                    getSetWeather.setCityName(oblast.text());
                    parserRayonCity(listUrlOblCity, getSetWeather.getCityName());
//                    System.out.println(oblast.text());
                    //System.out.println(urlHead + urlObl + urlCityHeadNow);
                } else {
                    if (urlObl.endsWith(urlCityEnd)) {
                        getSetWeather.setUrlRayonCityRes(urlHead + urlObl);
                        listUrlRayonCity.add(getSetWeather.getUrlRayonCityRes());
                        //System.out.println(getSetWeather.getUrlRayonCityRes());
                        //parserNRayonCity(listUrlRayonCity, city);
                    }
                }
            }
        } catch (Throwable error) {
            System.out.println("Что-то пошло не так...");
        }
    }
/*

    public static void parserNRayonCity(ArrayList<String> urlRayonC, String city) {
        GetSetWeather getSetWeather = new GetSetWeather();
        for (String urlCity : urlRayonC) {
            getSetWeather.setUrlCityRayonResult(urlCity);
        }
        try {
            Document weatherRayonCity = Jsoup.connect(getSetWeather.getUrlCityRayonResult())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements elements = weatherRayonCity.getElementsByClass("catalog-item-link");

            for (Element element : elements) {
                String urlRayonCit = element.getElementsByClass("link-item").attr("href");
                if (element.text().startsWith(city)) {
                    System.out.println(element.text() + ": " + urlHead + urlRayonCit);
                    listResultingRayon.add(urlHead + urlRayonCit + urlCityHeadNow);
                    parserRayonCity(listResultingRayon, getSetWeather.getCityName());
                }
            }

        } catch (Throwable err) {
            System.out.println("Error downloads");
        }
    }
*/


    public static void parserRayonCity(ArrayList<String> urlRayon, String city) {
        GetSetWeather getSetWeather = new GetSetWeather();
        System.out.println("Подготавливаю ссылку на город...");
        for (String urlRayonCity : urlRayon) {
            getSetWeather.setUrlRayonCity(urlRayonCity);
        }
        try {
            Document weatherRayonCity = Jsoup.connect(getSetWeather.getUrlRayonCity())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            System.out.println("Готовлю информацию о погоде...");
            Elements infoRayonCity = weatherRayonCity.getElementsByClass("weathertab weathertab-link tooltip");
            System.out.println("Погода в " + city + " на два дня: ");
            for (Element element : infoRayonCity) {
                System.out.println(element.attr("data-text") + ": " + element.text() + ":" + " Средняя температура: " + element.getElementsByClass("unit unit_temperature_c").text());
            }
            getSetWeather.setInfoTemper(weatherRayonCity.getElementsByClass("unit unit_temperature_c").first().text());
            getSetWeather.setGetWeatherTextNowForObl(weatherRayonCity.getElementsByClass("page-title").text());
            getSetWeather.setDateNowForObl(weatherRayonCity.getElementsByClass("now-localdate").text());
            getSetWeather.setHourWeatherNowObl(weatherRayonCity.getElementsByClass("now-desc").text());
            System.out.println(getSetWeather.getGetWeatherTextNowForObl() + ":");
            System.out.println(getSetWeather.getDateNowForObl() + ": " + getSetWeather.getHourWeatherNowObl() + ": " + getSetWeather.getInfoTemper());


        } catch (Throwable error) {
            System.out.println("Город не найден в списке городов России...");
        }
    }
    }

import com.google.common.base.MoreObjects;

import javax.xml.transform.sax.SAXResult;
import java.util.ArrayList;
import java.util.Date;

public class GetSetWeather {


    private String urlOblast;
    private String urlRayon;
    private String urlRayonCity;
    private String urlRayonCityRes;
    private String urlCityRayonResult;

    private String city;
    private String cityName;
    private String infoTemper;
    private String weatherTextNow;
    private String getWeatherTextNowForObl;
    private String dateNow;
    private String dateNowForObl;
    private String hourWeatherNow;
    private String hourWeatherNowObl;



    public String getUrlOblast() {
        return urlOblast;
    }

    public String getUrlRayon() {
        return urlRayon;
    }

    public String getCity() {
        return city;
    }

    public void setUrlOblast(String urlOblast) {
        this.urlOblast = urlOblast;
    }

    public void setUrlRayon(String urlRayon) {
        this.urlRayon = urlRayon;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrlRayonCity() {
        return urlRayonCity;
    }

    public void setUrlRayonCity(String urlRayonCity) {
        this.urlRayonCity = urlRayonCity;
    }


    public String getInfoTemper() {
        return infoTemper;
    }

    public void setInfoTemper(String infoTemper) {
        this.infoTemper = infoTemper;
    }

    public String getWeatherTextNow() {
        return weatherTextNow;
    }

    public void setWeatherTextNow(String weatherTextNow) {
        this.weatherTextNow = weatherTextNow;
    }

    public String getGetWeatherTextNowForObl() {
        return getWeatherTextNowForObl;
    }

    public void setGetWeatherTextNowForObl(String getWeatherTextNowForObl) {
        this.getWeatherTextNowForObl = getWeatherTextNowForObl;
    }

    public String getDateNow() {
        return dateNow;
    }

    public void setDateNow(String dateNow) {
        this.dateNow = dateNow;
    }

    public String getDateNowForObl() {
        return dateNowForObl;
    }

    public void setDateNowForObl(String dateNowForObl) {
        this.dateNowForObl = dateNowForObl;
    }

    public String getHourWeatherNow() {
        return hourWeatherNow;
    }

    public void setHourWeatherNow(String hourWeatherNow) {
        this.hourWeatherNow = hourWeatherNow;
    }

    public String getHourWeatherNowObl() {
        return hourWeatherNowObl;
    }

    public void setHourWeatherNowObl(String hourWeatherNowObl) {
        this.hourWeatherNowObl = hourWeatherNowObl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUrlRayonCityRes() {
        return urlRayonCityRes;
    }

    public void setUrlRayonCityRes(String urlRayonCityRes) {
        this.urlRayonCityRes = urlRayonCityRes;
    }

    public String getUrlCityRayonResult() {
        return urlCityRayonResult;
    }

    public void setUrlCityRayonResult(String urlCityRayonResult) {
        this.urlCityRayonResult = urlCityRayonResult;
    }

    @Override
    public String toString() {
        return "GetSetWeather{" +
                "urlOblast='" + urlOblast + '\'' +
                ", urlRayon='" + urlRayon + '\'' +
                ", urlRayonCity='" + urlRayonCity + '\'' +
                ", urlRayonCityRes='" + urlRayonCityRes + '\'' +
                ", urlCityRayonResult='" + urlCityRayonResult + '\'' +
                ", city='" + city + '\'' +
                ", cityName='" + cityName + '\'' +
                ", infoTemper='" + infoTemper + '\'' +
                ", weatherTextNow='" + weatherTextNow + '\'' +
                ", getWeatherTextNowForObl='" + getWeatherTextNowForObl + '\'' +
                ", dateNow='" + dateNow + '\'' +
                ", dateNowForObl='" + dateNowForObl + '\'' +
                ", hourWeatherNow='" + hourWeatherNow + '\'' +
                ", hourWeatherNowObl='" + hourWeatherNowObl + '\'' +
                '}';
    }
}

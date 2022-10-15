public class GetSetParserTwo {
    private String urlOblast;
    private String urlRayon;
    private String urlCity;

    private String infoTemper;
    private String weatherTextNowForObl;
    private String dateNowForObl;
    private String hourWeatherNowObl;

    private String cityEnter;

    public String getUrlOblast() {
        return urlOblast;
    }

    public void setUrlOblast(String urlOblast) {
        this.urlOblast = urlOblast;
    }

    public String getUrlRayon() {
        return urlRayon;
    }

    public void setUrlRayon(String urlRayon) {
        this.urlRayon = urlRayon;
    }

    public String getUrlCity() {
        return urlCity;
    }

    public void setUrlCity(String urlCity) {
        this.urlCity = urlCity;
    }

    public String getInfoTemper() {
        return infoTemper;
    }

    public void setInfoTemper(String infoTemper) {
        this.infoTemper = infoTemper;
    }

    public String getWeatherTextNowForObl() {
        return weatherTextNowForObl;
    }

    public void setWeatherTextNowForObl(String weatherTextNowForObl) {
        this.weatherTextNowForObl = weatherTextNowForObl;
    }

    public String getDateNowForObl() {
        return dateNowForObl;
    }

    public void setDateNowForObl(String dateNowForObl) {
        this.dateNowForObl = dateNowForObl;
    }

    public String getHourWeatherNowObl() {
        return hourWeatherNowObl;
    }

    public void setHourWeatherNowObl(String hourWeatherNowObl) {
        this.hourWeatherNowObl = hourWeatherNowObl;
    }

    public String getCityEnter() {
        return cityEnter;
    }

    public void setCityEnter(String cityEnter) {
        this.cityEnter = cityEnter;
    }

    @Override
    public String toString() {
        return "GetSetParserTwo{" +
                "urlOblast='" + urlOblast + '\'' +
                ", urlRayon='" + urlRayon + '\'' +
                ", urlCity='" + urlCity + '\'' +
                ", infoTemper='" + infoTemper + '\'' +
                ", weatherTextNowForObl='" + weatherTextNowForObl + '\'' +
                ", dateNowForObl='" + dateNowForObl + '\'' +
                ", hourWeatherNowObl='" + hourWeatherNowObl + '\'' +
                ", cityEnter='" + cityEnter + '\'' +
                '}';
    }
}

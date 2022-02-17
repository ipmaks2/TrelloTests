package api.utils;

public class ApiValue {

    public static String APIKEY = ConfProperties.getProperty("apikey");
    public static String TOKEN = ConfProperties.getProperty("token");
    public static String APIKEY_TOKEN_STRING = "key=" + APIKEY + "&token=" + TOKEN;
    public static String API_URL = "https://api.trello.com/1/";
    public static String AUTHORIZED_URL = API_URL + "members/me/?" + APIKEY_TOKEN_STRING;
    public static String getBoardInfoRequest = "members/me/boards?fields=name,url&";
    public static String getBoardInfoFilterRequest = "boards/?name=";
}

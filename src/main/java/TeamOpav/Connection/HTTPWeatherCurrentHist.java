package TeamOpav.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPWeatherCurrentHist {
    public static StringBuffer getWeatherData(String location) throws Exception {
        String apiKey = "372e85960410a8f82e15c5353e020e2e";
        URL url = new URL("http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + location);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();

        return response;
    }
}


//        public static class HTTPWeatherCurrent {
//            private HttpURLConnection connection;
//            private BufferedReader reader;
//            private StringBuilder content;
//            private String line;
//            private String source;
//            private int status;
//            private URL url;
//
//            public HTTPWeatherCurrent(String accessKey, String city) {
//                try {
//                    content = new StringBuilder();
//                    source = "http://api.weatherstack.com/current" +
//                            "?access_key=" + accessKey +
//                            "&query=\" +" + city;
//                    url = new URL(source);
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    status = connection.getResponseCode();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            public StringBuilder getData() {
//                try {
//                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    while ((line = reader.readLine()) != null) {
//                        content.append(line);
//                    }
//                    reader.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return content;
//            }
//        }
//
//
//
////    public HTTPWeatherHistorical(String accessKey, String city){
////        try {
////            content = new StringBuilder();
////            source = "http://api.weatherstack.com/historical" +
////                    "?access_key=" + accessKey +
////                    "&query=\" +" + city +
////            "historical_date=" + connection.getDate();
////            url = new URL(source);
////            connection = (HttpURLConnection) url.openConnection();
////            connection.setRequestMethod("GET");
////            status = connection.getResponseCode();
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//
//
//
//}

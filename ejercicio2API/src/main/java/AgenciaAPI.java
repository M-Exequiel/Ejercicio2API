import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Comparator;

import static spark.Spark.*;

public class AgenciaAPI {

    public static void main(String[] args) {

        final AgencyService agencyService = new AgencyServiceMapImp();

        port(4567);
        get("/agencias", (request, response) -> {

            response.type("application/json");
            String idSitio = request.queryParams("idSitio");
            System.out.println(idSitio);
            String idMetodoPago = request.queryParams("idMetodoPago");
            System.out.println(idMetodoPago);
            String coordenada = request.queryParams("near_to");
            System.out.println(coordenada);
            /*String[] coordenadasArray = coordenada.split(",");
            String latitud = coordenadasArray[0];
            String longitud = coordenadasArray[1];
            String radio = coordenadasArray[2];*/
            String criterioOrden = request.queryParams("criterioOrden");
            System.out.println(criterioOrden);
            String limite = "";
            if (request.queryParams().contains("limit")) {
                limite = request.queryParams("limit");
                System.out.println(limite);
            }
            String offset = "";
            if (request.queryParams().contains("offset")) {
                offset = request.queryParams("offset");
                System.out.println(offset);
            }
            try {
                String urlAgencies = "https://api.mercadolibre.com/sites/" + idSitio +
                        "/payment_methods/" + idMetodoPago +
                        "/agencies?near_to=" + coordenada;
                System.out.println(urlAgencies);
                if (limite != "" & offset != "") {
                    urlAgencies = "https://api.mercadolibre.com/sites/" + idSitio +
                            "/payment_methods/" + idMetodoPago +
                            "/agencies?near_to=" + coordenada + "&limit=" + limite + "&offset=" + offset;
                }
                if (offset != "") {
                    urlAgencies = "https://api.mercadolibre.com/sites/" + idSitio +
                            "/payment_methods/" + idMetodoPago +
                            "/agencies?near_to=" + coordenada + "&offset=" + offset;
                }
                if (limite != "") {
                    urlAgencies = "https://api.mercadolibre.com/sites/" + idSitio +
                            "/payment_methods/" + idMetodoPago +
                            "/agencies?near_to=" + coordenada + "&limit=" + limite;
                }
                System.out.println(urlAgencies);
                String dataAgencies = readUrl(urlAgencies);
                Agency[] agencies = new Gson().fromJson(new JsonParser().parse(dataAgencies).
                        getAsJsonObject().get("results"), Agency[].class);
                if (criterioOrden.contains("ADDRESS")){
                    System.out.println("es address");
                    Ordenador.ordenar(agencies,Agency.addressComparator);
                }
                if (criterioOrden.contains("CODE")){
                    System.out.println("es code");
                    Ordenador.ordenar(agencies,Agency.codeComparator);
                }
                for (Agency agency : agencies) {
                    System.out.println(agency.toString());
                    agencyService.addAgency(agency);
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un problema al obtener las agencias");
                e.printStackTrace();
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(agencyService.getAgency())));
        });
    }

    private static String readUrl(String urlString) throws IOException {

        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int read = 0;
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}

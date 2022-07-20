import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



public class Main {

    private static String HOST = "http://da-insurance-dev-1131818953.ap-southeast-1.elb.amazonaws.com";
    private static String LINK = "/insurance-core/internal/policy/insurance-policy-info?country=";
    private static String country = "SG";
    private static String userId = "343701";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        CallAPIService callAPIService = new CallAPIService();
        HttpHeaders headers = createAuthHeader();

        String path = HOST+ LINK+country;

        headers.set("userId", userId);
        ResponseEntity<String> response = callAPIService.triggerMicroserviceRestAPINew(path,
                null, String.class, headers, HttpMethod.GET, null, null);

        Response obj = mapper.readValue(response.getBody(),Response.class);
        System.out.println(obj.toString());
    }

    private static HttpHeaders createAuthHeader() throws NumberFormatException{
        String plainCreds ="admin"+ ":" + "adminPass";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("sourceApp", "DACore");
        return headers;
    }

}

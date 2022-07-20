import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.*;

import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

@Log4j
public class CallAPIService {

    public static final int DEFAULT_TIMEOUT = 100;
    public static final int DEFAULT_READ_TIMEOUT = 100;

    public <T> ResponseEntity<T> triggerMicroserviceRestAPINew(String webserviceURL,
                                                               String jsonRequest,
                                                               Class<T> responseClass,
                                                               HttpHeaders headers,
                                                               HttpMethod method,
                                                               Integer connectionTimeout,
                                                               Integer readTimeout) throws Exception {


        if (connectionTimeout == null) {
            connectionTimeout = DEFAULT_TIMEOUT;
        }

        if (readTimeout == null) {
            readTimeout = DEFAULT_READ_TIMEOUT;
        }

        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectionTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        HttpEntity<String> entity = jsonRequest != null ? new HttpEntity<>(jsonRequest, headers) : new HttpEntity<>(headers);
        ResponseEntity<T> result = null;

        webserviceURL = Normalizer.normalize(webserviceURL, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        System.out.println("triggerMicroserviceRestAPI request Normalizer uri: " + webserviceURL);

        try {
            result = restTemplate.exchange(webserviceURL, method, entity, responseClass);
        } catch (HttpStatusCodeException e) {
            String responseBody = StringUtils.newStringUtf8(e.getResponseBodyAsByteArray());
            System.out.println("jsonRequest=" + responseBody );
            throw new Exception(responseBody);

        } catch (UnknownContentTypeException e){
            String responseBody = StringUtils.newStringUtf8(e.getResponseBody());
            System.out.println("responseData=" + responseBody );
        } catch (ResourceAccessException e){
            throw new Exception("Timeout", e.getCause());
        } catch (Exception e) {
            System.out.println("jsonRequest : "+jsonRequest + " ERROR ::: " + e.getMessage());
            throw new Exception(e.getMessage(), e);
        }
        return result;
    }

    private <T> T readValue(ResponseEntity<?> response, Class<T> responseClass) {
        T result = null;
        if (response.getStatusCode() == HttpStatus.OK ||
                response.getStatusCode() == HttpStatus.CREATED) {
            result = responseClass.cast(response.getBody());
        } else {
            System.out.println("No data found "+ response.getStatusCode());
        }
        return result;
    }

}

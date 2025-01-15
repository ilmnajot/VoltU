package uz.ilmnajot.voltu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.ilmnajot.voltu.service.SmsService;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final RestTemplate restTemplate;


    @Value("${application.playMobile.url}") //taken
    private String playMobileUrl; //taken
    @Value("${application.playMobile.username}")
    private String playMobileUsername; // taken
    @Value("${application.playMobile.password}")
    private String playMobilePassword; //taken

    private static HttpEntity<String> getStringHttpEntity(String phoneNumber, String text, HttpHeaders headers) {
        text = "\"" + text + "\"";
        String json = """
                {
                    "messages":[
                        {
                            "recipient":998""" + phoneNumber + """
                ,
                "message-id":"abc000000001",      
                "sms":{   
                   "originator": "3700",
                    "content": {
                       "text": """ + text + """
                                  }
                             }
                        }
                   ]
                }
                """;

        //System.out.println(json);
        return new HttpEntity<>(json, headers);
    }

    public Boolean sendSmsMessage(String phoneNumber, String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(playMobileUsername, playMobilePassword);
        String result = restTemplate.exchange(
                playMobileUrl,
                HttpMethod.POST,
                getStringHttpEntity(phoneNumber, text, headers),
                String.class
        ).getBody();

        return result.equals("Request Received");
    }

}

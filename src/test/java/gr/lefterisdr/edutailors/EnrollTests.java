package gr.lefterisdr.edutailors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.lefterisdr.edutailors.exception.StudentAlreadyRegisteredAtCourseException;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EduTailorsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnrollTests
{
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpEntity<String> entityNoBody = new HttpEntity<>(null, new HttpHeaders());
    private ResponseEntity<String> response;

}

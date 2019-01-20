package gr.lefterisdr.edutailors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EduTailorsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListStudentsTests
{
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpEntity<String> entityNoBody = new HttpEntity<>(null, new HttpHeaders());
    private ResponseEntity<String> response;

    @Test
    public void test_number_of_students_in_course_1()
            throws IOException, JSONException
    {
        this.test_number_of_students_in_course(2,4);
    }

    @Test
    public void test_number_of_students_in_course_2()
            throws IOException, JSONException
    {
        this.test_number_of_students_in_course(1,2);
    }

    @Test
    public void test_number_of_students_in_course_3()
            throws IOException, JSONException
    {
        this.test_number_of_students_in_course(3,2);
    }

    private void test_number_of_students_in_course(int courseId, int expectedNumber)
            throws JSONException, IOException
    {
        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/student/" + courseId),
                HttpMethod.GET, entityNoBody, String.class);

        this.assertStatus(HttpStatus.OK);

        JsonNode jsonResponse = new ObjectMapper().readTree(response.getBody());

        assertThat(jsonResponse.size(), is(expectedNumber));
    }

    private String createURLWithPort(String uri)
    {
        return "http://localhost:" + port + uri;
    }

    private void assertStatus(HttpStatus status)
    {
        assertThat(response.getStatusCode(), equalTo(status));
    }
}

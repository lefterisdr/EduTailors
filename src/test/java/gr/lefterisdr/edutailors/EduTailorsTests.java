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
public class EduTailorsTests
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
    public void test_student_enroll_failure()
            throws IOException, JSONException
    {
        JsonNode response = this.enroll(1,2);
        this.assertStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.get("message").asText(), equalTo("StudentAlreadyRegisteredAtCourse"));
    }

    @Test
    public void test_student_enroll_success()
            throws IOException, JSONException
    {
        JsonNode response = this.getStudentInCourse(3,3);
        this.assertStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.get("message").asText(), equalTo("StudentNotRegisteredAtCourse"));

        this.enroll(3,3);
        this.assertStatus(HttpStatus.CREATED);

        this.getStudentInCourse(3,3);
        this.assertStatus(HttpStatus.OK);
    }

    @Test
    public void test_student_disenroll_failure()
            throws IOException, JSONException
    {
        JsonNode response = this.disenroll(1,4);
        this.assertStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.get("message").asText(), equalTo("StudentNotRegisteredAtCourse"));
    }

    @Test
    public void test_student_disenroll_success()
            throws IOException, JSONException
    {
        this.getStudentInCourse(3,3);
        this.assertStatus(HttpStatus.OK);

        this.disenroll(3,3);
        this.assertStatus(HttpStatus.OK);

        JsonNode response = this.getStudentInCourse(3,3);
        this.assertStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.get("message").asText(), equalTo("StudentNotRegisteredAtCourse"));
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

    private JsonNode getStudentInCourse(int courseId, int studentId)
            throws JSONException, IOException
    {
        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/student/" + studentId + "/" + courseId),
                HttpMethod.GET, entityNoBody, String.class);

        return new ObjectMapper().readTree(response.getBody());
//        return new ObjectMapper().treeToValue(node, Student.class);
    }

    private JsonNode enroll(int courseId, int studentId)
            throws IOException
    {
        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/student/" + studentId + "/" + courseId),
                HttpMethod.POST, entityNoBody, String.class);

        return new ObjectMapper().readTree(response.getBody());
    }

    private JsonNode disenroll(int courseId, int studentId)
            throws IOException
    {
        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/student/" + studentId + "/" + courseId),
                HttpMethod.DELETE, entityNoBody, String.class);

        return new ObjectMapper().readTree(response.getBody());
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

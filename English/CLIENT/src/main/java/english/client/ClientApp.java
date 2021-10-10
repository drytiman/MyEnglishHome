package english.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import english.api.dto.StudentDto;

import java.io.IOException;
import java.net.URL;

import static english.client.config.Config.API_ADDRESS;

public class ClientApp {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        try {
            StudentDto studentDto = OBJECT_MAPPER.readValue(new URL(API_ADDRESS + "/users/1"), StudentDto.class);
            System.out.println(studentDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
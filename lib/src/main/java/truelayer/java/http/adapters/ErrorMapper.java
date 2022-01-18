package truelayer.java.http.adapters;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Response;
import truelayer.java.Utils;
import truelayer.java.http.entities.ProblemDetails;

import java.io.IOException;

import static truelayer.java.Utils.getObjectMapper;

//todo tests for this class: deliberately postponed as we need to solve the implementation doubts first
public class ErrorMapper {
    private ErrorMapper(){}

    public static ProblemDetails fromResponse(Response response){
        String errorBody = "error";
        try {
            errorBody = response.errorBody().string();
            return getObjectMapper().readValue(errorBody, ProblemDetails.class);
        } catch (IOException e) {
            //todo how to properly log this ?
            e.printStackTrace();
            return ProblemDetails.builder()
                    .type("error")
                    .detail(errorBody)
                    .build();
        }
    }

    public static ProblemDetails fromThrowable(Throwable throwable){
        return ProblemDetails.builder()
                .type("error")
                .detail(throwable.getMessage())
                .build();
    }
}

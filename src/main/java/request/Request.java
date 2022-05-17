package request;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers.noBody;
import static java.net.http.HttpRequest.BodyPublishers.ofString;


public class Request {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();

        System.out.println("------HEAD-------------------------");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users"))
                .method("HEAD", noBody())
                .build();

        HttpResponse<Void> headResponse = client.send(request,
                HttpResponse.BodyHandlers.discarding());


        HttpHeaders headers = headResponse.headers();
        headers.map().forEach((key, values) ->
                System.out.printf("%s = %s%n", key, values));

        System.out.println("------POST-------------------------");


        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(ofString(requestBody))
                .uri(URI.create("https://reqres.in/api/users"))
                .build();
        HttpResponse<String> postResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());

        System.out.println("--------------OPTIONS---------------");

        HttpRequest optionsRequest = HttpRequest.newBuilder()
                .method("OPTIONS", noBody())
                .uri(URI.create("https://reqres.in/api/users"))
                .build();

        HttpResponse<Void> optionsResponse = client.send(optionsRequest, HttpResponse.BodyHandlers.discarding());

        HttpHeaders optionHeaders = optionsResponse.headers();
        optionHeaders.map().forEach((key, value) -> System.out.printf("%s = %s%n", key, value));


        System.out.println("-----------GET--------------------");

        HttpRequest getRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://reqres.in/api/users"))
                .build();
        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(getResponse.body().replace(",", ",\n"));

    }
}




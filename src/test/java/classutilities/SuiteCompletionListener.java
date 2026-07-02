package classutilities;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SuiteCompletionListener implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        // Do nothing on start
    }

    @Override
    public void onFinish(ISuite suite) {
        try {
            String fileContent = "target/surefire-reports/report.json";
            String apiKey = System.getenv("APIKEY");

            try {
                fileContent = Files.readString(Paths.get(fileContent));
                System.out.println("File Content Loaded: " + fileContent);
            } catch (IOException e) {
                System.err.println("Could not read file at: " + fileContent);
                e.printStackTrace();
                return; // Stop if there is no payload to send
            }

            if (apiKey != null && fileContent != null) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(
                                "https://automationreports-d6f64-default-rtdb.firebaseio.com/autoreports.json?auth="
                                        + apiKey))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(fileContent))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("API Request completed with status code: " + response.statusCode());
                System.out.println("Response Body: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
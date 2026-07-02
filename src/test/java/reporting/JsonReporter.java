package reporting;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Set;

public class JsonReporter implements IReporter {

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        JSONArray results = new JSONArray();
        suites.forEach(element -> {
            results.put(createSuiteJsonObject(element));
        });
        try (FileWriter file = new FileWriter(outputDirectory + "/report.json")) {
            file.write(results.toString());
        } catch (IOException e) {

        }
    }

    public JSONObject createSuiteJsonObject(ISuite suite) {
        JSONObject result = new JSONObject();
        JSONArray passedMethods = new JSONArray();
        JSONArray failedMethods = new JSONArray();
        JSONArray skippedMethods = new JSONArray();
        suite.getResults().entrySet().forEach(element -> {
            ITestContext context = element.getValue().getTestContext();
            passedMethods.put(createResultJsonArray(context.getPassedTests().getAllResults()));
            failedMethods.put(createResultJsonArray(context.getFailedTests().getAllResults()));
            skippedMethods.put(createResultJsonArray(context.getSkippedTests().getAllResults()));
        });
        result.put("name", suite.getName());
        result.put("passed", passedMethods);
        result.put("failed", failedMethods);
        result.put("skipped", skippedMethods);
        result.put("reportdate", LocalDate.now().toString());
        result.put("finerdate", DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(ZonedDateTime.now()));
        return result;

    }

    public JSONArray createResultJsonArray(Set<ITestResult> results) {
        JSONArray result = new JSONArray();
        results.forEach(element -> {
            JSONObject currentJSONResult = new JSONObject();
            currentJSONResult.put("name", element.getName());
            currentJSONResult.put("testNameNew", element.getTestName());
            result.put(currentJSONResult);
        });
        return result;
    }

}
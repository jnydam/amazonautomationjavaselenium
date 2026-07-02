package classutilities;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.annotations.BeforeMethod;

public interface ClassExtender {

    public final ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeMethod
    public default void setCustomTestName(Method method, Object[] testData) {
        if (testData != null && testData.length > 0) {
            // Craft a unique name using your parameters (e.g., MethodName_Param1)

            String[] testDataArray = Arrays.stream(testData)
                    .map(String::valueOf)
                    .toArray(String[]::new);

            String[] stringNewArray = Arrays.copyOf(Arrays.stream(testData)
                    .map(String::valueOf)
                    .toArray(String[]::new), testDataArray.length - 1);

            String joinedResult = String.join(",", stringNewArray);
            testName.set(method.getName() + "::::" + joinedResult);
        } else {
            testName.set(method.getName());
        }
    }

}

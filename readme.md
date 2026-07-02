A test automation framework using Selenium (https://www.selenium.dev/) for the Amazon.com website. Validations include ensuring that link navigations to different header pages are working properly, and E2E scenarios for path to purchase flows as well as new account creation flows are working properly.

To run the tests, please follow the following steps.

git clone https://github.com/jnydam/amazonautomationjavaselenium.git

cd project/

mvn install

mvn test

Reports can be seen in target/surefire-reports reports folder
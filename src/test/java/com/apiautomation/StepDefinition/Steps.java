package com.apiautomation.StepDefinition;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Exception;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Set;
import java.io.IOException;
import java.net.URL;
public class Steps {				

     
    @Given("Open the Firefox and launch the application")				
    public void open_the_Firefox_and_launch_the_application() throws Throwable							
    {
        //public Set<String> windowHandles;	
         String name = runBatchFile("C:\\Users\\KrishnaveniKuppusamy\\APISamplProject\\test.bat");
        // System.out.println("Name:"+name);
        WindowsDriver notepadSession;
        Process winAppDriverProcess;
         String winAppDriverPath = "C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe";
		String command = winAppDriverPath;
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.inheritIO();
        winAppDriverProcess = processBuilder.start();
        Thread.sleep(3000);
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        notepadSession = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723/"), capabilities);
        WebElement element = notepadSession.findElementByName("Untitled - Notepad");
        String atrr = element.getAttribute("NativeWindowHandle");
        int window = Integer.parseInt(atrr);
        String windowHex = Integer.toHexString(window);
        DesiredCapabilities newCap = new DesiredCapabilities();
        newCap.setCapability("appTopLevelWindow", windowHex);
        WindowsDriver newSession = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"),newCap);
       // windowHandles = appDriver.getWindowHandles();
        RestAssured.baseURI = "https://reqres.in/";
            // Perform API request and get the response
            Response response = RestAssured.given()
                    .param("paramName", "paramValue")
                    .get("api/users?page=2");
            // Validate API response
            int statusCode = response.getStatusCode();
            // Perform assertions and validations on the response
            System.out.println("API Status Code: " + response.asPrettyString());

        System.out.println("This Step open the Firefox and launch the application.");	

        Thread.sleep(5000);
        newSession.findElementByName("Text Editor").sendKeys("This is my code");
        Thread.sleep(5000);
        System.out.println("Print the size"+newSession.manage().window().getSize());
              Dimension dm = new Dimension(450,630);

        newSession.manage().window().setSize(dm);
        System.out.println("Print the size"+newSession.manage().window().getSize());
    }

    @When("Enter the Username and Password")					
    public void enter_the_Username_and_Password() throws Throwable 							
    {		
       System.out.println("This step enter the Username and Password on the login page.");					
    }		

    @Then("Reset the credentiala")					
    public void Reset_the_credential() throws Throwable 							
    {    		
        System.out.println("This step click on the Reset button.");					
    }		


    private static String runBatchFile(String batchFilePath) throws IOException, InterruptedException {
       
            // Use ProcessBuilder to execute the batch file
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", batchFilePath);
            Process process = processBuilder.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Batch file executed successfully");
            } else {
                System.out.println("Batch file execution failed with exit code: " + exitCode);
            }

       return process.toString();
    }

}
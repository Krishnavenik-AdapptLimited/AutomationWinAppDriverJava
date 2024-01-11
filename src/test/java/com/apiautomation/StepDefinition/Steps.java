package com.apiautomation.StepDefinition;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;		
import java.net.URL;
public class Steps {				

     
    @Given("Open the Firefox and launch the application")				
    public void open_the_Firefox_and_launch_the_application() throws Throwable							
    {		
        WindowsDriver notepadSession;
        Process winAppDriverProcess;
         String winAppDriverPath = "C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe";
		String command = winAppDriverPath;
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.inheritIO();
        winAppDriverProcess = processBuilder.start();
         Thread.sleep(3000);
		 DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\WINDOWS\\system32\\notepad.exe");
        notepadSession = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723/"), capabilities);
        
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
        notepadSession.findElementByName("Text Editor").sendKeys("This is my code");
        Thread.sleep(5000);
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

}
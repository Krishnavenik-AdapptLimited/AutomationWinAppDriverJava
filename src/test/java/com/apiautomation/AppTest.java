package com.apiautomation;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
/**
 * Unit test for simple App.
 */
@RunWith(Cucumber.class)				
@CucumberOptions(
    features="Features",
    glue={"com.apiautomation.StepDefinition"})						

public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}

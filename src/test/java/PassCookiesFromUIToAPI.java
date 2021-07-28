import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class PassCookiesFromUIToAPI {

    @Test
    public void cookieTest(){

        System.setProperty("webdriver.chrome.driver", "resources/driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/search?q=home");

        Set<Cookie> seleniumCookie = driver.manage().getCookies();

        List restAssuredCookie = new ArrayList();
        int i = 0;
        for(Cookie cookie: seleniumCookie)
        {
            restAssuredCookie.add(new Cookie.Builder(cookie.getName(),cookie.getValue()).build());
            System.out.println(restAssuredCookie.get(i));
            i++;
        }
        driver.quit();
//        given().spec(RestAssured.requestSpecification)
//                .basePath("/search?")
//                .cookies(new Cookies(restAssuredCookie))
//                .queryParam("q","home")
//                .get()
//                .then().statusCode(200);



    }
}

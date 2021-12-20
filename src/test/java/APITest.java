import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APITest
{
   @Test
    public void first(){
       given()
               .contentType(ContentType.JSON)
               .baseUri("https://petstore.swanger.io/v2")
               .when()
               .get("pet/findByStatus?ststus=sold")
               //.then()
               //.statusCode(200)
               .body()
               //.prettyPrint();
                .as(Root[])
    }


}

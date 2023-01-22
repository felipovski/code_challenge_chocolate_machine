package com.chococompany;

import com.chococompany.control.dto.ChocolatePercentageDto;
import com.chococompany.control.dto.ChocolateTypeDtoV1;
import com.chococompany.entity.Chocolate;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.google.gson.Gson;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
@QuarkusTestResource(ChocoLifeCycleManager.class)
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class ChocolateResourceTest {

    @Test
    @DataSet("chocolates.yml")
    public void percentageEndpointv1() {
        List<Chocolate> chocolates = Chocolate.listAll();
        assertThat(chocolates.size(), equalTo(3));
        assertThat(chocolates.get(0).id, equalTo(123L));

        var json = new Gson().toJson(new ChocolateTypeDtoV1("A"));

        var result = given()
                .body(json)
                .when()
                .post("/chocolate/percentage")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getObject(".", ChocolatePercentageDto.class);

        assertThat(result.percentage(), equalTo(80));
        assertThat(result.description(), equalTo("Chocolate oitenta porcento"));
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(new Header("X-API-VERSION", "1"));
    }
}
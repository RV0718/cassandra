package com.cassandra.example;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.cql.CqlIdentifier;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


public class PersonControllerTest {

    @Value("${spring.data.cassandra.port}")
    private static int port;

    @Value("${spring.data.cassandra.contact-points}")
    private static String contactPoints;

    @BeforeClass
    public static void startCassandraEmbedded() throws Exception {

        EmbeddedCassandraServerHelper.startEmbeddedCassandra("file:///"+EmbeddedCassandraServerHelper.CASSANDRA_RNDPORT_YML_FILE,60000);
        Cluster cluster = Cluster.builder()
                .addContactPoints(contactPoints).withPort(port).build();
        Session session = cluster.connect();
    }

   /* @Before
    public void createTable() {
        adminTemplate.createTable(
                true, CqlIdentifier.cqlId(DATA_TABLE_NAME),
                Book.class, new HashMap<String, Object>());
    }

    @After
    public void dropTable() {
        adminTemplate.dropTable(CqlIdentifier.cqlId(DATA_TABLE_NAME));
    }*/

    @AfterClass
    public static void stopCassandraEmbedded() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

    @Test
    public void testAllPersons() {
        given()
                .when()
                .get("/api/persons")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(notNullValue());
    }
}

package xyz.wenjiesx.webmvc.version.mapping.test;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.wenjiesx.webmvc.version.mapping.application.IntegerVersionTestApplication;

/**
 * @author wenji
 * @Date 2021/4/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegerVersionTestApplication.class,
        properties = "webmvc.version.mapping.header-key=clientVersion",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegerVersionTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @org.junit.Test
    public void test() {

        String host = "http://localhost:" + port;
        System.out.println("version 1 test");
        Assert.assertEquals("method1", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("1"), String.class));
        System.out.println("version 5 test");
        Assert.assertEquals("method1", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("5"), String.class));
        System.out.println("version 6 test");
        Assert.assertEquals("method1", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("6"), String.class));
        System.out.println("version 8 test");
        Assert.assertEquals("method1", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("8"), String.class));
        System.out.println("version 11 test");
        Assert.assertEquals("method2", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("11"), String.class));
        System.out.println("version 14 test");
        Assert.assertEquals("method2", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("14"), String.class));
        System.out.println("version 20 test");
        Assert.assertEquals("method3", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("20"), String.class));

    }

    public HttpEntity buildHttpEntity(String version) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("clientVersion", version);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return httpEntity;
    }

}

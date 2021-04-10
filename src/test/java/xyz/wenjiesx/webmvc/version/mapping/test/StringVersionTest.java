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
import xyz.wenjiesx.webmvc.version.mapping.application.StringVersionTestApplication;

/**
 * @author wenji
 * @Date 2021/4/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StringVersionTestApplication.class,
        properties = {"webmvc.version.mapping.header-key=clientVersion",
                "webmvc.version.mapping.clazz=xyz.wenjiesx.webmvc.version.mapping.condition.StringVersionRequestCondition"},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StringVersionTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @org.junit.Test
    public void test() {

        String host = "http://localhost:" + port;
        System.out.println("version 1.0.0 test");
        Assert.assertEquals("method1", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("1.0.0"), String.class));
        System.out.println("version 1.4.5 test");
        Assert.assertEquals("method1", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("1.4.5"), String.class));
        System.out.println("version 1.5.9 test");
        Assert.assertEquals("method1", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("1.5.9"), String.class));
        System.out.println("version 1.8.0 test");
        Assert.assertEquals("method2", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("1.8.0"), String.class));
        System.out.println("version 2.1.1 test");
        Assert.assertEquals("method2", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("2.1.1"), String.class));
        System.out.println("version 2.0 test");
        Assert.assertEquals("method2", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("2.0"), String.class));
        System.out.println("version 3.0.1 test");
        Assert.assertEquals("method3", restTemplate.postForObject(host + "/test/api1", buildHttpEntity("3.0.1"), String.class));
    }

    public HttpEntity buildHttpEntity(String version) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("clientVersion", version);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return httpEntity;
    }
}

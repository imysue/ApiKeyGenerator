package com.keyGenerator.service;

import com.keyGenerator.dto.KeyInfoParam;
import com.keyGenerator.entity.KeyInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class KeyGeneratorServiceImplTest {

    @Autowired
    private KeyGeneratorService keyGeneratorService;

    @Mock
    KeyInfoParam keyInfoParam;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("정상등록")
    public void registerKeyInfo() {

        //정상 등록 여부
        KeyInfoParam keyInfoParam = KeyInfoParam.builder()
                .key("policy-number1")
                .type("string")
                .generator("GenericKeyGenerator")
                .description("test1")
                .minLength(5)
                .build();

        KeyInfoParam returnKeyInfo = keyGeneratorService.registerKeyInfo(keyInfoParam);

        System.out.println("KeyInfoId : " + returnKeyInfo.getKeyInfoId());

        Assert.assertNotNull(returnKeyInfo.getKeyInfoId());
//        assertThat(returnKeyInfo.getKeyInfoId(), is(notNullValue()));
    }

    @Test
    @DisplayName("중복키")
    public void duplicateKey() {
    }
}
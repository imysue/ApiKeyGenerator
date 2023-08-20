package com.keyGenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyGenerator.dto.KeyGeneratorParam;
import com.keyGenerator.dto.KeyInfoParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KeyGeneratorControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    //    @MockBean
//	KeyInfoRepository keyInfoRepository;
//
//    KeyInfo RECORD_1 = new KeyInfo((long) 1, "policy-number", "보험 증서 번호에 사용할 KEY 값으로 테이블 PK 로 사용", "number", "mysql", 10);
//    KeyInfo RECORD_2 = new KeyInfo((long) 2, "claim-number", "고객센터에서 고객 문의사항이 접수될 때 사용하는 KEY", "string", "", 0);
//
    @Test
    void testRegisterKeyInfo() throws Exception {
        String url = "/key/registerKeyInfo";

        KeyInfoParam keyInfo = KeyInfoParam.builder().build();
        keyInfo.setKey("policy-number");
        keyInfo.setDescription("보험 증서 번호에 사용할 KEY 값으로 테이블 PK 로 사용");
        keyInfo.setType("number");
        keyInfo.setGenerator("mysql");
        keyInfo.setMinLength(10);

        String content = mapper.writeValueAsString(keyInfo);

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    MockHttpServletResponse res = result.getResponse();
                });
    }

    @Test
    void testGenerateKey() throws Exception {
        String url = "/key/generateKey";

        KeyGeneratorParam keyGeneratorParam = new KeyGeneratorParam();
        keyGeneratorParam.setKey("policy-number");
        keyGeneratorParam.setUserName("AAA");

        String content = mapper.writeValueAsString(keyGeneratorParam);

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    MockHttpServletResponse res = result.getResponse();
                });
    }
}
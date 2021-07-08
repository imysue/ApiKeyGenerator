package com.keyGenerator.service;

import com.keyGenerator.dto.KeyGeneratorParam;
import com.keyGenerator.dto.KeyInfoParam;
import com.keyGenerator.dto.ResMessage;


public interface KeyGeneratorService {

	public KeyInfoParam registerKeyInfo(KeyInfoParam param);

	public KeyGeneratorParam generateKey(KeyGeneratorParam param);
}

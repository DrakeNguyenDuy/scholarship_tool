package com.drakend.scholarshipManage.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperCustom extends ModelMapper {

	private static ModelMapperCustom instance;

	private ModelMapperCustom() {
	}

	private static ModelMapperCustom getInstance() {
		if (instance == null) {
			instance = new ModelMapperCustom();
		}
		return instance;
	}

	@Override
	public <D> D map(Object source, Class<D> destinationType) {
		return getInstance().map(source, destinationType);
	}

}

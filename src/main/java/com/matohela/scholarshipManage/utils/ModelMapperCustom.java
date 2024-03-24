package com.matohela.scholarshipManage.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

//@Component
public class ModelMapperCustom extends ModelMapper {

	private static ModelMapper instance;

	private ModelMapperCustom() {
	}

	private static ModelMapper getInstance() {
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

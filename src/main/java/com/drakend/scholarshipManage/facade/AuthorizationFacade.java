package com.drakend.scholarshipManage.facade;

import java.util.List;
import java.util.Map;

import com.drakend.scholarshipManage.dto.RoleDTO;

/**
 * <p>
 * Define all method for authorization facade
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
public interface AuthorizationFacade {

	RoleDTO edit(RoleDTO roleDTO, String modifiedBy);

	RoleDTO create(RoleDTO roleDTO, String createBy);

	List<RoleDTO> findAll(Map<String, String> query);

	RoleDTO findById(String id);

	String delete(String id, String modifiedBy);
}

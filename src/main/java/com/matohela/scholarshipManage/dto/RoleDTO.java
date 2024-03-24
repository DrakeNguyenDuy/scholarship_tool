package com.matohela.scholarshipManage.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * This class a present for role DTO and extended from {@link BaseDTO}.
 * Additionally, this have permissions
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends BaseDTO {

	private Set<PermissionDTO> permissions;
}

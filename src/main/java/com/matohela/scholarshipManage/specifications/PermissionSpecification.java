package com.matohela.scholarshipManage.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.matohela.scholarshipManage.common.ParameterCommon;
import com.matohela.scholarshipManage.entity.Permission;

public class PermissionSpecification {
	
	public static Specification<Permission> search(Map<String, String> filter){
		return (root, criteriaQuery, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            String name = filter.get(ParameterCommon.SEARCH);
            if(StringUtils.hasText(name)) {
            	predicates.add(criteriaBuilder.like(root.get(""), "%"+name+"%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}

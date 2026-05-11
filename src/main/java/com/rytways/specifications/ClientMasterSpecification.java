package com.rytways.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.rytways.model.ClientMaster;


public class ClientMasterSpecification {

	public static Specification<ClientMaster> clientName(String clientName) {
		return (root, query, builder) -> clientName == null || clientName.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("clientName"), clientName);
	}
	
	public static Specification<ClientMaster> status(String status) {
		return (root, query, builder) -> status == null || status.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("status"), status);
	}
}

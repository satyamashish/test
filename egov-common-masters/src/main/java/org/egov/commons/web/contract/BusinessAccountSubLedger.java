package org.egov.commons.web.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class BusinessAccountSubLedger {

	private Long id;

	private Long detailType;

	private Long detailKey;

	private Double amount;

	private BusinessAccountDetails businessAccountDetails;

}

package org.egov.commons.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
public class DepartmentCriteria {
    private String departmentName;

    private Boolean active;

    private List<Long> ids;

    private String tenantId;

    private String sortBy;

    private String sortOrder;

}


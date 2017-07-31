package org.egov.commons.persistence.repository.builder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.egov.commons.config.ApplicationProperties;
import org.egov.commons.repository.builder.DepartmentQueryBuilder;
import org.egov.commons.web.contract.DepartmentGetRequest;
import org.junit.Test;
import org.mockito.InjectMocks;

public class DepartmentQueryBuilderTest {

    @InjectMocks
    private ApplicationProperties applicationProperties;

    @Test
    public void no_input_test() {
        DepartmentGetRequest departmentGetRequest = new DepartmentGetRequest();

        DepartmentQueryBuilder builder = new DepartmentQueryBuilder();
        assertEquals(
                "select id,name,code,active,tenantId,createdBy,"
                        + "createdDate,lastModifiedBy,lastModifiedDate FROM eg_department" + " ORDER BY name ASC",
                builder.getQuery(departmentGetRequest, new ArrayList<>()));
    }

    @Test
    public void all_input_test() {
        DepartmentGetRequest departmentGetRequest = new DepartmentGetRequest();
        DepartmentQueryBuilder builder = new DepartmentQueryBuilder();
        departmentGetRequest.setName("Health");
        departmentGetRequest.setActive(true);
        departmentGetRequest.setIds(Arrays.asList(1L));
        departmentGetRequest.setTenantId("default");
        departmentGetRequest.setSortBy("code");
        departmentGetRequest.setSortOrder("DESC");

        assertEquals(
                "select id,name,code,active,tenantId,createdBy,"
                        + "createdDate,lastModifiedBy,lastModifiedDate FROM eg_department"
                        + " WHERE tenantId = ? AND id IN (1) AND name = ? AND active = ? ORDER BY code DESC",
                builder.getQuery(departmentGetRequest, new ArrayList<>()));
    }
}

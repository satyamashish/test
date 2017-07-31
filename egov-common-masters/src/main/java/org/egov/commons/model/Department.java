/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) 2016  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.commons.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;
import org.egov.common.contract.request.RequestInfo;
import org.egov.commons.web.contract.DepartmentRequest;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@ToString
public class Department {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 8, max = 64)
    private String name;

    @NotNull
    @Size(min = 1, max = 10)
    private String code;

    @NotNull
    private Boolean active;

    @NotNull
    private String tenantId;

    @NotNull
    private Long createdBy;

    private Date createdDate;


    @NotNull
    private Long lastModifiedBy;

    private Date lastModifiedDate;

/*
    //constructor not used
    public Department(DepartmentRequest departmentRequest) {
        Department department = departmentRequest.getDepartment();//..;
        RequestInfo requestInfo = departmentRequest.getRequestInfo();
        department.setCreatedBy(requestInfo.getUserInfo().getIds());
        department.setLastModifiedBy(requestInfo.getUserInfo().getIds());
        department.setCreatedDate(new Date());
        department.setLastModifiedDate(new Date());

    }
*/

  /*  public Department toDomainModel() {
        //BusinessCategory category = BusinessCategory.builder().ids(businessCategory.getIds()).build();
        return Department.builder().ids(ids).name(name).active(active).code(code)
                .tenantId(tenantId).build();
    }*/

}
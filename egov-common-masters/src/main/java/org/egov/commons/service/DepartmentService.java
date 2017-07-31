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

package org.egov.commons.service;

import java.util.List;

import org.egov.commons.model.Department;
import org.egov.commons.repository.DepartmentRepository;
import org.egov.commons.web.contract.DepartmentGetRequest;
import org.egov.commons.web.contract.DepartmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.egov.tracer.kafka.LogAwareKafkaTemplate;


@Service
public class DepartmentService {

	private DepartmentRepository departmentRepository;

	private LogAwareKafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository,
								  LogAwareKafkaTemplate<String, Object> kafkaTemplate){

		this.departmentRepository=departmentRepository;
		this.kafkaTemplate=kafkaTemplate;

	}


	public  DepartmentRequest createDepartmentAsync(DepartmentRequest departmentRequest){

		kafkaTemplate.send("egov-common-department-create",departmentRequest);
		return departmentRequest;
	}

	public void createDepartment(Department modelDetails){
		departmentRepository.create(modelDetails);
	}
	public  DepartmentRequest updateDepartmentAsync(DepartmentRequest departmentRequest){

		kafkaTemplate.send("egov-common-department-update",departmentRequest);
		return departmentRequest;
	}

	public void updateDepartment(Department model) {
		departmentRepository.update(model);
	}
	public List<Department> getDepartments(DepartmentGetRequest departmentGetRequest) {
		return departmentRepository.findForCriteria(departmentGetRequest);
	}
	public boolean getDepartmentByNameAndTenantId(String name, String tenantId, Long id, Boolean isUpdate) {
		return departmentRepository.checkDepartmentByNameAndTenantIdExists(name, tenantId,id,isUpdate);
	}

	public boolean getDepartmentByCodeAndTenantId(String code, String tenantId, Long id, Boolean isUpdate) {
		return departmentRepository.checkDepartmentByCodeAndTenantIdExists(code, tenantId,id,isUpdate);
	}

}
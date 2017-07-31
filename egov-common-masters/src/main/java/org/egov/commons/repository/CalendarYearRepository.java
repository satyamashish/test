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

package org.egov.commons.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.egov.commons.model.CalendarYear;
import org.egov.commons.repository.builder.CalendarYearQueryBuilder;
import org.egov.commons.repository.rowmapper.CalendarYearRowMapper;
import org.egov.commons.web.contract.CalendarYearGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarYearRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CalendarYearRowMapper calendarYearRowMapper;

	@Autowired
	private CalendarYearQueryBuilder calendarYearQueryBuilder;

	public List<CalendarYear> findForCriteria(CalendarYearGetRequest calendarYearGetRequest) {
		List<Object> preparedStatementValues = new ArrayList<Object>();
		String queryStr = calendarYearQueryBuilder.getQuery(calendarYearGetRequest, preparedStatementValues);
		List<CalendarYear> calendarYears = jdbcTemplate.query(queryStr, preparedStatementValues.toArray(),
				calendarYearRowMapper);
		return calendarYears;
	}

	@SuppressWarnings("static-access")
	public CalendarYear findCalendarYearByName(final int name, final String tenantId) {
		final List<Object> preparedStatementValues = new ArrayList<Object>();
		preparedStatementValues.add(name);
		preparedStatementValues.add(tenantId);
		final String query = calendarYearQueryBuilder.selectYearByNameQuery();
		final List<CalendarYear> calendarYears = jdbcTemplate.query(query, preparedStatementValues.toArray(),
				calendarYearRowMapper);
		if (!calendarYears.isEmpty())
			return calendarYears.get(0);

		return null;
	}

	public boolean checkYearByNameAndDate(final int name, final java.util.Date givenDate, final String tenantId) {
		final List<Object> preparedStatementValues = new ArrayList<Object>();
		preparedStatementValues.add(name);
		preparedStatementValues.add(givenDate);
		preparedStatementValues.add(givenDate);
		preparedStatementValues.add(tenantId);
		final String query = CalendarYearQueryBuilder.selectYearByByNameAndDateQuery();
		final List<Map<String, Object>> year = jdbcTemplate.queryForList(query, preparedStatementValues.toArray());
		if (!year.isEmpty())
			return true;

		return false;
	}

	public boolean checkYearByName(final int name, final String tenantId) {
		final List<Object> preparedStatementValues = new ArrayList<Object>();
		preparedStatementValues.add(name);
		preparedStatementValues.add(tenantId);
		final String query = CalendarYearQueryBuilder.selectYearByNameQuery();
		final List<Map<String, Object>> year = jdbcTemplate.queryForList(query, preparedStatementValues.toArray());
		if (!year.isEmpty())
			return true;

		return false;
	}
}
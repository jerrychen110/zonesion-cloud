package com.zonesion.cloud.web.rest.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * @Title: JdbcPaginationHelper.java
 * @Package com.zonesion.cloud.web.rest.util
 * @author: cc
 * @date: 2017年8月24日 下午3:52:40
 */
public class JdbcPaginationHelper<T> {
	private static final int DEFAULT_PAGE_SIZE = 10;

	public Page<T> fetchPage(final JdbcTemplate jdbcTemplate, final String countSql, final String dataSql,
			final Object args[], final int pageNo, final RowMapper<T> rowMapper) {
		return fetchPage(jdbcTemplate, countSql, dataSql, args, pageNo, DEFAULT_PAGE_SIZE, rowMapper);
	}

	public Page<T> fetchPage(final JdbcTemplate jdbcTemplate, final String countSql, final String dataSql,
			final Object args[], final int pageNo, final int pageSize, final RowMapper<T> rowMapper) {
		//总记录数据
		final int rowCount = jdbcTemplate.queryForObject(countSql, args, Integer.class);
		final Page<T> page = new Page<T>();
		page.setPageNo(pageNo);
		page.setTotalCount(rowCount);
		page.setPageSize(pageSize);
		//根据pageNo取一页数据
		final int startRow = (pageNo - 1) * pageSize;
		jdbcTemplate.query(dataSql, args, new ResultSetExtractor<Page<T>>() {
			public Page<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
				final List<T> pageItems = page.getResult();
				int currentRow = 0;
				while (rs.next() && currentRow < startRow + pageSize) {
					if (currentRow >= startRow) {
						pageItems.add(rowMapper.mapRow(rs, currentRow));
					}
					currentRow++;
				}
				return page;
			}
		});
		return page;
	}
}
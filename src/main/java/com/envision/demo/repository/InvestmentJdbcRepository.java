package com.envision.demo.repository;

import com.envision.demo.model.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvestmentJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class InvestmentRowMapper implements RowMapper<Investment> {
        @Override
        public Investment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Investment investment = new Investment();
            investment.setId(rs.getLong("id"));
            investment.setName(rs.getString("name"));
            investment.setTicker(rs.getString("ticker"));
            return investment;
        }

    }

    public List<Investment> findAll() {
        return jdbcTemplate.query("select * from investment", new InvestmentJdbcRepository.InvestmentRowMapper());
    }

    public Investment findById(long id) {
        return jdbcTemplate.queryForObject("select * from investment where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Investment>(Investment.class));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from investment where id=?", new Object[] { id });
    }

    public int insert(Investment investment) {
        return jdbcTemplate.update("insert into investment (id, name, ticker) " + "values(?,  ?, ?)",
                new Object[] { investment.getId(), investment.getName(), investment.getTicker() });
    }

    public int update(Investment investment) {
        return jdbcTemplate.update("update investment " + " set name = ?, ticker = ? " + " where id = ?",
                new Object[] { investment.getName(), investment.getTicker(), investment.getId() });
    }

}

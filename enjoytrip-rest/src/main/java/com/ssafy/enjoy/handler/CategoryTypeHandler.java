package com.ssafy.enjoy.handler;

import com.ssafy.enjoy.board.dto.Category;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryTypeHandler extends BaseTypeHandler<Category> {

    @Override
    public void setNonNullParameter(
            PreparedStatement ps, int i, Category parameter, JdbcType jdbcType
    ) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Category getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Category.lookup(rs.getString(columnName));
    }

    @Override
    public Category getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Category.valueOf(rs.getString(columnIndex));
    }

    @Override
    public Category getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Category.valueOf(cs.getString(columnIndex));
    }
}

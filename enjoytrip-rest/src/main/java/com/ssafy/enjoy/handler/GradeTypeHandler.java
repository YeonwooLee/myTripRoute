package com.ssafy.enjoy.handler;

import com.ssafy.enjoy.user.dto.Grade;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeTypeHandler extends BaseTypeHandler<Grade> {

    @Override
    public void setNonNullParameter(
            PreparedStatement ps, int i, Grade parameter, JdbcType jdbcType
    ) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Grade getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Grade.lookup(rs.getString(columnName));
    }

    @Override
    public Grade getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Grade.valueOf(rs.getString(columnIndex));
    }

    @Override
    public Grade getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Grade.valueOf(cs.getString(columnIndex));
    }
}

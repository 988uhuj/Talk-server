/**
 * 
 */
package me.risky.talk.common.util.pagination;


import me.risky.talk.common.util.Dialect;

/**
 * 数据库查询物理分页ORACLE方言
 * @author lyl
 *
 */
public class MySQLDialect extends Dialect {

	/**
	 * 生成物理分页查询语句
	 * @param sql 原始SQL
	 * @param offset 跳过的行数
	 * @param limit 限制的行数
	 * @return String
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
        if (offset > 0) {
            return sql + " limit " + offset + "," + limit;
        }
        return sql + " limit " + limit;
	}

}

package com.memory.base.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.DaoSupport;

import static org.springframework.util.Assert.notNull;

/**
 * DAO support
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 19:49
 */
public abstract class MySqlSessionDaoSupport extends DaoSupport {

    private SqlSession sqlSession;

    private boolean externalSqlSession;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (!this.externalSqlSession) {
            this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        }
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSession = sqlSessionTemplate;
        this.externalSqlSession = true;
    }

    public SqlSession getSqlSession() {
        return this.sqlSession;
    }

    @Override
    protected void checkDaoConfig() {
        notNull(this.sqlSession,
                "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
    }
}

package com.pfar.apibank.repository.impl;

import com.pfar.apibank.repository.DaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class DaoRepositoryImpl implements DaoRepository {

    private final EntityManager entityManager;

    @Override
    public List getReport(Date startDate, Date endDate, long clientId) {
        StringBuilder sql = new StringBuilder();
        sql
                .append("SELECT MV.DATE, CL.NAME, AC.NUMBER, AC.TYPE, MV.BALANCE - MV.AMOUNT, AC.STATE, MV.AMOUNT, MV.BALANCE ")
                .append("FROM CLIENT CL ")
                .append("INNER JOIN ACCOUNT AC ON AC.CLIENT_ID = CL.ID ")
                .append("INNER JOIN MOVEMENT MV ON MV.ACCOUNT_ID = AC.ID ")
                .append("WHERE ")
                .append(" MV.DATE BETWEEN :startDate AND :endDate AND CL.ID =:clientId ")
                .append("ORDER BY MV.DATE ASC");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }
}

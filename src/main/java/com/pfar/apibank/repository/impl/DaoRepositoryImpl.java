package com.pfar.apibank.repository.impl;

import com.pfar.apibank.repository.DaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class DaoRepositoryImpl implements DaoRepository {

    private final EntityManager entityManager;

    @Override
    public List getReport(Date startDate, Date endDate, long clientId) {
        String sql = "SELECT MV.DATE, CL.NAME, AC.NUMBER, AC.TYPE, MV.BALANCE - MV.AMOUNT, AC.STATE, MV.AMOUNT, MV.BALANCE " +
                "FROM CLIENT CL " +
                "INNER JOIN ACCOUNT AC ON AC.CLIENT_ID = CL.ID " +
                "INNER JOIN MOVEMENT MV ON MV.ACCOUNT_ID = AC.ID " +
                "WHERE " +
                " MV.DATE BETWEEN :startDate AND :endDate AND CL.ID =:clientId " +
                "ORDER BY MV.DATE ASC";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }
}

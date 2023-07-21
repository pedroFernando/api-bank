package com.pfar.apibank.repository;

import java.util.Date;
import java.util.List;

public interface DaoRepository {
    List<Object[]> getReport(Date startDate, Date endDate, long clientId);
}

package com.pfar.apibank.service;

import com.pfar.apibank.domain.dto.ReportDto;

import java.util.Date;
import java.util.List;

/**
 * Report
 *
 * @author Pedro Aponte <fernandoaponte1103@gmail.com>
 * @version 0.0.1
 */
public interface ReportService {

    /**
     * Get report by date range
     * @param startDate Date init
     * @param endDate Date end
     * @param clientId ID client
     * @return Report
     */
    List<ReportDto> getByDateRange(Date startDate, Date endDate, long clientId);

}

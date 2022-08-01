package com.pfar.apibank.service.impl;

import com.pfar.apibank.domain.dto.ReportDto;
import com.pfar.apibank.repository.DaoRepository;
import com.pfar.apibank.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final DaoRepository daoRepository;

    @Override
    public List<ReportDto> getByDateRange(Date startDate, Date endDate, long clientId) {
        List<Object[]> objects = this.daoRepository.getReport(startDate, endDate, clientId);
        List<ReportDto> reportDtos = new ArrayList<>(0);
        for (Object[] object : objects) {
            reportDtos.add(new ReportDto(object));
        }
        return reportDtos;
    }

}

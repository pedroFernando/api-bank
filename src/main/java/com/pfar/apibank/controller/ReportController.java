package com.pfar.apibank.controller;

import com.pfar.apibank.domain.dto.ReportDto;
import com.pfar.apibank.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/reportes", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping()
    public ResponseEntity<List<ReportDto>> getByDateRange(
            @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(name = "clientId") long clientId) {
        log.info("Get report by client id " + clientId + " and startDate=" + startDate + ", endDate=" + endDate);
        return ResponseEntity.ok(this.reportService.getByDateRange(startDate, endDate, clientId));
    }

}

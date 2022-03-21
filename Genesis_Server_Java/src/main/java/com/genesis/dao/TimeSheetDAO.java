package com.genesis.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.genesis.model.TimeSheet;

public interface TimeSheetDAO {
	public Integer addTimeSheet(TimeSheet timeSheet) throws Exception;
	public List<TimeSheet> getAllEmployeeTimeSheet() throws Exception;
	public TimeSheet getEmployeeTimeSheet(Integer employeeId, LocalDate date) throws Exception;
	public Integer clockIn(Integer employeeId, LocalDate date, LocalTime timeIn) throws Exception;
	public Integer clockOut(Integer employeeId, LocalDate date,LocalTime timeOut) throws Exception;
}

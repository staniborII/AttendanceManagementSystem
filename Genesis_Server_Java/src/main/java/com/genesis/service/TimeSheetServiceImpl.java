package com.genesis.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genesis.dao.TimeSheetDAO;
import com.genesis.model.TimeSheet;

@Service(value="timeSheetService")
@Transactional
public class TimeSheetServiceImpl implements TimeSheetService{
	
	@Autowired
	TimeSheetDAO timeSheetDAO;

	@Override
	public List<TimeSheet> getAllEmployeeTimeSheet() throws Exception {
		List<TimeSheet> userTimeSheetList = timeSheetDAO.getAllEmployeeTimeSheet();
		
		if(userTimeSheetList.isEmpty()) {
			throw new Exception("Service.NO_USER_TIMESHEET_FOUND");
		}
		return userTimeSheetList;
	}

	@Override
	public TimeSheet getEmployeeTimeSheet(Integer employeeId, LocalDate date) throws Exception {
		TimeSheet userTimeSheet = null;
		
		userTimeSheet = timeSheetDAO.getEmployeeTimeSheet(employeeId, date);	
		
		if(userTimeSheet == null) {
			throw new Exception("Service.USER_TIMESHEET_NONEXIST");
		}
		
		return userTimeSheet;
	}

	@Override
	public Integer clockIn(Integer employeeId, LocalDate date, LocalTime timeIn) throws Exception {
		Integer rowsUpdated = timeSheetDAO.clockIn(employeeId, date, timeIn);
		 
		 if(rowsUpdated >= 1) {
			 
			 return rowsUpdated;
		 }else {
			 throw new Exception("Service.No_ROWS_UPDATED");
		 }
	}

	@Override
	public Integer clockOut(Integer employeeId, LocalDate date, LocalTime timeOut) throws Exception {
		Integer rowsUpdated = timeSheetDAO.clockOut(employeeId, date, timeOut);
		 
		if(rowsUpdated >= 1) {
			 
			 return rowsUpdated;
		 }else {
			 throw new Exception("Service.No_ROWS_UPDATED");
		 }
	}

	@Override
	public Integer addTimeSheet(TimeSheet timeSheet) throws Exception {
		Integer retValue = 0;
		
		retValue = timeSheetDAO.addTimeSheet(timeSheet);
		
		if(retValue == 0) {
			throw new Exception("Service.INVALID_SERVICE_ERROR_ADD_TIMESHEET");
		}
		
//	    if(retValue == -1) {
//			throw new Exception("Service.TIMESHEET_ALREADY_EXISTS");
//		}
		
		return retValue;
	}

}

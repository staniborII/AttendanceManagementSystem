package com.genesis.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genesis.entity.TimeSheetEntity;
import com.genesis.model.TimeSheet;

@Repository(value="timeSheetDAO")
public class TimeSheetDAOImpl implements TimeSheetDAO{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public List<TimeSheet> getAllEmployeeTimeSheet() throws Exception {
		String sql = "SELECT t FROM TimeSheetEntity t";
		Query q = entityManager.createQuery(sql);
		
		List<TimeSheetEntity> userTimeSheetList = q.getResultList();
		List<TimeSheet> finalList = new ArrayList<>();
		
		for(TimeSheetEntity timeSheetEntity:userTimeSheetList) {
						
			TimeSheet timeSheet = new TimeSheet();
			
			//System.out.println(customer.getDvd().getDvdType().equals(dvdType));
			//System.out.println(customer.getDvd().getDvdType()+" "+dvdType);
			
			timeSheet.setTimeId(timeSheetEntity.getTimeId());
			timeSheet.setEmployeeId(timeSheetEntity.getEmployeeId());
			timeSheet.setAttendanceDate(timeSheetEntity.getAttendanceDate());
			timeSheet.setTimeIn(timeSheetEntity.getTimeIn());
			timeSheet.setTimeOut(timeSheetEntity.getTimeOut());
			timeSheet.setComment(timeSheetEntity.getComment());
			
			finalList.add(timeSheet);
		}
		
		return finalList;
	}

	@Override
	public TimeSheet getEmployeeTimeSheet(Integer employeeId, LocalDate date) throws Exception {
		TimeSheet timeSheet = null;
		String sql = "SELECT t FROM TimeSheetEntity t WHERE t.employeeId = ?1 AND date =?2";
		Query q = entityManager.createQuery(sql);
		q.setParameter(1, employeeId);
		q.setParameter(2, date);
		
		try {
			TimeSheetEntity timeSheetEntity = (TimeSheetEntity) q.getSingleResult();
				
			if(timeSheetEntity != null) {
				timeSheet = new TimeSheet();
				
				timeSheet.setTimeId(timeSheetEntity.getTimeId());
				timeSheet.setEmployeeId(timeSheetEntity.getEmployeeId());
				timeSheet.setAttendanceDate(timeSheetEntity.getAttendanceDate());
				timeSheet.setTimeIn(timeSheetEntity.getTimeIn());
				timeSheet.setTimeOut(timeSheetEntity.getTimeOut());
				timeSheet.setComment(timeSheetEntity.getComment());
			}
		} catch (Exception NoResultException) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());
			throw new Exception("Dao.USER_TIMESHEET_DOES_NOT_EXIST");
		}
			
		return timeSheet;
	}

	@Override
	public Integer clockIn(Integer employeeId, LocalDate date, LocalTime timeIn) throws Exception {
		Integer updateCount = null;
		
		/*
		 * get user time card using date and id
		 * then add time in information
		 * post it back
		 */
		
		try {
			
			String sql = "UPDATE TimeSheetEntity t SET t.timeIn =?1 WHERE t.employeeId = ?2 AND date =?3  ";
			Query q = entityManager.createQuery(sql);
		
			q.setParameter(1, timeIn);
			q.setParameter(2, employeeId);
			q.setParameter(3, date);
			
			updateCount = q.executeUpdate();//returns no. of entries updated
				
		} catch (Exception e) {
			System.out.println("Error in userLoginDAO: "+e.getMessage());
		}
		return updateCount;
	}

	@Override
	public Integer clockOut(Integer employeeId, LocalDate date,LocalTime timeOut) throws Exception {
		Integer updateCount = null;
		
		/*
		 * get user time card using date and id
		 * then add time in information
		 * post it back
		 */
		
		try {
			
			String sql = "UPDATE TimeSheetEntity t SET t.timeOut =?1 WHERE t.employeeId = ?2 AND date =?3  ";
			Query q = entityManager.createQuery(sql);
		
			q.setParameter(1, timeOut);
			q.setParameter(2, employeeId);
			q.setParameter(3, date);
			
			updateCount = q.executeUpdate();
				
		} catch (Exception e) {
			System.out.println("Error in userLoginDAO: "+e.getMessage());
		}
		return updateCount;
	}

	@Override
	public Integer addTimeSheet(TimeSheet timeSheet) throws Exception {
		Integer retValue = null;
		
		Integer timeId = null; 
		
		try {
			
			TimeSheetEntity timeSheetEntity = new TimeSheetEntity();
			timeSheetEntity.setEmployeeId(timeSheet.getEmployeeId());
			timeSheetEntity.setAttendanceDate(timeSheet.getAttendanceDate());
			timeSheetEntity.setTimeId(timeSheet.getTimeId());
			timeSheetEntity.setComment(timeSheet.getComment());
			timeSheetEntity.setTimeIn(timeSheet.getTimeIn());
			timeSheetEntity.setTimeOut(timeSheet.getTimeOut());

			
			entityManager.persist(timeSheetEntity);
			timeId = timeSheetEntity.getTimeId();
			
			retValue = timeId;
		} catch (Exception e) {
			System.out.println("Something went wrong while persisting to database: "+e.getMessage());
		}
		
		return retValue;
	}

}

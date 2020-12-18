package com.jrp.pma.dto;

import java.util.Date;

public interface TimelineData {

	//need to have the property names begin with get
	String getProjectName();
	Date getStartDate();
	Date getEndDate();
}

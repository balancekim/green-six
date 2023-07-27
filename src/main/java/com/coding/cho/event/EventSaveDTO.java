package com.coding.cho.event;

import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventSaveDTO {

	private long gno;
	private String name;
	private String content;

	private String[] url; //s3경로
	private String[] orgName; //s3경로
	private String[] newName; //s3경로
	private String[] bucketKey; //파일명
	private boolean[] def ; //true:def-img
	
	private List<EventImageEntity> gie ;
}

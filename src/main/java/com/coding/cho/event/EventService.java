package com.coding.cho.event;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface EventService {

	void save(EventSaveDTO dto);

	Map<String, String> tempUpload(MultipartFile temp);

}

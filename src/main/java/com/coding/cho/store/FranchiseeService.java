package com.coding.cho.store;

import org.springframework.ui.Model;

public interface FranchiseeService {

	void waiting(String id, Model model);
	
	void updateStatus(long storeNo, boolean status);
}

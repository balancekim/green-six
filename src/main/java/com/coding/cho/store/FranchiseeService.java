package com.coding.cho.store;

import java.util.List;

import org.springframework.ui.Model;

import com.coding.cho.order.dto.OrderDTO;

public interface FranchiseeService {

	void waiting(String id, Model model);
	
	void updateStatus(long storeNo, boolean status);

	List<OrderDTO> waiting(String id);

	void update(long cno);

	List<OrderDTO> processing(String id);

	void updateCancel(long cno);

	List<OrderDTO> cancel(String id);

	void updateEnd(long cno);

	List<OrderDTO> end(String id);




}

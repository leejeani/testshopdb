package com.shop.marker;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.dto.MarkerDTO;
import com.shop.service.MarkerService;

@SpringBootTest
class SelectMarkerTest {
	@Autowired
	MarkerService service;
	
	@Test
	void contextLoads() {
		List<MarkerDTO> list = null;
		try {
			list = service.getloc("j");
			for(MarkerDTO m : list) {
				System.out.println(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}






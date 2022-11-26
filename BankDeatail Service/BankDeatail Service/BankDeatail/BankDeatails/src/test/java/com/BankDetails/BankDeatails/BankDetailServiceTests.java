package com.BankDetails.BankDeatails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.BankDetails.BankDetails.BankModal.BankDetails;
import com.BankDetails.BankDetails.BankRepository.BankRepository;
import com.BankDetails.BankDetails.BankService.BankService;

@SpringBootTest
class BankDetailsSeerviceTests {

	@Autowired
	private BankService bs;
	
	@MockBean
	private BankRepository br;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getAdminTest() {
		when(br.findAll()).thenReturn(Stream
				.of(new BankDetails("Sbi", "236764823487", "romit halder", "7523465", "52357435656", "023","22/07"), new BankDetails("Sbi", "5623526662662", "Ranjan kumar", "8612861265", "532457435656", "065","21/07")).collect(Collectors.toList()));
		assertEquals(2, bs.getAllBank().size());
	}

}

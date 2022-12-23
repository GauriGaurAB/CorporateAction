package com.sdt.corporateaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CorporateActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorporateActionApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws GeneralSecurityException, IOException {
//		GoogleSheetsService googleSheetsService = new GoogleSheetsServiceImpl();
//		googleSheetsService.getSpreadsheetValues();
//	}
}

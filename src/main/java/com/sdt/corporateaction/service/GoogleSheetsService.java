package com.sdt.corporateaction.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GoogleSheetsService {

    List<List<Object>> getSpreadsheetValues() throws IOException, GeneralSecurityException;
}

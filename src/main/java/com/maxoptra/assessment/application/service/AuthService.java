package com.maxoptra.assessment.application.service;

import com.maxoptra.assessment.application.dto.LoginDto;

public interface AuthService {
	
	public boolean validateUser(LoginDto loginDto);

}

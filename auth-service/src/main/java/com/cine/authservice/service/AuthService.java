package com.cine.authservice.service;

import com.cine.authservice.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
}

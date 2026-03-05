package com.TuntunAuction.auth_service.services;

import com.TuntunAuction.auth_service.dtos.requests.RegisterRequest;
import com.TuntunAuction.auth_service.dtos.responses.RegisterResponse;

public interface RegisterService {

    RegisterResponse register(RegisterRequest registerRequest);
}

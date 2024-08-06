package br.com.events.event.event.business.service.impl;

import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.core.util.AuthUtil;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPerson;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthenticatedPerson getAuthenticatedPerson() {
        return AuthUtil.getAuthenticatedPerson();
    }
}

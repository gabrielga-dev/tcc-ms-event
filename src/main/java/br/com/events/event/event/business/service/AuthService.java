package br.com.events.event.event.business.service;

import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPerson;

public interface AuthService {

    AuthenticatedPerson getAuthenticatedPerson();
}

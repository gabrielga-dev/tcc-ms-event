package br.com.events.event.event.clean.data.io.inbound.auth;

import br.com.events.event.event.clean.data.io.outbound.msAuth.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
public class AuthenticatedPerson implements UserDetails {

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public AuthenticatedPerson(GetAuthenticatedPersonInformationResult response) {
        this.uuid = response.getUuid();
        this.firstName = response.getFirstName();
        this.lastName = response.getLastName();
        this.email = response.getEmail();
    }
}

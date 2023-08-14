package br.com.events.event.event.domain.mapper.event;

import br.com.events.event.event.domain.io.inbound.auth.AuthenticatedPerson;
import br.com.events.event.event.domain.io.inbound.event.create.base.EventAddressForm;
import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventRestForm;
import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventUseCaseForm;
import br.com.events.event.event.domain.io.inbound.event.create.out.CreateEventRestResult;
import br.com.events.event.event.domain.io.inbound.event.create.out.CreateEventUseCaseResult;
import br.com.events.event.event.domain.model.Address;
import br.com.events.event.event.domain.model.Event;
import br.com.events.event.event.util.helpers.DateHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

/**
 * This class map every needed class at event creation feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateEventMapper {


    /**
     * This method maps a {@link CreateEventRestForm} into a {@link CreateEventUseCaseForm} object
     *
     * @param createEventRestForm {@link CreateEventRestForm} object with the data to be mapped
     * @return {@link CreateEventUseCaseForm} object with the mapped information
     */
    public static CreateEventUseCaseForm toUseCaseForm(CreateEventRestForm createEventRestForm) {
        return CreateEventUseCaseForm
            .builder()
            .name(createEventRestForm.getName())
            .description(createEventRestForm.getDescription())
            .date(
                DateHelper.timestampToLocalDateTime(createEventRestForm.getDateTimestamp())
            )
            .address(createEventRestForm.getAddress())
            .build();
    }

    /**
     * This method maps a {@link CreateEventUseCaseResult} into a {@link CreateEventRestResult} object
     *
     * @param createEventUseCaseResult {@link CreateEventUseCaseResult} object with the data to be mapped
     * @return {@link CreateEventRestResult} object with the mapped information
     */
    public static CreateEventRestResult toRestResult(CreateEventUseCaseResult createEventUseCaseResult) {
        return CreateEventRestResult
            .builder()
            .name(createEventUseCaseResult.getName())
            .description(createEventUseCaseResult.getDescription())
            .dateTimestamp(
                DateHelper.LocalDateTimeToTimestamp(createEventUseCaseResult.getDate())
            )
            .build();
    }

    /**
     * This method maps a {@link CreateEventUseCaseForm} into a {@link Event} object
     *
     * @param form {@link CreateEventUseCaseForm} object with the data to be mapped
     * @return {@link Event} object with the mapped information
     */
    public static Event toEntity(final CreateEventUseCaseForm form) {
        var toReturn = new Event();
        toReturn.setName(form.getName());
        toReturn.setDescription(form.getDescription());
        toReturn.setDate(form.getDate());
        toReturn.setCreationDate(LocalDateTime.now());

        var authenticated = (AuthenticatedPerson) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        toReturn.setOwnerUuid(authenticated.getUuid());

        var address = toEntity(form.getAddress());
        address.setEventUuid(toReturn.getUuid());
        address.setEvent(toReturn);

        return toReturn;
    }

    /**
     * This method maps a {@link CreateEventUseCaseForm} into a {@link Event} object
     *
     * @param form {@link CreateEventUseCaseForm} object with the data to be mapped
     * @return {@link Event} object with the mapped information
     */
    public static Address toEntity(final EventAddressForm form) {
        var toReturn = new Address();
        toReturn.setStreet(form.getStreet());
        toReturn.setNeighbour(form.getNeighbour());
        toReturn.setComplement(form.getComplement());
        toReturn.setCity(form.getCityId());
        toReturn.setState(form.getStateIso());
        toReturn.setCountry(form.getCountryIso());
        toReturn.setZipCode(form.getZipCode());
        toReturn.setLatitude(form.getLatitude());
        toReturn.setLongitude(form.getLongitude());

        return toReturn;
    }

    /**
     * This method maps a {@link Event} into a {@link CreateEventUseCaseResult} object
     *
     * @param saved {@link Event} object with the data to be mapped
     * @return {@link CreateEventUseCaseResult} object with the mapped information
     */
    public static CreateEventUseCaseResult toUseCaseResult(final Event saved) {

        return CreateEventUseCaseResult
            .builder()
            .uuid(saved.getUuid())
            .name(saved.getName())
            .description(saved.getDescription())
            .date(saved.getDate())
            .build();
    }
}

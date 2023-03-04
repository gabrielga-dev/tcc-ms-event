package br.com.events.event.event.domain.mapper.event;

import br.com.events.event.event.domain.io.auth.AuthenticatedPerson;
import br.com.events.event.event.domain.io.event.create.rest.in.AddressCreateEventRestForm;
import br.com.events.event.event.domain.io.event.create.rest.in.CreateEventRestForm;
import br.com.events.event.event.domain.io.event.create.rest.out.CreateEventRestResult;
import br.com.events.event.event.domain.io.event.create.useCase.in.AddressCreateEventUseCaseForm;
import br.com.events.event.event.domain.io.event.create.useCase.in.CreateEventUseCaseForm;
import br.com.events.event.event.domain.io.event.create.useCase.out.CreateEventUseCaseResult;
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
            .address(toUseCaseForm(createEventRestForm.getAddress()))
            .build();
    }


    /**
     * This method maps a {@link AddressCreateEventRestForm} into a {@link AddressCreateEventUseCaseForm} object
     *
     * @param addressCreateEventRestForm {@link AddressCreateEventRestForm} object with the data to be mapped
     * @return {@link AddressCreateEventUseCaseForm} object with the mapped information
     */
    public static AddressCreateEventUseCaseForm toUseCaseForm(AddressCreateEventRestForm addressCreateEventRestForm) {
        return AddressCreateEventUseCaseForm
            .builder()
            .street(addressCreateEventRestForm.getStreet())
            .neighbour(addressCreateEventRestForm.getNeighbour())
            .complement(addressCreateEventRestForm.getComplement())
            .city(addressCreateEventRestForm.getCity())
            .state(addressCreateEventRestForm.getState())
            .country(addressCreateEventRestForm.getCountry())
            .zipCode(addressCreateEventRestForm.getZipCode())
            .latitude(addressCreateEventRestForm.getLatitude())
            .longitude(addressCreateEventRestForm.getLongitude())
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

        var authenticated = (AuthenticatedPerson)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
    public static Address toEntity(final AddressCreateEventUseCaseForm form) {
        var toReturn = new Address();
        toReturn.setStreet(form.getStreet());
        toReturn.setNeighbour(form.getNeighbour());
        toReturn.setComplement(form.getComplement());
        toReturn.setCity(form.getCity());
        toReturn.setState(form.getState());
        toReturn.setCountry(form.getCountry());
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

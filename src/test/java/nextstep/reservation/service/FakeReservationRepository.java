package nextstep.reservation.service;

import nextstep.reservation.domain.Reservation;
import nextstep.reservation.domain.Theme;
import nextstep.reservation.repository.ReservationRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FakeReservationRepository implements ReservationRepository {

    private final Map<Long, Reservation> reservations = new HashMap<>();

    private final Theme theme = new Theme("워너고홈", "병맛 어드벤처 회사 코믹물", 29_000);

    private Long reservationIdIndex = 0L;


    @Override
    public boolean existsByDateAndTime(Reservation reservation) {
        return reservations.values()
                .stream()
                .anyMatch(r -> Objects.equals(reservation.getDate(), r.getDate()) &&
                        Objects.equals(reservation.getTime(), r.getTime()));
    }

    @Override
    public Reservation insert(Reservation reservation) {
        reservation = new Reservation(
                ++reservationIdIndex,
                reservation.getDate(),
                reservation.getTime(),
                reservation.getName(),
                theme
        );
        reservations.put(reservationIdIndex, reservation);

        return reservation;
    }

    @Override
    public Reservation getById(Long id) {
        return reservations.get(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return reservations.keySet()
                .removeIf(id::equals);
    }
}

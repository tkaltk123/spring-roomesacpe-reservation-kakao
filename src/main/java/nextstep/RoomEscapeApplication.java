package nextstep;

import nextstep.console.controller.RoomEscapeController;
import nextstep.console.view.RoomEscapeInput;
import nextstep.console.view.RoomEscapeOutput;
import nextstep.reservation.mapper.ReservationMapper;
import nextstep.reservation.repository.ReservationRepository;
import nextstep.reservation.repository.SimpleReservationRepository;
import nextstep.reservation.repository.jdbc.ReservationResultSetParser;
import nextstep.reservation.repository.jdbc.ReservationStatementCreator;
import nextstep.reservation.service.ReservationService;
import nextstep.reservation.service.ReservationServiceImpl;
import org.mapstruct.factory.Mappers;

public class RoomEscapeApplication {


    private static void main(String[] args) {
        createController().run();
    }

    private static RoomEscapeController createController() {
        RoomEscapeInput input = new RoomEscapeInput(System.in);
        RoomEscapeOutput output = new RoomEscapeOutput();
        ReservationService service = createService();

        return new RoomEscapeController(input, output, service);
    }

    private static ReservationService createService() {
        ReservationRepository repository = createRepository();
        ReservationMapper mapper = Mappers.getMapper(ReservationMapper.class);

        return new ReservationServiceImpl(repository, mapper);
    }

    private static ReservationRepository createRepository() {
        ReservationStatementCreator statementCreator = new ReservationStatementCreator();
        ReservationResultSetParser resultSetParser = new ReservationResultSetParser();

        return new SimpleReservationRepository(statementCreator, resultSetParser);
    }
}

import org.junit.jupiter.api.BeforeEach;
import task1.DivisionManager;
import task1.Participant;

import java.util.ArrayList;
import java.util.List;

public class DivisionManagerTest {
	private List<Participant> participants;
	private DivisionManager divisionManager;

	@BeforeEach
	void setUp() {
		participants = new ArrayList<>();
		participants.add(new Participant("Петя", 500.0));
		participants.add(new Participant("Федя", 300.0));
		participants.add(new Participant("Иван", 280.0));
		divisionManager = new DivisionManager(participants);
	}
}

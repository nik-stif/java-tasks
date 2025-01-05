package task1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Participant("Петя", 500.0));
        participants.add(new Participant("Федя", 300.0));
        participants.add(new Participant("Иван", 280.0));

        DivisionManager divisionManager = new DivisionManager(participants);
        divisionManager.calculateCheque();
    }
}

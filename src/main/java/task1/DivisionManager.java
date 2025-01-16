package task1;

import java.util.ArrayList;
import java.util.List;

public class DivisionManager {
    private List<Participant> participants;

    public DivisionManager(List<Participant> participants) {
        this.participants = participants;
    }

    public void calculateCheque() {
        double totalSum = calculateTotalSum();
        double averageSum = totalSum / participants.size();

        System.out.println("Расчет чека:");
        System.out.println("Общая сумма чека: " + totalSum);
        System.out.println("Сумма на каждого участника: " + averageSum);
        System.out.println();

        List<Participant> difference = calculateDifference(averageSum);
        List<List<Participant>> listUnderpayersAndOverpayers = divideToUnderpayersAndOverpayers(difference);
        calculateMoneyTransfer(listUnderpayersAndOverpayers.get(0), listUnderpayersAndOverpayers.get(1));
    }

    private double calculateTotalSum() {
        double totalSum = 0;
        for (Participant participant : participants) {
            totalSum += participant.getPayment();
        }
        return totalSum;
    }

    private List<Participant> calculateDifference(double averageSum) {
        List<Participant> difference = new ArrayList<>();
        for (Participant participant : participants) {
            difference.add(new Participant(participant.getName(), participant.getPayment() - averageSum));
        }
        return difference;
    }

    private List<List<Participant>> divideToUnderpayersAndOverpayers(List<Participant> difference) {
        List<Participant> underpayers = new ArrayList<>();
        List<Participant> overpayers = new ArrayList<>();

        for (Participant participant : difference) {
            if (participant.getPayment() < 0) {
                underpayers.add(participant);
            } else if (participant.getPayment() > 0) {
                overpayers.add(participant);
            }
        }

        List<List<Participant>> result = new ArrayList<>();
        result.add(underpayers);
        result.add(overpayers);
        return result;
    }

    private void calculateMoneyTransfer(List<Participant> underpayers, List<Participant> overpayers) {
        int underpayersIndex = 0;
        int overpayersIndex = 0;

        while (underpayersIndex < underpayers.size() && overpayersIndex < overpayers.size()) {
            Participant underpayer = underpayers.get(underpayersIndex);
            Participant overpayer = overpayers.get(overpayersIndex);

            double transfer = Math.min(underpayer.getPayment() * (-1), overpayer.getPayment());

            System.out.println(underpayer.getName() + " отдает " + overpayer.getName() + " " + transfer);

            underpayer.setPayment(underpayer.getPayment() + transfer);
            overpayer.setPayment(overpayer.getPayment() - transfer);

            if (underpayer.getPayment() == 0) {
                underpayersIndex++;
            }

            if (overpayer.getPayment() == 0) {
                overpayersIndex++;
            }
        }
    }
}

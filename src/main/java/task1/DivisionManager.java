package task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, Double> difference = calculateDifference(averageSum);
        calculateMoneyTransfer(difference);
    }

    private double calculateTotalSum() {
        double totalSum = 0;
        for (Participant participant : participants) {
            totalSum += participant.getPayment();
        }
        return totalSum;
    }

    private Map<String, Double> calculateDifference(double averageSum) {
        Map<String, Double> difference = new HashMap<>();
        for (Participant participant : participants) {
            difference.put(participant.getName(), participant.getPayment() - averageSum);
        }
        return difference;
    }

    private void calculateMoneyTransfer(Map<String, Double> difference) {
        for (String underpayer : difference.keySet()) {
            while (difference.get(underpayer) < 0) {
                String overpayer = findOverpayer(difference);
                double transfer = Math.min(difference.get(underpayer) * (-1), difference.get(overpayer));

                System.out.println(underpayer + " отдает " + overpayer + " " + transfer);

                difference.put(underpayer, difference.get(underpayer) + transfer);
                difference.put(overpayer, difference.get(overpayer) - transfer);
            }
        }
    }

    private static String findOverpayer(Map<String, Double> difference) {
        for (Map.Entry<String, Double> entry : difference.entrySet()) {
            if (entry.getValue() > 0) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("Переплат нет, а недоплата все еще существует");
    }
}

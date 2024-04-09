package view;

import integration.Observer;

public class TotalRevenueView implements Observer{

    public TotalRevenueView(){}

    @Override
    public void update(double totalAmount) {
        System.out.printf("Total paid amount: %,.2f \n\n", totalAmount);
    }
}

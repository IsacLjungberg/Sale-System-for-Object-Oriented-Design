package se.kth.salesystem.integration;

public abstract class ObserverTemplate implements Observer{
    public void update(double totalRevenue) {
        //calculateTotalIncome(totalRevenue);
        //fråga om eventuellt
        showTotalRevenue(totalRevenue);
    }
    
    private void showTotalRevenue (double totalRevenue) {
        try{
            doShowTotalRevenue(totalRevenue);
        } catch ( Exception e ) {
            handleErrors ( e );
        }
    }
        
    protected abstract void doShowTotalRevenue(double totalRevenue) throws Exception ;
        
    protected abstract void handleErrors (Exception e);
}



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyConversionService {

	private Map<String, Double> exchangeRates;

	public CurrencyConversionService() {
		exchangeRates = new HashMap<>();

		exchangeRates.put("USD", 1.0);
		exchangeRates.put("EUR", 2.0);// Defining a Base currency
	}
	
	// Inserting the exchange rates
	public Map<String, Object> setExchangeRates(Map<String, Double> rates) {
	    Map<String, Object> response = new HashMap<>();
	    
	    if (rates != null) {
	        for (Map.Entry<String, Double> entry : rates.entrySet()) {
	            exchangeRates.put(entry.getKey(), entry.getValue());
	        }
	        response.put("success", true);
	        response.put("message", "Exchange rates have been updated.");
	    } else {
	        response.put("success", false);
	        response.put("message", "No exchange rates provided.");
	    }
	    
	    return response;
	}

 
	
	// Validating if we have any Exchange rates
    public Map<String, Double> getExchangeRatesValues() {
        System.out.println(exchangeRates);
        return (exchangeRates);
    }
    
    public List<Double> convertCurrencies(List<ConversionRequest> requests){

        List<Double> convertedAmounts = new ArrayList<>();
        for(ConversionRequest request : requests){
            double amount = request.getAmount();
            String fromCurrency = request.getFromCurrency();
            String toCurrency = request.getToCurrency();
        if(exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            double exchangeRate = exchangeRates.get(toCurrency) / exchangeRates.get(fromCurrency);
            double convertedAmount =  amount * exchangeRate;
            convertedAmounts.add(convertedAmount);
        }
        else {
            convertedAmounts.add(-1.0); // Failed to convert
        }  
        }
        return convertedAmounts;
    }

	//Exchange Rate calculation 
	public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
		if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
			double exchangeRate = exchangeRates.get(toCurrency) / exchangeRates.get(fromCurrency);
			double convertedAmount = amount * exchangeRate;
			System.out.println("Converting " + amount + " " + fromCurrency + " to " + toCurrency);
			System.out.println("Exchange Rate: " + exchangeRate);
			System.out.println("Converted Amount: " + convertedAmount);
			return convertedAmount;
		} 
		else 
		{
			String errorMessage = "Failed to convert currency. Exchange rates for " + fromCurrency + " to " + toCurrency
					+ " not available.";
			System.err.println(errorMessage);
			return -1;
		}
	}
	
	//Clear Exchange Rates
	public String clearExchangeRates() {
		exchangeRates.clear();
		System.out.println("Clearing exchange rates");
		return "Exchange rates have been cleared.";
	}

    public static void main(String[] args) {
        CurrencyConversionService currencyService = new CurrencyConversionService();
    
        // Adding example exchange rates
        Map<String, Double> exampleRates = new HashMap<>();
        exampleRates.put("INR", 74.0);
        exampleRates.put("JPY", 110.0);
    
        currencyService.setExchangeRates(exampleRates);
    
        // Validating if exchange rates are available
        Map<String, Double> ratesAvailable = currencyService.getExchangeRatesValues();
        System.out.println("Exchange rates available: " + ratesAvailable);
    
        // Performing currency conversion
        double convertedAmount = currencyService.convertCurrency(100.0, "USD", "EUR");
        System.out.println("Converted Amount: " + convertedAmount);
    


          // Creating an array of conversion requests
          List<ConversionRequest> conversionRequests = new ArrayList<>();
          conversionRequests.add(new ConversionRequest(100.0, "USD", "USD"));
          conversionRequests.add(new ConversionRequest(50.0, "EUR", "GBP"));
  
          // Performing currency conversions
          List<Double> convertedAmounts = currencyService.convertCurrencies(conversionRequests);
          System.out.println("Converted Amounts: " + convertedAmounts);
          // Clearing exchange rates
        String clearResponse = currencyService.clearExchangeRates();
        System.out.println(clearResponse);
    }

    
    
}
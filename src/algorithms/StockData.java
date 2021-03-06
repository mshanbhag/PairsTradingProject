/*
 * File: StockData.java
 * -------------------------
 * This file pulls in stock data in .csv form and tokenizes it,
 * putting the prices into an array of daily opens.
 */

/* Package Designation */
package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RVector;
import org.rosuda.JRI.Rengine;

/* Imports */

public class StockData {

	/* Class Variables */
	private ArrayList<Double> prices;
	private String stockSymbol;
	private String fullName;

	public StockData(String n, String s) {
		fullName = n;
		stockSymbol = s;
		prices = new ArrayList<Double>();

	}
	public Double getPrice(int index) {
		return prices.get(index);
	}
	public void addPrice(String l) {
		Double d= getOpenPriceFromLine(l);
		prices.add(getOpenPriceFromLine(l));
	}
	public void addPrice(Double d) {
		prices.add(d);
	}
	// Allows us to get the array.
	public ArrayList<Double> getPrices() {
		return prices;
	}

	// Allows us to get the stockSymbol.
	public String getStockSymbol() {
		return stockSymbol;
	}

	// Allows us to get the stockSymbol.
	public String getFullName() {
		return fullName;
	}
	
	public static Double getOpenPriceFromLine(String currentLine) {
		int start = currentLine.indexOf(',') + 1;
		currentLine = currentLine.substring(start);
		currentLine = currentLine.substring(0, currentLine.indexOf(','));
		double dailyPrice = Double.parseDouble(currentLine);
		return dailyPrice;
	}
	static public StockData readInData(int days, String ticker) {
		StockData s = new StockData("name", ticker);
		ArrayList<String> lines = DataProcessing.getXMostRecentDaysData(ticker, days);
		for (int i = 1; i < lines.size();i++) {
			s.addPrice(lines.get(i));
		}
		return s;
	}
	
	public static ArrayList<Double> calculateRatios(StockData s1, StockData s2) {
		ArrayList<Double> ratios= new ArrayList<Double>();
		for (int i = 0; i < s1.getPrices().size(); i++) {
			Double r = s1.getPrice(i) / s2.getPrice(i);
			ratios.add(r);
		}
		return ratios;
	}
	public String getPricesString() {
		String ans = "";
		for (int i = 0; i < prices.size(); i++) {
			ans += prices.get(i) + ", ";
		}
		return ans;
	}
	public double[] getPricesArray() {
		double[] p = new double[prices.size()];
		for (int i = 0; i < prices.size(); i++) {
			p[i] = prices.get(i).doubleValue();
		}
		return p;
		
	}

}

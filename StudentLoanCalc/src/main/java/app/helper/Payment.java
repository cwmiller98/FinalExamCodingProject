package app.helper;
import app.StudentCalc;
import app.controller.LoanCalcViewController;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.time.LocalDate;

import org.apache.poi.ss.formula.functions.Finance;

public class Payment {
	private static int pmtNbr= 0;
	private int myPmtNbr;
	private double loanBalance;
	private double interestRate;
	private double monthPay;
	private double extraPayment;
	private LocalDate pmtDate;
	private double principle;
	private double interest;
	
	public Payment(double loanBalance, double interestRate,double monthlyPmt, double extra, LocalDate ld) {
		this.loanBalance = loanBalance;
		this.interestRate = interestRate;
		monthPay = monthlyPmt;
		extraPayment = extra;
		month();
		pmtDate = ld;
		pmtNbr++;
		myPmtNbr = pmtNbr;
		
	}
	public void month() {
		if(loanBalance>monthPay+extraPayment) {
			interest = interestRate/12*loanBalance;
			System.out.println("Amount paid to interest is: " +interest);
			
			principle = (monthPay-interest+extraPayment);
			System.out.println("Amount paid to principle is: " +principle);
			
			System.out.println("Balance before: " + loanBalance);
			System.out.println("interest added this month " + Math.round(loanBalance * interestRate/12*100.0)/100.0);
			loanBalance = Math.round((loanBalance - principle)*100.0)/100.0;
			System.out.println("Balance after: " + loanBalance);
		}
		else {
			monthPay = loanBalance;
			extraPayment = 0;
			principle = loanBalance;
			interest = interestRate/12*loanBalance;
			loanBalance = 0;
		}


}
	public double getBalance() {
		return loanBalance;
	}
	public void setPmtNbr(int i) {
		pmtNbr = i;
	}
	public int getMyPmtNbr() {
		return myPmtNbr;
	}
	public double getPrinciple() {
		return Math.round(principle*100.0)/100.0;
	}

	public double getExtraPayment() {
		return extraPayment;
	}

	public double getInterest() {
		return Math.round(interest*100.0)/100.0;
	}
	public LocalDate getPmtDate() {
		return pmtDate;
	}
	
	public double getMonthPay() {
		return monthPay;
	}
	}

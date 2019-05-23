package app.helper;
import app.StudentCalc;
import app.controller.LoanCalcViewController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Loan {
	double dLoanAmount;
	double dInterestRate;
	int iTerm;
	double dFutureValue;
	boolean bInterestCalc;
	double dExtraPayment;
	double monthlyPayment;
	LocalDate pmtDate;
	
	private LinkedList<Payment> loanPayments = new LinkedList<Payment>();
	
	public Loan(double loanAmount, double interest,
			int term, double extraPayment, LocalDate ld) {
		dLoanAmount = loanAmount;
		dInterestRate = interest;
		iTerm = term;
		dExtraPayment = extraPayment;
		dFutureValue = 0.0;
		pmtDate= ld;
		calcPayment();
		goLoan();
	}
	private void calcPayment() {
			monthlyPayment = Math.round(-1*FinanceLib.pmt(dInterestRate/12, 12*iTerm, 
					dLoanAmount, 0, false)*100.0)/100.0;
}
	
	private void goLoan() {
		
			do {
				Payment pay = new Payment(dLoanAmount,dInterestRate,monthlyPayment,dExtraPayment,pmtDate);
				dLoanAmount = pay.getBalance();
				loanPayments.add(pay);
				pmtDate = pmtDate.plusMonths(1);
			
		}
			while(dLoanAmount > (monthlyPayment+dExtraPayment));
			Payment pay = new Payment(dLoanAmount, dInterestRate, monthlyPayment, dExtraPayment,pmtDate.plusMonths(1));
			loanPayments.add(pay);
			pay.setPmtNbr(0);
	}
	
	public LinkedList<Payment> getLoanPayments(){
		return loanPayments;
	}
	
	public double calcPrinciplePayment() {
		double princSum = 0.0;
		for (Payment p : this.getLoanPayments()) 
		{
			princSum += p.getPrinciple();
		}
		
		return Math.round(princSum*100.0)/100.0;
	}
	
	public double calcInterestPayment() {
		double intSum = 0.0;
		for (Payment p : this.getLoanPayments()) {
			intSum +=p.getInterest();
		}
		
		return Math.round(intSum * 100.0)/100.0;
	}
	
	public double calcTotalPayment() {
		return calcPrinciplePayment() + calcInterestPayment();
	}
}

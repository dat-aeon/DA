package mm.aeon.com.ass.front.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.Irr;

public class LoanCalculator {

    private static boolean paymentOnSaleDate = true;
    private static int monthlyInstallmentRound = -2;
    private static int firstInstallmentRound = -2;

    public static double calculateLastPayment(double totalProductPrice, double firstPayment, double monthlyPayment,
            double totalInterest, int loanTerm, double monthlyInstallment) {
        double lastPayment = 0;
        Calendar cal = Calendar.getInstance();

        Date today = cal.getTime();
        cal.add(Calendar.MONTH, loanTerm + 1); // to get previous year add -1
        cal.set(5, 2); // set date to alway 2
        Date lastDate = cal.getTime();

        long diff = lastDate.getTime() - today.getTime();
        long totalDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        List<Double> cashFlows = new ArrayList<Double>();
        cashFlows.add(totalProductPrice * -1);
        cashFlows.add(firstPayment);
        for (int i = 0; i < loanTerm - 1; i++) {
            cashFlows.add(monthlyPayment);
        }

        double[] target = new double[cashFlows.size()];
        for (int i = 0; i < target.length; i++) {
            target[i] = cashFlows.get(i);
        }

        double amountDue = Irr.irr(target) * 100;

        double eirDays = 0;
        if (totalDays <= 361 && totalDays >= 357) {
            eirDays = amountDue * 365 / totalDays;
        } else {
            eirDays = amountDue;
        }

        double baseEIR = amountDue;

        double apr = 0;
        if (loanTerm > 12) {
            apr = eirDays * 12;
        } else {
            apr = eirDays * loanTerm;
        }

        double apr28 = 0;
        if (apr > 28) {
            apr28 = (totalInterest * 28 / 100) / apr * 100;
        } else {
            apr28 = 0;
        }
        DecimalFormat ttt = new DecimalFormat("######");

        double readjustLastPayment = 0;
        if (apr > 28) {
            readjustLastPayment = Math.round((totalInterest - Double.parseDouble(ttt.format(apr28))) / 100) * 100;
        } else {
            readjustLastPayment = 0;
        }

        lastPayment = monthlyInstallment - readjustLastPayment;
        return lastPayment;
    }

    public static double calculateFirstPayment(double initialPaymentForPSG, double totalRepayment,
            double monthlyInstallment, int loanTerm, double firstPaymentForPSG) {
        double firstPayment = 0;

        if (initialPaymentForPSG == 0) {
            double value1 = monthlyInstallment * (loanTerm - 1);
            double value2 = totalRepayment - value1;
            Integer value2AsInt = (int) value2;
            String value2AsString = value2AsInt.toString();
            String lastTwoDigitsStringForValue2;
            int lastTwoDigitsForValue2;

            if (value2AsString.length() > 2) {
                lastTwoDigitsStringForValue2 = value2AsString.substring(value2AsString.length() - 2);
            } else {
                lastTwoDigitsStringForValue2 = value2AsString;
            }
            lastTwoDigitsForValue2 = Integer.parseInt(lastTwoDigitsStringForValue2);

            if (lastTwoDigitsForValue2 < 50) {
                double value3 = monthlyInstallment * (loanTerm - 1);
                double value4 = totalRepayment - value3;
                firstPayment = excelRoundDown(value4, firstInstallmentRound);
            } else {
                double value5 = monthlyInstallment * (loanTerm - 1);
                double value6 = totalRepayment - value5;
                firstPayment = excelRoundUp(value6, firstInstallmentRound);
            }

        } else {
            firstPayment = firstPaymentForPSG;
        }

        return firstPayment;
    }

    public static double calculateFirstPaymentForPSG(double initialPaymentForPSG) {
        double firstPaymentForPSG = 0;
        if (paymentOnSaleDate) {
            firstPaymentForPSG = initialPaymentForPSG;
        } else {
            firstPaymentForPSG = initialPaymentForPSG;
        }

        return firstPaymentForPSG;
    }

    public static double calculateTotalConSaving(double consaving, int loanTerm) {
        double totalConSaving = consaving;
        if (loanTerm == 6) {
            totalConSaving += 100;
        } else if (loanTerm == 9 || loanTerm == 12) {
            totalConSaving += 200;
        } else if (loanTerm == 18) {
            totalConSaving += 300;
        } else if (loanTerm == 24) {
            totalConSaving += 400;
        }
        return totalConSaving;
    }

    public static double calculateMonthlyInstallment(double totalProductPrice, double monthlyInstallmentForPSG,
            int loanTerm) {
        double monthlyInstallment = 0;
        if (monthlyInstallmentForPSG == 0) {
            monthlyInstallment = excelRoundDown(totalProductPrice / loanTerm, monthlyInstallmentRound);
        } else {
            monthlyInstallment = monthlyInstallmentForPSG;
        }
        return monthlyInstallment;
    }

    public static double calculateTotalRepayment(double financeAmount, double totalInterest) {
        double totalRepayment = 0;
        totalRepayment = financeAmount + totalInterest;
        return totalRepayment;
    }

    public static double modifyCalculateTotalRepayment(double monthlyPayment, int loanTerm, double firstPayment,
            double lastPayment) {
        double modifyTotalRepayment = 0;
        modifyTotalRepayment = (monthlyPayment * (loanTerm - 2)) + firstPayment + lastPayment;
        return modifyTotalRepayment;
    }

    public static double calculateTotalInterest(double financeAmount, int loanTerm, double interestRateMonthly,
            double totalInterestForPSG) {
        double totalInterest = 0;

        if (totalInterestForPSG == 0) {
            if (financeAmount * interestRateMonthly * loanTerm > 49) {
                totalInterest = Math.ceil((financeAmount / 100) * interestRateMonthly * loanTerm / 100) * 100;
            } else {
                totalInterest = Math.floor((financeAmount / 100) * interestRateMonthly * loanTerm / 100) * 100;
            }
        } else {
            totalInterest = totalInterestForPSG;
        }

        return totalInterest;
    }

    public static double calculateFinanceAmountForPSG(double totalProductPrice, double downPayment,
            double promoDiscount, double insPremium, double vatAmount, double financeAmountForPSG) {
        double financeAmount = 0;
        if (financeAmountForPSG == 0) {
            if (paymentOnSaleDate) {
                financeAmount = totalProductPrice - downPayment - promoDiscount + insPremium + vatAmount;
            } else {
                financeAmount = totalProductPrice - downPayment - promoDiscount + insPremium + vatAmount;
            }
        } else {
            financeAmount = financeAmountForPSG;
        }

        return financeAmount;
    }

    public static double calculateProcessingFees(boolean motorcycleLoanFlag, double totalProductPrice) {
        double processingFees = 0;
        if (motorcycleLoanFlag) {
            if (totalProductPrice >= 350000 && totalProductPrice < 400000) {
                processingFees = (double) 7000;
            } else if (totalProductPrice >= 400000 && totalProductPrice < 500000) {
                processingFees = (double) 8000;
            } else if (totalProductPrice >= 500000) {
                processingFees = (double) 10000;
            }
        } else {
            if (totalProductPrice >= 50000 && totalProductPrice <= 100000) {
                processingFees = (double) 0;
            } else if (totalProductPrice > 100000 && totalProductPrice <= 200000) {
                processingFees = (double) 1000;
            } else if (totalProductPrice > 200000 && totalProductPrice <= 300000) {
                processingFees = (double) 2000;
            } else if (totalProductPrice > 300000 && totalProductPrice <= 400000) {
                processingFees = (double) 3000;
            } else if (totalProductPrice > 400000 && totalProductPrice <= 500000) {
                processingFees = (double) 4000;
            } else if (totalProductPrice > 500000 && totalProductPrice <= 600000) {
                processingFees = (double) 5000;
            } else if (totalProductPrice > 600000 && totalProductPrice <= 700000) {
                processingFees = (double) 6000;
            } else if (totalProductPrice > 700000) {
                processingFees = (double) 10000;
            }
        }
        return processingFees;
    }

    public static Double excelRoundUp(double amount, int signature) {
        double roundDownValue = 0;
        if (signature > 0) {
            BigDecimal bd = new BigDecimal(amount);
            bd = bd.setScale(signature, BigDecimal.ROUND_UP);
            roundDownValue = bd.doubleValue();
        }
        if (signature < 0) {
            roundDownValue = roundItTheHardWayForRoundUp(amount, signature);
        }
        return roundDownValue;

    }

    public static Double excelRoundDown(double amount, int signature) {
        double roundDownValue = 0;
        if (signature > 0) {
            BigDecimal bd = new BigDecimal(amount);
            bd = bd.setScale(signature, BigDecimal.ROUND_DOWN);
            roundDownValue = bd.doubleValue();
        }
        if (signature < 0) {
            roundDownValue = roundItTheHardWayForRoundDown(amount, signature);
        }
        return roundDownValue;

    }

    public static Double roundItTheHardWayForRoundUp(double x, int p) {
        return ((double) Math.ceil(x * Math.pow(10, p))) / Math.pow(10, p);
    }

    public static Double roundItTheHardWayForRoundDown(double x, int p) {
        return ((double) Math.floor(x * Math.pow(10, p))) / Math.pow(10, p);
    }
}

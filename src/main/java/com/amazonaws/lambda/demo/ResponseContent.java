package com.amazonaws.lambda.demo;

public class ResponseContent {

	String cin;
	double total_discounted;
	double last_month;
	double pending_payments;
	boolean status;

	public ResponseContent() {
		cin = "0";
		total_discounted = -1.0;
		last_month = -1.0;
		pending_payments = -1.0;
		status = false;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public double getTotal_discounted() {
		return total_discounted;
	}

	public void setTotal_discounted(double total_discounted) {
		this.total_discounted = total_discounted;
	}

	public double getLast_month() {
		return last_month;
	}

	public void setLast_month(double last_month) {
		this.last_month = last_month;
	}

	public double getPending_payments() {
		return pending_payments;
	}

	public void setPending_payments(double pending_payments) {
		this.pending_payments = pending_payments;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isValid() {
		if (cin != "0" && (this.last_month != 0 || this.pending_payments != 0 || this.total_discounted != 0)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ResponseContent [cin=" + cin + ", total_discounted=" + total_discounted + ", last_month=" + last_month
				+ ", pending_payments=" + pending_payments + ", status=" + status + "]";
	}

}

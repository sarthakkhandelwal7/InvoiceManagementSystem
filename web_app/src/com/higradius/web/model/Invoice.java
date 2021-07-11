package com.higradius.web.model;

public class Invoice {
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public float getInvoice_amount() {
		return invoice_amount;
	}
	public void setInvoice_amount(float invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getPredicted_payment_date() {
		return predicted_payment_date;
	}
	public void setPredicted_payment_date(String predicted_payment_date) {
		this.predicted_payment_date = predicted_payment_date;
	}
	public String getNotes() {
		return notes;
	}
	public int getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(int serial_no) {
		this.serial_no = serial_no;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	String customer_name;
	String customer_number;
	String invoice_id;
	float invoice_amount;
	String due_date;
	String predicted_payment_date;
	String notes;
	int serial_no;
}

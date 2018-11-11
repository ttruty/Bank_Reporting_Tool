package controllers;

import java.sql.ResultSet;

import models.DaoModel;
import records.BankRecords;
import views.LoanView;

public class LoanProcessing extends BankRecords {

	public static void main()
	{
		BankRecords br = new BankRecords();
		br.readData();
		DaoModel dao = new DaoModel();
		dao.createTable();
		dao.insertRecords(robjs); // perform inserts
		ResultSet rs;
		rs = dao.retrieveRecords();
		new LoanView().runView(rs);

	}
}

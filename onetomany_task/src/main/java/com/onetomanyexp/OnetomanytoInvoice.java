package com.onetomanyexp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class OnetomanytoInvoice {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			emf = Persistence.createEntityManagerFactory("company");
			entityManager = emf.createEntityManager();

			transaction = entityManager.getTransaction();

			// start transaction
			transaction.begin();

			// entity
			Company company = new Company();
			company.setCompanyName("xyz");
			company.setRegno("1");
			company.setContactNo("+123456789");

			Invoice invoice1 = new Invoice();
			invoice1.setAmount(12000);
			invoice1.setCompany(company);


			List<Invoice> invoiceList = new ArrayList();
			invoiceList.add(invoice1);

			company.setListInvoice(invoiceList);

			// save call
			entityManager.persist(company);
			System.out.println("Company Data Successfully");

		} catch (Exception e) {
			System.out.println(e);
			transaction.rollback();
		} finally {
			if (transaction.isActive()) {
				transaction.commit();
			}
			if (entityManager != null) {
				entityManager.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}

}

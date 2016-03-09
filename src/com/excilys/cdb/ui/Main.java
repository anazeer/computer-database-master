package com.excilys.cdb.ui;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.NumberFormatter;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

public class Main {
	
	// Services
	static ComputerService computerService = new ComputerService();
	static CompanyService companyService = new CompanyService();
	
	// List containing valid inputs for each menu
	static List<Integer> generalInstr = new ArrayList<Integer>();
	static List<Integer> companyInstr = new ArrayList<Integer>();
	static List<Integer> computerInstr = new ArrayList<Integer>();
	
	// Number of options for each menu
	static final int countGeneralInstr = 3;
	static final int countCompanyInstr = 2;
	static final int countComputerInstr = 6;
	
	// Read inputs
	static Scanner scan;
	
	/**
	 * Show main menu
	 */
	static void showGeneralInstr() {
		System.out.println("\nComputer database management");
		System.out.println("Choose your database:");
		System.out.println("1. Company");
		System.out.println("2. Computer");
		System.out.println("3. Exit");
	}
	
	/**
	 * Show company menu
	 */
	static void showCompanyInstr() {
		System.out.println("\nCompany");
		System.out.println("Choose your operation:");
		System.out.println("1. List companies");
		System.out.println("2. Return to menu");
	}
	
	/**
	 * Show computer menu
	 */
	static void showComputerInstr() {
		System.out.println("\nComputer");
		System.out.println("Choose your operation:");
		System.out.println("1. List computers");
		System.out.println("2. Computer details");
		System.out.println("3. Create computer");
		System.out.println("4. Update computer");
		System.out.println("5. Delete computer");
		System.out.println("6. Return to menu");
	}
	
	
	static int readInt(int step) {
		int entry = -1;
		try {
			entry = Integer.parseInt(scan.nextLine());
			if(step == 0 && !generalInstr.contains(entry)) {
				throw new IllegalArgumentException();
			}
			else if(step == 1 && !companyInstr.contains(entry)) {
				throw new IllegalArgumentException();
			}
			else if(step == 2 && !computerInstr.contains(entry)) {
				throw new IllegalArgumentException();
			}
		}
		catch(NumberFormatException e) {
			System.err.println("Select a valid operation.");
			entry = -1;
		}
		return entry;
	}
	
	static Long readId() {
		Long id = null;
		while(id == null) {
			System.out.println("Enter the ID:");
			try {
				id = Long.parseLong(scan.nextLine());
			}
			catch(NumberFormatException e) {
				System.err.println("Select a valid operation.");
			}
		 }
		return id;

	}
	
	static void initLists() {
		for(int i = 1; i <= countGeneralInstr; i++) {
			generalInstr.add(i);
		}
		for(int i = 1; i <= countCompanyInstr; i++) {
			companyInstr.add(i);
		}
		for(int i = 1; i <= countComputerInstr; i++) {
			computerInstr.add(i);
		}
	}
	
	static Computer constructComputer() {
		System.out.println("Select computer name:");
		String name = scan.nextLine();
		
		System.out.println("Select introduced date (dd/mm/yyyy): (If unknown, put anything but a valid date");
		Date introduced = null;
		String introducedEntry = scan.next("[0-9]{2}/[0-9]{2}/[0-9]{4}");
		try {
		    introduced = new SimpleDateFormat("dd/MM/yyyy").parse(introducedEntry);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		scan.nextLine(); // consume new line left-over
		
		System.out.println("Select discontinued date: (If unknown, put anything but a valid date");
		Date discontinued = null;
		String discontinuedEntry = scan.next("[0-9]{2}/[0-9]{2}/[0-9]{4}");
		try {
			discontinued = new SimpleDateFormat("dd/MM/yyyy").parse(discontinuedEntry);
		} catch (ParseException e) {
			discontinued = null;
		}
		scan.nextLine(); // consume new line left-over
		
		System.out.println("Select company id");
		Long company_id = readId();
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setCompany_id(company_id);
		return computer;
	}
	
	static void session() {
		initLists();
		scan = new Scanner(System.in);
		boolean exit = false;
		int step = 0;
		while(!exit) {
			int entry = -1;
			while(entry == -1) {
				switch(step) {
					case 0 : showGeneralInstr(); entry = readInt(step); break;
					case 1 : showCompanyInstr(); entry = readInt(step); break;
					case 2 : showComputerInstr(); entry = readInt(step); break;
				}
			}
			if(step == 0) {
				switch(entry) {
					case 1 : step = 1; break;
					case 2 : step = 2; break;
					case 3 : exit = true; break;
				}
			}
			else if(step == 1) {
				switch(entry) {
					case 1 : companyService.list(); break;
					case 2 : step = 0; break;
				}
			}
			else if(step == 2) {
				switch(entry) {
					case 1 : computerService.list(); break;
					case 2 : Long id = readId(); computerService.showDetails(id); break;
					case 3 : Computer computer = constructComputer(); computerService.create(computer); break;
					case 4 : Computer updateComputer = constructComputer(); Long updateId = readId(); updateComputer.setId(updateId);computerService.update(updateComputer); break;
					case 5 : Long delId = readId(); computerService.delete(delId); break;
					case 6 : step = 0; break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		session();
		scan.close();
	}

}

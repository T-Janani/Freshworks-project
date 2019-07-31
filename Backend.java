package com.gcc.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.*;

class Test {
	static class Employee {
		private String id;
		private String name;
		private String age;
		private String phone;
		private String salary;
		private String designation;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getAge() {
			return this.age;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getPhone() {
			return this.phone;
		}

		public void setSalary(String salary) {
			this.salary = salary;
		}

		public String getSalary() {
			return this.salary;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getDesignation() {
			return this.designation;
		}

		public String toString() {
			return id + "\t" + name + "\t\t" + salary + "\t" + designation;
		}
	}

	static class Employee_manage {
		FileInputStream fin;
		ObjectInputStream ois;
		FileOutputStream fout;
		ObjectOutputStream oos;
		HashMap<String, Employee> hm;
		String filename;

		Employee_manage(String name) throws Exception {
			filename = name;
		}

		public void create() throws Exception {
			Scanner s = new Scanner(System.in);
			Employee e = new Employee();
			System.out.println("Enter the id");
			e.setId(s.next());
			System.out.println("Enter the name");
			e.setName(s.next());
			System.out.println("enter the salary");
			e.setSalary(s.next());
			System.out.println("enter the designation");
			e.setDesignation(s.next());
			System.out.println("enter the age");
			e.setAge(s.next());
			System.out.println("enter the phone");
			e.setPhone(s.next());
			Gson gson = new Gson();
			BufferedReader br = new BufferedReader(new FileReader("emp.json"));
			Type hmType = new TypeToken<HashMap<String, Employee>>() {
			}.getType();
			hm = gson.fromJson(br, hmType);
			hm.put(e.getId(), e);
			br.close();
			FileWriter writer = new FileWriter("emp.json");
			String json = gson.toJson(hm);
			writer.write(json);
			writer.close();
		}

		public void display() throws Exception {
			Gson gson = new Gson();
			BufferedReader br = new BufferedReader(new FileReader("emp.json"));
			Type hmType = new TypeToken<HashMap<String, Employee>>() {
			}.getType();
			hm = gson.fromJson(br, hmType);
			for (Employee e : hm.values()) {
				System.out.print(e.getId() + "\t" + e.getName() + "\t\t" + e.getSalary() + "\t");
				System.out.println(e.getDesignation() + "\t");
			}
		}

		public void edit() throws Exception {
			Scanner s = new Scanner(System.in);
			Gson gson = new Gson();
			BufferedReader br = new BufferedReader(new FileReader("emp.json"));
			Type hmType = new TypeToken<HashMap<String, Employee>>() {
			}.getType();
			hm = gson.fromJson(br, hmType);
			String eid = s.next();
			Employee e = (Employee) hm.get(eid);
			System.out.println("Enter the name");
			e.setName(s.next());
			System.out.println("enter the salary");
			e.setSalary(s.next());
			System.out.println("enter the designation");
			e.setDesignation(s.next());
			System.out.println("enter the age");
			e.setAge(s.next());
			System.out.println("enter the phone");
			e.setPhone(s.next());
			hm.put(e.getId(), e);
			FileWriter writer = new FileWriter("emp.json");
			String json = gson.toJson(hm);
			writer.write(json);
			writer.close();
		}
	}

	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		try {
			FileInputStream fin = new FileInputStream("emp.json");
			fin.close();
		} catch (Exception e) {
			FileWriter f = new FileWriter("emp.json");
			HashMap<String, Employee> s = new HashMap<String, Employee>();
			String json = gson.toJson(s);
			f.write(json);
			f.close();
		}
		Employee_manage em = new Employee_manage("emp.json");
		int choice, flag = 0, count = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the choice\n1.enter details\n2.display\n3.edit\n4.Exit");
		choice = sc.nextInt();
		sc.nextLine();
		while (true) {
			switch (choice) {
			case 1:
				em.create();
				count++;
				break;
			case 2:
				System.out.println("Id\tName\t\tSalary\tDesignation");
				em.display();
				break;
			case 3:
				em.edit();
				break;
			case 4:
				flag = 1;
			}
			if (flag == 1)
				break;
			System.out.println("Enter the choice\n1.enter details\n2.display\n3.edit\n4.Exit");
			choice = sc.nextInt();
			sc.nextLine();
		}
	}
}

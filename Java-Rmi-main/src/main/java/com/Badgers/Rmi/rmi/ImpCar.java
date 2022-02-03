package com.Badgers.Rmi.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImpCar extends UnicastRemoteObject implements ICar {
	
    public ImpCar() throws RemoteException {
        super();
    }

    @Override
    public void newCar() throws RemoteException {
    	
    	System.out.println("Araba Ekleme Ýþlemi Baþarýlý bir þekilde gerçekleþti!!!");
    }

    @Override
    public void newReceipt() throws RemoteException{
    	System.out.println("Fatura Ekleme Ýþlemi Baþarýlý bir þekilde gerçekleþti!!!");
    }

    @Override
    public void findCarBySN(boolean control) throws RemoteException{
    		if(control) System.out.println("Seri Numarasýna göre Araba Baþarýlý bir þekilde bulunmuþtur!!!");
    		else System.out.println("Seri Numarasýna Göre Araba Bulunamamýþtýr.");
    }

    @Override
    public void findCarByBrand(boolean control) throws RemoteException{
    		if(control) System.out.println("Markasýna göre Araba Baþarýlý bir þekilde bulunmuþtur!!!");
    		else System.out.println("Markasýna göre Araba Bulunamamýþtýr.");
    }

    @Override
    public void findReceiptById(boolean control) throws RemoteException{
    		if(control) System.out.println("Id'ye göre fatura Baþarýlý bir þekilde bulunmuþtur!!!");
    		else System.out.println("Id'ye göre fatura Bulunamamýþtýr.");
    }

    @Override
    public void findReceiptByCustomerName(boolean control) throws RemoteException{
    		if(control) System.out.println("Müþteri Ýsmine göre fatura Baþarýlý bir þekilde bulunmuþtur!!!");
    		else System.out.println("Müþteri Ýsmine göre fatura Bulunamamýþtýr.");
    }
}

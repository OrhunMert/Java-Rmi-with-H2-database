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
    	
    	System.out.println("Araba Ekleme ��lemi Ba�ar�l� bir �ekilde ger�ekle�ti!!!");
    }

    @Override
    public void newReceipt() throws RemoteException{
    	System.out.println("Fatura Ekleme ��lemi Ba�ar�l� bir �ekilde ger�ekle�ti!!!");
    }

    @Override
    public void findCarBySN(boolean control) throws RemoteException{
    		if(control) System.out.println("Seri Numaras�na g�re Araba Ba�ar�l� bir �ekilde bulunmu�tur!!!");
    		else System.out.println("Seri Numaras�na G�re Araba Bulunamam��t�r.");
    }

    @Override
    public void findCarByBrand(boolean control) throws RemoteException{
    		if(control) System.out.println("Markas�na g�re Araba Ba�ar�l� bir �ekilde bulunmu�tur!!!");
    		else System.out.println("Markas�na g�re Araba Bulunamam��t�r.");
    }

    @Override
    public void findReceiptById(boolean control) throws RemoteException{
    		if(control) System.out.println("Id'ye g�re fatura Ba�ar�l� bir �ekilde bulunmu�tur!!!");
    		else System.out.println("Id'ye g�re fatura Bulunamam��t�r.");
    }

    @Override
    public void findReceiptByCustomerName(boolean control) throws RemoteException{
    		if(control) System.out.println("M��teri �smine g�re fatura Ba�ar�l� bir �ekilde bulunmu�tur!!!");
    		else System.out.println("M��teri �smine g�re fatura Bulunamam��t�r.");
    }
}

package com.Badgers.Rmi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICar extends Remote {
    public void newCar() throws RemoteException;
    public void newReceipt() throws RemoteException;
    public void findCarBySN(boolean control) throws RemoteException;
    public void findCarByBrand(boolean control) throws RemoteException;
    public void findReceiptById(boolean control) throws RemoteException;
    public void findReceiptByCustomerName(boolean control) throws RemoteException;
}

package com.Badgers.Rmi.client;


import com.Badgers.Rmi.rmi.ICar;
import com.Badgers.Rmi.rmi.ImpCar;

import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Client {
    private static final int port=1099;
    private static final String url="Stardew";
    
    //H2 database
    static final String JDBC_DRIVER = "org.h2.Driver";   
    static final String DB_URL = "jdbc:h2:~/test"; 
    
    static final String USER = "orhun"; 
    static final String PASS = "123";
    
    public static void createDatas(Statement stmt) throws SQLException {
    	String sql1,sql2,sql3,sql4,sql5;
    	sql1 = "INSERT INTO CAR " + "VALUES (1, 'GTR27', 'Peugeot', 'Blue' ,700,2007,37000)";
    	stmt.executeUpdate(sql1);
    	sql2 = "INSERT INTO CAR " + "VALUES (2, 'GTR28', 'OPEL', 'Black' ,570,2017,57000)";
    	stmt.executeUpdate(sql2);
    	sql3 = "INSERT INTO CAR " + "VALUES (3, 'GTR29', 'BMW', 'White' ,270,2021,87000)"; 
    	stmt.executeUpdate(sql3);
    	sql4 = "INSERT INTO RECEIPT " + "VALUES(1,1,'Orhun')";
    	stmt.executeUpdate(sql4);
    	sql5 = "INSERT INTO RECEIPT " + "VALUES(2,2,'Mert')";
    	stmt.executeUpdate(sql5);
    }
    public static String InsertNewCar() {
    	//case 1
    	int SeriesNum,Weight,Price,Year;
    	String Model,Brand,Color;
    	
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("Lutfen Arabanin Seri numarasini giriniz:");
    	SeriesNum = scan.nextInt();
    	scan.nextLine();//Bunu intten sorna String'de bir problem oldugu icin yapiyoruz.
    	System.out.println("Lutfen Arabanin Modelini giriniz:");
    	Model = scan.nextLine();
    	System.out.println("Lutfen Arabanin Markasini giriniz:");
    	Brand = scan.nextLine();
    	System.out.println("Lutfen Arabanin Rengini giriniz:");
    	Color = scan.nextLine();
    	System.out.println("Lutfen Arabanin Yilini giriniz:");
    	Year = scan.nextInt();
    	scan.nextLine();
    	System.out.println("Lutfen Arabanin Agirligini giriniz:");
    	Weight = scan.nextInt();
    	scan.nextLine();
    	System.out.println("Lutfen Arabanin Fiyatini giriniz:");
    	Price = scan.nextInt();
    	scan.nextLine();
    	
    	String sql = "INSERT INTO CAR " + "VALUES("+SeriesNum+",'"+Model+"','"+Brand+"','"+Color+"',"+Weight+","+Year+","+Price+" )";    
    	
    	return sql;
       
    	
    }
    public static String InsertNewReceipt() {
    	//case 2
    	int receiptId,id;
    	String customerName;
    	
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("Lutfen Fatura Numarasini Giriniz:");
    	receiptId = scan.nextInt();
    	scan.nextLine();
    	
    	System.out.println("Lutfen Arabanin Seri Numarasini Giriniz:");
    	id = scan.nextInt();
    	scan.nextLine();
    	
    	System.out.println("Lutfen Fatura Sahibinin ismini Giriniz:");
    	customerName = scan.nextLine();
    	
    	String sql = "INSERT INTO RECEIPT " + "VALUES("+receiptId+","+id+",'"+customerName+"')";
    	    	
    	
    	return sql;
    }
    
    public static int readCarTabletoFindById() {
    	//case 3
    	int SeriesNum;
    	Scanner scan = new Scanner(System.in);
    			
    	System.out.println("Listelemek istediginiz Arabanin Seri Numarasini Giriniz:");
    	SeriesNum = scan.nextInt();
    	scan.nextLine();
    	    	
    	return SeriesNum;
    	
    }
    public static String readCarTabletoFindByBrand() {
    	//case 4
    	String Brand;
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("Listeleme istediginiz Arabanin Markasini Giriniz:");
    	Brand = scan.nextLine();
    	
    	return Brand;
    }
    public static int readReceiptTabletoFindById() {
    	//case 5 
    	
    	int ReceiptSeriesNum;
    	Scanner scan = new Scanner(System.in);
    			
    	System.out.println("Listelemek istediginiz Faturanin Seri Numarasini Giriniz:");
    	ReceiptSeriesNum = scan.nextInt();
    	scan.nextLine();
    	
    	return ReceiptSeriesNum;
    }
    public static String readReceiptTabletoFindByCustomerName() {
    	//case 6
    	
    	String customerName;
    	Scanner scan = new Scanner(System.in);
    			
    	System.out.println("Listelemek istediginiz Faturanin Musteri Adini Giriniz:");
    	customerName = scan.nextLine();
    	
    	
    	return customerName;
    }
    public static void menu(ICar car,Statement stmt){
    	//client sonsuz donguye alimali.
    	while(true) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1- Yeni Araba Ekle");
        System.out.println("2- Yeni Fatura Ekle");
        System.out.println("3- Arabayi Seri Numarasina Gore Goster");
        System.out.println("4- Arabayi Markasina Gore Goster");
        System.out.println("5- Aracin Seri Numarasina gore fatura goster");
        System.out.println("6- Faturayi Alicinin Adina Gore Goster");
        System.out.println("0- Cikis");
        System.out.print("Menuden Bir Islem Secin: ");
        int choose = sc.nextInt();
        String sql =" ";
        try{
           
        	switch (choose){
                case 1:
                	
                	sql = InsertNewCar();
                	stmt.executeUpdate(sql);
                	
                	car.newCar();
                	
                	break;
                case 2: 
                	
                	sql = InsertNewReceipt();
                	stmt.executeUpdate(sql);
                	
                	car.newReceipt();
                	
                	break;
                case 3:
                	
                	int SeriesNum = readCarTabletoFindById();
                	boolean controlCase3=false;
                	
                	sql ="SELECT id,model,brand,color,year,weight,price FROM CAR WHERE CAR.id = "+SeriesNum; 
                	ResultSet resultset = stmt.executeQuery(sql);
                	//ResultSet boolean bir deger tutuyor.Yani executeQuery fonksiyonu boolean donduruyor.
                	while(resultset.next()) {
                		
                		
                		int id = resultset.getInt("id");
                		controlCase3 = true;
                		int Weight,Price,Year;
                	    String Model,Brand,Color;
                	    	
                	    Weight = resultset.getInt("weight");
                	    Price = resultset.getInt("price");
                	    Year = resultset.getInt("year");
                	    	
                	    Model = resultset.getString("model");
                	    Brand = resultset.getString("brand");
                	    Color = resultset.getString("color");
                	    	
                	    System.out.println("Aracin Seri Numarasi:"+id);
                	    System.out.println("Aracin Agirligi:"+Weight);
                	    System.out.println("Aracin Fiyati:"+Price);
                	    System.out.println("Aracin Yili:"+Year);
                	    System.out.println("Aracin Modeli:"+Model);
                	    System.out.println("Aracin Markasi:"+Brand);
                	    System.out.println("Aracin Rengi:"+Color);
                	    System.out.println();

                	}
                	
                	car.findCarBySN(controlCase3);
                	
                	break;
                	
                case 4: 
                	
                	String Brand = readCarTabletoFindByBrand();
                	boolean controlCase4 = false;
                	sql ="SELECT id,model,brand,color,year,weight,price FROM CAR  WHERE CAR.brand = '"+Brand+"'"; 
                	ResultSet rs = stmt.executeQuery(sql);
                	
                	while(rs.next()) {
                		
                		    String marka = rs.getString("brand");
                		    controlCase4=true;
                		    
                			int Weight,Price,Year,id;
                	    	String Model,Color;
                	    	
                	    	id = rs.getInt("id");
                	    	Weight = rs.getInt("weight");
                	    	Price = rs.getInt("price");
                	    	Year = rs.getInt("year");
                	    	
                	    	Model = rs.getString("model");
                	    	Color = rs.getString("color");
                	    	
                	    	System.out.println("Aracin Seri Numarasi:"+id);
                	    	System.out.println("Aracin Agirligi:"+Weight);
                	    	System.out.println("Aracin Fiyati:"+Price);
                	    	System.out.println("Aracin Yili:"+Year);
                	    	System.out.println("Aracin Modeli:"+Model);
                	    	System.out.println("Aracin Markasi:"+Brand);
                	    	System.out.println("Aracin Rengi:"+Color);
                	    	System.out.println();
	
                	}
                	
                	car.findCarByBrand(controlCase4);
                	
                	break;
                	
                case 5: 
                	
                	int ReceiptSeriesNum = readReceiptTabletoFindById();
                	boolean controlCase5 = false;
                	sql ="SELECT receiptId,id,customerName FROM RECEIPT WHERE RECEIPT.id = "+ReceiptSeriesNum; 
                	ResultSet RS = stmt.executeQuery(sql);
                
                	while(RS.next()) {
                		
                		
                		int id = RS.getInt("id");
                		controlCase5 = true;
                			
                		int receiptId;
                	    String customerName;
                	    	
                	    receiptId = RS.getInt("receiptId");
                	    	
                	    customerName = RS.getString("customerName");
                	    	
                	    System.out.println("Futuranin Id'si:"+receiptId);
                	    System.out.println("Faturadaki aracin Seri Numarasi:"+id);
                	    System.out.println("Faturadaki aracin Sahibi:"+customerName);
                	    System.out.println();
                	    	
                	    
                		
                	}
                	
                	car.findReceiptById(controlCase5); 
                	
                	break;
                	
                case 6:
                	
                	String InputcustomerName = readReceiptTabletoFindByCustomerName();
                	boolean controlCase6=false;
                	sql ="SELECT receiptId,id,customerName FROM RECEIPT WHERE RECEIPT.customerName = '"+InputcustomerName+"'"; 
                	ResultSet resultSet1 = stmt.executeQuery(sql);
                
                	while(resultSet1.next()) {
                		
                		
                		String customerName = resultSet1.getString("customerName");
                		controlCase6 = true;
                		
                		int receiptId,id;
	
                	    receiptId = resultSet1.getInt("receiptId");
                	    id = resultSet1.getInt("id");
                	    		
                	    System.out.println("Faturanin Id'si:"+receiptId);
                	    System.out.println("Faturadaki aracin Seri Numarasi:"+id);
                	    System.out.println("Faturadaki aracin Sahibi::"+customerName);
                	    System.out.println();
   
                		
                	}
                	
                	car.findReceiptByCustomerName(controlCase6);
                	
                	break;
                	
                case 0: 
                	System.exit(0);
                default:
                    System.out.println("Hatali Tuslama Yaptiniz Tekrar Giriniz");
                    
            }
           
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    	}

    }

    public static void main(String[] args) {
    	
    	Connection conn = null;
        Statement stmt = null;
        
        try {
        	
        	Class.forName(JDBC_DRIVER);
        	
        	System.out.println("Connecting to database..."); 
            conn = DriverManager.getConnection(DB_URL,USER,PASS);  
            
            System.out.println("Creating table in given database..."); 
            stmt = conn.createStatement();
            
            try{
				//Bunu demezsek olmuyor.
				String Sql = "DROP TABLE CAR";
				String Sql1 = "DROP TABLE RECEIPT";
				stmt.execute(Sql);
				stmt.execute(Sql1);
			}catch (Exception e){
				System.out.println("Drop Edilecek Tablo Yok");
			}


            try{
				//Her seferinde yeniden veri tabani olusturuluyor.

				String sql =  "CREATE TABLE  CAR "
						+ "(id INTEGER not NULL ,"
						+ "model VARCHAR(255), "
						+ "brand VARCHAR(255), "
						+ "color VARCHAR(255), "
						+ "weight INTEGER, "
						+ "year INTEGER, "
						+ "price INTEGER, "
						+ "PRIMARY KEY(id))";

				stmt.executeUpdate(sql);

				sql = "CREATE TABLE RECEIPT"
						+ "(receiptId INTEGER not NULL, "
						+ "id INTEGER not NULL, "
						+ "customerName VARCHAR(255), "
						+ "PRIMARY KEY (receiptId))";

				stmt.executeUpdate(sql);

				System.out.println("Created table in given database...\n");

				createDatas(stmt);//Bununla baslangicta database'mizde veriler olsun istiyoruz.

			}catch (Exception e){
				System.out.println("Tablolar zaten create edilmis durumda");
			}

            ICar car=(ICar)Naming.lookup("rmi://127.0.0.1:"+port+"/"+url);
            menu(car,stmt); 
            
            //Olusturdugumuz nesnelerin kapatilmasi gerekiyor.
            stmt.close();
            conn.close(); 

        }catch(SQLException se){ 
            se.printStackTrace(); 
         }catch (Exception e){
            System.out.println("Exception: "+e);
        }
        finally{
        	 try{ 
                 if(stmt!=null) stmt.close();    
              }catch(SQLException se2) { 
              } // nothing we can do 
              try { 
                 if(conn!=null) conn.close();
              } catch(SQLException se){ 
                 se.printStackTrace(); 
              } //end finally try 
        }
       
    }

    
}

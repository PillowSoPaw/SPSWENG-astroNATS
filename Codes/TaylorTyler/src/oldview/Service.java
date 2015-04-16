package oldview;
//package view;
//
//import java.util.ArrayList;
//
///*
//    Services should be instantiated from the beginning, loading all
//    the services and the products used. Please expand this class
//    for it's other uses.
//*/
//
//public class Service {
//    private String name;
//    private ArrayList<Product> productsUsed;
//    
//    public Service(String name){
//        this.name = name;
//        productsUsed = new ArrayList();
//    }
//    
//    public Service(String name, Product[] product){
//        this.name = name;
//        
//        for(int i = 0; i < product.length; i++)
//            productsUsed.add(product[i]);
//    }
//    
//    public void addProduct(Product product){
//        productsUsed.add(product);
//    }
//    
//    public String getName(){
//        return name;
//    }
//    
//    public String[] getProductNames(){
//        String[] names;
//        names = new String[productsUsed.size()];
//        
//        int i = 0;
//        while(i < productsUsed.size()){
//            names[i] = productsUsed.get(i).getName();
//            i++;
//        }
//        
//        return names;
//    }
//}

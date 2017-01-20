/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database;

/**
 *
 * @author Flo
 */
public class Queries {
    private String mQuery;
    private final String type;
    private final String table;
    
    public Queries(String table, String type){
        this.type = type;
        this.table = table;
    }
    public void buildQuery(){
        switch(this.type){
            case "insert":
                this.mQuery = "insert into ";
                break;
            case "select":
                this.mQuery = "select * from ";
                break;
            case "update":
                this.mQuery = "update ";
                break;
            default : 
                this.mQuery = "";
        }
        this.mQuery += table;
    }
    public String getMQuery(){
        return this.mQuery;
    }
}

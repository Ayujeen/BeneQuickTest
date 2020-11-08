/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bqtest.service;

import bqtest.web.FileController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.graalvm.compiler.lir.aarch64.AArch64Move;


public class UnitTest {
    String id,address,city,fname,lname,state,zip;
    
    
    
    
    
    public UnitTest() throws Exception {
        test_member();
        test_process_file();
        test_FileController_loadData();
        test_members_import();
        test_split_members_by_state();
        
    }
    
    public void test_member(){
        
        id="001";
        address="my address";
        city="city1";
        fname="First name";
        lname="Last name";
        state="My State";
        zip="My Zip";
        
        Member aa = new Member();
        aa.setId(id);
        aa.setAddress(address);
        aa.setCity(city);
        aa.setFirstName(fname);
        aa.setLastName(lname);
        aa.setState(state);
        aa.setZip(zip);
        
        if(aa.getId().equals(id) && aa.getAddress().equals(address) && aa.getCity().equals(city) && aa.getFirstName().equals(fname) && aa.getLastName().equals(lname) && aa.getState().equals(state) && aa.getZip().equals(zip)){
            System.out.println("Members.java Test Successful");
        }else{
            System.out.println("Members.java Test Failed");
        }
    }
    
    public void test_FileController_loadData() throws Exception{
        
        String filename="test.txt";
        //System.out.println(FileController.class.getMethods());
        
        
//        MemberImporterImpl aa;
//        
//        aa.processFile();
        
//        final MemberImporter aa = new MemberImporter() {
//            @Override
//            public Map<String, List<Member>> processFile(File inputMemberFile) throws Exception {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        };
//        
//        
//        FileController fc = new FileController(aa);
//        
//        Map<String, List<Member>> result =fc.loadData(filename);
//       
//        
//        if (result!=null){
//            System.out.println("FileController.java Test Successful");
//        }else{
//            System.out.println("FileController.java Test Failed");
//        }
               
    }
    
    public void test_process_file() throws IOException{
        File inputMemberFile = new File("test.txt");
        MemberImporterImpl instance = new MemberImporterImpl();
        Map<String, List<Member>> result = instance.processFile(inputMemberFile);
        
        if(!result.equals(null) && result.size()>0){
            System.out.println("Process file Test Successful");
        }else{
            System.out.println("Process file Test Failed");
        }
    }
    
    public void test_members_import() throws IOException{
        File inputFile = new File("test.txt");
        MemberImporterImpl instance = new MemberImporterImpl();
        
        List<Member> result = instance.importMembers(inputFile);
        
        if(!result.equals(null) && result.size()>0){
            System.out.println("Members Import Test Successful");
        }else{
            System.out.println("Members Import Test Failed");
        }
        
    }
    
    public void test_split_members_by_state(){
        List<Member> validMembers = new ArrayList<>();
        Member aa= new Member();
        aa.setState("OK");
        aa.setId("1");
        aa.setFirstName("Paul");
        
        validMembers.add(aa);
        Member bb1= new Member();
        bb1.setState("MN");
        bb1.setId("2");
        bb1.setFirstName("John");
        
        validMembers.add(bb1);
        Member cc= new Member();
        cc.setState("OK");
        cc.setId("3");
        cc.setFirstName("Harry");
        
        validMembers.add(cc);
        Member dd= new Member();
        dd.setState("IL");
        dd.setId("4");
        dd.setFirstName("Eliza");
        
        validMembers.add(dd);
        Member ee= new Member();
        ee.setState("MN");
        ee.setId("5");
        ee.setFirstName("Pauli");
        validMembers.add(ee);
        
        List <Member> ok= new ArrayList<>();
        ok.add(aa);
        ok.add(cc);
        List <Member> il= new ArrayList<>();
        il.add(dd);
        List <Member> mn= new ArrayList<>();
        mn.add(bb1);
        mn.add(ee);
        
        MemberImporterImpl instance = new MemberImporterImpl();
        Map<String, List<Member>> expResult = new HashMap<>();
        
        expResult.put("OK", ok);
        expResult.put("IL", il);
        expResult.put("MN", mn);
        
        Map<String, List<Member>> result = instance.splitMembersByState(validMembers);
        
        if(expResult.equals(result)){
            System.out.println("Split members by state Test Success.");
        }else{
            System.out.println("Split members by state Test Failed.");
        }       
        
    }
    
    
    
    public static void main(String [] args) throws Exception{
        UnitTest aa= new UnitTest();
       
    }

   

    
}

package com.whatsapp;

import java.util.ArrayList;
import java.util.Scanner;

class Contacts {

    int cnt_id;
    private String cnt_name;
    static int count=0;
    private String cnt_number;

    public Contacts() {
        count+=1;
        this.cnt_id=count;
    }
    public int getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(int cnt_id) {

        this.cnt_id = cnt_id;
    }

    public String getCnt_name() {
        return cnt_name;
    }

    public void setCnt_name(String cnt_name) {
        this.cnt_name = cnt_name;
    }

    public String getCnt_number() {
        return cnt_number;
    }

    public void setCnt_number(String cnt_number) {
        this.cnt_number = cnt_number;
    }

}

class Messages {

    int msgId;
    String text;
    String msgDirection;
    static int count=0;

    public String getMsgDirection() {
        return msgDirection;
    }

    public void setMsgDirection(String msgDirection) {
        this.msgDirection = msgDirection;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Messages(String text, String msgDirection) {
        count+=1;
        this.msgId = count;
        this.text = text;
        this.msgDirection = msgDirection;
    }

}

class Chats {

    int chatId;
    Contacts c;
    ArrayList<Messages> listOfMsgs=new ArrayList<Messages>();
    static int count=0;
    
    public ArrayList<Messages> getListOfMsgs() {
        return listOfMsgs;
    }

    public void setListOfMsgs(ArrayList<Messages> listOfMsgs) {
        this.listOfMsgs = listOfMsgs;
    }
    
    public Chats(int chatId, Contacts c,Messages msg) {
        count+=1;
        this.chatId = 1;
        this.c = c;
        listOfMsgs.add(msg);
    }

}

class WhatsAppModel {

    ArrayList<Chats> listOfChats=new ArrayList<Chats>();
    ArrayList<Contacts> listOfContacts=new ArrayList<Contacts>();
    Scanner inpt = new Scanner(System.in);

    public void splitInput(String input){
        String[] inp=input.split(" ");
        String method=inp[0];
        String url=inp[1];
        if(method.equals("Post")){
            post(url);
        }
        else if(method.equals("Get") ){
            get(url);
        }
        else if(method.equals("Delete") ){
            delete(url);
        }
        else if(method.equals("Put")){
            updateContact(url);
        }
    }

    public void updateContact(String url){
        int id= Integer.parseInt(url.substring(10));
        System.out.print("what do you want to change ?(name/number)");
        String change=inpt.next();
        for(Contacts c:listOfContacts){
            if(c.cnt_id==id){
                if(change.equals("name")){
                    System.out.print("Enter the name ?  ");
                    String name=inpt.next();
                    c.setCnt_name(name);
                    System.out.println("Name has been updated !");
                }
                else{
                    System.out.print("Enter the number ?  ");
                    String number=inpt.next();
                    c.setCnt_number(number);
                    System.out.println("Number has been updated !");
                }
                break;
            }
        }
    }

    public void delete(String url){
        if(url.startsWith("/contacts")){
            int id= Integer.parseInt(url.substring(10,url.length()));
            for(Contacts c:listOfContacts){
                if(c.cnt_id==id){
                    int indx=listOfContacts.indexOf(c);
                    listOfContacts.remove(indx);
                    System.out.println("Contact Deleted !");
                    break;
                }
            }
        }
        else if(url.startsWith("/chats") && url.endsWith("clear")){
            String[] spitUrl=url.split("/");
            int id= Integer.parseInt(spitUrl[2]);
            int index=chatIndex(id);
            listOfChats.get(index).listOfMsgs.clear();
            System.out.println("Cleared Chat History !!!");
        }
        else{
            int id=Integer.parseInt(url.substring(7));
            int index=chatIndex(id);
            listOfChats.remove(index);
            System.out.println("Deleted Chat  !!!");
        }

    }

    public void get(String url){
        if(url.equals("/contacts")){
            for(Contacts c:listOfContacts){
                System.out.println(c.getCnt_id()+"  " +c.getCnt_name());
            }
        }
        else if(url.equals("/chats")){
            viewChat();
        }
        else{
            if(url.startsWith("/contacts")){
                int id= Integer.parseInt(url.substring(10,url.length()));
                for(Contacts c:listOfContacts){
                    if(c.getCnt_id()==id){
                        System.out.println(c.getCnt_name()+" "+c.getCnt_number());
                    }
                }
            }
            else{
                int id=Integer.parseInt(url.substring(7));
                viewPersonChat(id);
            }
        }
    }

    public void post(String url){
        if(url.equals("/contacts")) {
            addContact();
        }
        else if(url.startsWith("/chats/")){
            int id=Integer.parseInt(url.substring(7));
            postMessage(id);
        }
    }

    public void addContact(){
        System.out.print("Enter the name ?  ");
        String name=inpt.next();
        System.out.print("Enter the Phone number ?  ");
        String phnumber=inpt.next();
        Contacts c = new Contacts();
        c.setCnt_number(phnumber);
        c.setCnt_name(name);
        System.out.println("Successfully added ..\n" + "Name : " + c.getCnt_name() + " Phone number : " + c.getCnt_number());
        listOfContacts.add(c);
    }

    public void postMessage(int id){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the Message ?  ");
        String text=sc.nextLine();
        System.out.print("Enter the MsgDirection ?(in/out)  ");
        String msdr=inpt.next();
        Messages msg=new Messages(text,msdr);
        Contacts cont = null;
        for(Contacts c: listOfContacts){
            if(c.getCnt_id()==id){
                cont=c;
                break;
            }
        }
        int indx=chatIndex(cont.cnt_id);
        if(indx==-1){
            Chats chat=new Chats(0,cont,msg);
            listOfChats.add(chat);
            System.out.println("message sent!!");
        }
        else{
            listOfChats.get(indx).listOfMsgs.add(msg);
            System.out.println("message sent!!");
        }
    }

    public void viewChat(){
        for(Chats chat:listOfChats){
            int msgs=chat.listOfMsgs.size()-1;
            System.out.println("\n"+chat.c.getCnt_name()+"\t"+chat.listOfMsgs.get(msgs).text+"\t"+chat.listOfMsgs.get(msgs).msgDirection+"\n");
        }
    }

    public void viewPersonChat(int id){
        int indx=chatIndex(id);
       System.out.println("\n"+listOfChats.get(indx).c.getCnt_name()+"\n");
        for(Messages msg: listOfChats.get(indx).listOfMsgs){
            System.out.println(msg.text+"\t  "+msg.msgDirection);

        }
    }

    public int chatIndex(int id){
        int indx=-1;
        for(Chats ct:listOfChats){
            if(ct.c.getCnt_id()==id){
               indx=listOfChats.indexOf(ct);
            }
        }
        return indx;
    }

    public static void main(String[] args) {
    
        WhatsAppModel obj=new WhatsAppModel();
        
        do {
            Scanner input=new Scanner(System.in);
            System.out.println("Enter the command ?");
            String command = input.nextLine();
            obj.splitInput(command);
            if(command.equals("no")){
                return;
            }
        }while(true);
        
    }
}



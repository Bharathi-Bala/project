package com.whatsapp;

import java.util.*;

class Contacts {
    private int cnt_id;
    private String cnt_name;
    private String cnt_number;
    static int count=0;

    private static HashMap<Integer,Contacts> listOfContacts=new HashMap<Integer, Contacts>();

    public Contacts(String cnt_name, String cnt_number) {
        count+=1;
        this.cnt_id = count;
        this.cnt_name = cnt_name;
        this.cnt_number = cnt_number;
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

    public static HashMap<Integer, Contacts> getListOfContacts() {
        return listOfContacts;
    }

    public  void setListOfContacts(Contacts c) {
        listOfContacts.put(c.cnt_id,c);
    }
}
class Message {
    private int msg_Id;
    private String text;
    private String msg_dir;

    static int count;

    public Message(String text, String msg_dir) {
        count+=1;
        this.msg_Id =count;
        this.text = text;
        this.msg_dir = msg_dir;
    }

    public int getMsg_Id() {
        return msg_Id;
    }

    public void setMsg_Id(int msg_Id) {
        this.msg_Id = msg_Id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMsg_dir() {
        return msg_dir;
    }

    public void setMsg_dir(String msg_dir) {
        this.msg_dir = msg_dir;
    }
}
class Chats {
    private int ch_id;
    private  Contacts contact;
    private ArrayList<Message> listOfMsg=new ArrayList<Message>();
    private boolean status;
    private boolean block;
    private static HashMap<Integer,Chats> listOfChats=new HashMap<Integer, Chats>();

    static int count=0;

    Chats(){
      count+=1;
      this.ch_id=count;
    }
    public int getCh_id() {
        return ch_id;
    }

    public void setCh_id(int ch_id) {
        this.ch_id = ch_id;
    }

    public Contacts getContact() {
        return contact;
    }

    public void setContact(Contacts contact) {
        this.contact = contact;
    }

    public ArrayList<Message> getListOfMsg() {
        return listOfMsg;
    }

    public void setListOfMsg(Message msg) {
        this.listOfMsg.add(msg);
    }

    public static HashMap<Integer, Chats> getListOfChats() {
        return listOfChats;
    }

    public static void setListOfChats(Chats chat) {
        Chats.listOfChats.put(chat.contact.getCnt_id(),chat);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }
}

class Status {

    private int status_id;
    private Contacts contact;
    private static HashMap<Integer,Status> listOfStatus=new HashMap<Integer, Status>();

    private boolean mute;

    static int count=0;

    private  ArrayList<String> statuses=new ArrayList<String>();

    Status(){
        count+=1;
        this.status_id=count;
    }
    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public Contacts getContact() {
        return contact;
    }

    public void setContact(Contacts contact) {
        this.contact = contact;
    }

    public static HashMap<Integer, Status> getListOfStatus() {
        return listOfStatus;
    }

    public static void setListOfStatus(Status s) {
        listOfStatus.put(s.contact.getCnt_id(),s);
    }

    public ArrayList<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(String s) {
       this.statuses.add(s);
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

}

class WhatsApp {

    public void splitInput(String input){

         String[] arr=input.split(" ");
         if(arr[0].equals("Post")){
             post(arr);
         }
         else if(arr[0].equals("Get")){
             get(arr);
         }
         else if(arr[0].equals("Put")){
             put(arr);
         }
         else if(arr[0].equals("Delete")){
             delete(arr);
         }
         else{
             System.out.println("No such method");
         }

    }
    public void delete(String[] arr){
        String url=arr[1];
        if(url.startsWith("/contacts")){
            int id=Integer.parseInt(url.substring(10));
            Contacts.getListOfContacts().remove(id);
            System.out.println("Contact deleted !!");
        }
        else if(url.startsWith("/chats")){
            int id=Integer.parseInt(url.substring(7));
            Chats.getListOfChats().remove(id);
            System.out.println("Chat deleted !!");
        }
        else if(url.startsWith("/status")){
            int id=Integer.parseInt(url.substring(8));
            int st_id=Integer.parseInt(arr[2]);
            Status.getListOfStatus().get(id).getStatuses().remove(st_id-1);
            System.out.println("Status Deleted ! ");
        }
        else{
            System.out.println("No such url!!");
        }
    }
    public  void put(String[] arr){
        String url=arr[1];
        if(url.startsWith("/contacts")){
            int id=Integer.parseInt(url.substring(10));
            String[] part=arr[2].split(":");
            String pro=part[0];
            String val=part[1];
            if(pro.equals("name")){
                Contacts.getListOfContacts().get(id).setCnt_name(val);
                System.out.println("Name Updated !");
            }
            else if(pro.equals("number")){
                Contacts.getListOfContacts().get(id).setCnt_number(val);
                System.out.println("Number Updated !");
            }
            else{
                System.out.print("No such property found to update !");
            }
        }
        else if(url.startsWith("/status")){
            int id=Integer.parseInt(url.substring(8));
            String val=arr[2];
            if(val.equals("mute")){
                Status.getListOfStatus().get(id).setMute(true);
                System.out.println("Status muted !!");
            }
            else {
                Status.getListOfStatus().get(id).setMute(false);
                System.out.println("Status Unmuted !!");
            }
        }
        else{
            int id=Integer.parseInt(url.substring(7));
            String status=arr[2];
            if(status.equals("archieve")){
                Chats.getListOfChats().get(id).setStatus(true);
                System.out.println(Chats.getListOfChats().get(id).getContact().getCnt_name()+"'s chat has been archieved !!!");
            }
            else if(status.equals("unarchieve")){
                Chats.getListOfChats().get(id).setStatus(false);
                System.out.println(Chats.getListOfChats().get(id).getContact().getCnt_name()+"'s chat has been Unarchieved !!!");
            }
            else if(status.equals("block")){
                Chats.getListOfChats().get(id).setBlock(true);
                System.out.println(Chats.getListOfChats().get(id).getContact().getCnt_name()+"'s chat has been blocked !!!");
            }
            else if(status.equals("unblock")){
                Chats.getListOfChats().get(id).setBlock(false);
                System.out.println(Chats.getListOfChats().get(id).getContact().getCnt_name()+"'s chat has been Unblocked !!!");
            }
           else{
                System.out.println("Something went wrong !!!");
            }
        }

    }
    public void post(String[] arr){
        String url=arr[1];
        if(url.equals("/contacts")){
            String name=arr[2];
            String number=arr[3];
            Contacts c=new Contacts(name,number);
            c.setListOfContacts(c);
            System.out.println("Contact created with id : "+c.getCnt_id());
        }
        else if(url.startsWith("/chats/")){
            int id=Integer.parseInt(url.substring(7));
            String msg_txt=arr[2];
            String msg_dir=arr[3];
            Contacts c=Contacts.getListOfContacts().get(id);
            Message msg=new Message(msg_txt,msg_dir);
            if(Chats.getListOfChats().containsKey(id)){
                if(Chats.getListOfChats().get(id).isBlock()){
                    System.out.println("Can't send message since the chat has been blocked !!");
                }
                else{
                    Chats.getListOfChats().get(id).setListOfMsg(msg);
                    System.out.println("Message sent !");
                }
            }
            else{
                Chats ct=new Chats();
                ct.setContact(c);
                ct.setListOfMsg(msg);
                Chats.setListOfChats(ct);
                System.out.println("Message sent !!");
            }
        }
        else if(url.startsWith("/status/")){
            if(arr.length==3){
                int id=Integer.parseInt(url.substring(8));
                String msg_txt=arr[2];
                System.out.println("entered !!");
                System.out.println(id+" "+msg_txt);
                Contacts c=Contacts.getListOfContacts().get(id);
                if(Status.getListOfStatus().containsKey(id)){
                    System.out.println("Already there exit a status");
                    Status.getListOfStatus().get(id).setStatuses(msg_txt);
                    System.out.println(c.getCnt_name()+" "+ Status.getListOfStatus().get(id).getStatuses().size());
                }
                else{
                    System.out.println("status created");
                    Status st=new Status();
                    st.setContact(c);
                    st.setStatuses(msg_txt);
                    Status.setListOfStatus(st);
                    System.out.println(c.getCnt_name()+" "+Status.getListOfStatus().get(id).getStatuses().size());
                }
            }
            else {
                System.out.println("replying to a status ");
                int id=Integer.parseInt(url.substring(8));
                int st_id=Integer.parseInt(arr[2]);
                String msg_txt=arr[3];
                System.out.println(id+"  "+st_id+"   "+msg_txt);
                String msg_dir="out";
                Contacts c=Contacts.getListOfContacts().get(id);
                Message msg=new Message(msg_txt,msg_dir);
                String status=Status.getListOfStatus().get(id).getStatuses().get(st_id-1);
                if(Chats.getListOfChats().containsKey(id)){
                    Chats.getListOfChats().get(id).setListOfMsg(new Message(status,"status"));
                    Chats.getListOfChats().get(id).setListOfMsg(msg);
                    System.out.println("Replied to the status !");
                }
                else{
                    Chats ct=new Chats();
                    ct.setContact(c);
                    ct.setListOfMsg(new Message(status,"status"));
                    ct.setListOfMsg(msg);
                    Chats.setListOfChats(ct);
                    System.out.println("Replied to the status !");
                }
            }
        }
    }
    public void get(String[] arr){
        String url=arr[1];
        if(url.equals("/contacts")){
            viewContact(Contacts.getListOfContacts());
        }
        else if(url.startsWith("/contacts")){
            int id=Integer.parseInt(url.substring(10));
            System.out.println(Contacts.getListOfContacts().get(id).getCnt_name()+"\t\t"+Contacts.getListOfContacts().get(id).getCnt_number());
        }
        else if(url.equals("/chats")){
            viewChats(Chats.getListOfChats());
        }
        else if(url.equals("/chats/archieved")){
            System.out.println("Archieved Chats : ");
            for (Chats chat : Chats.getListOfChats().values()){
                if(chat.isStatus()){
                    System.out.println(chat.getContact().getCnt_name()+"\t\t"+chat.getListOfMsg().get(chat.getListOfMsg().size()-1).getText()+"\t\t"+chat.getListOfMsg().get(chat.getListOfMsg().size()-1).getMsg_dir());
                }
            }
        }
        else if(url.startsWith("/chats/")){
            int id=Integer.parseInt(url.substring(7));
            Chats c=Chats.getListOfChats().get(id);
            ArrayList<Message> msgs=c.getListOfMsg();
            System.out.println(c.getContact().getCnt_name());
            for(Message m:msgs){
               System.out.println(m.getText()+"\t\t"+m.getMsg_dir()+"\n");
            }
        }
        else if(url.equals("/status")){
            viewStatuses();

        }
        else if(url.startsWith("/status/")){
            int id=Integer.parseInt(url.substring(8));
            ArrayList<String> st=Status.getListOfStatus().get(id).getStatuses();
            System.out.println("\n"+Status.getListOfStatus().get(id).getContact().getCnt_name()+"\n");
            for(String s:st){
                System.out.println(s);
            }
        }
    }
    public void viewStatuses(){
        System.out.println("\nStatus : \n");
        for(Status status: Status.getListOfStatus().values()){
            if(status.isMute()==false){
                System.out.println(status.getContact().getCnt_name()+"\t\t"+status.getStatuses().get(status.getStatuses().size()-1)+"\n");
            }
        }
        mutedStatuses();
    }
    public void mutedStatuses(){
        System.out.println("\nMuted Status : \n");
        for(Status status: Status.getListOfStatus().values()){
            if(status.isMute()){
                System.out.println(status.getContact().getCnt_name()+"\t\t"+status.getStatuses().get(status.getStatuses().size()-1));
            }
        }
    }
    public void viewChats(HashMap<Integer, Chats> listOfChats){
        for (Chats chat : listOfChats.values()){
            if(chat.isStatus()==false){
                System.out.println(chat.getContact().getCnt_name()+"\t\t"+chat.getListOfMsg().get(chat.getListOfMsg().size()-1).getText()+"\t\t"+chat.getListOfMsg().get(chat.getListOfMsg().size()-1).getMsg_dir());
            }
        }
    }
    public void viewContact(HashMap<Integer, Contacts> listOfContacts){
        Iterator<Map.Entry<Integer, Contacts>> itr = listOfContacts.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<Integer, Contacts> entry = itr.next();
            System.out.println(  entry.getKey() + "\t\t" + entry.getValue().getCnt_name()+"\t\t"+entry.getValue().getCnt_number()+"\n");
        }

    }
    public static void main(String[] args) {

        WhatsApp w = new WhatsApp();	
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your request ? ");
            String input = sc.nextLine();
            w.splitInput(input);
        } while (true);
    }
}


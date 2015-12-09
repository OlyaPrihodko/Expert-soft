package com.expert_soft.prihodko.task.logic;

import com.expert_soft.prihodko.task.entity.Contact;
import com.expert_soft.prihodko.task.exception.logicException.LogicException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOperations {

    private static final String NO_FILE = "NO_FILE";
    private static final String NEW_LINE = "\r\n";
    private static final String DELIMETER = ",";
    private boolean somethingIncorrectData;

    public FileOperations(){
        somethingIncorrectData=true;
    }
    public boolean isSomethingIncorrectData() {
        return somethingIncorrectData;
    }
    public void setSomethingIncorrectData(boolean somethingIncorrectData) {
        this.somethingIncorrectData = somethingIncorrectData;
    }

    public String getFileInfo(HttpServletRequest request) throws LogicException{
        String result="";
        try{
            byte[] data;
            final int size = 1024*1024;
            ServletInputStream in = request.getInputStream();
            BufferedInputStream bf = new BufferedInputStream(in, size);
            byte[] all_data = new byte[size];
            int b,j = 0;
            while ((b = bf.read()) != -1){
                all_data[j] = (byte)b;
                j++;
            }
            data = new byte[j];
            for(int i=0; i<j; i++){
                data[i] = all_data[i];
            }
            String boundary = extractBoundary(request.getHeader("Content-Type"));
            HashMap<String, Integer[]> fileInfo = extractFiles(data, boundary);
            String name="";
            for (Map.Entry<String, Integer[]> entry: fileInfo.entrySet()){
                if(!entry.getKey().equals(NO_FILE)){
                    name = entry.getKey();
                    break;
                }
            }
            StringBuffer dat = new StringBuffer();
            for (int i= fileInfo.get(name)[0];i<fileInfo.get(name)[1];i++){
                dat.append((char)data[i]);
               /*     if(data[i]==-48){
                    i++;
                    byte b2 = data[i];
                    dat.append((char)(1152+b2));
                }else{
                if(data[i]==-47){
                    i++;
                    byte b2 = data[i];
                    dat.append((char)(1216+b2));
                }
                else{
                        dat.append((char)data[i]);
                    }
                    }*/

            }
            result = new String(dat);
        }
        catch (IOException e){
            throw new LogicException("DownloadCommand has problem with file", e);
        }
        return result;
    }
    public ArrayList<Contact> parseStringData(String allData){
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        StringTokenizer row = new StringTokenizer(allData,NEW_LINE);
        while (row.hasMoreTokens()) {
            StringTokenizer elements = new StringTokenizer(row.nextToken(),DELIMETER);
            String[] cont = new String[5];
            int i=0;
            while (elements.hasMoreTokens()) {
                cont[i] = elements.nextToken();
                i++;
            }
            if(correctData(cont)){
                Contact contact = new Contact(cont[0],cont[1],cont[2],cont[3],cont[4]);
                contactList.add(contact);
            }else{
                setSomethingIncorrectData(false);
            }
        }
        return contactList;
    }
    private boolean correctData(String[] elements){
        String correctNameSurname = "[a-zA-Z]+";
        String correctLogin = "\\w+";
        String correctEmail = "([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";
        String correctPhone = "\\d+";
        Matcher m;
        boolean result=true;
        for(int i=0;i< elements.length;i++){
            switch (i) {
                case 0:
                    Pattern p0 = Pattern.compile(correctNameSurname);
                    m = p0.matcher(elements[i]);
                    if (!m.matches()) {
                        result = false;
                    }
                    break;
                case 1:
                    Pattern p1 = Pattern.compile(correctNameSurname);
                    m = p1.matcher(elements[i]);
                    if (!m.matches()) {
                        result = false;
                    }
                    break;
                case 2:
                    Pattern p2 = Pattern.compile(correctLogin);
                    m = p2.matcher(elements[i]);
                    if (!m.matches()) {
                        result = false;
                    }
                    break;
                case 3:
                    Pattern p3 = Pattern.compile(correctEmail);
                    m = p3.matcher(elements[i]);
                    if (!m.matches()) {
                        result = false;
                    }
                    break;
                case 4:
                    Pattern p4 = Pattern.compile(correctPhone);
                    m = p4.matcher(elements[i]);
                    if (!m.matches()) {
                        result = false;
                    }
                    break;
            }
        }

        return result;
    }
    private String extractBoundary(String str){
        int index = str.lastIndexOf("boundary=");
        String boundary = str.substring(index + 9);
        boundary = "--" + boundary;
        return boundary;
    }
    private HashMap<String, Integer[]> extractFiles(byte[] data, String boundary)
    {
        int i = 0, index = 0, prev_index = 0;

        String data_str = new String(data);
        HashMap<String, Integer[]> fInfo = new HashMap<String, Integer[]>();
        while((index = data_str.indexOf(boundary,index)) != -1)
        {
            if (i != 0)
            {
                StringBuffer fileName = new StringBuffer(NO_FILE);
                Integer [] indexes = new Integer[2];
                indexes[0] = prev_index;
                indexes[1] = index - 2;
                extractData(fileName,indexes, data_str.substring(prev_index, index));
                fInfo.put(fileName.toString(), indexes);
            }
            index += boundary.length();
            prev_index = index;
            i++;
        }
        return fInfo;
    }
    private void extractData(StringBuffer fName, Integer[] indexes, String data)
    {
        char[] ch = {'\r','\n','\r','\n'};
        String new_line = new String(ch);
        String header;
        int index = data.indexOf(new_line,2);
        if (index != -1)
        {
            header = data.substring(0,index);
            String srt =  getFilename(header);
            fName.replace(0,fName.length(),srt);
            indexes[0] += index + 4;
        }
    }
    private String getFilename(String header){
        String filename;
        header.toLowerCase();
        int index;
        if ((index = header.indexOf("filename=")) != -1){
            int up_index = header.indexOf((int)'"',index + 1 + 9);
            filename = header.substring(index+9+1,up_index);
            index = filename.lastIndexOf((int)'/');
            up_index = filename.lastIndexOf((int)'\\');
            filename = filename.substring(Math.max(index, up_index) + 1);
        }
        else
            filename = NO_FILE;
        return filename;
    }

}

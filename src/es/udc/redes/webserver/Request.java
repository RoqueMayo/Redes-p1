package es.udc.redes.webserver;

import java.io.*;
import java.time.*;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;

public class Request {
    private final String[] lineRequest;
    private int status = 200;
    private File file;
    private PrintWriter out;
    private OutputStream outStream;

    public Request(String[] request, OutputStream output) {
        this.lineRequest = request;
        this.outStream= output;
    }

    public String validate(){

        String line = lineRequest[0];
        String[] chunks= line.split(" ");
        String str;

        if(chunks.length <3)
            return null;

        if (!chunks[2].startsWith("HTTP/"))
            return null;

        str= chunks[0];
        if(!str.equals("GET") && !str.equals("HEAD"))
            return null;

        return chunks[1];
    }

    public void Response(){
        String FileName;

        FileName = validate();
        out = new PrintWriter(out, true);
        if(FileName == null) {
            status = 400;
        }

        else{
            file = new File("p1-files",FileName);
        }

        if(!file.exists()) {
            status = 404;
        }
    }











}

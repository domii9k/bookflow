package edu.api.bookflow.Exceptions;

import lombok.Data;

@Data
public class CustomErrorHandler {
    private String msg;
    private String campo;

    public CustomErrorHandler(String msg, String campo){
        this.campo=campo;
        this.msg=msg;
    }
    public CustomErrorHandler(){
        super();
    }
}

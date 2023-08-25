/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Caili
 * @author alex
 */
public class Processor {
    public Map<String, String> registers = new HashMap<>();
    public Map<String, String> operations = new HashMap<>();
    
    /**
     * 
     */
    public Processor() {
        registers.put("AX","0001");
        registers.put("BX","0010");
        registers.put("CX","0011");
        registers.put("DX","0100");
        
        operations.put("LOAD","0001");
        operations.put("STORE","0010");
        operations.put("MOV","0011");
        operations.put("SUB","0100");
        operations.put("ADD","0101");
    }
    
    /**
     * 
     * @return 
     */
    public Map<String, String> getRegisters() {
        return registers;
    }
    
    /**
     * 
     * @return 
     */
    public Map<String, String> getOperations() {
        return operations;
    }
    
    /**
     * 
     * @param value
     * @return 
     */
    public String convert(Map<String, String> value){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try{
            json = objectMapper.writeValueAsString(value);
        }
        catch(JsonProcessingException e){
            return json;
        }
        
        return json;
    }
    
    /**
     * 
     * @return 
     */
    public String convertRegisterToJson(){
        return convert(getRegisters());
    }
    
    /**
     * 
     * @return 
     */
    public String convertOperationToJson(){
        return convert(getOperations());
    }
    
    /**
     * 
     * @param key
     * @return 
     */
    public String getValueRegisterByKey(String key){
        String result = null;
        for (Map.Entry<String, String> entry : getRegisters().entrySet()) {
            if(entry.getKey().equalsIgnoreCase(key)){
                result = entry.getValue();
            }
        }
        return result;
    }
    
    /**
     * 
     * @param key
     * @return 
     */
    public String getValueOperationByKey(String key){
        String result = null;
        for (Map.Entry<String, String> entry : getOperations().entrySet()) {
            if(entry.getKey().equalsIgnoreCase(key)){
                result = entry.getValue();
            }
        }
        return result;
    }
}

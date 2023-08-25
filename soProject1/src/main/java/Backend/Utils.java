/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Caili
 * @author alex
 */
public class Utils {
    
    
    /**
     * Metodo para convertir un numero entero a binario
     * @param number
     * @return 
     */
    public String convertDecimalToBinary(int number){
        if(number<0){
            return Integer.toBinaryString(number).substring(24, 32);
        }
        return fillZeros(number); 
    }
    
    /**
     * Metodo para rellenar el numero binario con ceros para mantener su representacion en 8 bits
     * @param number
     * @return 
     */
    private String fillZeros(int number){
        String binary = Integer.toBinaryString(number);
        
        if (binary.length() >= 8) {
            return binary;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 8 - binary.length()) {
            sb.append('0');
        }
        sb.append(binary);

        return sb.toString();
    }
    
    
    /**
     * Metodo para obtener la representacion binaria de la instruccion asm
     * @param line
     * @return 
     */
    public String getBinaryCode(String line){
        Processor p = new Processor();
        
        String[] code=line.split("\\s+");
        String operator = code[0];
        String register = code[1].contains(",")==true?code[1].substring(0, code[1].length()-1):code[1];
        String number = code[1].contains(",")==true? code[2] : "";
        int sendNumber = number.equals("") ? 0 : Integer.parseInt(number);
        return p.getValueOperationByKey(operator)+" "+p.getValueRegisterByKey(register)
                +" "+convertDecimalToBinary(sendNumber);
    }
    
    /**
     * Metodo para operar y almacenar resultados de las instrucciones asm
     * @param line
     * @param registers 
     */
    public void getRegistersData(String line, HashMap<String,Integer> registers) {
        String[] code=line.split("\\s+");
        String operator = code[0];
        String register = code[1];
        int result = 0;
        switch(operator) {
            case "MOV":
                register = code[1].contains(",")==true?code[1].substring(0, code[1].length()-1):code[1];
                int value = Integer.parseInt(code[2]);
                registers.put(register, value);
                break;
            case "LOAD":
                registers.put("AC", registers.get(register));
                break;
            case "STORE":
                registers.put(register, registers.get("AC"));
                break;
            case "ADD":
                result = registers.get("AC")+registers.get(register);
                registers.put("AC",result);
                break;
            case "SUB":
                result = registers.get("AC")-registers.get(register);
                registers.put("AC",result);
                break;
            default:
                break;
        }
        
    }

    /**
     * Metodo para obtener la cantidad de lineas del archivo
     * @param location
     * @return 
     */
    public int countLines(String location){
        int result = 0;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(location))) {
                String line;
                while ((line = br.readLine()) != null) {
                    result++;
                }
            }
        } catch (IOException e) {
        }
        return result;
    }
    
    /**
     * 
     * @param location
     * @return 
     */
    public int getRandomInitial(String location){
        int countLines = countLines(location);

        int min = 0;
        int max = 99;

        int number = new Random().nextInt((max - min) + 1) + min;
        if(number<10){
            return getRandomInitial(location);
        }

        int sub = max - number;
        if(sub<countLines){
            return getRandomInitial(location);
        }
        return number;
    }
    
    
    /**
     * Metodo que procesa cada linea del archivo asm
     * @param location
     * @return 
     */
    public List<Map> result(String location){
        List<Map> result = new ArrayList();
        HashMap<String, Integer> registers = new HashMap<>();
        registers.put("AC",0 );
        registers.put("AX",0 );
        registers.put("BX",0 );
        registers.put("CX",0 );
        registers.put("DX",0 );
        registers.put("PC",0);
        registers.put("IR",0);
        
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(location))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Map<String, String> data = new HashMap<>();
                    getRegistersData(line,registers);
                    data.put("CODE_ASM", line);
                    data.put("CODE_BINARY", getBinaryCode(line));
                    
                    data.put("AC",Integer.toString(registers.get("AC")));
                    data.put("AX",Integer.toString(registers.get("AX")));
                    data.put("BX",Integer.toString(registers.get("BX")));
                    data.put("CX",Integer.toString(registers.get("CX")));
                    data.put("DX",Integer.toString(registers.get("DX")));
                    data.put("PC", "0");
                    data.put("IR", data.get("CODE_ASM"));
                   
                  
                    result.add(data);
                }
            }
        } catch (IOException e) {
        }
        return result;
    }
}

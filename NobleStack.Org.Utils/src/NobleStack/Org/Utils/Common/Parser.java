/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.Utils.Common;

import com.google.gson.Gson;

/**
 *
 * @author Rajesh
 */
public class Parser<T> {
    
    /**
     *
     * @param json
     * @param classType
     * @return
     */
    public T convert(String json, Class<T> classType){
        Gson gson = new Gson();        
        return gson.fromJson(json, classType);
    }
}

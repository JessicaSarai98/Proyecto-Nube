package mx.uady.sicei.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class TokenDecodificado {
    public String sub, exp, iat, ext;

    public static TokenDecodificado getDecod(String encodedToken) throws UnsupportedEncodingException{
        String[] a = encodedToken.split("\\.");
        String b64payload = a[1];
        String jsonString = new String(Base64.decodeBase64(b64payload), "UTF-8");
        return new Gson().fromJson(jsonString, TokenDecodificado.class);
    }

    public String toString(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this); 
    }
}
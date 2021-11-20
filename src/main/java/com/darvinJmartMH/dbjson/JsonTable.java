package com.darvinJmartMH.dbjson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

public class JsonTable<T> extends Vector<T> {
    public final String filepath;
    public static final Gson gson = new Gson();

    public JsonTable(Class<T> clazz, String filepath) throws IOException {
        this.filepath = filepath;
        try
        {
            //Load Json from existing file
            Class<T[]> array = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            T[] loaded = readJson(array, filepath);
            if(loaded != null)
            {
                Collections.addAll(this, loaded);
            }
        }
        catch (FileNotFoundException e)
        {
            //Create new file if not exist
            File f = new File(filepath);
            File f1 =  f.getParentFile();
            if(f1 != null)
            {
                f1.mkdirs();
            }
            f.createNewFile();
        }
    }

    public static<T> T readJson(Class<T> clazz, String filepath) throws FileNotFoundException {
        T object = null;
        try{
            JsonReader jsonReader = new JsonReader(new FileReader(filepath));
            object = gson.fromJson(jsonReader, clazz);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public void writeJson() throws IOException{
        writeJson(this, this.filepath);
    }

    public static void writeJson(Object object, String filepath) throws IOException{

        FileWriter fileWriter = new FileWriter(filepath);
        String info = gson.toJson(object);
        fileWriter.write(info);
        fileWriter.close();
    }
}

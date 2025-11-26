package com.example.design2javafx;

import com.example.design2javafx.HorrorCharacters.HorrorCharacter;

import java.io.*;
import java.util.ArrayList;

public class Backend {

    public void BinaryExport(String binFileName, ArrayList<HorrorCharacter> hc)
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binFileName)))
        {
            oos.writeInt(hc.size());
            for (HorrorCharacter horrorCharacter : hc)
            {
                oos.writeObject(horrorCharacter);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void BinaryImport(String binFileName, ArrayList<HorrorCharacter> hc)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(binFileName)))
        {
            hc.clear();
            int count = ois.readInt();
            for (int i = 0; i < count; i++)
            {
                hc.add((HorrorCharacter)ois.readObject());
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createFile(File file)
    {
        try
        {
            if (!file.createNewFile())
            {
                System.out.println("Already exists");
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}

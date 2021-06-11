package com.wcp.env;

import java.util.Properties;

public interface Environment {

    public Properties getAllProperties();

    public String getProperty(String key);

    public String getProperty(String key, String defaultValue);

    public int getInt(String key);

    public boolean getBoolean(String key);

    public byte getByte(String key);

    public short getShort(String key);

    public long getLong(String key);

    public float getFloat(String key);

    public double getDouble(String key);

}
package com.ludei;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.PluginEntry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by imanolmartin on 22/12/15.
 */
public class CocoonApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);
        List<PluginEntry> pluginEntries = parser.getPluginEntries();
        for (PluginEntry entry : pluginEntries) {
            try {
                Class clazz = Class.forName(entry.pluginClass);
                Method method = clazz.getMethod("onApplicationCreate", Application.class);
                method.invoke(null, this);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (InvocationTargetException e) {
                e.printStackTrace();

            } catch (NoSuchMethodException e) {
                //e.printStackTrace();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);

        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);
        List<PluginEntry> pluginEntries = parser.getPluginEntries();
        for (PluginEntry entry : pluginEntries) {
            try {
                Class clazz = Class.forName(entry.pluginClass);
                Method method = clazz.getMethod("onApplicationAttachBaseContext", Application.class);
                method.invoke(null, this);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (InvocationTargetException e) {
                e.printStackTrace();

            } catch (NoSuchMethodException e) {
                //e.printStackTrace();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);
        List<PluginEntry> pluginEntries = parser.getPluginEntries();
        for (PluginEntry entry : pluginEntries) {
            try {
                Class clazz = Class.forName(entry.pluginClass);
                Method method = clazz.getMethod("onApplicationTerminate", Application.class);
                method.invoke(null, this);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (InvocationTargetException e) {
                e.printStackTrace();

            } catch (NoSuchMethodException e) {
                //e.printStackTrace();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
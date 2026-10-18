package com.cquewjh1.weibo.model;

import java.io.*;
import java.util.*;

public class BlahDAOFileImpl implements  BlahDAO{
    private String USERS;

    public BlahDAOFileImpl(String USERS) {
        this.USERS = USERS;
    }

    private class TxtFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }
    private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();
    static class DateComparator implements Comparator<Blah> {
        @Override
        public int compare(Blah b1, Blah b2) {
            return -b1.getDate().compareTo(b2.getDate());
        }
    }
    private DateComparator comparator = new DateComparator();
    @Override
    public List<Blah> getBlahs(Blah blah) {
        File border = new File(USERS + "/" + blah.getUsername());
        String[] txts = border.list(filenameFilter);
        Map<Date, String> messages = new TreeMap<Date, String>();
        for(String txt : txts) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(USERS + "/" + blah.getUsername() + "/" + txt), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String text = null;
            StringBuilder builder = new StringBuilder();
            while(true) {
                try {
                    if (!((text = reader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                builder.append(text);
            }
            Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
            messages.put(date, builder.toString());
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<Blah> blahs = new ArrayList<Blah>();

        for (Date date : messages.keySet()) {
            String txt = messages.get(date);
            blahs.add(new Blah(blah.getUsername(), date, txt));
        }

        return blahs;
    }

    @Override
    public void addBlah(Blah blah) {
        String file = USERS + "/" + blah.getUsername() + "/" + blah.getDate().getTime() + ".txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write(blah.getTxt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBlah(Blah blah) {
        File file = new File(USERS + "/" + blah.getUsername() + "/" + blah.getDate().getTime() + ".txt");
        if(file.exists()) {
            file.delete();
        }
    }
}

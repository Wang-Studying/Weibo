package com.cquewjh1.weibo.model;

import java.io.*;

public class AccountDAOFileImpl implements AccountDAO {
    private String USERS;

    public AccountDAOFileImpl(String USERS) {
        this.USERS = USERS;
    }

    @Override
    public boolean isUserExisted(Account account) {
        for (String file : new File(USERS).list()) {
            if (file.equals(account.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addAccount(Account account) {
        File userhome = new File(USERS + "/" + account.getName());
        userhome.mkdir();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new FileWriter(userhome + "/profile"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write(account.getEmail() + "\t" + account.getPassword());
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
    public Account getAccount(Account account) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(USERS + "/" + account.getName() + "/profile"), "UTF-8"));
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
        String[] txts = builder.toString().split("\t");
        account.setPassword(txts[1]);
        account.setEmail(txts[0]);
        return account;
    }
}

package javaapplication58;

import java.io.Serializable;

public class User implements Serializable{
private String login;
private char[] password;
private int number;
private boolean registered;

    public User() 
     {
        this.login = "_admin";
        this.number = 0;
        this.setPassword("7426605".toCharArray());
    }
  
    public User(String login, int id) {
  
        this.login = login;
        this.number = id;
        registered = false;
        }

    public final void setPassword(char[] psw)
    {
        password = psw;
        registered = true;
        Team.saveUsersToFile();
    }

    public void setNumber(int number) {  this.number = number;    }
    public boolean isRegistered( )    {  return registered;       }
    public final char[] getPassword() {  return password;         }
    public final int getNumber()      {  return number;           }
    public final String getLogin()    {  return login;            }

    @Override
    public String toString() {    return " пользователем " + login; }
}

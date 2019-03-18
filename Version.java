package javaapplication58;

import java.io.Serializable;

public class Version implements Serializable{    
    private int a,b,c;
    private Version versionOfRegistrationFailure;

    public Version(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return a + "." + b + "." + c;
    }

    public Version getVersionOfRegistrationFailure() {
        return versionOfRegistrationFailure;
    }

    public void setVersionOfRegistrationFailure(Version versionOfRegistrationFailure) {
        this.versionOfRegistrationFailure = versionOfRegistrationFailure;
    }
    
    
    
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
    
    public boolean equals(Version v){
        if (a!= v.a) return false;
        if (b!= v.b) return false;
        return c == v.c;
    }
}

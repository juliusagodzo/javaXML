
import java.io.Serializable;

public class Model implements Serializable {
    private double operando1;
    private double operando2;
    private double ris;
    private String operatore;
    
    //public static final long serialVersionUID = 40L;
    public Model() {
        operando1=0;
        operando2=0;
        operatore="";
        ris=0;
    }
    public Model(double operando1,double operando2, String operatore) {
        this.operando1=operando1;
        this.operando2=operando2;
        this.operatore=operatore;
        ris=0;
    }
    
    public double getOperando1() {
        return operando1;
    }
    public double getOperando2() {
        return operando2;
    }
    public double getRis() {
        return ris;
    }
    public String getOperatore() {
        return operatore;
    }
    
    public void setOperando1(double operando1) {
        this.operando1=operando1;
    }
    public void setOperando2(double operando2) {
        this.operando2=operando2;
    }
    public void setRis(double ris) {
        this.ris=ris;
    }
    public void setOperatore(String operatore) {
        this.operatore=operatore;
    }
    
    
    public String toString() {
        return getOperando1()+getOperatore()+getOperando2()+"="+getRis();
    }
}

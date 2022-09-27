import java.util.*;

public class Poste {
    private String console;
    private String ecran;
    private String avaiblily;

    public String getAvaiblily() {
        return avaiblily;
    }

    public void setAvaiblily(String avaiblily) {
        this.avaiblily = avaiblily;
    }
    public Scanner sc = new Scanner(System.in); 

    public Poste(String console, String ecran, String avaiblily) {
        this.console = console;
        this.ecran = ecran;
        this.avaiblily = avaiblily;
    }

    public String getInfos(){  
        System.out.println("console : "+console + " >> Ecran : "+ecran + " >> avaiblity : "+avaiblily);
        return "";
    }
     public String getPoste(){  
        return "console : "+console + " - Ecran : "+ecran + " - avaiblity : "+avaiblily;
    }
    
    public String postesNotAvaible(){
       System.out.println("console : "+console + " - Ecran : "+ecran + " - avaiblity : "+avaiblily);
        return "";
    }
    
    
    }
//    public poste(String console ,String ecran,String avaiblity) {
//        
//        System.out.println("the poste method is working");
//        //String posteTitle;
//        //int posteNb;
//        //ArrayList<String> posts = new ArrayList<String>();
//        
//        //posts.add("PlayStation5 - Samsung");
//        //posts.add("PlayStation5 - Samsung");
//        //posts.add("PlayStation5 - écrans Dell");
//        //posts.add("Xbox - écrans Dell");
//        //posts.add("Xbox - écrans Asus");
//        //posts.add("Xbox - écrans Asus");
//        //posts.add("Xbox - HP");
//        //posts.add("Nintendo switch - écrans Dell");
//        //posts.add("Nintendo switch - écrans Asus");
//        
//        //for(int i = 0 ; i<posts.size() ; i++){
//    //System.out.println(i+1 +" . " +posts.get(i));     
//    
////}
//        }
    
   

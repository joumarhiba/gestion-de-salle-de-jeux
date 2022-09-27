import java.util.Map.Entry;
import java.util.*;
import java.time.LocalTime; 
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.json.JSONObject;
import java.io.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameSpace {
    String jeuchoisi, username;
    File file = new File("C:\\Users\\YC\\Desktop\\jsonfile.json");
     FileWriter writer ;
    String postInfo, formattedtimenow, montant, formattedfakeendDay;
    int h=0,nbJoueur =0,code, option, starthour,startMin , som=0;
    int[] codeArray = new int[18];
    int[] optionArr = new int[18];
    static int countdown,intDuration, tarif;
    static Timer timer;
    LocalTime timenow = LocalTime.now(), endDay, fakeendDay;
    int c = 0;
    JSONObject obj=new JSONObject();
    HashMap<String, String> joueur = new HashMap<String, String>();
    HashMap<Integer, Poste> posts = new HashMap<>();
     HashMap<Integer, String> waiting = new HashMap<>(); 
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);

    
    public static void main(String[] args) throws IOException {
        
            new GameSpace().globalMenu();
            GameSpace jeu = new GameSpace();
            System.out.println();
            new GameSpace().allPostes();
        
        }
    
    
    //this method get all postes avaibles in the first time.
    public void allPostes(){
        //posts.add(postes);
        posts.put(1,new Poste("PlayStation5","Samsung","disponible"));
        posts.put(2,new Poste("PlayStation5","Samsung","disponible"));
        posts.put(3,new Poste("PlayStation5","écran Dell","disponible"));
        posts.put(4,new Poste("Xbox","écran Dell","disponible"));
        posts.put(5, new Poste("Xbox","écran Dell","disponible"));
        posts.put(6,new Poste("Xbox","écran Asus","disponible"));
        posts.put(7,new Poste("Xbox","écran HP","disponible"));
        posts.put(8,new Poste("Nintendo switch","écran Dell","disponible"));
        posts.put(9,new Poste("Nintendo switch","écran Asus","disponible"));
       
        
        int i=0;
            for(Poste p : posts.values()){
                i++;
                System.out.print(i+ " . ");
                System.out.println( p.getInfos());
            }
    }
    
    // this method allows to user to choose nbr of post and it generate a code for him
    public void posteChoice() {
         System.out.println("choisir le poste que vous voulez : ");
        option  = sc.nextInt();
        for(int b =0 ; b < optionArr.length;b++){
            if(optionArr[b] == 0){
                optionArr[b] = option;
            }
            else{
                continue;
            }
           break;  
        }
        
        System.out.println();
        code = rand.nextInt(98)+1;
        for(int b =0 ; b < codeArray.length;b++){
            if(codeArray[b] == 0){
                codeArray[b] = code;
            }
            else{
                continue;
            }
           break;  
        }
        System.out.println("Voici votre code " +code);
      
    }
    
    public void timingChoose() {
        
        long diffH, diffM, Min = 0;
        ArrayList<String> duration = new ArrayList<>();
       for(int b = 1 ; b <= optionArr.length; b++){
            if(optionArr[b] == optionArr[b-1]){
                System.out.println("vous ne pouvez pas choisir l'heure et minites");
                 
            }
           
            else {
                System.out.println("optionArr[b] : "+optionArr[b] +" optionArr[b-1] "+optionArr[b-1]);
                System.out.print("Choisir l'heure de début ");
                starthour  = sc.nextInt();
                sc.nextLine();
                System.out.print("Choisir la minute de début ");
                startMin = sc.nextInt();
                break;
            }
            
        }
        LocalTime startTime = LocalTime.of(starthour,startMin);  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
       String formattedTime = startTime.format(formatter);
        String[] t = formattedTime.split(":");
        int hours = Integer.parseInt(t[0]);
       // int minutes = Integer.parseInt(t[1]);
                
        if(hours >= 9 && hours <= 12 ||  hours>= 14 && hours <= 20) {
         System.out.println();
         LocalTime endTime = LocalTime.of(12,00);
         endDay = LocalTime.of(20,00);
         fakeendDay = LocalTime.of(20,00, 00, 00000);
         formattedfakeendDay = fakeendDay.format(DateTimeFormatter.ofPattern("HH:mm"));
            //Morning
            // MUST ADD GESTION OF STARTTIME IF IT IS 12:00  , 'hoursBetween' == 6h SO I NEED TO MENTION THAT STRAT FROM 14h
            
            if(hours <= 12 ) {
                long mins = ChronoUnit.MINUTES.between(startTime, endTime);
                diffH = mins / 60;  diffM = mins % 60;
                diffH = diffH + 6;
                Min = diffH*60+diffM;
                System.out.println("il reste vers la fin du journée : en minutes "+Min);
            }else if(hours >= 14) {
                long mins = ChronoUnit.MINUTES.between(startTime, endDay);
                diffH = mins / 60;  diffM = mins % 60; Min = diffH*60+diffM;
                System.out.println("mins : "+mins);
                System.out.println("diff hours : "+diffH);
                System.out.println("il reste vers la fin du journée : "+diffH + "h"+diffM+"m "+" en minutes "+Min);
            }
            System.out.println("vous avez le choix entre ces périodes : ");
         
           duration.add("30min");
             duration.add("1h");
            duration.add("2h");
            duration.add("5h");
            duration.add("9h");
         if(Min== 540){
         }
         if(Min< 540 && Min>= 300){
             duration.remove("9h");
         }
         if(Min >=120 && Min < 300){
              duration.remove("9h");
             duration.remove("5h");
         }
         if(Min<120 && Min >= 60){
              duration.remove("9h");
             duration.remove("5h");
             duration.remove("2h");
         }
         if(Min<60 && Min>= 30){
             duration.remove("9h");
             duration.remove("5h");
             duration.remove("2h");
             duration.remove("1h");
         }
         if(Min < 30){
              duration.remove("9h");
             duration.remove("5h");
             duration.remove("2h");
             duration.remove("1h");
             duration.remove("30min");
             System.out.println("No more choices ");
         }
            System.out.println(duration);
            Scanner scan= new Scanner(System.in); 
            System.out.print("combien de temps vous voulez rester sur ce poste: ");  
            String cDurt= scan.nextLine();            
            for(String d : duration)
            {
                if(d.equalsIgnoreCase(cDurt) || d.contains(cDurt)){
                    String value = cDurt.replaceAll("/^[A-Za-z]+$/", "");
                    intDuration = Integer.parseInt(value);
                    System.out.println("result : "+intDuration);
                    
                    timenow = LocalTime.now();
                    formattedtimenow = timenow.format(DateTimeFormatter.ofPattern("HH:mm"));
                    System.out.println("now : "+formattedtimenow+" starttime "+formattedTime);
                    if(formattedtimenow.equalsIgnoreCase(formattedTime)){
                        //countdown = intDuration*60; // CONVERTIR L'HEURE EN MINUTE
                        countdown = 50;
                        timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            //System.out.println(setInterval());
                             if(setInterval() == 0){
                                 if(disponibilité.equalsIgnoreCase("Non-Disponible")) {
                                      disponibilité = "DISPONIBLE ENCORE";
                                     try {
                                         //YOU NEED TO CHANGE SOMETHING IN updatedPostes()
                                         updatedPostes();
                                     } catch (IOException ex) {
                                         Logger.getLogger(GameSpace.class.getName()).log(Level.SEVERE, null, ex);
                                     }
                                     System.out.println("... ... ... the TIME IS OVER 30S... ....... ....... ........");
                                 }
                            }
                            
                        }
                    }, 1000, 1000);
                    }
                   
                }
            }
            
        }
        
        else {
            System.out.println("vous NE pouvez PLUS jouer");
        }
        
    }
    
     private static int setInterval() {
                        if (countdown == 1)
                            timer.cancel();
                        return --countdown;
                    }
     
     
    //it return all postes (avaible or not ) in the second time 
     String disponibilité = "Non-Disponible";
    public void updatedPostes() throws IOException {
        
        for(Entry<Integer, Poste> entry: posts.entrySet()) {
            if(option == entry.getKey()) {
                int j=1;
                for(Poste p : posts.values()) {
                    j++;
                    if( entry.getKey()+1 == j) {
                            System.out.println();
                            System.out.println("NOTICE !!!! Si vous choisissez le poste non-disponible pour l'instant vous serez dans la liste d'attente");
                            p.setAvaiblily(disponibilité);
                       int c2 = 0;
                       
                            for(Poste updatedPost : posts.values()){
                            c2++;
                            System.out.print(c2+ " : ");
                            updatedPost.getInfos();
                            if(updatedPost.getAvaiblily().equals("DISPONIBLE ENCORE")){
                                for(Entry<Integer, Poste> entry2: posts.entrySet()) {
                                if(option == entry2.getKey()){
                                        //System.out.println("Le poste n° "+entry2.getKey()+ " est libre ");
                                        //:: System.out.println("le poste qui sera effacer de waiting : "+entry2.getValue());   
                                        waiting.remove(entry2.getKey());
                                  }
                                // System.out.print("ce poste est libre POUR "+updatedPost.getInfos());
                                }
                            }
                            if(waiting.size() <= 8){
                                for(int i = 0; i < optionArr.length; i++) {
                                    if(optionArr[i] == option && updatedPost.getAvaiblily().equals("Non-Disponible")) {
                                        // System.out.println("the option ixesting "+optionArr[i] +" has avaibility "+updatedPost.getAvaiblily());
                                        waiting.put(code,updatedPost.getPoste()); 
                                        setInterval();
                                        
                                    }
                                     } 
                            }
                            else {
                                System.out.println("not more players........... la file d'attente est pleine");
                                globalMenu();
                            }
                            
                            if(updatedPost.getAvaiblily().equals(disponibilité)) {
                                // System.out.println("updatedPost.getAvaiblily() >>> "+updatedPost.getAvaiblily()+ "setInterval "+countdown) ;
                                if(updatedPost.getAvaiblily().equals("Non-Disponible")){
                                    updatedPost.setAvaiblily("DISPONIBLE ENCORE");
                                }
                                for(int k : waiting.keySet()){
                                    if(waiting.get(k).contains(disponibilité)) {
                                        if(disponibilité.equalsIgnoreCase("DISPONIBLE ENCORE")) {
                                            
                                        }
                                        
                                    }
                                }
                            }
                            
                        }
                    }
                }
            }
        }       
    }

    public void Menu() {
    
        Scanner sc = new Scanner(System.in);
         char choix;
          
            System.out.println(" choisir le type du jeu souhaité");
            System.out.println("*** Football");
            System.out.println("1 . FIFA");
            System.out.println("2 . PES");
            System.out.println();
            System.out.println("*** . Guerre");
            System.out.println("3 . Counter-Strike");
            System.out.println("4 . Assassin's Creed");
            System.out.println("5 . Exit");
            System.out.println();
                
            System.out.println("Veuillez entrer votre choix");
            choix  = sc.next().charAt(0);
            System.out.println();
            GameSpace jeu = new GameSpace();
             
            switch(choix) {
                case '1':
                jeu.jeuchoisi = "fifa";
                break;
            case '2':
                jeu.jeuchoisi = "PES";
                break;
            case '3':
                jeu.jeuchoisi = "Counter-Strike";
                break;
            case '4':
                jeu.jeuchoisi = "Assassin's Creed";
                break;
            default:
                break;
            }
            joueur.put("jeuChoisi", jeu.jeuchoisi);
            System.out.println("jeu choisi est : "+joueur.get("jeuChoisi"));
          
}
   int in = 1;
     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");
     String formatteddtf = dtf.format(LocalDateTime.now());
    public void gestionTarif(){
       switch(intDuration){
           case 30 : 
               tarif = 5;break;
           case 1 : 
               tarif = 10;
               break;
            case 2 : 
               tarif = 18; break;
            case 5 : 
               tarif = 40; break;
            case 9 : 
               tarif = 65; break;
            default:
                break;
       }
       int i =0;
       System.out.println("Vous devez payer : "+tarif+"\n Merciii !!");
       som = som + tarif;
       System.out.println("LA SOMME : "+som);
       
       if(!file.exists()){
           try {
               file.createNewFile();
           } catch (IOException ex) {
               Logger.getLogger(GameSpace.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else{
          
           try {
               
                String[] d = formatteddtf.split("/");
                writer = new FileWriter(file, true);
                montant = Integer.toString(som);
                //  if(LocalTime.now().compareTo(fakeendDay) > 0){
                
                if(file.length() > 0){
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    System.out.println("====> "+reader.readLine().contains(d[1]));
                    if(reader.readLine().contains(d[1])){
                        System.out.println(reader.readLine());
                    }
                }
                
                    System.out.println("la somme est du jour : "+som+"\n");
                    obj.put(d[1], montant);
                     writer.write(obj.toString());
                     writer.write(" , \n");
                     jsonfile();
         
         // }
                     writer.close();
                
           } catch (IOException ex) {
               Logger.getLogger(GameSpace.class.getName()).log(Level.SEVERE, null, ex);
           }           
       }
       
   }
    public void jsonfile() throws IOException{
        if(obj.length() > 0) {
            
               if(LocalTime.now().compareTo(fakeendDay) > 0) {
                   in++;
               }
               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");
                String formatteddtf2 = dtf.format(LocalDateTime.now());
                String[] d = formatteddtf2.split("/");
               System.out.println("the second day ");
               // intin++;
               
                obj.put(d[1],montant);
                System.out.println("length ===== "+obj.length());
                writer.write(obj.toString());
                     writer.write(" , ");
                System.out.println("contenu du json "+obj + "le montant du mois actuel  "+obj.get(d[1])+" le montant du mois precedent "+obj.get(d[1])); 
                
          }
    }
    
    public void globalMenu() throws IOException{
         //Menu principale
        
        System.out.println();
        System.out.println("Menu Principale ");
            System.out.println("1 . Ajouter un autre joueur ");
//            System.out.println("2 . Voir le total ");
            System.out.println("2 . Exit ");
            System.out.println();
            System.out.println("Veuillez entrer votre choix !");
            char e  = sc.next().charAt(0);
            System.out.println();
            GameSpace ecran = new GameSpace();
            
                      
            switch(e) {
                case '1':
                        System.out.println("Veuillez entrer votre nom et prénom ");
                        String username = sc.nextLine();
                        new GameSpace().Menu();
                        nbJoueur++;
                            System.out.println("le nb des joueur : "+nbJoueur);
                        if(nbJoueur == 1) {
                           allPostes();
                           posteChoice();
                           timingChoose();
                           gestionTarif();
                           globalMenu();
                           
                        }
                        else {
                            updatedPostes();
                            posteChoice();
                            timingChoose();
                            gestionTarif();
                            globalMenu();
                            
                        }
                        break;
            
            case '2':
                System.out.println("vous avez quitter le console :( ");
                break;
            
            default:
                break;
            }
            
    }

    private LocalTime LocalTime(String value) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

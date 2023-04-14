import java.io.*;
import java.util.*;



class Main
{
    
    public static void main (String [] args) throws Exception
    {
        
        
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Hashtable<String, Integer> ht1 = new Hashtable<>();
        try 
        {
            BufferedReader buff = input;
            String line ="";
            line = buff.readLine();
            int docs = Integer.parseInt(line);
            int z =0;
            String str = "";
            
            while(z<docs)
            {
                
                while (line!=null)
                {   
                    line = buff.readLine();
                    
                    if(line.equals("END"))
                    {
                        break;
                    }     
                    str=str+" "+line;                      
                }

                String [] words;
                words = str.split("[^a-z]+");
                List <String> wordList = new ArrayList<String>(Arrays.asList(words));
                wordList.remove(0);
                
                
                
                ArrayList <String> desirable =new ArrayList<String>();
                ArrayList <String> containing =new ArrayList<String>();

                for(int i =0; i< wordList.size();i++)
                {
                    String word = wordList.get(i);
                    if(desirable.contains(word)==false)
                    {    
                        desirable.add(word);
                    }   
                }

                int selection = 100001;
                int left=0;
                int right=0;
                for(int i =0; i< wordList.size();i++)
                {
                    for (int j =i+1; j< wordList.size()+1; j++)
                    { 
                        if(wordList.subList(i, j).containsAll(desirable))
                        {

                            if(j-i<selection)
                            {
                                selection = j-i;
                                left =i+1;
                                right=j;
                            }

                        }
                    }
                }
                System.out.println("Document "+(z+1)+": "+left+" "+right);
                desirable.clear();
                str = "";
                z++;
            }
            buff.close();
        } 
        catch (IOException e) 
        {
         
        }
        

        
        
        

    }

    
}

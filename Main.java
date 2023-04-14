import java.io.*;
import java.util.*;


class Main
{

    private static boolean [][] checked = new boolean[0][0];
    private static String [][] array = new String [0][0];
    private static ArrayList <Integer> islandX =new ArrayList <Integer>();
    private static ArrayList <Integer> islandY =new ArrayList <Integer>();

    private static boolean isNumber(String string) {
        try {
            int i = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static int search (int x,int y,String str) {
		int [] rowTraverse = { -1, 0, 0, 1}; 
		int [] colTraverse = { 0, -1, 1, 0};
		if ((x>=0 && x<array.length && y>=0 && y<array[x].length)==false) 
		{
			return 0;
            
		}
		if(array[x][y].equals(str) && !checked[x][y])
		{
            int count=1;
			checked[x][y]=true;
            islandX.add(x);
            islandY.add(y);
			
			for (int i=0;i<rowTraverse.length;i++) {
				
				if (y+colTraverse[i]== array[x].length) 
                {
					count=count+search(x+rowTraverse[i],0,str);					
				} 
                else if (y+colTraverse[i]== -1) 
                {
					count=count+search(x+rowTraverse[i],array[x].length-1,str);
				} 
                else
                {
					count=count+search(x+rowTraverse[i],(y+colTraverse[i]),str);
				}
			}
			return count;
		}
		else{
			return 0;
		}	
	}

    public static void main(String [] args) throws Exception
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        if(args.length==0)
        {
            int z = 0;
            int max =0;
            BufferedReader buff = input;
            String [] coords = new String [2];
            String [] majid = new String [2]; 
            String land = "";
            String water ="";        
            ArrayList<String> lw = new ArrayList<String>();
            for (String line = buff.readLine(); line != null; line = buff.readLine()) {
                if(line.equals(""))
                {
                    coords = new String [2];
                    majid = new String [2];
                    lw.clear();
                    z=0;
                    max = 0;
                }
                else if(isNumber(line.replaceAll("\\s+",""))==true)
                {
                    if(coords[0]==null)
                    {
                        coords = line.split(" ");
                        array = new String [Integer.parseInt(coords[0])][Integer.parseInt(coords[1])];
                        checked = new boolean [Integer.parseInt(coords[0])][Integer.parseInt(coords[1])];
                    }
                    else
                    {
                        //this is where everything gets done now basically
                        majid = line.split(" ");
                        land = array[Integer.parseInt(majid[0])][Integer.parseInt(majid[1])];
                        if(lw.size()!=2)
                        {
                                               
                        }
                        else if(lw.get(0).equals(land)==true)
                        {
                            water=lw.get(1);
                        }
                        else
                        {
                            water=lw.get(0);
                        }
                        //System.out.println(land+":"+water);
                        //System.out.println(Arrays.deepToString(array));
                        search(Integer.parseInt(majid[0]),Integer.parseInt(majid[1]),land);
                        for(int t =0; t<Integer.parseInt(coords[0]);t++)
                        {
                            for(int l = 0; l<Integer.parseInt(coords[1]); l++)
                            { 
                                if(array[t][l].equals(land))
                                {  
                                    int search = search(t,l,land);
                                    if(max<search)
                                    {
                                        max=search; 
                                    }

                                }
                            }
                        } 
                        System.out.println(max);
                    }
                    
                }
                else
                {
                    for(int i=0; i<line.length();i++)
                    {
                        array[z][i]=Character.toString(line.charAt(i));
                        checked[z][i]=false;
     
                        if(lw.contains(array[z][i])==false)
                        {
                            lw.add(array[z][i]);
                        }
                    }
                    z++;
                }
             }
        }
        else if(args[0].equals("PartB"))
        {
            int z = 0;
            int max =0;
            BufferedReader buff = input;
            String [] coords = new String [2];
            String [] majid = new String [2]; 
            String land = "";
            String water ="";
            ArrayList <Integer> islandBigX =new ArrayList <Integer>();
            ArrayList <Integer> islandBigY =new ArrayList <Integer>();
            ArrayList<String> lw = new ArrayList<String>();
            int distance =0;
            int maxDistance = 0;
            int distances=0;
    
            
            for (String line = buff.readLine(); line != null; line = buff.readLine()) {
                if(line.equals(""))
                {
                    coords = new String [2];
                    majid = new String [2];
                    lw.clear();
                    z=0;
                    max = 0;
                    distance =0;
                    maxDistance = 0;
                    distances = 0;
                    
                }
                else if(isNumber(line.replaceAll("\\s+",""))==true)
                {
                    if(coords[0]==null)
                    {
                        coords = line.split(" ");
                        array = new String [Integer.parseInt(coords[0])][Integer.parseInt(coords[1])];
                        checked = new boolean [Integer.parseInt(coords[0])][Integer.parseInt(coords[1])];
                    }
                    else
                    {
                        //we now have all relevant infoo
                        majid = line.split(" ");
                        land = array[Integer.parseInt(majid[0])][Integer.parseInt(majid[1])];
                        if(lw.size()!=2)
                        {
                           maxDistance = -1;                    
                        }
                        else if(lw.get(0).equals(land)==true)
                        {
                            water=lw.get(1);
                        }
                        else
                        {
                            water=lw.get(0);
                        }
                        search(Integer.parseInt(majid[0]),Integer.parseInt(majid[1]),land);
                        islandX.clear();
                        islandY.clear();
                        for(int t =0; t<Integer.parseInt(coords[0]);t++)
                        {
                            for(int l = 0; l<Integer.parseInt(coords[1]); l++)
                            { 
                                if(array[t][l].equals(land))
                                {  
                                    int search = search(t,l,land);
                                    if(max<=search)
                                    {
                                        max=search;
                                        islandBigX=new ArrayList <Integer>(islandX);
                                        islandBigY=new ArrayList <Integer>(islandY);
                                        
                                    }
                                    
                                    islandX.clear();
                                    islandY.clear();
                                }
                            }
                        }
                        int [] nw = new int [2];
                        for(int a =0; a<Integer.parseInt(coords[0]);a++)
                        {
                            for(int b = 0; b<Integer.parseInt(coords[1]); b++)
                            { 
                                if(islandBigX.size()!=0 && a==islandBigX.get(0)&&b==islandBigY.get(0))
                                {
                                    nw = new int []{islandBigX.get(0),islandBigY.get(0)};
                                }
                            }
                        }

                        for(int c =0; c<islandBigX.size(); c++)
                        {
                            distance = (Math.abs(nw[0]-islandBigX.get(c)))+(Math.abs(nw[1]-islandBigY.get(c)));
                            if(distance>maxDistance)
                            {
                                maxDistance=distance;
                            }
                        }

    
                        if(max==0)
                        {
                            System.out.println(max+ " "+"-1");
                        }
                        else
                        {
                            System.out.println(max+ " "+maxDistance);
                        }
                        
                    }
                    
                }
                else
                {
                    for(int i=0; i<line.length();i++)
                    {
                        array[z][i]=Character.toString(line.charAt(i));
                        checked[z][i]=false;
     
                        if(lw.contains(array[z][i])==false)
                        {
                            lw.add(array[z][i]);
                        }
                    }
                    z++;
                }

             }
        }
        
    }
}
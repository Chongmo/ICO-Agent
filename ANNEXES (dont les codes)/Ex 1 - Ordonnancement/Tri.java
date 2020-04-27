public class Tri {
	 public static void triFusion(int t[],int[] p)
     {
     int longueur=t.length;
     if (longueur>0)
         {
         triFusion(t,p,0,longueur-1);
         }
     }

 private static void triFusion(int t[], int[] p, int deb,int fin)
     {
     if (deb!=fin)
         {
         int milieu=(fin+deb)/2;
         triFusion(t,p,deb,milieu);
         triFusion(t,p,milieu+1,fin);
         fusion(t,p,deb,milieu,fin);
         }
     }

 private static void fusion(int t[],int p[],int deb1,int fin1,int fin2)
     {
     int deb2=fin1+1;

     //on recopie les éléments du début du tableau
     int table1[]=new int[fin1-deb1+1];
     for(int i=deb1;i<=fin1;i++)
         {
         table1[i-deb1]=t[i];
         }
     
     int compt1=deb1;
     int compt2=deb2;
     
     for(int i=deb1;i<=fin2;i++)
         {        
         if (compt1==deb2) //c'est que tous les éléments du premier tableau ont été utilisés
             {
             break; //tous les éléments ont donc été classés
             }
         else if (compt2==(fin2+1)) //c'est que tous les éléments du second tableau ont été utilisés
             {
             t[i]=table1[compt1-deb1]; //on ajoute les éléments restants du premier tableau
             compt1++;
             }
         else if (p[table1[compt1-deb1]]<p[t[compt2]])
             {
             t[i]=table1[compt1-deb1]; //on ajoute un élément du premier tableau
             compt1++;
             }
         else
             {
             t[i]=t[compt2]; //on ajoute un élément du second tableau
             compt2++;
             }
         }
     }

}

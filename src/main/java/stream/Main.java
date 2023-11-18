package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
       // List<Personne[]>couples =couplesFHPossibles();
       // couples.forEach(couple->System.out.println("("+couple[0]+","+couple[1]+")"));
        List<String> motsToTest = Arrays.asList("code","anagram","mrgaana","edoc", "anagrmm");
        System.out.println("Version V2");
        List<String> motsSansDoublons = removeAnagramsV2(motsToTest);
        motsSansDoublons.forEach(System.out::println);
        System.out.println("Version V1");
        motsSansDoublons = removeAnagramsV1(motsToTest);
        motsSansDoublons.forEach(System.out::println);
    }
/*
on veut retirer de la liste de mots en entrée, les anagrammes , c'est à dire tous les mots ayant le même nombre de lettres
qu'un mot déjà existant et ayant toutes ses lettres dans le mots existant et eéciproquement
 */
    public static List<String> removeAnagramsV1(List<String> dataToProceed)
    {
        List<String> result = new ArrayList<>();
        dataToProceed.forEach(wordToCheck-> {
            boolean isAnagram =false;
            for ( String wordAdded:result)
            {
                if (wordAdded.length() == wordToCheck.length() && !isAnagram) {
                    isAnagram=true;
                    for (int i=0;i<wordAdded.length()&& isAnagram;i++)
                    {  boolean isLetterFound=false;
                        for (int j=0;j<wordToCheck.length() && !isLetterFound;j++)
                        {
                            isLetterFound=wordAdded.charAt(i) == wordToCheck.charAt(j);
                        }
                        isAnagram=isLetterFound;
                    }
                }
            }
            if (!isAnagram)
            {
                result.add(wordToCheck);
            }
        });
            return result;
    }

    public static List<String> removeAnagramsV2(List<String> dataToProceed)
    {
        List<String> result = new ArrayList<>();
        dataToProceed.forEach(wordToAdd-> {
            if (result.stream().noneMatch(wordToCheck -> checkAnagrams(wordToAdd,wordToCheck) )) {
                result.add(wordToAdd);
            }
        });
        return result;
    }

    public static boolean checkAnagrams(String word1, String word2)
    {
        boolean isAnagram =word1.length() == word2.length();
        for (int i=0;i<word1.length() && isAnagram;i++)
        {
            boolean isLetterFound =false;
            for (int j=0; j< word2.length() && !isLetterFound;j++)
            {
                isLetterFound= word1.charAt(i)== word2.charAt(j);
            }
            isAnagram = isLetterFound;
        }
        return isAnagram;
    }
    public static void exoCarre()
    {
        /*Étant donné une liste de nombres,
        comment retourneriez-vous une liste du carré de chaque nombre ?
         Par exemple, étant donné [1, 2, 3, 4, 5] vous devriez retourner [1, 4, 9, 16, 25].

         */
        List<Integer> nombres = Arrays.asList(1,2,3,4,5);
        List<Integer> nombresCarres =nombres.stream().map(nombre-> nombre*nombre).collect(Collectors.toList());
    }

    public static void exoBinomesPossibles()
    {
        /*
        Etant donné deux listes de nombres,
        comment renverrais-tu tous les binômes possibles ?
         Par exemple, si vous avez une liste [1, 2, 3] et une liste [3, 4],
         vous devez retourner [(1, 3), (1, 4), (2, 3), (2, 4) 3, 3), (3, 4)].
         Pour plus de simplicité, vous pouvez représenter une paire sous forme de tableau avec deux éléments.
         */
        List<Integer> l1 = Arrays.asList(1,2,3);
        List<Integer> l2 = Arrays.asList(3,4);
        List <int[]> resultat=l1.stream().flatMap(nombre1->l2.stream().map(nombre2-> new int []{nombre1,nombre2})).collect(Collectors.toList());
        for(int [] t : resultat)
        {
            System.out.println("("+t[0]+ ","+t[1]+")");
        }
    }

    public static void exoBinomesDivisiblePar3Possibles()
    {
        /*
        Etant donné deux listes de nombres,
        comment renverrais-tu tous les binômes possibles dont la somme est divisible par 3?
         Par exemple, si vous avez une liste [1, 2, 3] et une liste [3, 4],
         vous devez retourner [(2, 4) et (3, 3) ].
         Pour plus de simplicité, vous pouvez représenter une paire sous forme de tableau avec deux éléments.
         */
        List<Integer> l1 = Arrays.asList(1,2,3);
        List<Integer> l2 = Arrays.asList(3,4);
        //List <int[]> resultat=l1.stream().flatMap(nombre1->l2.stream().map(nombre2-> new int []{nombre1,nombre2})).filter(nombre-> (nombre[0]+nombre[1])%3==0).collect(Collectors.toList());
        List <int[]> resultat=l1.stream().flatMap(nombre1->l2.stream().filter(nombre2-> (nombre1+nombre2)%3==0).map(nombre2-> new int []{nombre1,nombre2})).collect(Collectors.toList());
        for(int [] t : resultat)
        {
            System.out.println("("+t[0]+ ","+t[1]+")");
        }
    }
    public static List<Personne> creationListPersonnes()
    {
        return Arrays.asList(new Personne(Genre.HOMME,"PALATIER","Loic",185),
                new Personne(Genre.FEMME,"DESBEAUMONT","Celine",192),
                new Personne(Genre.FEMME,"SCHWARZWALD","Liliane",159),
                new Personne(Genre.HOMME,"BLAUHAUS","Ivan",176),
                new Personne(Genre.FEMME,"LILANTRO","Jaqueline",164),
                new Personne(Genre.FEMME,"LEBO","Amélie",168)
        );

    }
    public static void streamSurPersonnes()
    {
        List<Personne> personnes = creationListPersonnes();

        System.out.println("Taille moyenne :"+personnes.stream().mapToInt(Personne::getTaille).average().orElse(0));
        System.out.println("Taille moyenne des hommes:"+personnes.stream().filter(p-> p.getSexe().equals(Genre.HOMME)).mapToInt(Personne::getTaille).average().orElse(0));
        System.out.println("Moyenne du Nombre de lettres dans le nom des femmes :"+ personnes.stream().filter(p-> p.getSexe().equals(Genre.FEMME)).mapToInt(p -> p.getNom().length()).average().orElseThrow());
        long nbPersonnes = personnes.size();
        long nbFemmes = personnes.stream().filter(p->p.getSexe().equals(Genre.FEMME)).count();
        System.out.println("Femmes :"+ nbFemmes +" /"+nbPersonnes);
        System.out.println("personnes ayant un nom commencant avant ou avec la lettre M :");
        personnes.stream().filter(p->p.getNom().toUpperCase().charAt(0)<='M').forEach(System.out::println);
    }


    public static List<Personne[]> couplesFHPossibles()
    {
        List<Personne> personnes = creationListPersonnes();

        return personnes.stream().filter(p->p.getSexe() == Genre.HOMME).flatMap(
                homme-> personnes.stream().filter(p->p.getSexe() == Genre.FEMME).map(femme -> new Personne[]{homme,femme}) )
                .collect(Collectors.toList());
    }
    public static void essaiStreamString()
    {
        List<String> essai = new ArrayList<>();
        essai.add("Thomas");
        essai.add("David");
        essai.add("Emilie");
        essai.add("Tristan");

        essai.stream().map(element-> element +" aime le foot").forEach(System.out::println);

        // chaque prenom aime tous les autres.
        essai.stream().flatMap(element-> essai.stream().filter(nom -> !nom.equals(element)).map(nom->element+ " aime "+nom)).forEach(System.out::println);
    }
}
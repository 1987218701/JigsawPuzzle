package JigsawPuzzle;

import java.util.Random;

public class GameUtil {
    private GameUtil(){
    }

    public static String createCode(){
        Random r = new Random();
        char[] words = new char[52];
        char a = 'a';
        for (int i = 0; i < 26; i++) {
            words[i] = a;
            a++;
        }
        char A = 'A';
        for (int i = 26; i < words.length; i++) {
            words[i] = A;
            A++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(52);
            sb.append(words[index]);
        }
        int num = r.nextInt(10);
        sb.append(num);
        char[] strs = sb.toString().toCharArray();
        for (int i = 0; i < strs.length; i++) {
            int j = r.nextInt(strs.length);
            char temp = strs[i];
            strs[i] = strs[j];
            strs[j] = temp;
        }
        String str = "";
        for (int i = 0; i < strs.length; i++) {
            str += strs[i];
        }
        return str;
    }

}

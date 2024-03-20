package lv3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BrianWorry {

    public BrianWorry() {
        System.out.println("constructor");
    }
    public void start() {
//        String[] sentences = {"HaEaLaLaObWORLDb", "SpIpGpOpNpGJqOqA", "AxAxAxAoBoBoB"};
        List<String> sentences = new ArrayList<>();
//        sentences.add("HaEaLaLaObWORLDb");
//        sentences.add("SpIpGpOpNpGJqOqA");
//        sentences.add("AxAxAxAoBoBoB");

        sentences.add("aAAAaAbAbAcAdAdAceBBefBgBfBhBiCiDDDjEjEkEkElEmEl");
        for (int i=0;i<sentences.size();i++) {
            System.out.printf("%d %s -> %s%n", i+1, sentences.get(i), solution(sentences.get(i)));
            System.out.printf("%d %s -> %s%n", i+1, sentences.get(i), solution2(sentences.get(i)));
            System.out.printf("%d %s -> %s%n", i+1, sentences.get(i), solution3(sentences.get(i)));
        }
    }

    class Token {
        String str;
        boolean isCondition1;
        boolean isCondition2;
    }

    boolean[] checked = new boolean[26];

    public String solution(String sentence) {
        ArrayList<Token> tokens = new ArrayList<>();
        while (!sentence.isEmpty()) {
            char firstChar = sentence.charAt(0);
            if (isLowerCase(firstChar)) {
                if (sentence.length() == 1) {
                    return "invalid";
                }
                if (setChecked(firstChar)) {
                    //같은 기호를 2번 사용
                    return "invalid";
                }
                if (sentence.indexOf(firstChar, 1) != sentence.lastIndexOf(firstChar)) {
                    //2번 조건에 개수가 2개가 아님
                    return "invalid";
                }
                if (cutCondition1(tokens, sentence.substring(1, sentence.lastIndexOf(firstChar)), false, true)) {
                    return "invalid";
                }
                sentence = sentence.substring(sentence.lastIndexOf(firstChar)+1);
            } else {
                if (sentence.length() == 1) {
                    Token token = new Token();
                    token.str = String.valueOf(firstChar);
                    tokens.add(token);
                    break;
                }
                char char2 = sentence.charAt(1);
                if (!isLowerCase(char2)) {
                    Token token = new Token();
                    token.str = String.valueOf(firstChar);
                    tokens.add(token);
                    sentence = sentence.substring(1);
                } else {
                    if (sentence.indexOf(char2) == sentence.lastIndexOf(char2) || sentence.indexOf(char2, 2) != sentence.lastIndexOf(char2)) {
                        //조건1에서 기호의 개수가 1 혹은 3이상
                        if (cutCondition1(tokens, sentence.substring(0, sentence.lastIndexOf(char2) + 2), true, false)) {
                            return "invalid";
                        }
                        sentence = sentence.substring(sentence.lastIndexOf(char2) + 2);
                    } else {
                        Token token = new Token();
                        token.str = String.valueOf(firstChar);
                        tokens.add(token);
                        sentence = sentence.substring(1);
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        StringBuilder word = new StringBuilder();
        while (!tokens.isEmpty()) {
            if (tokens.size() >= 3 &&
                    (tokens.get(0).str.length() == 1 && !tokens.get(0).isCondition1 && !tokens.get(0).isCondition2) &&
                    (tokens.get(1).str.length() == 1 && !tokens.get(1).isCondition1 && tokens.get(1).isCondition2) &&
                    (tokens.get(2).str.length() == 1 && !tokens.get(2).isCondition1 && !tokens.get(2).isCondition2) ) {
                if (word.length() != 0) {
                    answer.append(word).append(" ");
                    word = new StringBuilder();
                }
                System.out.printf("%s %b %b\n", tokens.get(0).str, tokens.get(0).isCondition1, tokens.get(0).isCondition2);
                System.out.printf("%s %b %b\n", tokens.get(1).str, tokens.get(1).isCondition1, tokens.get(1).isCondition2);
                System.out.printf("%s %b %b\n", tokens.get(2).str, tokens.get(2).isCondition1, tokens.get(2).isCondition2);
                answer.append(tokens.get(0).str).append(tokens.get(1).str).append(tokens.get(2).str).append(" ");
                tokens.remove(0);
                tokens.remove(0);
                tokens.remove(0);
            } else {
                if (tokens.get(0).str.length() == 1 && !tokens.get(0).isCondition1 && !tokens.get(0).isCondition2) {
                    word.append(tokens.get(0).str);
                    tokens.remove(0);
                } else {
                    if (word.length() != 0) {
                        answer.append(word).append(" ");
                        word = new StringBuilder();
                    }
                    System.out.printf("%s %b %b\n", tokens.get(0).str, tokens.get(0).isCondition1, tokens.get(0).isCondition2);
                    answer.append(tokens.get(0).str).append(" ");
                    tokens.remove(0);
                }
            }
        }
        return answer.toString().trim();
    }

    private boolean cutCondition1(ArrayList<Token> tokens, String str, boolean condition1, boolean condition2) {
//        System.out.print(str);
        Token w = new Token();
        if (str.length() == 1) {
            w.str = str;
            w.isCondition1 = condition1;
            w.isCondition2 = condition2;
            tokens.add(w);
//            System.out.printf(" %b %b\n", w.isCondition1, w.isCondition2);
            return false;
        }
        char symbol = str.charAt(1);
        if (isLowerCase(symbol)) {
            StringBuilder word = new StringBuilder();
            if (setChecked(symbol)) {
                return true;
            }
            boolean isLower = false;
            for (int i=0;i<str.length();i++) {
                char c = str.charAt(i);
                if (isLower == isLowerCase(c)) {
                    if (isLower) {
                        if (c != symbol) {
                            return true;
                        }
                    }else {
                        word.append(c);
                    }
                    isLower = !isLower;
                }
            }
            w.str = word.toString();
            w.isCondition1 = condition1;
            w.isCondition2 = condition2;
            tokens.add(w);
        } else {
            for (int i=0;i<str.length();i++) {
                if (isLowerCase(str.charAt(i))) {
                    return true;
                }
            }
            w.str = str;
            w.isCondition1 = condition1;
            w.isCondition2 = condition2;
            tokens.add(w);
        }
//        System.out.printf(" %b %b\n", w.isCondition1, w.isCondition2);
        return false;
    }

    private boolean setChecked(char c) {
        checked[c-97] = !checked[c-97];
        return !checked[c - 97];
    }

    private boolean isLowerCase(char c) {
        return 'a' <= c && 'z' >= c;
    }

    /*
    1. aHELLOa bWORLDb (공백이 제거되어야 한다.)
    2. HaEaLaLObWORLDb (규칙 1은 단어의 모든 글자 사이에 적용되어야 한다. 단, 이 문장은 원문이 HELL O WORLD인 경우 올바른 변환이다.)
    3. aHELLOWORLDa (규칙 2는 한 단어에 적용되어야 한다. 단, 이 문장은 원문이 HELLOWORLD인 경우 올바른 변환이다.)
    4. HaEaLaLaOWaOaRaLaD (첫 번째 단어에 쓰인 기호 a를 두 번째 단어에 쓸 수 없다.)
    5. abHELLObaWORLD (하나의 규칙을 같은 단어에 두 번 적용할 수 없다.)
    6. (규칙 1) 특정 단어를 선택하여 글자 사이마다 같은 기호를 넣는다. ex) HELLO -> HaEaLaLaO
    7. (규칙 2) 특정 단어를 선택하여 단어 앞뒤에 같은 기호를 넣는다. ex) WORLD -> bWORLDb
    8. 위의 두 가지 규칙은 한 단어에 모두 적용될 수 있지만 같은 규칙은 두 번 적용될 수 없다.
    9. 한 번 쓰인 소문자(특수기호)는 다시 쓰일 수 없다.
     */

    char[] sentence;

    class Word {
        int rule, start, end;

        public Word(int rule, int start, int end) {
            this.rule = rule;
            this.start = start;
            this.end = end;
        }
    }

    public String solution2(String s) {
        sentence = s.toCharArray();
        String invalid = "invalid";

        LinkedHashMap<Character, List<Integer>> chars = new LinkedHashMap<>();
        int len = s.length();
        char c;
        for (int i=0;i<len;++i) {
            c = sentence[i];
            if (c >= 'a' && c <= 'z') {
                if (!chars.containsKey(c)) {
                    chars.put(c, new ArrayList<>());
                }
                chars.get(c).add(i);
            }
        }

        List<Word> words = new ArrayList<>();
        int start_str = 0, start_char, end_char, start_char_pre = -1, end_char_pre = -1,
                start_word = 0, end_word = 0, end_word_pre = -1, num, rule = -1, distance;
        boolean isDistance2;
        for (List<Integer> positions : chars.values()) {
            num = positions.size();
            start_word = start_char = positions.get(0);
            end_word = end_char = positions.get(num - 1);
            isDistance2 = true;
            for (int i=1;i<num;i++) {
                distance = positions.get(i) - positions.get(i - 1);
                if (distance < 2) {
                    return invalid;
                } else if (distance > 2) {
                    isDistance2 = false;
                    break;
                }
            }
            if (num >= 3 && !isDistance2) {
                return invalid;
            }
            if (num == 1 || num >= 3) {
                rule = 1;
                start_word--;
                end_word++;
                if (start_word< 0 || end_word >= len) {
                    return invalid;
                }
            }
            if (num == 2) {
                rule = isDistance2? 0 : 2;
            }
            if (start_char_pre < start_char && end_char < end_char_pre) {
                if (rule == 2) {
                    return invalid;
                }
                continue;
            }
            if (end_word_pre >= start_word) {
                return invalid;
            }
            words.add(new Word(rule, start_word, end_word));
            start_char_pre = start_char;
            end_char_pre = end_char;
            end_word_pre = end_word;
        }
        StringBuilder answer = new StringBuilder();
        int size = words.size();
        Word word;
        for (int i=0;i<size;++i) {
            word = words.get(i);
            rule = word.rule;
            start_word = word.start;
            end_word = word.end;
            if (rule == 0) {
                if ((start_str <= start_word - 1) && (end_word + 1 < (i < size - 1 ? words.get(i+1).start : len))) {
                    start_word--;
                    end_word++;
                }
            }
            if (start_str < start_word) {
                answer.append(getStr(start_str, start_word - 1));
            }
            answer.append(getStr(start_word, end_word));
            start_str = end_word + 1;
        }
        if (start_str < len) {
            answer.append(getStr(start_str, len - 1));
        }
        return answer.toString().trim();
    }

    public String getStr(int start, int end) {
        StringBuilder str = new StringBuilder();
        char c;
        for (int i=start;i <= end; ++i) {
            c = sentence[i];
            if (c>= 'A' && c <= 'Z') {
                str.append(c);
            }
        }
        return str.toString() + " ";
    }

    public String solution3(String sentence) {
        StringBuilder answerList = new StringBuilder();
        //HashMap과 달리 입력 순서 보장
        LinkedHashMap<Character, ArrayList<Integer>> lowerCount = new LinkedHashMap<>();
        String invalid = "invalid";
        try {
            int size = sentence.length();

            //소문자의 각 종류 / 위치 파악
            for(int i=0; i<size; i++){
                char c = sentence.charAt(i);

                if(Character.isLowerCase(c)){
                    if(!lowerCount.containsKey(c)){
                        lowerCount.put(c, new ArrayList<Integer>());
                    }
                    lowerCount.get(c).add(i);
                }
            }

            int stringIdx = 0;
            int startChar, endChar;
            int lastStartChar = -1, lastEndChar = -1;
            int startWord = 0, endWord = 0;
            int lastStartWord= -1, lastEndWord = -1;
            int count, distance;
            int rule = 0;

            ArrayList<Integer> temp;
            for(char c : lowerCount.keySet()){
                temp = lowerCount.get(c);
                count = temp.size();
                startChar = temp.get(0);
                endChar = temp.get(count-1);


                //AaA, AaAaAaA...
                if(count == 1 || count >= 3){
                    for(int i=1; i<count; i++){
                        //간격 2 넘어가면 x
                        if(temp.get(i) - temp.get(i-1) != 2) return invalid;
                    }
                    rule = 1;
                }

                else if (count == 2){
                    distance = endChar - startChar;

                    //다른 기호 안에 있음 (규칙 2와 겹침)
                    if(distance == 2 && (endChar < lastEndChar && startChar > lastStartChar)){
                        rule = 1;
                    }
                    else if(distance >= 2){
                        rule = 2;
                    }
                    //소문자 연속은 x
                    else  return invalid;
                }

                //규칙에 따른 예외
                if(rule == 1){
                    //기호 위치에서 앞뒤로 한칸씩
                    startWord = startChar -1;
                    endWord = endChar+1;

                    //이전 단어 안에 포함되어 있는 경우
                    if(lastStartWord < startWord && lastEndWord > endWord){
                        //규칙 2 아니면 안됨
                        if(startChar - lastStartChar  == 2 && lastEndChar - endChar == 2){
                            continue;
                        }
                        else return invalid;
                    }
                }

                else if (rule == 2){
                    startWord = startChar;
                    endWord = endChar;
                    //규칙 2는 중복되면 안됨
                    if(lastStartWord < startWord && lastEndWord > endWord) return invalid;
                }

                if(lastEndWord >= startWord) return  invalid;

                //소문자 등장 이전에 존재하던 앞의 단어 추가
                if(stringIdx < startWord){
                    answerList.append(makeWord(sentence,stringIdx,startWord-1));
                    answerList.append(" ");
                }
                answerList.append(makeWord(sentence,startWord,endWord));
                answerList.append(" ");
                lastStartWord = startWord;
                lastEndWord = endWord;
                lastStartChar = startChar;
                lastEndChar = endChar;
                stringIdx = endWord+1;
            }
            //뒤에 남는 단어들도 더하기
            if(stringIdx < size){
                answerList.append(makeWord(sentence,stringIdx,size-1));
            }
        }
        catch (Exception e){
            return invalid;
        }
        return answerList.toString().trim();
    }

    public String makeWord(String sentence, int start, int end){
        String word = sentence.substring(start, end+1);
        return word.replaceAll("[a-z]","");
    }
}

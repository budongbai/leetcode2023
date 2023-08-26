/**
 * 1768. 交替合并字符串
 * <p>
 * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
 * <p>
 * 返回 合并后的字符串 。
 */
public class L1768 {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < word1.length() && i < word2.length()) {
            res.append(word1.charAt(i));
            res.append(word2.charAt(i));
            i++;
        }
        while (i < word1.length()) {
            res.append(word1.charAt(i));
            i++;
        }
        while (i < word2.length()) {
            res.append(word2.charAt(i));
            i++;
        }
        return res.toString();
    }
}

package Level3;

import java.util.*;
import java.util.regex.*;

public class Matching_Score { // 매칭 점수
    Map<String, Page> map = new HashMap<>();

    public int solution(String word, String[] pages) {
        // 초기화
        for(int i = 0; i < pages.length; i++) {
            Page page = new Page(i, pages[i].toLowerCase());
            page.setDefaultScore(word);
            map.put(page.url, page);
        }

        for(Page p : map.values())
            p.setLinkScore();

        ArrayList<Page> list = new ArrayList<>(map.values());
        Collections.sort(list);

        return list.get(0).idx; // 매칭 점수가 가장 높은 웹 페이지의 index 반환
    }


    class Page implements Comparable<Page> {
        int idx;
        String html, url;
        int defaultScore = 0; // 기본 점수
        int extLinkCount = 0; // 외부 링크 수
        double linkScore = 0; // 링크 점수

        Page(int idx, String html) {
            this.idx = idx;
            this.html = html;
            findUrl();
            findExtLinkCount();
        }

        public void findUrl() { // 해당 웹 페이지의 주소 구하기
            Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
            Matcher matcher = pattern.matcher(html);
            while(matcher.find()) url = matcher.group(1);
        }

        public void findExtLinkCount() { // 외부 링크 수 구하기
            extLinkCount = html.split("<a href=").length - 1;
        }

        public void setDefaultScore(String word) { // 기본 점수 구하기
            int idx = html.indexOf(word);
            while(idx != -1) {
                if(!Character.isLowerCase(html.charAt(idx - 1)) && !Character.isLowerCase(html.charAt(idx + word.length())))
                    defaultScore++;

                idx = html.indexOf(word, idx + 1);
            }
        }

        public void setLinkScore() { // 링크 점수 구하기
            Pattern pattern = Pattern.compile("<a href=\"https://(.+?)\">");
            Matcher matcher = pattern.matcher(html);
            while(matcher.find()) {
                String extUrl = matcher.group(1);
                if(map.containsKey(extUrl))
                    map.get(extUrl).linkScore += (double) defaultScore / extLinkCount;
            }
        }

        @Override
        public int compareTo(Page o) { // 매칭 점수 비교
            double a = (double) o.defaultScore + o.linkScore;
            double b = (double) this.defaultScore + this.linkScore;

            return Double.compare(a, b);
        }
    }
}

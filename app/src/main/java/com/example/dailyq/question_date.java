package com.example.dailyq;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class question_date {
    public static void initialize(Context context) {
        List<String> dataList = new ArrayList<>();
        dataList.add("가장 가고 싶은 여행지는 어디인가요?");
        dataList.add("어제 잠들기 직전에 한 일을 적어주세요.");
        dataList.add("당신의 낭만은 어떤 모습이었나요?");
        dataList.add("당신은 무엇을 떠올리면 행복하다고 느끼나요?");
        dataList.add("지금 이루어졌으면 하는 소원을 한 가지 적어주세요.");
        dataList.add("지금 이루어졌으면 하는 소원을 한 가지 적어주세요.");
        dataList.add("나를 가장 잘 표현할 수 있는 한 마디가 있을까요?");
        dataList.add("실패를 경험해본 적이 있나요?");
        dataList.add("오늘 나는 어떤 일로 만족했나요?");
        dataList.add("오늘 나는 어떤 일을 후회하나요?");
        dataList.add("가장 큰 성취감을 느낀 순간은 언제인가요?");
        dataList.add("내일의 목표는 무엇인가요?");
        dataList.add("어떤 것을 향해 노력 중인가요?");
        dataList.add("내가 어떤 사람이 되고 싶은가요?");
        dataList.add("가장 큰 장점은 무엇이라고 생각하나요?");
        dataList.add("가장 큰 단점은 무엇이라고 생각하나요?");
        dataList.add("자주 미루는 습관이 있나요? 어떻게 극복하고자 하나요?");
        dataList.add("가장 좋아하는 취미나 활동은 무엇인가요?");
        dataList.add("스트레스를 푸는 방법은 무엇인가요?");
        dataList.add("자기계발을 위해 어떤 노력을 하고 있나요?");
        dataList.add("가장 소중하게 여기는 가치는 무엇인가요?");
        dataList.add("주변 사람들에게 어떤 영향을 주고 싶나요?");
        dataList.add("내가 가장 좋아하는 것은 무엇인가요?");
        dataList.add("가장 큰 꿈이 무엇인가요?");
        dataList.add("가장 힘들게 극복한 어려움은 무엇인가요?");
        dataList.add("내가 좋아하는 일은 무엇인가요?");
        dataList.add("다른 사람들이 나에게 기대하는 것은 무엇인가요?");
        dataList.add("오늘 하루를 한 마디로 표현한다면 어떤 말을 하고 싶나요?");
        dataList.add("내일의 나에게 하고 싶은 조언은 무엇인가요?");
        dataList.add("가장 기억에 남는 좋은 경험은 무엇인가요?");
        dataList.add("현재의 나는 만족스러운가요? 그 이유는 무엇인가요?");
        dataList.add("가장 어려웠던 결정은 무엇이었나요?");
        dataList.add("나를 더 발전시키기 위해 어떤 노력을 할 수 있을까요?");
        dataList.add("내가 중요하게 생각하는 가족이나 친구와의 관계를 개선하고 싶다면 어떻게 해야 할까요?");
        dataList.add("나에게 가장 도움이 되는 습관은?");
        dataList.add("내가 현재 가진 능력과 잠재력을 최대한 발휘하고 있나요?");
        dataList.add("나를 행복하게 만드는 것들은 무엇인가요?");
        dataList.add("가장 큰 두려움은 무엇인가요? 어떻게 극복하고자 하나요?");
        dataList.add("현재의 삶에 만족하나요? 그 이유는 무엇인가요?");
        dataList.add("내가 가장 잘하는 일은 무엇인가요?");
        dataList.add("지금까지 배운 교훈 중에서 가장 중요한 것은 무엇인가요?");
        dataList.add("나에게 가장 큰 영감을 주는 사람은 누구인가요?");
        dataList.add("더 나은 사람이 되기 위해 무엇을 배워야 할까요?");
        dataList.add("내가 성취하고 싶은 목표는 무엇인가요?");
        dataList.add("현재의 삶에서 불행하게 느껴지는 부분은 무엇인가요? 어떻게 개선하고자 하나요?");
        dataList.add("나를 자극하고 동기부여하는 것은 무엇인가요?");
        dataList.add("어떤 일을 하면서 가장 흥미롭게 느낄 수 있나요?");
        dataList.add("내가 가장 감사하게 생각하는 것은 무엇인가요?");
        dataList.add("나를 위해 어떤 자기 관리 방법을 가지고 있나요?");
        dataList.add("어떤 환경에서 가장 효율적으로 일할 수 있나요?");
        dataList.add("내가 어떤 가치를 사회에 기여하고 싶나요?");
        dataList.add("나의 인생 이야기에서 가장 자랑스러운 순간은 무엇인가요?");
        dataList.add("가장 꾸준히 유지하고 있는 습관은 무엇인가요?");
        dataList.add("미래의 나에게 하고 싶은 한마디는 무엇인가요?");
        dataList.add("어떤 방식으로 새로운 것을 배우고 발전시키고 있나요?");
        dataList.add("나를 더 잘 이해하고 싶다면 어떤 노력을 해야 할까요?");
        dataList.add("가장 중요하게 생각하는 가치관은 무엇인가요?");
        dataList.add("나에게 가장 큰 영향을 주었던 책은 무엇인가요?");
        dataList.add("나를 더 잘 돌봐주고 사랑해줄 수 있는 방법은 무엇인가요?");
        dataList.add("나에게 있어 가장 소중한 추억은 무엇인가요?");
        dataList.add("나를 더욱 강하게 만들어주는 동기부여 요소는 무엇인가요?");
        dataList.add("내가 가장 선호하는 여가 활동은 무엇인가요?");
        dataList.add("나의 목표를 달성하기 위해 어떤 계획을 세워야 할까요?");
        dataList.add("내가 존경하는 사람들의 공통점은 무엇인가요?");
        dataList.add("지난 한 주 동안 어떤 성취를 이루었나요?");
        dataList.add("나에게 가장 중요한 건강 관리 방법은 무엇인가요?");
        dataList.add("내가 장래에 이루고자 하는 가장 큰 목표는 무엇인가요?");
        dataList.add("나를 행복하게 만드는 사람들과의 관계는 어떤 모습인가요?");
        dataList.add("현재의 나와 1년 전의 나를 비교해보았을 때, 어떤 성장이 있었나요?");
        dataList.add("나를 위해 특별히 관심을 가지고 있는 분야는 무엇인가요?");
        dataList.add("나는 주변 사람들에게 어떤 영향을 주고 싶은가요?");
        dataList.add("내가 주변 사람들에게 가장 많이 감사하게 생각하는 것은 무엇인가요?");
        dataList.add("나의 삶에서 가장 중요한 가치는 무엇인가요?");
        dataList.add("내가 어떤 사람이 되고 싶은지에 대해 명확한 비전이 있나요?");
        dataList.add("나를 가장 힘들게 만드는 것은 무엇인가요? 어떻게 극복하고자 하나요?");
        dataList.add("지금까지 어떤 도전과 고난을 극복했나요? 그 과정에서 얻은 교훈은 무엇인가요?");
        dataList.add("나를 더 발전시키기 위해 어떤 노력을 할 수 있을까요?");
        dataList.add("내가 주변 사람들에게 주는 가장 큰 가치는 무엇인가요?");
        dataList.add("내가 좋아하는 일을 하는 데에 가장 큰 장애물은 무엇인가요? 어떻게 극복하고자 하나요?");
        dataList.add("내가 주변 사람들에게 어떤 영감을 줄 수 있을까요?");
        dataList.add("나에게 가장 큰 자부심을 느끼게 해 준 일은 무엇인가요?");
        dataList.add("현재의 삶에 만족하나요? 그 이유는 무엇인가요?");
        dataList.add("내가 주변 사람들에게 가장 중요하게 생각하는 가치는 무엇인가요?");
        dataList.add("내가 가장 좋아하는 일은 무엇인가요? 그 일에 대해 어떤 열정을 가지고 있나요?");
        dataList.add("나는 미래에 어떤 유산을 남기고 싶은가요?");
        dataList.add("나에게 가장 큰 영향을 주었던 책이나 영화는 무엇인가요?");
        dataList.add("가장 좋아하는 음식은 무엇인가요?");
        dataList.add("하루를 시작할 때 가장 먼저 하는 일은 무엇인가요?");
        dataList.add("가장 좋아하는 색깔은 무엇인가요?");
        dataList.add("주말에 가장 좋아하는 활동은 무엇인가요?");
        dataList.add("가장 행복한 순간은 언제인가요?");
        dataList.add("내가 주변 사람들에게 힘이 되고 싶다면 어떻게 해야 할까요?");
        dataList.add("나의 성공을 위해 극복해야 할 가장 큰 장애물은 무엇인가요? 어떻게 극복할 수 있을까요?");
        dataList.add("오늘 아침에 무엇을 먹었나요?");
        dataList.add("가장 좋아하는 음악 장르는 무엇인가요?");
        dataList.add("주말에 가장 좋아하는 활동은 무엇인가요?");
        dataList.add("가장 최근에 본 영화나 드라마는 무엇인가요?");
        dataList.add("혹시 어떤 새로운 취미를 시작하거나 배우고 있나요?");
        dataList.add("가장 좋아하는 계절은 어떤 계절인가요? 그 이유는 무엇인가요?");
        dataList.add("오늘은 어떤 옷을 입었나요? 스타일이나 색상을 좋아하는 특별한 옷이 있나요?");
        dataList.add("가장 최근에 한 번 웃은 일은 무엇이었나요?");

        saveDataToFile(context, dataList);
    }

    private static void saveDataToFile(Context context, List<String> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            String data = dataList.get(i);
            String fileName = "question" + (i + 1) + ".txt";
            String filePath = context.getFilesDir().getPath() + "/" + fileName;

            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                fos.write(data.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        public static String readData(Context context, int questionNumber) {
            String fileName = "question" + questionNumber + ".txt";
            String filePath = context.getFilesDir().getPath() + "/" + fileName;

            try {
                FileInputStream fis = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                String dataLine = br.readLine();
                br.close();
                return dataLine;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

}

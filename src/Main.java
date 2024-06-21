import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        File originLanguage = new File("src/en_us.json");
        File targetLanguage = new File("src/ko_kr.json");

        ArrayList<String[]> originDataGettering = trimmingData(originLanguage);
        ArrayList<String[]> targetDataGettering = trimmingData(targetLanguage);

        dataChecking(originDataGettering,targetDataGettering);
        ArrayList<String> totalData = missingDataIndex(originDataGettering,targetDataGettering);
        addMessage(totalData);
    }

    private static ArrayList<String> missingDataIndex(ArrayList<String[]> original, ArrayList<String[]> target) {
//        ArrayList<Boolean> resultList = new ArrayList<>();
        ArrayList<String> resultList = new ArrayList<>();

        for (String[] originData : original) {
            if (originData.length < 2)
                continue;

            String data = originData[1];
            for (String[] targetData : target) {
                if (originData[0].equals(targetData[0])) {
                    data = targetData[1];
                    break;
                }
            }
            resultList.add(originData[0] + ": " + data +",\n");
        }

//        System.out.println(resultList);
//        for (int i = 0; i < resultList.size(); i++)
//            if (!resultList.get(i))
//                System.err.println(original.get(i)[0]);
        return resultList;
    }

    private static void addMessage(ArrayList<String> totalData) {
        try(FileWriter writer = new FileWriter("KR.json")) {
            for (String data : totalData) {
                writer.write(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dataChecking(ArrayList<String[]> original, ArrayList<String[]> target) {
        ArrayList<Boolean> resultList = new ArrayList<>();
        for (String[] originData : original) {
            boolean existChecker = false;
            for (String[] targetData : target) {
                if (originData[0].equals(targetData[0])) {
                    existChecker = true;
                    break;
                }
            }
            resultList.add(existChecker);
        }
        System.err.println("Missing Key Value ↓↓↓↓↓↓↓↓↓↓");
        for (int i = 0; i < resultList.size(); i++)
            if (!resultList.get(i))
                System.err.println(original.get(i)[0]);
    }

    private static ArrayList<String[]> trimmingData(File targetFile) throws Exception {
        ArrayList<String[]> resultValue = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(targetFile));
        while (bufferedReader.ready()) {
            String[] data = bufferedReader.readLine().split(",");
//            System.out.println(Arrays.toString(data));
            String[] keyValue = data[0].split(":");
//            keyValue

            resultValue.add(keyValue);
        }

        return resultValue;
    }
}

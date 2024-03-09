import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        File originLanguage = new File("C:\\Users\\Jwon0\\IdeaProjects\\Wurst7\\src\\main\\resources\\assets\\wurst\\lang\\en_us.json");
        File targetLanguage = new File("C:\\Users\\Jwon0\\IdeaProjects\\Wurst7\\src\\main\\resources\\assets\\wurst\\lang\\ko_kr.json");

        ArrayList<String> originDataGettering = trimmingData(originLanguage);
        ArrayList<String> targetDataGettering = trimmingData(targetLanguage);

        missingDataIndex(originDataGettering,targetDataGettering);
    }

    private static void missingDataIndex(ArrayList<String> original, ArrayList<String> target) {
        ArrayList<Boolean> resultList = new ArrayList<>();
        for (String originData : original) {
            boolean existChecker = false;
            for (String targetData : target) {
                if (originData.equals(targetData)) {
                    existChecker = true;
                    break;
                }
            }
            resultList.add(existChecker);
        }
        System.err.println("Missing Key Value ↓↓↓↓↓↓↓↓↓↓");
        for (int i = 0; i < resultList.size(); i++)
            if (!resultList.get(i)) System.out.println(original.get(i));
    }

    private static ArrayList<String> trimmingData(File targetFile) throws Exception {
        ArrayList<String> resultValue = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(targetFile));
        while (bufferedReader.ready()) {
            String[] key = bufferedReader.readLine().split(":");
            String[] keyValue = key[0].split("\"");

            if (keyValue.length < 2)
                continue;

            resultValue.add(keyValue[1]);
        }

        return resultValue;
    }
}

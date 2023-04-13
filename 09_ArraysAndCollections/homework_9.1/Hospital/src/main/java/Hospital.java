import java.text.DecimalFormat;
import java.util.StringJoiner;

public class Hospital {

    public static final double MAX_TEMPERATURE = 36.90001;
    public static final double MIN_TEMPERATURE = 36.20001;
    public static final double MIN_TEMPERATURE_RANDOM =  32;
    public static final double MAX_TEMPERATURE_RANDOM =  40;

    public static float[] generatePatientsTemperatures(int patientsCount) {
        //TODO: напишите метод генерации массива температур пациентов
        float[] patientTemp = new float[patientsCount];
        for (int i = 0; i < patientTemp.length; i++) {
            patientTemp[i] =  (float) ((Math.random() *
                    (MAX_TEMPERATURE_RANDOM-MIN_TEMPERATURE_RANDOM) + MIN_TEMPERATURE_RANDOM));
        }
        return patientTemp;
    }

    public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */
        DecimalFormat formatter = new DecimalFormat("#0.0");
        StringJoiner joiner = new StringJoiner(" ");
        for (float temp : temperatureData) {
            joiner.add(formatter.format(temp));
        }
        String report =
                "Температуры пациентов: " + joiner.toString() +
                        "\nСредняя температура: " + getAverageTempereture(temperatureData) +
                        "\nКоличество здоровых: " + getHealthyCount(temperatureData);

        return report;
    }

    public static float getAverageTempereture(float[] patientTemp) {
        float allTemp = 0;
        for (float temp : patientTemp) {
            allTemp += temp;
        }
        float averageTemp = (float) (allTemp / patientTemp.length);
        return (float) (Math.round(averageTemp * 100) / 100.0);
    }

    public static int getHealthyCount(float[] patientTemp) {
        int healthyCount = 0;
        for (float temp : patientTemp) {
            if (temp <= MAX_TEMPERATURE && temp >= MIN_TEMPERATURE) {
                healthyCount++;
            }
        }
        return healthyCount;
    }
}
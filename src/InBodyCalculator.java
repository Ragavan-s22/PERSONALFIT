public class InBodyCalculator {
    public static String calculateInBodyResults(double weight, double height, int age, String gender) {
        double bmi = weight / Math.pow(height / 100, 2);
        double bmr;
        if (gender.equalsIgnoreCase("M")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
        double smm = weight * 0.4;  // Skeletal Muscle Mass estimation
        double bfm = weight * 0.2;  // Body Fat Mass estimation
        double pbf = (bfm / weight) * 100;  // Percentage Body Fat
        double targetWeight = height / 100 * height / 100 * 22;  // Ideal BMI 22
        double weightControl = targetWeight - weight;
        double fatControl = bfm - (targetWeight * 0.2);
        double muscleControl = smm - (targetWeight * 0.4);
        double obesityDegree = (weight / targetWeight) * 100;

        return String.format("BMI: %.2f\nBMR: %.2f\nSMM: %.2f kg\nBFM: %.2f kg\nPBF: %.2f%%\nTarget Weight: %.2f kg\nWeight Control: %.2f kg\nFat Control: %.2f kg\nMuscle Control: %.2f kg\nObesity Degree: %.2f%%",
                bmi, bmr, smm, bfm, pbf, targetWeight, weightControl, fatControl, muscleControl, obesityDegree);
    }
}
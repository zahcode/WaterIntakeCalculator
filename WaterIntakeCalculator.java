package com.zahcode.water_alarm.helpers;

import com.zahcode.water_alarm.enum_.EnvironmentTemperature;
import com.zahcode.water_alarm.enum_.Gender;

public class WaterIntakeCalculator {

    /**
     * Calculates daily water intake in liters.
     *
     * @param weightKg               Weight of the person in kilograms.
     * @param exerciseMinutes        Minutes of exercise per day.
     * @param isPregnant             True if the person is pregnant.
     * @param isBreastfeeding        True if the person is breastfeeding.
     * @param gender                 Gender of the person.
     * @param environmentTemperature Temperature of the environment.
     * @return Daily water intake in liters.
     */
    public static double calculateWaterIntake(double weightKg, int exerciseMinutes, boolean isPregnant, boolean isBreastfeeding, Gender gender, EnvironmentTemperature environmentTemperature) {
        double baseIntakePerKg = 25; // ml of water per kg of body weight
        double exerciseIntake = 500 / 30; // additional ml per minute of exercise
        double intakeAdjustment = 0; // Adjustment based on gender and special conditions

        // Calculate base water intake
        double dailyIntake = weightKg * baseIntakePerKg;

        // Add extra intake for exercise, adjusted for minutes
        dailyIntake += exerciseMinutes * exerciseIntake;

        // Adjust intake based on gender and special conditions
        if (isPregnant) {
            intakeAdjustment += 300; // Adjust for pregnancy
        } else if (isBreastfeeding) {
            intakeAdjustment += 700; // Adjust for breastfeeding
        }

        // Adjust for environment temperature
        switch (environmentTemperature) {
            case HIGH:
                intakeAdjustment += dailyIntake * 0.20; // Increase by 20% for high temperatures
                break;
            case LOW:
                intakeAdjustment += dailyIntake * -0.05; // Decrease by 5% for low temperatures
                break;
            case MODERATE:
                // No adjustment needed for moderate temperatures
                break;
        }

        dailyIntake += intakeAdjustment;

        // Convert ml to liters for the final result and make it such 2.1
        double liters = Math.round((dailyIntake / 1000) * 10) / 10.0;
        return Math.min(liters, 2.9);
    }
}


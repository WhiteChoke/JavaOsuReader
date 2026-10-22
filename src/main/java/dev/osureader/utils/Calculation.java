package dev.osureader.utils;

import dev.osureader.enums.Grade;

public class Calculation {
    public static double calculateAccuracy(int n300, int n100, int n50, int nMiss) {
        double a = 300 * n300 + 100 * n100 + 50 * n50;
        double b = 300 * (n300+n100+n50+nMiss);

        if (b == 0) return 0;

        var accuracy = a / b * 100;

        return Math.round(accuracy * 100) / 100.0;
    }

    public static int calculateBpm(double bpm)
    {
        return (int)Math.ceil(60 * 1000 / bpm);
    }

    public static Grade CalculateGrade(double accuracy, int n300, int n100, int n50, int nMiss)
    {
        var total = n300+n100+n100+nMiss;

        var percent300 = n300 * 100 / total;
        var percent50 = n50 * 100 / total;

        if (accuracy == 100) return Grade.SS;
        if ( percent300 > 90 && percent50 < 1 && nMiss == 0 ) return Grade.S;
        if ( (percent300 > 80 && nMiss == 0) || percent300 > 90 ) return Grade.A;
        if ( (percent300 > 70 && nMiss == 0) || percent300 > 80 ) return Grade.B;
        if ( percent300 > 60 ) return Grade.C;
        return Grade.D;
    }
}

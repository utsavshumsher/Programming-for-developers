package Question5;


import java.util.Arrays;

public class BatteryReplace {
    public int findBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        int replacements = 0;
        int currentCharge = startChargeCapacity;
        int distance = 0;

        // Sort service centers by their distance from source city
        Arrays.sort(serviceCenters, (a, b) -> a[0] - b[0]);

        for (int[] center : serviceCenters) {
            int centerDistance = center[0];
            int centerCapacity = center[1];
            // If currentCharge is not enough to reach the next service center, replace the battery
            if (centerDistance - distance > currentCharge) {
                replacements++;
                currentCharge = centerCapacity;
            }
            // Add centerCapacity to currentCharge
            currentCharge -= (centerDistance - distance);
            distance = centerDistance;
            // If the distance to the destination is less than or equal to currentCharge, return replacements
            if (targetMiles - distance <= currentCharge) {
                return replacements;
            }
        }

        // If the distance to the destination is greater than currentCharge, replace the battery
        if (targetMiles - distance > currentCharge) {
            replacements++;
        }
        return replacements;
    }

    public static void main(String[] args) {
        int[][] serviceCenters = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        int targetMiles = 100;
        int startChargeCapacity = 10;
        BatteryReplace solution = new BatteryReplace();
        System.out.println("Number of battery replacements: " + solution.findBatteryReplacements(serviceCenters, targetMiles, startChargeCapacity));
    }
}

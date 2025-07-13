#include <stdio.h>

double getPositiveAverage(double Array[], int items) {
    if(items == 0) {
        return 0;
    } else {
        int Pnum = 0;
        double sum = 0;

        for(int i = 0; i < items; i++) {
            if(Array[i] > 0) {
                sum += Array[i];
                Pnum++;
            }
        }

        if(Pnum > 0) {
            return sum / Pnum;
        } else {
            return 0;
        }
    }
}

int countRangeValues(double Array[], int items, double value) {
    if(items == 0) {
        return 0;
    } else {
        double lowValue = value - 0.5;
        double highValue = value + 0.5;
        int rangeCount = 0;

        for(int i = 0; i < items; i++) {
            if(Array[i] >= lowValue && Array[i] < highValue) {
                rangeCount++;
            }
        }

        return rangeCount;
    }
}

double getMaxAbsolute(double Array[], int items) {
    if(items == 0) {
        return 0;
    } else {
        double maxValue = 0;
        double abMaxValue = 0;
        double compare = 0;

        for(int i = 0; i < items; i++) {
            if(Array[i] < 0) {
                compare = Array[i] * -1;
            } else {
                compare = Array[i];
            }

            if(Array[i] == abMaxValue) {
                maxValue = Array[i];
            }else if(compare > abMaxValue) {
                abMaxValue = compare;
                maxValue = Array[i];
            }
        }

        return maxValue;
    }
}

int countInverses(int Array[], int items) {
    if(items == 0) {
        return 0;
    } else {
        int count = 0;
        int value = 0;
        int j = 0;

        for(int i = 0; i < items; i++) {
            if(Array[i] != 0) {
                value = Array[i] * -1;
                j = i + 1;

                while(value != Array[j] && j < items) {
                    j++;
                }

                if(j != items) {
                    Array[j] = 0;
                    count++;
                }
                j = 0;
            }
        }

        return count;
    }
}

int getMaxCount(double Array[], int items) {
    double value = getMaxAbsolute(Array, items);
    return countRangeValues(Array, items, value);
}
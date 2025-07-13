#include <stdio.h>

void printStringStats(char str[]) {

    int length = 0;
    int Vcount = 0;
    int Con = 0;

    for(int i = 0; str[i] != '\0'; i++) {
        if((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z')) {
            char letter = str[i];

            switch (letter) {
                case 'a' :
                case 'e' :
                case 'i' :
                case 'o' :
                case 'u' :
                case 'A' :
                case 'E' :
                case 'I' :
                case 'O' :
                case 'U' :
                    length++;
                    Vcount++;
                    break;
                default:
                    length++;
                    Con++;
            }
        } else {
            length++;
        }
    }

    int bit = length + 1;
    printf("%d:%d:%d:%d\n", length, bit, Vcount, Con);
}
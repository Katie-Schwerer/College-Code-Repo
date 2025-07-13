#include <stdio.h>
#define COLORSIZE 30


typedef struct {
    char color[COLORSIZE];
    int x;
    int y;
} point_t;

void getPoint(point_t *p) {
    int x1 = 0;
    int y1 = 0;

    printf("Enter point color:");
    scanf("%s",p->color);
    printf("Enter point x:");
    scanf("%d",&p->x);
    printf("Enter point y:");
    scanf("%d",&y1);

    p->x = x1;
    p->y = y1;

}

void printPoint(point_t p) {
    printf("Color:%s\n", p.color);
    printf("x:%d\n", p.x);
    printf("y:%d\n", p.y);

}
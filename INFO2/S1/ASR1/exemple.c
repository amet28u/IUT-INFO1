#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){
    int a;
    if(argc != 2){
        printf("Usage :%s nombre\n",argv[0]);
}
    a=atoi(argv[1]);
    printf("\n");
    bonjour(a);
    revoir(--a);
    exit(0);
}

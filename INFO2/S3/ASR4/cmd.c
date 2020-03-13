/* source de la commande cmd1 10,10s */
f1(){
char *p= "aaaaaaaaaaaaaaaaaaaaa";
char c=p[2];
}
main(){
int i;
for(i=0;i<50000000;i++) f1();
}
/* source de la commande cmd2 35,30s */
f2(){
char p[]= "aaaaaaaaaaaaaaaaaaaaa";
char c=p[2];
}
main(){
int i;
for(i=0;i<50000000;i++) f2();
}
/* source de la commande cmd3 10,10s */
f3(){
static char *p= "aaaaaaaaaaaaaaaaaaaaa";
char c=p[2];
}
main(){
int i;
for(i=0;i<50000000;i++) f3();
}
/* source de la commande cmd4 9,08s */
f4(){
static char p[]= "aaaaaaaaaaaaaaaaaaaaa";
char c=p[2];;
}
main(){
int i;
for(i=0;i<50000000;i++) f4();
}
/* source de la commande cmd5 2,03s */
#define f5() {
char *p= "aaaaaaaaaaaaaaaaaaaaa";
char c=p[2];
}
main(){
int i;
for(i=0;i<50000000;i++) f5();
}

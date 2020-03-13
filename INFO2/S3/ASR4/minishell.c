#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define SIZE_NAME 28
#define SIZE_PATH 50

typedef enum {MOT, TUB, INF, SUP, SPP, NL, FIN} LEX;

static LEX getlex(char *mot){
  enum {Neutre, Spp, Equote, Emot} etat=Neutre;
  int c;
  char *w;
  w=mot;
  while ((c=getchar()) != EOF){
    switch(etat){
      case Neutre:
        switch(c){
          case '<':
            return (INF);
          case '>':
            etat=Spp;
            continue;
          case '|':
            return (TUB);
          case '"':
            etat=Equote;
            continue;
          case ' ':
          case '\t':
            continue;
          case '\n':
            return(NL);
          default:
            etat=Emot;
            *w++=c;
            continue;
        }
      case Spp:
        if(c=='>')
          return(SPP);
        ungetc(c,stdin);
        return(SUP);
      case Equote:
        switch(c){
          case '\\':
            *w++=c;
            continue;
          case '"':
            *w='\0';
            return(MOT);
          default:
            *w++=c;
            continue;
        }
      case Emot:
        switch(c){
          case '|':
          case '<':
          case '>':
          case ' ':
          case '\t':
          case '\n':
            ungetc(c,stdin);
            *w='\0';
            return(MOT);
          default:
            *w++=c;
            continue;
        }
    }
  }
  return(FIN);
}


int main(int argc, char* argv[]){
  char mot[200];
  char* arg[50];
  int cpt=0;
  char name[SIZE_NAME];
  char path[SIZE_PATH];
  //Initialisation tableau
  for (int i = 0; i < 50; i++){
    arg[i] = (char*)malloc(100);
  }
  gethostname(name, SIZE_NAME);
  getcwd(path, SIZE_PATH);
  printf("%s:%s$ ", name, path);
  while(1){
    switch(getlex(mot)){
      case MOT:
        //printf("MOT: %s\n",mot);
        strcpy(arg[cpt], mot);
        cpt++;
        break;
      case TUB:
        printf("TUBE\n");
        break;
      case INF:
        printf("REDIRECTION ENTREE\n");
        break;
      case SUP:
        printf("REDIRECTION SORTIE\n");
        break;
      case SPP:
        printf("REDIRECTION AJOUT\n");
        break;
      case NL:
        //printf("NOUVELLE LIGNE \n");
        arg[cpt]=NULL;
        cpt=0;
        //Exception CD
        if (strcmp(arg[0], "cd") == 0){
          chdir(arg[1]); //Ne marche pas avec cd ..
          gethostname(name, SIZE_NAME);
          getcwd(path, SIZE_PATH);
          printf("%s:%s$ ", name, path);
          break;
        }
        //Execution par execvp et tableau arg
        switch(fork()){
          case -1:
            perror("fork");
            exit(1);
          case 0:
            execvp(arg[0], arg);
            perror("execvp");
            exit(1);
          default:
            wait(NULL);
        }
        gethostname(name, SIZE_NAME);
        getcwd(path, SIZE_PATH);
        printf("%s:%s$ ", name, path);
        break;
      case FIN:
        printf("FIN \n");
        exit(0);
    }
  }
}

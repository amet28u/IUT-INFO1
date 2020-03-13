#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <fcntl.h>
#include <mqueue.h>
#include <sys/stat.h>
#include <string.h>

int main(int argc, char* argv[]){

    mqd_t mq;
    struct mq_attr ma;
    int send;

    ma.mq_flags = 0;
    ma.mq_maxmsg = 10;
    ma.mq_msgsize = 1024;
    ma.mq_curmsgs = 0;

    if ((mq = mq_open("/test_queue", O_WRONLY | O_CREAT, 0740, &ma)) == -1){
        perror("mq_open");
        exit(1);
    }

    if ((send = mq_send(mq, argv[1], strlen(argv[1]), 0)) == -1){
        perror("mq_send");
        exit(1);
    }
    
    return 0;
}

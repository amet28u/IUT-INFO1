#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <fcntl.h>
#include <mqueue.h>

int main(int argc, char* argv[]){

    mqd_t mq;
    int recv;
    char buffer[1024];

    if ((mq = mq_open("/test_queue", O_RDONLY, 0700, NULL)) == -1){
        perror("mq_open");
        exit(1);
    }

    if ((recv = mq_receive(mq, buffer, 1024, NULL)) == -1){
        perror("mq_receive");
        exit(1);
    }

    printf("%s\n", buffer);

    return 0;
}
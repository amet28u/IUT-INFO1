#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>

int main(int argc, char* argv[]){

	struct hostent *hp = gethostbyname(argv[1]);
	struct servent *sp = getservbyname("msp", "tcp");
	struct sockaddr_in sa;
	bcopy((char*)hp->h_addr, (char*)&sa.sin_addr, hp->h_length);

	sa.sin_family = hp->h_addrtype;
	sa.sin_port = sp->s_port;

	printf("%x\n", sa.sin_addr.s_addr);

	int s = socket (hp->h_addrtype, SOCK_STREAM,0);
	//connect(s, &sa, sizeof(sa));

	fprintf ( stdout, "Service de numero de port %d et de nom %s\n", sp->s_port, sp->s_name);
	fprintf ( stdout, "Service %p demandé à %s\n", sa.sin_port, argv[1]);
	fprintf ( stdout, "Type d'adresse %d ; descripteur de socket %d\n", sa.sin_family,s);
	perror ("connect");

	return 0;
}

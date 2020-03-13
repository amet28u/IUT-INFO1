void TriBulle(LISTE *l){
	int continu=0;
	tmp=l;
	while (continu==0){
		continu=0;
		l=tmp;
		while (l->suc!=NULL){
			if (l->data>l->suc->data){
				echange(l,l->suc);
				continu=1;
			}
			l=l->suc;
		}
	}	
}
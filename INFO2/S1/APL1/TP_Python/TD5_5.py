palindrome=1
char=input("Entrez une chaîne de caractère : ")
taille=len(char)
for i in range(1,taille+1):
    if char[i-1]!=char[taille-i]:
        palindrome=0
if palindrome==1:
    print("Ce mot est un palindrome")
else:
    print("Ce mot n'est pas un palindrome")
        

    .data 0x10000000
tab:.word 10, 20, 30, 40
indice:.word 0
    
	.text
	.globl __start

__start: 


    la $a0 tab
    lw $a1 indice
    jal change # change(tab, indice)


    li $v0 10
    syscall # syscall 10 (exit)

    change:
        mul $t0,$a1,4
        add $t0,$a0,$t0
        lw $t1,0($t0)
        lw $t2,4($t0)
        sw $t1,4($t0)
        sw $t2,0($t0)
        jr $31 #retour de la procédure


	
